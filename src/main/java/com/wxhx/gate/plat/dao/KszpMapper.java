package com.wxhx.gate.plat.dao;

import com.wxhx.gate.plat.dao.entity.Kszp;

import tk.mybatis.mapper.common.Mapper;

public interface KszpMapper extends Mapper<Kszp>{
	//根据身份证和照片类型更新人脸机采集照片
	int updatePhoto(Kszp kszp);
}
