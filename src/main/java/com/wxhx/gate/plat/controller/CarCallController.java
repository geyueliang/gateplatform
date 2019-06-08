package com.wxhx.gate.plat.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wxhx.gate.plat.controller.vo.CarCallInfoVo;
import com.wxhx.gate.plat.service.IExamProcessService;

/**
 *  提供给考车调用的web服务
 * @author geliang
 *
 */
@RestController
public class CarCallController {

	private IExamProcessService iExamProcessService;
	
	@RequestMapping(path = "/examProcess",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
//	@RequestMapping(path = "/examProcess",method = RequestMethod.POST)
//	@ResponseBody
	public String carCall(@RequestBody CarCallInfoVo callInfoVo) {
		String result = "";
		try {
			result = iExamProcessService.doProcess(callInfoVo.getContent());
		} catch (Exception e) {
			JSONObject json = new JSONObject();
			json.put("code", "0");
			json.put("message", "error");
			result = json.toJSONString();
		}
		return result;
	}
	
}
