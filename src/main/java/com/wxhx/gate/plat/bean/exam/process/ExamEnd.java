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
public class ExamEnd extends ProcessBase{
	
	private String zp;
	
	private String jssj;
	
	private String kscj;
	
	public String getZp() {
		return zp;
	}

	public void setZp(String zp) {
		this.zp = zp;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getKscj() {
		return kscj;
	}

	public void setKscj(String kscj) {
		this.kscj = kscj;
	}
	
	@Override
	public String toString() {
		return HXCoreUtil.getJsonString(this);
	}
	
	
	
}
