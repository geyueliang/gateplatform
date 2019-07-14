package com.wxhx.gate.plat.bean.exam.process;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.wxhx.basic_client.common.HXCoreUtil;

/**
 * 身份验证信息
 * @author geliang
 *
 */
@XmlRootElement(name="drvexam")
@XmlAccessorType(XmlAccessType.FIELD)
public class IdentityComparison extends ProcessBase{
	
	private String ksxtbh;
	
	private String ksysfzmhm;
	
	private String zp;
	
	private String kssj;	//开始时间 yyyy-MM-ddhh24:mi:ss
	
	private String addressType;	//发起项目结束地点类型 1 结束地点 2 项目开始地点

	public String getKsxtbh() {
		return ksxtbh;
	}

	public void setKsxtbh(String ksxtbh) {
		this.ksxtbh = ksxtbh;
	}

	

	public String getKsysfzmhm() {
		return ksysfzmhm;
	}

	public void setKsysfzmhm(String ksysfzmhm) {
		this.ksysfzmhm = ksysfzmhm;
	}

	public String getZp() {
		return zp;
	}

	public void setZp(String zp) {
		this.zp = zp;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	
	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	@Override
	public String toString() {
		return HXCoreUtil.getJsonString(this);
	}
}
