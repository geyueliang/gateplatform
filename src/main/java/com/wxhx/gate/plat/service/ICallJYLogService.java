package com.wxhx.gate.plat.service;

import java.util.List;

import com.wxhx.gate.plat.dao.entity.CallJyLog;

/**
 * 调用精英接口日志记录服务
 * @author geliang
 *
 */
public interface ICallJYLogService {
	
	/**
	 * 查询调用精英接口日志
	 * @return
	 */
	public List<CallJyLog> callJYLog(String sfzmhm,String day);

}
