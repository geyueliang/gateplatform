package com.wxhx.gate.plat.bean.exam.process;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 项目开始
 */
@XmlRootElement(name="drvexam")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemBegin extends ProcessBase{

	private String ksxm;	//考试项目
	
	private String sbxh;	//设备序号
	
	private String kssj;	//开始时间

	public String getKsxm() {
		return ksxm;
	}

	public void setKsxm(String ksxm) {
		this.ksxm = ksxm;
	}

	public String getSbxh() {
		return sbxh;
	}

	public void setSbxh(String sbxh) {
		this.sbxh = sbxh;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	
	
	
}
