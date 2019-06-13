package com.wxhx.gate.plat.service;

/**
 * 车辆信息处理
 * @author geliang
 *
 */
public interface IDealCarInfoService {

	/**
	 * 上报异常车辆
	 * @param carNum
	 */
	public boolean reportExceptionCar(String carNum);
	
}
