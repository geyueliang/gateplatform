package com.wxhx.gate.plat.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.constent.CommonTestConstent;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;
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
	HXRespons<RegisterResponse> register(@RequestBody Map<String,Object> reqMap){
		//判断当前用户是否是在管理员列表中
		String carNo = reqMap.get("carNo")+"";
		
		//如果是测试环境根据将身份证编号换成测试编号
		if(isTest) {
			if(!HXCoreUtil.isEmpty(CommonTestConstent.replaceMap.get(carNo)+"")){
				carNo = CommonTestConstent.replaceMap.get(carNo)+"";
			} 
		}
		
		
		if(WhiteListInit.WHITE_LIST.contains(carNo)) {
			HXRespons<RegisterResponse> r = new HXRespons<RegisterResponse>("SUCCESS", "管理員", null);
			return r;
		}
		RegisterInfoVo registerInfoVo = new RegisterInfoVo();
		registerInfoVo.setSfzmhm(carNo);
		registerInfoVo.setKskm(kskm);
		registerInfoVo.setKsdd(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSDD));
		return iRegisterService.register(registerInfoVo);
	}
	
}
