package com.wxhx.gate.plat.controller.vo;

/**
 * 
 * @author coyi
 *
 */
public class OpenGateVO {
	private String appid;	//开发者应用ID
	private String deviceno;	//设备编号
	private String timestemp;	//时间戳
	private String sign;	//签名字符串
	private String data;	//设备参数
	private String Device_AppID;	//开发者应用ID
	private String Device_No;	//设备编号
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getDeviceno() {
		return deviceno;
	}
	public void setDeviceno(String deviceno) {
		this.deviceno = deviceno;
	}
	public String getTimestemp() {
		return timestemp;
	}
	public void setTimestemp(String timestemp) {
		this.timestemp = timestemp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDevice_AppID() {
		return Device_AppID;
	}
	public void setDevice_AppID(String device_AppID) {
		Device_AppID = device_AppID;
	}
	public String getDevice_No() {
		return Device_No;
	}
	public void setDevice_No(String device_No) {
		Device_No = device_No;
	}
	
}
