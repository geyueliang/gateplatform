package com.wxhx.gate.plat.dao;

import org.apache.ibatis.annotations.Param;

import com.wxhx.gate.plat.dao.entity.Kszp;

import tk.mybatis.mapper.common.Mapper;

public interface KszpMapper extends Mapper<Kszp>{
	//根据身份证和照片类型更新人脸机采集照片
	int updatePhoto(Kszp kszp);
	
	
	/**
	 *  根据考生身份证和类型查询具体的照片信息
	 * @param carNo
	 * @param type
	 * @return
	 */
	Kszp getKszpByCarNo(@Param("carNo") String carNo,@Param("type")String type);
	
	/**
	 * 根据身份证号码更新流水号
	 * @param sfzmhm
	 * @return
	 */
	int updateLsh(Kszp kszp);
	
	/**
	 * 插入照片之前先删除报道门禁照片
	 * @param sfzmhm
	 * @return
	 */
	int deleteKszp(Kszp kszp);
}
