package com.wxhx.gate.plat.timer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoQueryVO;
import com.wxhx.gate.plat.controller.vo.WhiteListVO;
import com.wxhx.gate.plat.dao.KsclMapper;
import com.wxhx.gate.plat.dao.entity.Kscl;
import com.wxhx.gate.plat.service.IKsyyxxService;
import com.wxhx.gate.plat.service.bean.WebServiceResult;
import com.wxhx.gate.plat.service.out.IControlCenterService;
import com.wxhx.gate.plat.service.out.IDongwoPlatService;
import com.wxhx.gate.plat.service.out.IManagerPlatService;

/**
 * 定时获取上车人员信息并更新预约信息表
 * @author geliang
 *
 */
@Component
public class UpdateReserveInfoTimer {
	
	private static Logger logger = LoggerFactory.getLogger(UpdateReserveInfoTimer.class);


	@Autowired
	private KsclMapper ksclMapper;
	
	@Autowired
	private IManagerPlatService iManagerPlatServer;
	
	@Autowired
	private IKsyyxxService iKsyyxxServer;
	
	@Autowired
	private IDongwoPlatService iDongwoPlatService; // 东沃
	
	@Autowired
	private IControlCenterService iControlCenterService; // 控制中心
	
	Map<String, String> ksclMap = new HashMap<String, String>();
	
	List<String> appointmentInfoList = new ArrayList<String>();
	
	@Scheduled(fixedRateString = "${wxhx.gate.plate.update.reserveInfo.rate}",initialDelayString = "${wxhx.gate.plate.update.delay.timme}")
	public void updateInfo() {
		
		ExaminationInfo zpInfo = null;
		
		ExamineeInfoQueryVO examineeInfoQueryVO = new ExamineeInfoQueryVO();
		//考试地点
		examineeInfoQueryVO.setKsdd(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSDD));
		//考试科目
		examineeInfoQueryVO.setKskm("2");
		//返回记录数量
		examineeInfoQueryVO.setFhjls("10");
		WebServiceResult<ExaminationInfo> webServiceRes = iManagerPlatServer.getSortInfo(examineeInfoQueryVO);
		if(webServiceRes.getBodyContent().getContent()!=null) {
			List<ExaminationInfo> sortExams = webServiceRes.getBodyContent().getContent();
			//轮询更新数据
			for(ExaminationInfo examSortInfo:sortExams) {
				//如果不在缓存里，就更新
				if(!appointmentInfoList.contains(examSortInfo.getSfzmhm())) {
					//设定考车路线
					examSortInfo.setLxxh(ksclMap.get(examSortInfo.getKchp()));
					//设定准考证名编号
					examineeInfoQueryVO.setSfzmhm(examSortInfo.getSfzmhm());
					WebServiceResult<ExaminationInfo> appointmentInfoRes = iManagerPlatServer.getAppointmentInfo(examineeInfoQueryVO);
					if(appointmentInfoRes.getBodyContent().getContent()!=null) {
						ExaminationInfo appointmentInfo = (ExaminationInfo)appointmentInfoRes.getBodyContent().getContent().get(0);
						examSortInfo.setZkzmbh(appointmentInfo.getZkzmbh());
						examSortInfo.setYycs(appointmentInfo.getYycs());
						examSortInfo.setDlr(appointmentInfo.getDlr());
						
						//插入考试预约信息
						if(iKsyyxxServer.selectByIdNum(examSortInfo.getSfzmhm())) {
							iKsyyxxServer.updateKsyyxx(examSortInfo);
						}else {
							iKsyyxxServer.insertKsyyxx(examSortInfo);
						}
						
						
						//查询照片信息
						//获取预约照片信息
						WebServiceResult<ExaminationInfo> zpResult = iManagerPlatServer.getZP(examineeInfoQueryVO);
						if(zpResult != null && zpResult.getBodyContent() != null) {
							zpInfo = (ExaminationInfo)zpResult.getBodyContent().getContent().get(0);
						}
						//插入预约（XZ）照片和 采集（BD）照片
						iControlCenterService.insertPhotoInfo(examSortInfo);
						
						
						//插入人脸机
						WhiteListVO whiteListVO = new WhiteListVO();
						whiteListVO.setName(appointmentInfo.getXm());
						whiteListVO.setIdnum(examSortInfo.getSfzmhm());
						whiteListVO.setPhoto(zpInfo.getZp());
						
						whiteListVO.setValidStart(HXCoreUtil.getNowDataStr(new Date(), "yyyy.MM.dd")); // 起
						whiteListVO.setValidEnd(HXCoreUtil.getNowDataStr(new Date(), "yyyy.MM.dd")); // 止
						FaceResponse faceResponse = iDongwoPlatService.insertWhiteList(whiteListVO);
						
						
						try {
							//更新照片流水号
							if(iKsyyxxServer.updateKszpLsh(examSortInfo)) {
								if(iKsyyxxServer.updateKsyyxx(examSortInfo)) {
									HXLogUtil.info(logger,"更新预约信息成功{0}",examSortInfo);
									//更新成功，添加缓存
									appointmentInfoList.add(examSortInfo.getSfzmhm());
								}
							}
						} catch (Exception e) {
							HXLogUtil.error(logger,"更新预约信息失败{0}",e.getStackTrace());
						}
					}
				}
			}
		}
	}
	
	
	@PostConstruct
	public void initKSCL() {
		//获取当前可用循环的车辆信息
		List<Kscl> kscls = ksclMapper.selectAll();
		int successSum = 0;
		//循环车辆
		for(Kscl kscl:kscls) {
			ksclMap.put(kscl.getKchp(), kscl.getLxxh());
		}
	}
}
