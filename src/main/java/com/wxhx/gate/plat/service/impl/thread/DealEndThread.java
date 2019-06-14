package com.wxhx.gate.plat.service.impl.thread;

import com.wxhx.gate.plat.bean.exam.process.ExamEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamItemEnd;
import com.wxhx.gate.plat.service.IExamProcessService;

/**
 * 处理项目结束和科目结束的线程
 * @author geliang
 *
 */
public class DealEndThread implements Runnable{

	private IExamProcessService iExamProcessService;
	
	private ExamItemEnd examItemEnd;
	
	private ExamEnd examEnd;
	
	
	public DealEndThread(IExamProcessService iExamProcessService,ExamItemEnd examItemEnd,ExamEnd examEnd) {
		this.iExamProcessService = iExamProcessService;
		this.examItemEnd = examItemEnd;
		this.examEnd = examEnd;
	}
	
	public void run() {
		try {
			//项目结束
			iExamProcessService.examItemEnd(examItemEnd);
			//科目结束
			iExamProcessService.examEnd(examEnd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
