package com.wxhx.gate.plat.service.out;

import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;

/**
 * ���ÿ���ƽ̨�ӿ�
 * @author geliang
 *
 */
public interface IManagerPlatService {

	RegisterResponse register(RegisterInfoVo registerVo);
	
}
