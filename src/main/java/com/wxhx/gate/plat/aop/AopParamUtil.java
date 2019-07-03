package com.wxhx.gate.plat.aop;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.gate.plat.bean.exam.process.ExamEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamItemEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamMark;
import com.wxhx.gate.plat.bean.exam.process.IdentityComparison;
import com.wxhx.gate.plat.bean.exam.process.ItemBegin;
import com.wxhx.gate.plat.bean.exam.process.ProcessBase;
import com.wxhx.gate.plat.bean.exam.process.ProcessImage;
import com.wxhx.gate.plat.bean.exam.process.ReadVideo;
import com.wxhx.gate.plat.bean.exam.process.WirteVideo;
import com.wxhx.gate.plat.dao.entity.CallJyLog;
import com.wxhx.gate.plat.service.bean.WebServiceResult;
import com.wxhx.gate.plat.util.HXCallWebServiceUtil;

/**
 * 参数解析工具类
 * @author geliang
 *
 */
public class AopParamUtil {
	
	private static Logger logger = LoggerFactory.getLogger(AopParamUtil.class);


	/**
	 * 根据参数内容判断当前调用接口
	 * @param param
	 * @return
	 */
	public static CallJyLog getJKInfo(Object param,String res) {
		CallJyLog callJyLog = new CallJyLog();
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
		//id
		callJyLog.setId(HXCoreUtil.getUuId());
		try {
			
			ProcessBase processBase = (ProcessBase) param;
			callJyLog.setSfzmhm(processBase.getSfzmhm());	//身份证号码
			//调用结果
			WebServiceResult serviceResult =  HXCallWebServiceUtil.xmlToBean(res.toString(), WebServiceResult.class);
			if(serviceResult!=null && serviceResult.getHead()!=null && HXCoreUtil.isEquals("1", serviceResult.getHead().getCode())) {
				callJyLog.setDyjg("1");
			}
			else {
				callJyLog.setDyjg("0");
			}
			//结果内容
			callJyLog.setJgnr(res.toString());
			//接口id
			callJyLog.setJkid(jkid);
			//接口描述
			callJyLog.setJkid(jkms);
			//调用日期
			callJyLog.setDyrq(HXCoreUtil.getNowDataStr(new Date(), "yyyyMMdd"));
			//调用时间
			callJyLog.setDysj(new Date());
		} catch (Exception e) {
			callJyLog = null;
			HXLogUtil.error(logger, "执行保存调用精英日志异常{0}", e);
		}
		return callJyLog;
		
	}
	
}
