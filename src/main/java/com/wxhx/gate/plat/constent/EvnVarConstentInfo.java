package com.wxhx.gate.plat.constent;
/**
 * 环境变量key
 * @author geliang
 *
 */

import java.util.Map;

public class EvnVarConstentInfo {

	public static String JKXLH = "jaya2xlh";	//精英接口序列号
	
	public static String WEBSERVICEURL = "jaya2url";	//精英webservice地址
	
	public static String KSDD = "xzksdd";	//考场地点
	
	public static String XZKCXH = "xzkcxh";	//考场序号
	
	public static String XZGLBM  = "xzglbm";	//管理部门
	
	public static String XZFZJG = "xzfzjg";	//发证机关
	
	public static String KSXTBH = "ksxtbh";	//考试系统编号
	
	public static String FACEMACHE_URL = "faceUrl";	//人脸机url地址
	
	public static String LOCAL_URL = "localUrl";	//人脸机返回信息url地址

	
	
	private static Map<String, String> systemInfoMap;
	
	/***
	 * 根据指定的key获取当前系统参数
	 * @param key
	 * @return
	 */
	public static String getSystemInfo(String key) {
		return systemInfoMap.get(key);
	}


	public static void setSystemInfoMap(Map<String, String> systemInfoMap) {
		EvnVarConstentInfo.systemInfoMap = systemInfoMap;
	}
	
	
	
}
