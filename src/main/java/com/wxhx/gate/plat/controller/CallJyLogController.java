package com.wxhx.gate.plat.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxhx.basic_client.common.HXCoreUtil;

@RestController
public class CallJyLogController {

	
	/**
	 * 返回调用精英日志查询
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/calljyLog", method= RequestMethod.POST)
	public String addWhiteUser(@RequestBody Map<String,String> reqMap) throws Exception{
		String sfzmhm = reqMap.get("sfzmhm");
		String day = reqMap.get("day");
		String result = HXCoreUtil.getJsonString(reqMap);
		return result;
	}
	
}
