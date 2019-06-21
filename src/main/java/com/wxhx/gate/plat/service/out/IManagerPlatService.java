package com.wxhx.gate.plat.service.out;

import com.wxhx.gate.plat.bean.out.CheckResponse;
import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.bean.out.SystemCheckInfo;
import com.wxhx.gate.plat.bean.out.VidoeCheckResponse;
import com.wxhx.gate.plat.controller.vo.CheckresultVO;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoQueryVO;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoVO;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;
import com.wxhx.gate.plat.controller.vo.VideoCheckQueryVO;
import com.wxhx.gate.plat.service.bean.WebServiceResult;

/**
 * 精英平台
 * @author geliang
 *
 */
public interface IManagerPlatService {

	/**
	 * 报道
	 * @param registerVo
	 * @return
	 */
	RegisterResponse register(RegisterInfoVo registerVo) throws Exception;
	
	/**
	 * 获取预约信息
	 * @param examineeInfoVO
	 * @return
	 */
	WebServiceResult<ExaminationInfo> getAppointmentInfo(ExamineeInfoQueryVO examineeInfoQueryVO);
	
	
	/**
	 * 获取排考信息
	 * @param examineeInfoVO
	 * @return
	 */
	WebServiceResult<ExaminationInfo> getSortInfo(ExamineeInfoQueryVO examineeInfoQueryVO);
	
	/**
	 * 获取考生考车线路排考信息
	 * @param examineeInfoQueryVO
	 * @return
	 */
	WebServiceResult<ExaminationInfo> getExaminSortInfo(String kchp);
	
	/**
	 * 获取考生照片
	 * @param examineeInfoVO
	 * @return
	 */
	WebServiceResult<ExaminationInfo> getZP(ExamineeInfoQueryVO examineeInfoQueryVO) throws Exception;
	
	/**
	 * 写入考生门禁照片
	 * @param examineeInfoVO
	 * @return
	 */
	RegisterResponse uploadFacePhoto(ExamineeInfoVO examineeInfoVO);
	
	
	/**
	 *  写入视频认证
	 * @param examineeInfoVO
	 * @return
	 */
	RegisterResponse writeVideoAttestation(ExamineeInfoVO examineeInfoVO);
	
	
	/**
	 * 获取系统检测项
	 * @param examineeInfoVO
	 * @return
	 */
	WebServiceResult<SystemCheckInfo> getSystemTests(ExamineeInfoQueryVO examineeInfoQueryVO);
	
	/**
	 * 写入检测结果
	 * @param checkresultVO
	 * @return
	 */
	WebServiceResult<CheckResponse> writeCheckResult(CheckresultVO checkresultVO);
	
	/**
	 *  读取视频认证结果
	 * @param examineeInfoVO
	 * @return
	 */
	WebServiceResult<VidoeCheckResponse> readVideoAttestation(VideoCheckQueryVO videoCheckQueryVO);
	
}
