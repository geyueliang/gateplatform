package com.wxhx.gate.plat.service;

import java.util.List;

import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.CheckResponse;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoQueryVO;
import com.wxhx.gate.plat.dao.entity.Kscl;

/**
 * 系统检测接口
 * @author coyi
 *
 */
public interface ISystemCheckService {
	
	/**
	 * 开始检测
	 */
	HXRespons<CheckResponse> startCheck(List<Kscl> kscls);
}
