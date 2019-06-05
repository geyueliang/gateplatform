package com.wxhx.gate.plat.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.wxhx.gate.plat.util.PersonFaceMachineInfo;

@Service
public class RegisterServiceImpl implements IRegisterService {
	
	@Autowired
	private IManagerPlatService iManagerPlatService;	//精英
	
	@Autowired
	private IDongwoPlatService iDongwoPlatService;	//东沃
	
	@Autowired
	private IControlCenterService iControlCenterService;	//控制中心
	
	private static String fhjls = "1";		//返回记录数
	
	public HXRespons<RegisterResponse> register(RegisterInfoVo registerInfoVo) {
		HXRespons<RegisterResponse> finalResult = new HXRespons<RegisterResponse>("ERROR","操作失败",null);
		ExaminationInfo  appointmentInfo = null;
		FaceResponse faceResponse = null;
		ExaminationInfo sortInfo = null;
		WhiteListVO whiteListVO = new WhiteListVO();
		
		RegisterResponse result = iManagerPlatService.register(registerInfoVo);
		//如果报道成功则获取排考信息，预约信息
		if("1".equals(result.getCode())) {
			ExamineeInfoQueryVO examineeInfoQueryVO = new ExamineeInfoQueryVO();
			
			//获取预约信息
			examineeInfoQueryVO.setKskm(registerInfoVo.getKskm());
			examineeInfoQueryVO.setSfzmhm(registerInfoVo.getSfzmhm());
			examineeInfoQueryVO.setKsdd(registerInfoVo.getKsdd());
			appointmentInfo= iManagerPlatService.getAppointmentInfo(examineeInfoQueryVO);
			
			//插入人脸机白名单
			whiteListVO.setPersonnelName(appointmentInfo.getXm());
			whiteListVO.setPersonnelIDCard(appointmentInfo.getSfzmhm());
			whiteListVO.setPersonnelPhoto(appointmentInfo.getZp());
			whiteListVO.setPersonnelIDCardEff(HXCoreUtil.getNowDataStr(new Date(),"yyyy.MM.dd"));  //起
			whiteListVO.setPersonnelIDCardExp(HXCoreUtil.getNowDataStr(new Date(),"yyyy.MM.dd"));  //止
			faceResponse = iDongwoPlatService.insertWhiteList(whiteListVO);
			
			//获取排考信息插入oracle
			examineeInfoQueryVO.setFhjls(fhjls);
			sortInfo= iManagerPlatService.getSortInfo(examineeInfoQueryVO);
			
			//插入本地Oracle数据库
			int res = iControlCenterService.insertSortInfo(sortInfo);
			if(res > 0 && faceResponse != null && appointmentInfo != null) {
				finalResult = 	new HXRespons<RegisterResponse>("SUCCESS","操作成功",result);
			}
		}
		return finalResult;
	}

}
