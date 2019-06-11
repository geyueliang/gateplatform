package com.wxhx.gate.plat.bean.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wxhx.basic_client.common.HXCoreUtil;

@XmlRootElement(name="drvexam")
@XmlAccessorType(XmlAccessType.FIELD)
public class CheckResponse {
	
		@XmlElement(name="checkresult")
		private String checkresult;

		public String getCheckresult() {
			return checkresult;
		}

		public void setCheckresult(String checkresult) {
			this.checkresult = checkresult;
		}

		@Override
		public String toString() {
			return HXCoreUtil.getJsonString(this);
		}
		
}
