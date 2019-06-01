package com.wxhx.gate.plat.bean.out;

/**
 * 调用精英平台的报到信息返回
 * @author geliang
 *
 */
public class RegisterResponse {
	
	private  String code;
	
	private String message;
	
	private String keystr;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getKeystr() {
		return keystr;
	}

	public void setKeystr(String keystr) {
		this.keystr = keystr;
	}
	
	
	
	

}
