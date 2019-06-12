package com.wxhx.gate.plat.bean.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wxhx.basic_client.common.HXCoreUtil;

@XmlRootElement(name="drvexam")
@XmlAccessorType(XmlAccessType.FIELD)
public class VidoeCheckResponse {
	@XmlElement(name="lsh")
	private String lsh;	//流水号
	
	@XmlElement(name="kskm")
	private String kskm;	//考试科目
	
	@XmlElement(name="zkzmbh")
	private String zkzmbh;	//准考证明编号
	
	@XmlElement(name="sfzmhm")
	private String sfzmhm;	//身份证明号码
	
	@XmlElement(name="xm")
	private String xm;	//姓名
	
	@XmlElement(name="ksdd")
	private String ksdd;	//考试地点
	
	@XmlElement(name="rzjg")
	private String rzjg; 	//认证结果
	
	@XmlElement(name="bz")
	private String bz;	//备注

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

	public String getSfzmhm() {
		return sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
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
	
	@Override
	public String toString() {
		return HXCoreUtil.getJsonString(this);
	}
}
