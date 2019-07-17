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
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.CheckresultVO;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoQueryVO;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoVO;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;
import com.wxhx.gate.plat.controller.vo.VideoCheckQueryVO;
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
	public static CallJyLog getJKInfo(Object param,WebServiceResult serviceResult) {
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
			callJyLog.setDyjg(serviceResult.getHead().getCode());
			//结果内容
			callJyLog.setJgnr(serviceResult.getHead().getMessage());
			//接口id
			callJyLog.setJkid(jkid);
			//接口描述
			callJyLog.setJkms(jkms);
			//调用日期
			callJyLog.setDyrq(HXCoreUtil.getNowDataStr(new Date(), "yyyyMMdd"));
			//调用时间
			callJyLog.setDysj(new Date());
			//调用类型
			callJyLog.setLogType("1");	//考试过程
		} catch (Exception e) {
			callJyLog = null;
			HXLogUtil.error(logger, "执行保存调用精英日志异常{0}", e);
		}
		return callJyLog;
		
	}
	
	public static CallJyLog getManagerPlatJKInfo(Object param,Object res) {
		CallJyLog callJyLog = new CallJyLog();
		
		String jkid = "";
		String jkms = "";
		String dyjg = "";
		String sfzmhm = "";
		String jgnr = "";
		if(param instanceof RegisterInfoVo) {
			RegisterInfoVo registerInfoVo = (RegisterInfoVo)param;
			jkid = "17E05";
			jkms = "考生报道";
			sfzmhm = registerInfoVo.getSfzmhm();
			dyjg = getDyjg(res);
			jgnr = getJgnr(res);
		}
		
		if(param instanceof ExamineeInfoQueryVO) {
			ExamineeInfoQueryVO examineeInfoQueryVO = (ExamineeInfoQueryVO)param;
			if(HXCoreUtil.isEmpty(examineeInfoQueryVO.getSfzmhm()) && HXCoreUtil.isEmpty(examineeInfoQueryVO.getKscx()) && !HXCoreUtil.isEmpty(examineeInfoQueryVO.getKskm())) {
				jkid = "17E11";
				jkms = "排考信息";
			}
			else if(!HXCoreUtil.isEmpty(examineeInfoQueryVO.getSfzmhm()) && HXCoreUtil.isEmpty(examineeInfoQueryVO.getKscx()) && !HXCoreUtil.isEmpty(examineeInfoQueryVO.getKskm())) {
				jkid = "17E13";
				jkms = "预约信息";
				sfzmhm = examineeInfoQueryVO.getSfzmhm();
			}
			else if(!HXCoreUtil.isEmpty(examineeInfoQueryVO.getKscx())) {
				jkid = "17E01";
				jkms = "获取系统检测项";
			}
			else {
				jkid = "17E04";
				jkms = "照片信息";
				sfzmhm = examineeInfoQueryVO.getSfzmhm();
			}
			dyjg = getDyjg(res);
			jgnr = getJgnr(res);
			
		}
		
		if(param instanceof ExamineeInfoVO) {
			ExamineeInfoVO examineeInfoVO = (ExamineeInfoVO)param;
			if(!HXCoreUtil.isEmpty(examineeInfoVO.getMjzp())) {
				jkid = "17E25";
				jkms = "写入考生门禁照片";
				sfzmhm = examineeInfoVO.getSfzmhm();
			}
			else {
				jkid = "17E14";
				jkms = "写入视频认证";
				sfzmhm = examineeInfoVO.getSfzmhm();
			}
			dyjg = getDyjg(res);
			jgnr = getJgnr(res);
		}
		
		if(param instanceof String) {
			jkid = "17E07";
			jkms = "获取当前可用车牌信息";
			dyjg = getDyjg(res);
			jgnr = getJgnr(res);
		}
		
		if(param instanceof CheckresultVO) {
			jkid = "17E02";
			jkms = "写入检测结果";
			dyjg = getDyjg(res);
			jgnr = getJgnr(res);
		}
		
		if(param instanceof VideoCheckQueryVO) {
			jkid = "17E15";
			jkms = "读取视频认证结果";
			dyjg = getDyjg(res);
			jgnr = getJgnr(res);
		}
		//id
		callJyLog.setId(HXCoreUtil.getUuId());
		//身份证号码
		callJyLog.setSfzmhm(sfzmhm);
		//调用结果
		callJyLog.setDyjg(dyjg);
		//结果内容
		callJyLog.setJgnr(jgnr);
		//接口id
		callJyLog.setJkid(jkid);
		//接口描述
		callJyLog.setJkms(jkms);
		//调用日期
		callJyLog.setDyrq(HXCoreUtil.getNowDataStr(new Date(), "yyyyMMdd"));
		//调用时间
		callJyLog.setDysj(new Date());
		callJyLog.setLogType("0"); //一般过程
		return callJyLog;
	}
	
	private static String getJgnr(Object res) {
		String jgnr = "";
		if(res instanceof WebServiceResult) {
			WebServiceResult serviceResult = (WebServiceResult)res;
			jgnr = serviceResult.getHead().getMessage();
		}else {
			RegisterResponse registerResponse = (RegisterResponse)res;
			jgnr = registerResponse.getMessage();
		}
		return jgnr;
	}

	//获取调用结果
	private static String getDyjg(Object res) {
		String dyjg = "0";
		if(res instanceof WebServiceResult) {
			WebServiceResult serviceResult = (WebServiceResult)res;
			dyjg = serviceResult.getHead().getCode();
		}else {
			RegisterResponse registerResponse = (RegisterResponse)res;
			dyjg = registerResponse.getCode();
		}
		return dyjg;
	}
	
}
