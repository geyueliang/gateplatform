package com.wxhx.gate.plat.service;

import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoVO;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVO;

/**
 * 考生报道
 * @author geliang
 *
 */
public interface IRegisterService {

	
	/**
	 * 报道
	 * @param registerInfoVo
	 * @return
	 */
	HXRespons<RegisterResponse> register(RegisterInfoVO registerInfoVo);
	
}
