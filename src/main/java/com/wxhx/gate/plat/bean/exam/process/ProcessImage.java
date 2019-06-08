package com.wxhx.gate.plat.bean.exam.process;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.wxhx.basic_client.common.HXCoreUtil;

/**
 * 过程图片
 * @author geliang
 *
 */
@XmlRootElement(name="drvexam")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcessImage extends ProcessBase {

	private String ksxm;	//考试项目
	
	private String zpsj;	//时分秒
	
	private String zp;
	
	private String cs;	//车速


	public String getZpsj() {
		return zpsj;
	}

	public void setZpsj(String zpsj) {
		this.zpsj = zpsj;
	}

	public String getZp() {
		return zp;
	}

	public void setZp(String zp) {
		this.zp = zp;
	}

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}
	
	
	
	public String getKsxm() {
		return ksxm;
	}

	public void setKsxm(String ksxm) {
		this.ksxm = ksxm;
	}

	@Override
	public String toString() {
		return HXCoreUtil.getJsonString(this);
	}
}
