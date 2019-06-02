package com.wxhx.gate.plat.bean.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXHttpClient;

/**
 * @author geliang
 *
 */
@XmlRootElement(name="head")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegisterResponse {
	
	@XmlElement(name="code")
	private  String code;
	
	@XmlElement(name="message")
	private String message;
	
	@XmlElement(name="keystr")
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

	@Override
	public String toString() {
		return HXCoreUtil.getJsonString(this);
	}
	
	
	
	
	

}
