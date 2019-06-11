package com.wxhx.gate.plat.bean.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wxhx.basic_client.common.HXCoreUtil;

/**
 * 系统检测项
 * @author coyi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="drvpara")
public class SystemCheckInfo {
	@XmlElement(name="checkcode")
	private String checkcode;
	
	@XmlElement(name="checkname")
	private String checkname;
	
	@XmlElement(name="checkkey")
	private String checkkey;
	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public String getCheckname() {
		return checkname;
	}
	public void setCheckname(String checkname) {
		this.checkname = checkname;
	}
	public String getCheckkey() {
		return checkkey;
	}
	public void setCheckkey(String checkkey) {
		this.checkkey = checkkey;
	}
	@Override
	public String toString() {
		return HXCoreUtil.getJsonString(this);
	}
}
