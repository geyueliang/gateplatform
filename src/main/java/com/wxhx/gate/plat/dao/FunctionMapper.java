package com.wxhx.gate.plat.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 调用函数dao
 * @author geliang
 *
 */
public interface FunctionMapper {
	
	/**
	 * 加密解码
	 * @param encodeStr
	 * @return
	 */
	//select sys.uf_e_decrypt('{0}') from dual
	public String decodeStr(@Param("encodeStr") String encodeStr);
	
	/**
	 * 获取数据库系统时间
	 * @return
	 */
	public String getDbDate();
	
	/**
	 * 判断指定的表是否存在
	 * @param tableName
	 * @return
	 */
	public int tableExit(@Param("tableName")String tableName);
	
	
}
