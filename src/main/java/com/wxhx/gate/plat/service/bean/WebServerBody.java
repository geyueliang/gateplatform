package com.wxhx.gate.plat.service.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;


@XmlAccessorType(XmlAccessType.FIELD)
//@XmlSeeAlso(YyInfo.class)
public class WebServerBody<T> {

//	@XmlElement(name  = "drvexam")
	@XmlAnyElement(lax = true)
	private T content;

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	

	
	

	
	
	
	
	
}
