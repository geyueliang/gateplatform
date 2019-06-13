package com.wxhx.gate.plat.dao;

import org.apache.ibatis.annotations.Param;

import com.wxhx.gate.plat.dao.entity.Kscl;

import tk.mybatis.mapper.common.Mapper;

public interface KsclMapper extends Mapper<Kscl>{
	
	//根据考车号牌查询考车编号
	String selectByKchp(@Param("kchp") String kchp);
	
	//根据考车号牌查询考车信息
	Kscl selectKcInfo(@Param("kchp") String kchp);
}
