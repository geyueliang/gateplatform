package com.wxhx.gate.plat.bean.exam.process;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.wxhx.basic_client.common.HXCoreUtil;

/**
 * 读取视频认证结果
 * @author coyi
 *
 */
@XmlRootElement(name="QueryCondition")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReadVideo extends ProcessBase{
	private String kchp;

	public String getKchp() {
		return kchp;
	}

	public void setKchp(String kchp) {
		this.kchp = kchp;
	}
	
	@Override
	public String toString() {
		return HXCoreUtil.getJsonString(this);
	}
}
