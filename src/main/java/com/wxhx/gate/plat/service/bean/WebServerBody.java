package com.wxhx.gate.plat.service.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;


@XmlAccessorType(XmlAccessType.FIELD)
//@XmlSeeAlso(YyInfo.class)
public class WebServerBody<T> {

//	@XmlElement(name  = "drvexam")
	@XmlAnyElement(lax = true)
	private List<T> content;

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}



	

	
	

	
	
	
	
	
}
