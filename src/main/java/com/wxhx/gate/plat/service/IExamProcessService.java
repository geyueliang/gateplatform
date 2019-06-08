package com.wxhx.gate.plat.service;

import com.wxhx.gate.plat.bean.exam.process.ExamEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamItemEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamMark;
import com.wxhx.gate.plat.bean.exam.process.IdentityComparison;
import com.wxhx.gate.plat.bean.exam.process.ProcessImage;

/**
 * 考试过程接口
 * @author geliang
 *
 */
public interface IExamProcessService {

	/**
	 * 身份校验
	 * @param comparison
	 * @return
	 */
	public String idCheck(IdentityComparison comparison) throws Exception;
	
	
	/**
	 * 项目扣分
	 * @param examMark
	 * @return
	 */
	public String examMarkHappen(ExamMark examMark) throws Exception;
	
	
	/**
	 * 上传过程图片
	 * @param processImage
	 * @return
	 */
	public String uploadImage(ProcessImage processImage) throws Exception;
	
	/**
	 *  项目结束
	 * @param examItemEnd
	 * @return
	 */
	public String examItemEnd(ExamItemEnd examItemEnd) throws Exception;
	
	/**
	 * 科目结束
	 * @param examEnd
	 * @return
	 */
	public String examEnd(ExamEnd examEnd) throws Exception;
	
	/**
	 * 统一处理考试过程
	 * @param content
	 * @return
	 */
	public String doProcess(String content) throws Exception;
	
}
