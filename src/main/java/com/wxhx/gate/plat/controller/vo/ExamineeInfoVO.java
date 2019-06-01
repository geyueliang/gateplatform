package com.wxhx.gate.plat.controller.vo;

/**
 * 考生信息入参
 * @author coyi
 *
 */
public class ExamineeInfoVO {
	
	private String kskm;	//考试科目
	
	private String kscx;	//考试车型
	
	private String fhjls;	//返回记录数
	
	private String ksdd;	//考试地点
	
	private String kchp;	//考试车牌

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
	
}
