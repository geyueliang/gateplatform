package com.wxhx.gate.plat.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 闸机平台工具类
 * @author geliang
 *
 */
public class GatePlatUtil {

	/**
	 * 将制定日期转换为指定格式
	 * @param format
	 * @param date
	 * @return
	 */
	public static String getFormatDate(String format,Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}
	
}
