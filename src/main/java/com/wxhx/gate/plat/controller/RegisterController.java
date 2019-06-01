package com.wxhx.gate.plat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVO;
import com.wxhx.gate.plat.service.IRegisterService;

/**
 * 考生报道
 * @author geliang
 *
 */
@RestController
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private IRegisterService iRegisterService;
	/**
	 * 报道
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	HXRespons<RegisterResponse> register(RegisterInfoVO registerInfoVo){
		return iRegisterService.register(registerInfoVo);
	}
	
}
