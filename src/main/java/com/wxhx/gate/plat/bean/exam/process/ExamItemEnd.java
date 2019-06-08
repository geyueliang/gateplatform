package com.wxhx.gate.plat.bean.exam.process;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.wxhx.basic_client.common.HXCoreUtil;

/**
 * 考试项目结束
 * @author geliang
 *
 */
@XmlRootElement(name="drvexam")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExamItemEnd  extends ProcessBase{
	
	private String sbxh;	//设备序列号
	
	private String jssj;	//结束时间 时分秒
	
	private String czlx;	//操作类型
	
	private String ksxm;	//考试项目

	public String getSbxh() {
		return sbxh;
	}

	public void setSbxh(String sbxh) {
		this.sbxh = sbxh;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getCzlx() {
		return czlx;
	}

	public void setCzlx(String czlx) {
		this.czlx = czlx;
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
