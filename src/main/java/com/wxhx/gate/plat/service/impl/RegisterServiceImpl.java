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
		return null;
	}

}
