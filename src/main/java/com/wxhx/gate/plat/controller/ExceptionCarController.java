package com.wxhx.gate.plat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.service.IDealCarInfoService;

/**
 * 异常车辆处理
 * @author geliang
 *
 */
@RestController
public class ExceptionCarController {

	@Autowired
	private IDealCarInfoService iDealCarInfoService;
	
	/**
	 * 新增白名单
	 * @param file
	 * @param userWhiteList
	 * @return
	 * @throws Exception 
	 */

	@RequestMapping(value = "/exceptionCar", method= RequestMethod.GET)
	public HXRespons<String> exceptionCarRecord(@RequestParam("carNum") String carNum){
		HXRespons<String> result = new HXRespons<String>();
		result.setResCode("ERROR");
		if (HXCoreUtil.isEmpty(carNum)) {
			return result;
		}
		if(iDealCarInfoService.reportExceptionCar(carNum)) {
			result.setResCode("SUCCESS");
		}
		return result;
	}
	
}
