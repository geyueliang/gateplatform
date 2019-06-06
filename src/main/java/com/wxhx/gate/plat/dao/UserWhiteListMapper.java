package com.wxhx.gate.plat.dao;

import java.util.List;

import com.wxhx.gate.plat.dao.entity.UserWhiteList;

import tk.mybatis.mapper.common.Mapper;

public interface UserWhiteListMapper extends Mapper<UserWhiteList>{
	
	/**
	 * 查询可用管理员名单
	 * @return
	 */
	List<UserWhiteList> selectEnableList();
	
}