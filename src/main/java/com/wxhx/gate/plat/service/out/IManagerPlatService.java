package com.wxhx.gate.plat.service.out;

import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;

/**
 * @author geliang
 *
 */
public interface IManagerPlatService {

	/**
	 * 报道注册接口
	 * @param registerVo
	 * @return
	 */
	RegisterResponse register(RegisterInfoVo registerVo);
	
}
