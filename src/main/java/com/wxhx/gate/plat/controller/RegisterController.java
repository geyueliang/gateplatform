package com.wxhx.gate.plat.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;
import com.wxhx.gate.plat.init.HXSystemInfo;
import com.wxhx.gate.plat.init.WhiteListInit;
import com.wxhx.gate.plat.service.IRegisterService;

/**
 * 考生报道
 * @author geliang
 *
 */
@RestController
@RequestMapping("/register")
public class RegisterController {
	
	private static Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private IRegisterService iRegisterService;
	
	private static String kskm = "2";
	
	@Value("${wxhx.gate.test:false}")
	private boolean isTest;
	/**
	    *报道
	 *  @param sfzhm 身份证号码
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	HXRespons<RegisterResponse> register(@RequestBody Map<String,String> reqMap){
		HXRespons<RegisterResponse> registerResponse = new HXRespons<RegisterResponse>("0", "报道失败", null);
		//判断当前用户是否是在管理员列表中
		String carNo = reqMap.get("carNo")+"";
		String name = reqMap.get("name");
		if(carNo == null || HXCoreUtil.isEquals("", carNo) || name == null || HXCoreUtil.isEquals("", name)) {
			return registerResponse;
		}
		
		//测试
		/*
		 * if(HXCoreUtil.isEquals("320831199003100634", carNo)) { name = "吴义"; carNo =
		 * "522127198510066559"; }
		 */
		HXLogUtil.debug(logger,"当前白名单信息 userwhitelist {0}",WhiteListInit.WHITE_LIST);
		if(WhiteListInit.WHITE_LIST.contains(carNo)) {
			registerResponse = new HXRespons<RegisterResponse>("1", "管理員", null);
			return registerResponse;
		}
		HXLogUtil.debug(logger,"开始报道，报道信息是 params {0}",reqMap);
		RegisterInfoVo registerInfoVo = new RegisterInfoVo();
		registerInfoVo.setName(name);
		registerInfoVo.setSfzmhm(carNo);
		registerInfoVo.setKskm(kskm);
		registerInfoVo.setKsdd(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSDD));
		
		try {
			registerResponse = iRegisterService.register(registerInfoVo);
		} catch (Exception e) {
			registerResponse = new HXRespons<RegisterResponse>("0", "报道失败:"+e.getMessage(), null);
		}
		return registerResponse;
	}
	
}
