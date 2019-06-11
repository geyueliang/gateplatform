package com.wxhx.gate.plat.service;

import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.CheckResponse;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoQueryVO;

/**
 * 系统检测接口
 * @author coyi
 *
 */
public interface ISystemCheckService {
	
	/**
	 * 开始检测
	 */
	HXRespons<CheckResponse> startCheck(ExamineeInfoQueryVO examineeInfoQueryVO);
}
