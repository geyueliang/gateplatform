package com.wxhx.gate.plat.service;

import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.ExamFinishResponse;

/**
 * 手动考试结束
 * @author coyi
 *
 */
public interface IExamFinishService {
	
	/**
	 * 考试科目结束，调用17C56接口
	 * @param cardId
	 * @param score
	 * @return
	 */
	public HXRespons<ExamFinishResponse> ExamFinish(String sfzmhm,String score);

}
