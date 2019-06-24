package com.wxhx.gate.plat.dao;

import org.apache.ibatis.annotations.Param;

import com.wxhx.gate.plat.dao.entity.Ksyyxx;

import tk.mybatis.mapper.common.Mapper;

public interface KsyyxxMapper extends Mapper<Ksyyxx>{
	
	//根据身份证查询预约信息
	Ksyyxx selectByIdNum(@Param("sfzmhm") String idNum);
	
	//根据身份证号码更新预约信息
	int updateByCardId(Ksyyxx ksyyxx);
	
	/**
	 * 根据身份证号码删除预约信息
	 * @param sfzmhm
	 * @return
	 */
	int deleteKsyyxx(String sfzmhm);
   
}