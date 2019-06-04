package com.wxhx.gate.plat.bean.out;

/**
 * 东沃通用返回信息
 * @author coyi
 *
 */
public class FaceResponse {
	private int code;	//返回状态 1：成功，其他失败
	private String msg;	//提示信息	
	private String appid;	//开发者应用ID
	private String deviceno;	//设备编号
	private String sign;	//签名字符串
	private String timestemp;	//时间戳
	private String data;	//返回数据中data的参数
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
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
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTimestemp() {
		return timestemp;
	}
	public void setTimestemp(String timestemp) {
		this.timestemp = timestemp;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
