package com.wxhx.gate.plat.dao;

import org.apache.ibatis.annotations.Param;

public interface KsyxxMapper {

	/**
	 * 根据考试员名字查询考试员身份证信息
	 * @param ksyName
	 * @return
	 */
	public String getKsySfz(@Param("ksyName") String ksyName);
	
}