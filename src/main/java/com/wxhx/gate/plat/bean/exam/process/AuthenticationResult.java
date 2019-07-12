package com.wxhx.gate.plat.bean.exam.process;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 视频认证结果返回
 * @author geliang
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="drvexam")
public class AuthenticationResult {

	private String lsh;
	
	private String kskm;
	
	private String zkzmbh;
	
	private String sfzmhm;
	
	private String xm;
	
	private String ksdd;
	
	private String rzjg;
	
	private String bz;

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getKskm() {
		return kskm;
	}

	public void setKskm(String kskm) {
		this.kskm = kskm;
	}

	public String getZkzmbh() {
		return zkzmbh;
	}

	public void setZkzmbh(String zkzmbh) {
		this.zkzmbh = zkzmbh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getKsdd() {
		return ksdd;
	}

	public void setKsdd(String ksdd) {
		this.ksdd = ksdd;
	}

	public String getRzjg() {
		return rzjg;
	}

	public void setRzjg(String rzjg) {
		this.rzjg = rzjg;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSfzmhm() {
		return sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}
	
	
}
