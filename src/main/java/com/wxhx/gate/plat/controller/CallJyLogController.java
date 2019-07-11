package com.wxhx.gate.plat.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.gate.plat.service.ICallJYLogService;
import com.wxhx.gate.plat.service.out.IManagerPlatService;

@RestController
public class CallJyLogController {
	
	private static Logger logger = LoggerFactory.getLogger(CallJyLogController.class);
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
		String sfzmhm = (String)reqMap.get("sfzmhm");
	    Map<String, Object> resMap = new HashMap<String, Object>();
	    String res = "";
	    
	    try {
	      res = this.managerService.getMjzp(sfzmhm);
	    } catch (Exception e) {
	      res = e.getStackTrace().toString();
	    } 
	    HXLogUtil.info(logger, "controller获取考生照片{0}", new Object[] { res });
	    resMap.put("result", res);
	    return HXCoreUtil.getJsonString(resMap);
	}
}
