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

		//报道
		registerInfoVo.setName(null);
		result = iManagerPlatService.register(registerInfoVo);
		
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
					}else {
						finalResult = new HXRespons<RegisterResponse>("0", "插入报道照片失败", null);
					}
				}else {
					finalResult = new HXRespons<RegisterResponse>("0", "插入人脸机白名单失败", null);
				}
			}else {
				finalResult = new HXRespons<RegisterResponse>("0", "获取精英返回照片失败", null);
			}
			
		}else {
			//返回精英报道返回信息
			finalResult = new HXRespons<RegisterResponse>("0", "精英返回信息："+result.getMessage(), null);
		}
		return finalResult;
	}

}
