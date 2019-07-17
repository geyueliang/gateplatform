package com.wxhx.gate.plat.bean.exam.process;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.wxhx.basic_client.common.HXCoreUtil;

/**
 * 
 *  考试扣分
 * @author geliang
 *
 */
@XmlRootElement(name="drvexam")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExamMark extends ProcessBase{
	
	private String ksxm;
	
	private String kfxm;
	
	private String kfsj;
	
	private String kcsb;

	public String getKsxm() {
		return ksxm;
	}

	public void setKsxm(String ksxm) {
		this.ksxm = ksxm;
	}

	public String getKfxm() {
		return kfxm;
	}

	public void setKfxm(String kfxm) {
		this.kfxm = kfxm;
	}

	
	public String getKcsb() {
		return kcsb;
	}

	public void setKcsb(String kcsb) {
		this.kcsb = kcsb;
	}

	public String getKfsj() {
		return kfsj;
	}

	public void setKfsj(String kfsj) {
		this.kfsj = kfsj;
	}

	@Override
	public String toString() {
		return HXCoreUtil.getJsonString(this);
	}
}
