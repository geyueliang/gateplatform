package com.wxhx.gate.plat.service;

import com.wxhx.gate.plat.bean.exam.process.ExamEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamItemEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamMark;
import com.wxhx.gate.plat.bean.exam.process.ExamStop;
import com.wxhx.gate.plat.bean.exam.process.IdentityComparison;
import com.wxhx.gate.plat.bean.exam.process.ItemBegin;
import com.wxhx.gate.plat.bean.exam.process.ProcessImage;
import com.wxhx.gate.plat.bean.exam.process.ReadVideo;
import com.wxhx.gate.plat.bean.exam.process.WirteVideo;
import com.wxhx.gate.plat.service.bean.WebServiceResult;

/**
 * 上传开始过程信息到精英平台接口
 * @author geliang
 *
 */
public interface IUploadExamProcessInfoService {
	
	
	/**
	 * 视频认证发起写入
	 * @param wirteVideo
	 * @return
	 * @throws Exception
	 */
	public WebServiceResult writeVideo(WirteVideo wirteVideo) throws Exception;
	
	/**
	 * 读取视频认证结果
	 * @param readVideo
	 * @return
	 * @throws Exception
	 */
	public WebServiceResult readVideo(ReadVideo readVideo) throws Exception;
	

	/**
	 * 身份校验（科目开始）
	 * @param comparison
	 * @return
	 */
	public WebServiceResult idCheck(IdentityComparison comparison) throws Exception;
	
	/**
	 * 项目开始
	 * @param itemBegin
	 * @return
	 * @throws Exception
	 */
	public WebServiceResult itemBegin(ItemBegin itemBegin) throws Exception;

	/**
	 * 上传过程图片
	 * @param processImage
	 * @return
	 */
	public WebServiceResult uploadImage(ProcessImage processImage) throws Exception;
	
	
	/**
	 * 项目扣分
	 * @param examMark
	 * @return
	 */
	public WebServiceResult examMarkHappen(ExamMark examMark) throws Exception;
	
	
	/**
	 *  项目结束
	 * @param examItemEnd
	 * @return
	 */
	public WebServiceResult examItemEnd(ExamItemEnd examItemEnd) throws Exception;
	
	/**
	 * 科目结束
	 * @param examEnd
	 * @return
	 */
	public WebServiceResult examEnd(ExamEnd examEnd) throws Exception;
	
	/**
	 * 暂停考试
	 * @param examStop
	 * @return
	 * @throws Exception
	 */
	public WebServiceResult stopExam(ExamStop examStop) throws Exception;
	
	/**
	 * 统一处理考试过程
	 * @param content
	 * @return
	 */
	public int unifiedEntrance(String content) throws Exception;
	
}
