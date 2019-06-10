package com.wxhx.gate.plat.service.impl;

import java.util.Date;
import java.util.List;

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

	private static String fhjls = "100"; // 返回记录数

	@Transactional(rollbackFor = Exception.class)
	public HXRespons<RegisterResponse> register(RegisterInfoVo registerInfoVo) {
		HXRespons<RegisterResponse> finalResult = new HXRespons<RegisterResponse>("ERROR", "操作失败", null);
		ExaminationInfo appointmentInfo = null;
		FaceResponse faceResponse = null;
		List<ExaminationInfo> sortInfoList = null;
		ExaminationInfo zpInfo = null;
		WhiteListVO whiteListVO = new WhiteListVO();

//		RegisterResponse result = iManagerPlatService.register(registerInfoVo);
		RegisterResponse result = new RegisterResponse();
		result.setCode("1");
		// 如果报道成功则获取排考信息，预约信息
		if ("1".equals(result.getCode())) {
			ExamineeInfoQueryVO examineeInfoQueryVO = new ExamineeInfoQueryVO();
			// 获取预约信息
			examineeInfoQueryVO.setSfzmhm(registerInfoVo.getSfzmhm());
			examineeInfoQueryVO.setKskm(registerInfoVo.getKskm());
			examineeInfoQueryVO.setKsdd(registerInfoVo.getKsdd());
			appointmentInfo = (ExaminationInfo) iManagerPlatService.getAppointmentInfo(examineeInfoQueryVO)
					.getBodyContent().getContent().get(0);
			if (appointmentInfo != null) {
				// 获取考生照片
				zpInfo = (ExaminationInfo) iManagerPlatService.getZP(examineeInfoQueryVO).getBodyContent().getContent().get(0);

				if (zpInfo != null) {
					// 插入人脸机白名单
					whiteListVO.setName(appointmentInfo.getXm());
					whiteListVO.setIdnum(appointmentInfo.getSfzmhm());
					whiteListVO.setPhoto(zpInfo.getZp());
					whiteListVO.setValidStart(HXCoreUtil.getNowDataStr(new Date(), "yyyy.MM.dd")); // 起
					whiteListVO.setValidEnd(HXCoreUtil.getNowDataStr(new Date(), "yyyy.MM.dd")); // 止
					faceResponse = iDongwoPlatService.insertWhiteList(whiteListVO);

					if (faceResponse.getCode() == 0) {
						// 获取排考信息,插入支队数据库
						int res = 0;
						examineeInfoQueryVO.setFhjls(fhjls);
						sortInfoList = (List<ExaminationInfo>) iManagerPlatService.getSortInfo(examineeInfoQueryVO).getBodyContent().getContent();
						for (ExaminationInfo sortInfo : sortInfoList) {
							if (HXCoreUtil.isEquals(appointmentInfo.getSfzmhm(), sortInfo.getSfzmhm())) {
								sortInfo.setZkzmbh(appointmentInfo.getZkzmbh());
								res = iControlCenterService.insertSortInfo(sortInfo);
							}
						}

						// 插入照片信息
						appointmentInfo.setZp(zpInfo.getZp());
						int photoRes = iControlCenterService.insertPhotoInfo(appointmentInfo);

						if (res > 0 && photoRes > 0 && appointmentInfo != null) {
							finalResult = new HXRespons<RegisterResponse>("SUCCESS", "操作成功", result);
						}
					}
				}
			}
		}
		return finalResult;
	}

}
