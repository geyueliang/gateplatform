package com.wxhx.gate.plat.controller.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 考生信息入参
 * @author coyi
 *
 */
@XmlRootElement(name="QueryCondition")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExamineeInfoQueryVO {
	
	@XmlElement(name="kskm", required = true)
	private String kskm;	//考试科目
	
	@XmlElement(name="kscx", required = true)
	private String kscx;	//考试车型
	
	@XmlElement(name="cx")
	private String cx;	//考试车型
	
	@XmlElement(name="fhjls", required = true)
	private String fhjls;	//返回记录数
	
	@XmlElement(name="ksdd", required = true)
	private String ksdd;	//考试地点
	
	@XmlElement(name="kchp", required = true)
	private String kchp;	//考试车牌
	
	@XmlElement(name="sfzmhm", required = true)
	private String sfzmhm;	//身份证明号码
	
	@XmlElement(name="mjzp", required = true)
	private String mjzp;	//门禁照片
	
	@XmlElement(name="ksrq", required = true)
	private String ksrq;	//考试日期

	public String getKskm() {
		return kskm;
	}

	public void setKskm(String kskm) {
		this.kskm = kskm;
	}

	public String getKscx() {
		return kscx;
	}

	public void setKscx(String kscx) {
		this.kscx = kscx;
	}

	public String getFhjls() {
		return fhjls;
	}

	public void setFhjls(String fhjls) {
		this.fhjls = fhjls;
	}

	public String getKsdd() {
		return ksdd;
	}

	public void setKsdd(String ksdd) {
		this.ksdd = ksdd;
	}

	public String getKchp() {
		return kchp;
	}

	public void setKchp(String kchp) {
		this.kchp = kchp;
	}

	public String getSfzmhm() {
		return sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public String getMjzp() {
		return mjzp;
	}

	public void setMjzp(String mjzp) {
		this.mjzp = mjzp;
	}

	public String getKsrq() {
		return ksrq;
	}

	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}

	public String getCx() {
		return cx;
	}

	public void setCx(String cx) {
		this.cx = cx;
	}
	
}
