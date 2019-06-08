package com.wxhx.gate.plat.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 调用函数dao
 * @author geliang
 *
 */
public interface FunctionMapper {
	
	//select sys.uf_e_decrypt('{0}') from dual
	public String decodeStr(@Param("encodeStr") String encodeStr);
	
}
