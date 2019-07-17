package com.wxhx.gate.plat.controller;

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
import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.exam.process.ExamStop;
import com.wxhx.gate.plat.bean.out.ExamFinishResponse;
import com.wxhx.gate.plat.service.IExamFinishService;
import com.wxhx.gate.plat.service.IUploadExamProcessInfoService;
import com.wxhx.gate.plat.service.bean.WebServiceResult;

@RestController
public class ExamFinishController {

	private Logger logger  = LoggerFactory.getLogger(ExamFinishController.class);
	
	@Autowired
	private IExamFinishService iExamFinishService;
	
	@Autowired
	private IUploadExamProcessInfoService iUploadExamProcessInfoService;
	
	@RequestMapping(value = "/examFinish",method = RequestMethod.POST)
	HXRespons<ExamFinishResponse> examFinish(@RequestBody Map<String,String> reqMap){
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
	
	
	@SuppressWarnings("all")
	@RequestMapping(value = "/examStop",method = RequestMethod.POST)
	HXRespons<ExamFinishResponse> examStop(@RequestBody ExamStop examStop){
		HXRespons response = new HXRespons("0", "暂停考试失败", null);
		try {
			WebServiceResult webServiceResult = iUploadExamProcessInfoService.stopExam(examStop);
			if(webServiceResult.getHead()!=null && HXCoreUtil.isEquals("1", (webServiceResult.getHead().getCode()))){
				response.setResCode("1");
				response.setResMsg("暂停考生成功，请等待并联系精英处理！");
			}
			else {
				response.setResMsg("暂停考生失败,失败信息为:"+webServiceResult.getHead().getMessage()+"请等待并联系精英处理！");
			}
		} catch (Exception e) {
			HXLogUtil.error(logger, "暂停考生考试错误{0},{1}", examStop,e.fillInStackTrace());
			response.setResMsg("暂停考试错误："+e.getMessage());
		}
		return response;
	}
	
}
