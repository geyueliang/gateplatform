package com.wxhx.gate.plat.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.gate.plat.service.ICallJYLogService;
import com.wxhx.gate.plat.service.out.IManagerPlatService;

@RestController
public class CallJyLogController {
	@Autowired
	private ICallJYLogService iCallJYLogService;

	@Autowired
	private IManagerPlatService managerService;
	
	/**
	 * 返回调用精英日志查询
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/calljyLog", method= RequestMethod.POST)
	public String addWhiteUser(@RequestBody Map<String,String> reqMap) throws Exception{
		String sfzmhm = reqMap.get("sfzmhm");
		String day = reqMap.get("day");
		String jkid = reqMap.get("jkid");
		String result = HXCoreUtil.getJsonString(iCallJYLogService.callJYLog(sfzmhm,day,jkid));
		return result;
	}
	
	
	
	@RequestMapping(value = "/queryMjzp", method= RequestMethod.POST)
	public String queryMjzp(@RequestBody Map<String,String> reqMap) throws Exception{
		String sfzmhm = reqMap.get("sfzmhm");
		String res = "";
		try {
			//调用接口
			res = managerService.getMjzp(sfzmhm);
		} catch (Exception e) {
			res = e.getStackTrace().toString();
		}
		return res;
	}
}
