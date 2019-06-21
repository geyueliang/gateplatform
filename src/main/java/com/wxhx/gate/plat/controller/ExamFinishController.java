package com.wxhx.gate.plat.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.ExamFinishResponse;
import com.wxhx.gate.plat.service.IExamFinishService;

@RestController
public class ExamFinishController {

	@Autowired
	private IExamFinishService iExamFinishService;
	
	@RequestMapping(value = "/examFinish",method = RequestMethod.POST)
	HXRespons<ExamFinishResponse> register(@RequestBody Map<String,String> reqMap){
		HXRespons<ExamFinishResponse> examinationInfo = new HXRespons<ExamFinishResponse>("0", "结束考试失败", null);
		
		
		String sfzmhm = reqMap.get("sfzmhm");
		String score = reqMap.get("score");
		if(HXCoreUtil.isEmpty(sfzmhm) || HXCoreUtil.isEmpty(score)) {
			examinationInfo = new HXRespons<ExamFinishResponse>("0", "考试分数或身份证号码不能为空", null);
			return examinationInfo;
		}
		
		
		examinationInfo = iExamFinishService.examFinish(sfzmhm, score);
		return examinationInfo;
	}
}
