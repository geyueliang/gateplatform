package com.wxhx.gate.plat.bean.out;

/**
 * 东沃通用返回信息
 * @author coyi
 *
 */
public class FaceResponse {
	
	private int code;	//返回状态 1：成功，其他失败
	
	private String msg;	//提示信息	

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
	
	
}
