package com.wxhx.gate.plat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxhx.gate.plat.dao.entity.Ksgc;

public interface KsgcMapper {
	
	/**
	 * 得到当前考试次数
	 * @param carNo
	 * @return
	 */
	int getNowKscs(@Param("carNo") String carNo);
	
	
	/**
	 * 获取当前考试 本次考试的 扣分项目
	 * @param carNo
	 * @param kscs
	 * @return
	 */
	List<Ksgc> getKfxm(@Param("carNo") String carNo,@Param("kscs") int kscs);
	
}