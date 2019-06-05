package com.wxhx.gate.plat.service.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * webService 返回信息
 * @author geliang
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement(name = "root")
//@XmlSeeAlso(YyInfo.class)
public class WebServiceResult<T> {
	
	@XmlElement(name = "head")  
	private WebServiceResultHead head;
	
	@XmlElement(name = "body")
	private WebServerBody<T> bodyContent;

	public WebServiceResultHead getHead() {
		return head;
	}

	public void setHead(WebServiceResultHead head) {
		this.head = head;
	}

	public WebServerBody getBodyContent() {
		return bodyContent;
	}

	public void setBodyContent(WebServerBody bodyContent) {
		this.bodyContent = bodyContent;
	}
	
	
	
	
	
}
