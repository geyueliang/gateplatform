package com.wxhx.gate.plat.service.out;

import java.util.List;

import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoQueryVO;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoVO;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;
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
	WebServiceResult<RegisterResponse> register(RegisterInfoVo registerVo);
	
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
	 * 获取考生照片
	 * @param examineeInfoVO
	 * @return
	 */
	WebServiceResult<ExaminationInfo> getZP(ExamineeInfoQueryVO examineeInfoQueryVO);
	
	/**
	 * 写入考生门禁照片
	 * @param examineeInfoVO
	 * @return
	 */
	WebServiceResult<RegisterResponse> uploadFacePhoto(ExamineeInfoVO examineeInfoVO);
	
	
	/**
	 *  写入视频认证
	 * @param examineeInfoVO
	 * @return
	 */
	WebServiceResult<RegisterResponse> writeVideoAttestation(ExamineeInfoVO examineeInfoVO);
}
