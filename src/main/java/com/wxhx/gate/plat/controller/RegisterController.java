package com.wxhx.gate.plat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;

/**
 * �����������
 * @author geliang
 *
 */
@RestController
@RequestMapping("/register")
public class RegisterController {
	
	
	
	

	/**
	 * ��ʼע��
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	HXRespons<RegisterResponse> register(RegisterInfoVo registerInfoVo){
		return null;
	}
	
}
