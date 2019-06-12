package com.wxhx.gate.plat.controller.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 读取视频认证结果VO
 * @author coyi
 *
 */
@XmlRootElement(name="QueryCondition")
@XmlAccessorType(XmlAccessType.FIELD)
public class VideoCheckQueryVO {
	@XmlElement(name="lsh", required = true)
	private String lsh;	//流水号
	
	@XmlElement(name="kskm", required = true)
	private String kskm;	//考试科目
	
	@XmlElement(name="kchp", required = true)
	private String kchp;	//考试车牌
	
	@XmlElement(name="ksdd", required = true)
	private String ksdd;	//考试地点

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

	public String getKchp() {
		return kchp;
	}

	public void setKchp(String kchp) {
		this.kchp = kchp;
	}

	public String getKsdd() {
		return ksdd;
	}

	public void setKsdd(String ksdd) {
		this.ksdd = ksdd;
	}
	
}
