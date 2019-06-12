package com.wxhx.gate.plat.bean.exam.process;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.wxhx.basic_client.common.HXCoreUtil;

/**
 * 视频认证发起写入
 * @author coyi
 *
 */
@XmlRootElement(name="drvexam")
@XmlAccessorType(XmlAccessType.FIELD)
public class WirteVideo extends ProcessBase{
	private String kchp;
	private String ksxtbh;
	
	public String getKchp() {
		return kchp;
	}

	public void setKchp(String kchp) {
		this.kchp = kchp;
	}

	public String getKsxtbh() {
		return ksxtbh;
	}

	public void setKsxtbh(String ksxtbh) {
		this.ksxtbh = ksxtbh;
	}

	@Override
	public String toString() {
		return HXCoreUtil.getJsonString(this);
	}
}
