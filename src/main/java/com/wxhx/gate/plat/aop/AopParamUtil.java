package com.wxhx.gate.plat.aop;

import java.util.HashMap;
import java.util.Map;

import com.wxhx.gate.plat.bean.exam.process.ExamEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamItemEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamMark;
import com.wxhx.gate.plat.bean.exam.process.IdentityComparison;
import com.wxhx.gate.plat.bean.exam.process.ItemBegin;
import com.wxhx.gate.plat.bean.exam.process.ProcessImage;
import com.wxhx.gate.plat.bean.exam.process.ReadVideo;
import com.wxhx.gate.plat.bean.exam.process.WirteVideo;

/**
 * 参数解析工具类
 * @author geliang
 *
 */
public class AopParamUtil {

	/**
	 * 根据参数内容判断当前调用接口
	 * @param param
	 * @return
	 */
	public static Map<String, String> getJKInfo(Object param) {
		Map<String, String> result = new HashMap<String, String>();
		String jkid = "";
		String jkms = "";
		if(param instanceof IdentityComparison) {
			jkid = "17C51";
			jkms = "身份认证";

		}
		if(param instanceof ItemBegin) {
			jkid = "17C52";
			jkms = "项目开始";

		}
		if(param instanceof ExamMark) {
			jkid = "17C53";
			jkms = "项目扣分";
		}		
		if(param instanceof ProcessImage) {
			jkid = "17C54";
			jkms = "上传 图片";
		}
		if(param instanceof ExamItemEnd) {
			jkid = "17C55";
			jkms = "项目结束";

		}
		if(param instanceof ExamEnd) {
			jkid = "17C56";
			jkms = "科目结束";

		}
		if(param instanceof WirteVideo) {
			jkid = "17E14";
			jkms = "发起视频认证";

		}
		if(param instanceof ReadVideo) {
			jkid = "17E15";
			jkms = "读取视频认证结果";
		}
		result.put("jkid", jkid);
		result.put("jkms", jkms);
		return result;
	}
	
}
