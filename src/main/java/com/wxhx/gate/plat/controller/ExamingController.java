package com.wxhx.gate.plat.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.bean.out.RecordInfo;
import com.wxhx.gate.plat.service.IExamStartService;

/**
 * 开始考试
 * @author coyi
 *
 */
@RestController
@RequestMapping("/examStart")
public class ExamingController {
	
	@Autowired
	private IExamStartService iExamStartService;
	
	/**
	 * 开始考试
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String examStart(@RequestBody RecordInfo recordInfo){
		System.out.println(recordInfo);
		HXRespons<FaceResponse> examResult = iExamStartService.examing(recordInfo);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("code", examResult.getResCode());
		res.put("msg", examResult.getResMsg());
		return HXCoreUtil.getJsonString(res);
	}
}
