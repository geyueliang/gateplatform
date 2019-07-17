package com.wxhx.gate.plat.bean.exam.process;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.wxhx.basic_client.common.HXCoreUtil;

/**
 * 考试暂停请求
 * @author geliang
 *
 */
@XmlRootElement(name="drvexam")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExamStop extends ProcessBase{

	private String ksrq;	//考试日期
	
	private String kcxh;	//考场序号
	
	private String qxlx;	//取消类型 1暂停考试；2设备异常重考；3成绩不合格；4：考试恢复
	
	private String ztyy;	//取消原因

	public String getKsrq() {
		return ksrq;
	}

	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}

	public String getKcxh() {
		return kcxh;
	}

	public void setKcxh(String kcxh) {
		this.kcxh = kcxh;
	}

	public String getQxlx() {
		return qxlx;
	}

	public void setQxlx(String qxlx) {
		this.qxlx = qxlx;
	}

	public String getZtyy() {
		return ztyy;
	}

	public void setZtyy(String ztyy) {
		this.ztyy = ztyy;
	}

	@Override
	public String toString() {
		return HXCoreUtil.getJsonString(this);
	}
	
	
	
}
