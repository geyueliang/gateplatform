package com.wxhx.gate.plat.controller.vo;

/**
 * 远程一键开门
 * @author coyi
 *
 */
public class FaceGateVO extends DongWoVO{
	private String deviceno;	//设备编号
	private FaceMacDevVO data;
	public String getDeviceno() {
		return deviceno;
	}
	public void setDeviceno(String deviceno) {
		this.deviceno = deviceno;
	}
	public FaceMacDevVO getData() {
		return data;
	}
	public void setData(FaceMacDevVO data) {
		this.data = data;
	}
	
}
