package com.wxhx.gate.plat.controller.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author geliang
 *
 */
@XmlRootElement(name="vioViolation")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegisterInfoVo {

	@XmlElement(name="kskm", required = true)
	private String kskm;
	
	@XmlElement(name="sfzmhm", required = true)
	private String sfzmhm;
	
	@XmlElement(name="ksdd", required = true)
	private String ksdd;

	public String getKskm() {
		return kskm;
	}

	public void setKskm(String kskm) {
		this.kskm = kskm;
	}

	public String getSfzmhm() {
		return sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public String getKsdd() {
		return ksdd;
	}

	public void setKsdd(String ksdd) {
		this.ksdd = ksdd;
	}
	
	
	
}
