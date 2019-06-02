package com.wxhx.gate.plat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoVO;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;
import com.wxhx.gate.plat.controller.vo.WhiteListVO;
import com.wxhx.gate.plat.service.IRegisterService;
import com.wxhx.gate.plat.service.out.IControlCenterService;
import com.wxhx.gate.plat.service.out.IDongwoPlatService;
import com.wxhx.gate.plat.service.out.IManagerPlatService;

@Service
public class RegisterServiceImpl implements IRegisterService {
	
	@Autowired
	private IManagerPlatService iManagerPlatService;
	
	@Autowired
	private IDongwoPlatService iDongwoPlatService;
	
	@Autowired
	private IControlCenterService iControlCenterService;
	
	
	public HXRespons<RegisterResponse> register(RegisterInfoVo registerInfoVo) {
		//开始报道
		ExamineeInfoVO examineeInfoVO = new ExamineeInfoVO();
		
		RegisterResponse result = iManagerPlatService.register(registerInfoVo);
		//如果报道成功则获取排考信息，预约信息
		if("1".equals(result.getCode())) {
			//获取预约信息
			ExaminationInfo  appointmentInfo= iManagerPlatService.getAppointmentInfo(examineeInfoVO);
			
			WhiteListVO whiteListVO = new WhiteListVO();
			//whiteListVO.setPersonnelCardNo(appointmentInfo.getClass());
			whiteListVO.setPersonnelPhoto(appointmentInfo.getZp());
			//插入人脸机白名单
			iDongwoPlatService.insertWhiteList(whiteListVO);
			
			//排考信息
			ExaminationInfo sortInfo= iManagerPlatService.getSortInfo(examineeInfoVO);
			iControlCenterService.insertSortInfo(sortInfo);
			
		}
		
		return null;
	}

}
