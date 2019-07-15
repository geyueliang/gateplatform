package com.wxhx.gate.plat.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.gate.plat.controller.vo.CarCallInfoVo;
import com.wxhx.gate.plat.service.IExamProcessService;

/**
 *  提供给考车调用的web服务
 * @author geliang
 *
 */
@RestController
public class CarCallController {
	
	private static Logger logger = LoggerFactory.getLogger(CarCallController.class);

	
	private static List<String> contents = new ArrayList<String>();
	
	@Autowired
	private IExamProcessService iExamProcessService;
	
//	@RequestMapping(path = "/examProcess",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@RequestMapping(path = "/examProcess",method = RequestMethod.POST)
//	@ResponseBody
	public int carCall(CarCallInfoVo callInfoVo) {
		int result = 0;
		try {
			HXLogUtil.info(logger,"=============车载调用接口入参{0}=============",callInfoVo.getContent());
			contents.add(callInfoVo.getContent());
			String resJson = iExamProcessService.doProcess(callInfoVo.getContent());
			result =Integer.parseInt(JSONObject.parseObject(resJson).getString("code"));
		} catch (Exception e) {
			result = 0;
		}
		HXLogUtil.info(logger,"=============车载调用返回{0}=============",result);
		return result;
	}
	
}
