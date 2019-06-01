package com.wxhx.gate.plat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;
import com.wxhx.gate.plat.service.IRegisterService;
import com.wxhx.gate.plat.service.out.IManagerPlatService;

@Service
public class RegisterServiceImpl implements IRegisterService {
	
	@Autowired
	private IManagerPlatService iManagerPlatService;
	

	public HXRespons<RegisterResponse> register(RegisterInfoVo registerInfoVo) {
		//调用外部接口 返回 RegisterResponse
		//如果registerRespons中的code正确
		//在调用其他接口获取预约信息和考生信息
		//oracle入口操作
		//调用人脸机平台
		return null;
	}

}
