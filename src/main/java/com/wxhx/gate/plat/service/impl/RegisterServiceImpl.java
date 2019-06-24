package com.wxhx.gate.plat.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoQueryVO;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;
import com.wxhx.gate.plat.controller.vo.WhiteListVO;
import com.wxhx.gate.plat.service.IRegisterService;
import com.wxhx.gate.plat.service.bean.WebServiceResult;
import com.wxhx.gate.plat.service.out.IControlCenterService;
import com.wxhx.gate.plat.service.out.IDongwoPlatService;
import com.wxhx.gate.plat.service.out.IManagerPlatService;

@Service
public class RegisterServiceImpl implements IRegisterService {

	@Autowired
	private IManagerPlatService iManagerPlatService; // 精英

	@Autowired
	private IDongwoPlatService iDongwoPlatService; // 东沃

	@Autowired
	private IControlCenterService iControlCenterService; // 控制中心

	@Transactional(rollbackFor = Exception.class)
	public HXRespons<RegisterResponse> register(RegisterInfoVo registerInfoVo) throws Exception{
		HXRespons<RegisterResponse> finalResult = new HXRespons<RegisterResponse>("0", "报道失败", null);
		ExaminationInfo appointmentInfo = null;
		ExaminationInfo zpInfo = null;
		FaceResponse faceResponse = null;
		RegisterResponse result = null;
		WhiteListVO whiteListVO = new WhiteListVO();

		//先插入预约信息
		appointmentInfo = new ExaminationInfo();
		appointmentInfo.setSfzmhm(registerInfoVo.getSfzmhm());
		appointmentInfo.setKsdd(registerInfoVo.getKsdd());
		appointmentInfo.setKskm(registerInfoVo.getKskm());
		appointmentInfo.setLsh(HXCoreUtil.getNowDataStr(new Date(), "yyMMddhhMMss"));
		appointmentInfo.setZkzmbh("0000");
		appointmentInfo.setXm(registerInfoVo.getName());
		appointmentInfo.setYycs(0);
		appointmentInfo.setKsxm("0000");
		
		if(iControlCenterService.getExaminationInfo(appointmentInfo)!=null) {
			finalResult = new HXRespons<RegisterResponse>("0", "今天已报道，请与管理员联系", null);
			return finalResult;
		}
		
		int res = iControlCenterService.insertSortInfo(appointmentInfo);
		
		if(res == 1) {
			//报道
			registerInfoVo.setName(null);
			result = iManagerPlatService.register(registerInfoVo);
			
			//返回精英报道返回信息
			finalResult = new HXRespons<RegisterResponse>(result.getCode(), result.getMessage(), null);
			
//			result = new RegisterResponse();
//			result.setCode("1");
			
			if(HXCoreUtil.isEquals(result.getCode(), "1")) {
				
				ExamineeInfoQueryVO examineeInfoQueryVO = new ExamineeInfoQueryVO();
				examineeInfoQueryVO.setSfzmhm(registerInfoVo.getSfzmhm());
				examineeInfoQueryVO.setKsdd(registerInfoVo.getKsdd());
					
				//获取预约照片信息
				WebServiceResult<ExaminationInfo> zpResult = iManagerPlatService.getZP(examineeInfoQueryVO);
				if(zpResult != null && zpResult.getBodyContent() != null) {
					zpInfo = (ExaminationInfo)zpResult.getBodyContent().getContent().get(0);
						
					// 插入人脸机白名单
					whiteListVO.setName(appointmentInfo.getXm());
					whiteListVO.setIdnum(registerInfoVo.getSfzmhm());
					whiteListVO.setPhoto(zpInfo.getZp());
					
					whiteListVO.setValidStart(HXCoreUtil.getNowDataStr(new Date(), "yyyy.MM.dd")); // 起
					whiteListVO.setValidEnd(HXCoreUtil.getNowDataStr(new Date(), "yyyy.MM.dd")); // 止
					faceResponse = iDongwoPlatService.insertWhiteList(whiteListVO);
					
					if (faceResponse.getCode() == 0) {
						appointmentInfo.setZp(zpInfo.getZp());
						// 插入照片信息
						int photoRes = iControlCenterService.insertPhotoInfo(appointmentInfo);
					
						if (photoRes > 0) {
							finalResult = new HXRespons<RegisterResponse>("1", "报道成功", result);
						}
					}
				}
			}
		}
		return finalResult;
	}

}
