package com.wxhx.gate.plat.bean.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wxhx.basic_client.common.HXCoreUtil;

/**
 * 通用返回信息
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
	
	@XmlElement(name="rownum")
	private String rownum;

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



	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	@Override
	public String toString() {
		return HXCoreUtil.getJsonString(this);
	}
	
	
	
	
	

}
