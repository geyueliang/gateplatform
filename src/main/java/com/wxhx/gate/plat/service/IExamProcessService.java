package com.wxhx.gate.plat.service;

import com.wxhx.gate.plat.bean.exam.process.ExamEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamItemEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamMark;
import com.wxhx.gate.plat.bean.exam.process.IdentityComparison;
import com.wxhx.gate.plat.bean.exam.process.ItemBegin;
import com.wxhx.gate.plat.bean.exam.process.ProcessImage;
import com.wxhx.gate.plat.bean.exam.process.ReadVideo;
import com.wxhx.gate.plat.bean.exam.process.WirteVideo;

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
	 * 项目开始
	 * @param itemBegin
	 * @return
	 * @throws Exception
	 */
	public String itemBegin(ItemBegin itemBegin) throws Exception;
	
	
	/**
	 * 项目扣分
	 * @param examMark
	 * @return
	 */
	public String examMarkHappen(ExamMark examMark,ExamItemEnd examItemEnd) throws Exception;
	
	
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
	public String examItemEnd(ExamItemEnd examItemEnd,boolean isNormal) throws Exception;
	
	/**
	 * 科目结束
	 * @param examEnd
	 * @return
	 */
	public String examEnd(ExamEnd examEnd) throws Exception;
	
	/**
	 * 视频认证发起写入
	 * @param wirteVideo
	 * @return
	 * @throws Exception
	 */
	public String writeVideo(WirteVideo wirteVideo) throws Exception;
	
	/**
	 * 读取视频认证结果
	 * @param readVideo
	 * @return
	 * @throws Exception
	 */
	public String readVideo(ReadVideo readVideo) throws Exception;
	
	/**
	 * 统一处理考试过程
	 * @param content
	 * @return
	 */
	public String doProcess(String content) throws Exception;
	
}
