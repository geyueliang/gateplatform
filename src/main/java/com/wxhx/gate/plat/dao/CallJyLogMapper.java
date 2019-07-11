package com.wxhx.gate.plat.dao;

import java.util.List;

import com.wxhx.gate.plat.dao.entity.CallJyLog;

import tk.mybatis.mapper.common.Mapper;

public interface CallJyLogMapper extends Mapper<CallJyLog>{
	
	/**
	 * 模糊查询，按时间倒序
	 * @param callJyLog
	 * @return
	 */
	List<CallJyLog> fuzzySelect(CallJyLog callJyLog);
}