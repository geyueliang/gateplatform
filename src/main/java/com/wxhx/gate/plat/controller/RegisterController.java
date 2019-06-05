package com.wxhx.gate.plat.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;
import com.wxhx.gate.plat.service.IRegisterService;
import com.wxhx.gate.plat.util.PersonFaceMachineInfo;

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
	
	private static String kskm = "2";
	/**
	    *报道
	 *  @param sfzhm 身份证号码
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	HXRespons<RegisterResponse> register(@RequestBody Map<String,Object> reqMap){
		RegisterInfoVo registerInfoVo = new RegisterInfoVo();
		registerInfoVo.setSfzmhm(reqMap.get("carNo")+"");
		registerInfoVo.setKskm(kskm);
		registerInfoVo.setKsdd(PersonFaceMachineInfo.KSDD);
		return iRegisterService.register(registerInfoVo);
	}
	
}
