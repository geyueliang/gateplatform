package com.wxhx.gate.plat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;
import com.wxhx.gate.plat.service.IRegisterService;
import com.wxhx.gate.plat.service.out.IManagerPlatService;
import com.wxhx.gate.plat.timer.UpdateReserveInfoTimer;

@Service
public class RegisterServiceImpl implements IRegisterService {
	
	private static Logger logger = LoggerFactory.getLogger(UpdateReserveInfoTimer.class);

	@Autowired
	private IManagerPlatService iManagerPlatService; // 精英

	@Transactional(rollbackFor = Exception.class)
	public HXRespons<RegisterResponse> register(RegisterInfoVo registerInfoVo) throws Exception{
		//精英平台报道
		RegisterResponse result = iManagerPlatService.register(registerInfoVo);
		HXLogUtil.info(logger,"报道返回状态{0}，返回信息{1}",result.getCode(),result.getMessage());
		return new HXRespons<RegisterResponse>(result.getCode(), result.getMessage(), null);
	}

}
