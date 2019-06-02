package com.wxhx.gate.plat.service.bean;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * webservice发送请求
 * @author geliang
 *
 */
@XmlRootElement(name="root")
//@XmlSeeAlso({RegisterInfoVo.class})
public class WebServiceBean<T> {

	
	private T content;

	@XmlAnyElement(lax = true)
	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}


	
	
	
	
	
}
