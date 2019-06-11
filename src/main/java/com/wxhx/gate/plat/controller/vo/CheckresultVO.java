package com.wxhx.gate.plat.controller.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 写入检测结果
 * @author coyi
 *
 */
@XmlRootElement(name="drvexam")
@XmlAccessorType(XmlAccessType.FIELD)
public class CheckresultVO {
	@XmlElement(name="ksdd", required = true)
	private String ksdd;
	
	@XmlElement(name="kskm", required = true)
	private String kskm;
	
	@XmlElement(name="cx", required = true)
	private String cx;
	
	private CheckCar checkcar;

	public String getKsdd() {
		return ksdd;
	}

	public void setKsdd(String ksdd) {
		this.ksdd = ksdd;
	}

	public String getKskm() {
		return kskm;
	}

	public void setKskm(String kskm) {
		this.kskm = kskm;
	}

	public String getCx() {
		return cx;
	}

	public void setCx(String cx) {
		this.cx = cx;
	}

	public CheckCar getCheckcar() {
		return checkcar;
	}

	public void setCheckcar(CheckCar checkcar) {
		this.checkcar = checkcar;
	}
	
	
	
}
