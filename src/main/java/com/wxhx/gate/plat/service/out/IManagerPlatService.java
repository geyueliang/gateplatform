package com.wxhx.gate.plat.service.out;

import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
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
	WebServiceResult<ExaminationInfo> getAppointmentInfo(ExamineeInfoVO examineeInfoVO);
	
	
	/**
	 * 获取排考信息
	 * @param examineeInfoVO
	 * @return
	 */
	WebServiceResult<ExaminationInfo> getSortInfo(ExamineeInfoVO examineeInfoVO);
	
	/**
	 * 获取考生照片
	 * @param examineeInfoVO
	 * @return
	 */
	WebServiceResult<ExaminationInfo> getZP(ExamineeInfoVO examineeInfoVO);
	
	/**
	 * 写入考生门禁照片
	 * @param examineeInfoVO
	 * @return
	 */
	WebServiceResult<RegisterResponse> uploadFacePhoto(ExamineeInfoVO examineeInfoVO);
	
}
