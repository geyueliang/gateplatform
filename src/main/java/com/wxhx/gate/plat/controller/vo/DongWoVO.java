package com.wxhx.gate.plat.controller.vo;

/**
 * 东沃基类
 * @author coyi
 *
 */
public class DongWoVO {
	private String appid;	//开发者应用ID
	private String timestemp;	//时间戳
	private String sign;	//签名字符串
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
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
}
