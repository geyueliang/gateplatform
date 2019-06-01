package com.wxhx.gate.plat.service;

import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;

/**
 * @author geliang
 *
 */
public interface IRegisterService {

	
	HXRespons<RegisterResponse> register(RegisterInfoVo registerInfoVo);
	
}
