package com.wxhx.gate.plat.service.out;

import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;

/**
 * 调用考试平台接口
 * @author geliang
 *
 */
public interface IManagerPlatService {

	RegisterResponse register(RegisterInfoVo registerVo);
	
}
