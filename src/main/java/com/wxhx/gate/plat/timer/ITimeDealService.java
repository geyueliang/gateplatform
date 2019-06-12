package com.wxhx.gate.plat.timer;

public interface ITimeDealService {

	/**
	 * 获取服务校对时间
	 * @return
	 */
	String getServerTime();
	
	/**
	 * 设置系统时间
	 * @param timeStr
	 * @return
	 */
	boolean setSysTime(String timeStr);
	
}
