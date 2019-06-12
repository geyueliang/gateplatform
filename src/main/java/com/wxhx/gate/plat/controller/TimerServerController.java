package com.wxhx.gate.plat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wxhx.gate.plat.timer.ITimeDealService;

/**
 * 平台提供的检验时间controlller
 * @author geliang
 *
 */
@RestController
public class TimerServerController {

	@Autowired
	private ITimeDealService iTimeDealServer;
	
	@RequestMapping("/getServerTime")
	public String sendServerTime() {
		String serverTime = iTimeDealServer.getServerTime();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("serverTime", serverTime);
		return jsonObject.toJSONString();
	}
	
}
