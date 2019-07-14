package com.wxhx.gate.plat.service.impl.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wxhx.basic_client.common.HXLogUtil;
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
	
	private static Logger logger = LoggerFactory.getLogger(DealEndThread.class);

	public DealEndThread(IExamProcessService iExamProcessService,ExamItemEnd examItemEnd,ExamEnd examEnd) {
		this.iExamProcessService = iExamProcessService;
		this.examItemEnd = examItemEnd;
		this.examEnd = examEnd;
	}
	
	public void run() {
		try {
			HXLogUtil.info(logger, "扣分结束项目和科目,项目内容{0},{1}", examItemEnd,examEnd);
			//项目结束
			iExamProcessService.examItemEnd(examItemEnd,false);
			//科目结束
//			iExamProcessService.examEnd(examEnd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
