package com.wxhx.gate.plat.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 人脸机器常用属性
 * @author geliang
 *
 */
public class PersonFaceMachineInfo {

	//开发者id
	public static String APPID = "mj4bfdecbef41e2faf";
	
	//设备id
	public static String DEVICENO = "";
	
	
	/**
	 *  获取时间戳
	 * @return
	 */
	public static String  getTimeTemp() {
		String timeFormatStr = "yyyyMMddHHmmssfff";
		SimpleDateFormat dateFormat = new SimpleDateFormat(timeFormatStr);
		return dateFormat.format(new Date());
	}
	
}
