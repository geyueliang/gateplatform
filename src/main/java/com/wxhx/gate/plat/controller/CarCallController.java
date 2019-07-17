package com.wxhx.gate.plat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.gate.plat.controller.vo.CarCallInfoVo;
import com.wxhx.gate.plat.service.IUploadExamProcessInfoService;

/**
 *  提供给考车调用的web服务
 * @author geliang
 *
 */
@RestController
public class CarCallController {
	
	private static Logger logger = LoggerFactory.getLogger(CarCallController.class);

	
	@Autowired
	private IUploadExamProcessInfoService iUploadExamProcessInfoService;
	
	@RequestMapping(path = "/examProcess",method = RequestMethod.POST)
	public int carCall(CarCallInfoVo callInfoVo) {
		int r = 0;
		try {
			r = iUploadExamProcessInfoService.unifiedEntrance(callInfoVo.getContent());
		} catch (Exception e) {
			r = 0;
			HXLogUtil.error(logger, "车载调用出错,错误堆栈为{0}", e.getStackTrace());
		}
		return r;
	}
	
}
