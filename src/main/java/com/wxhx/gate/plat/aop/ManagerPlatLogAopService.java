package com.wxhx.gate.plat.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.wxhx.basic_client.common.BasicClientException;
import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.basic_client.config.thread.HXThreadManager;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.CheckresultVO;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoQueryVO;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoVO;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;
import com.wxhx.gate.plat.controller.vo.VideoCheckQueryVO;
import com.wxhx.gate.plat.dao.CallJyLogMapper;
import com.wxhx.gate.plat.dao.entity.CallJyLog;
import com.wxhx.gate.plat.service.bean.WebServiceResult;
import com.wxhx.gate.plat.util.HXCallWebServiceUtil;


@Aspect
@Configuration
public class ManagerPlatLogAopService {

	// 获取自动记录出入参的日志logger
	private static Logger logger = LoggerFactory.getLogger(ManagerPlatLogAopService.class);
	
	@Autowired
	private HXThreadManager hxThreadManager;

	@Autowired
	private CallJyLogMapper callJyLogMapper;
	
	/**
	 * 
	 * @Title: autoLogPrint @Description: 注解切点 @param: @return: void @throws
	 */
	@Pointcut("@annotation(com.wxhx.gate.plat.aop.annotation.ManagerPlatLogSaveAnnotation)")
	public void autoLogPrint() {
	}
	
	
	@AfterReturning(pointcut = "autoLogPrint()", returning = "res")
	public void printAfterCallMethod(JoinPoint joinPoint, final Object res) {
		// 当前拦截的方法名字
		String fullMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		//方法入参
		Object[] args = joinPoint.getArgs();
		//根据当前入参 判断执行的精英接口
		if(args!=null && args.length>0) {
			final Object param = args[0];
				try {
					hxThreadManager.createPool("save_log").execute(new Runnable() {
						public void run() {
							CallJyLog callJyLog = new CallJyLog();
							
							String jkid = "";
							String jkms = "";
							String dyjg = "";
							if(param instanceof RegisterInfoVo) {
								jkid = "17E05";
								jkms = "考生报道";
								dyjg = getDyjg(res);
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
								}
								else if(!HXCoreUtil.isEmpty(examineeInfoQueryVO.getKscx())) {
									jkid = "17E01";
									jkms = "获取系统检测项";
								}
								else {
									jkid = "17E04";
									jkms = "照片信息";
								}
								dyjg = getDyjg(res);
								
							}
							
							if(param instanceof ExamineeInfoVO) {
								ExamineeInfoVO examineeInfoVO = (ExamineeInfoVO)param;
								if(!HXCoreUtil.isEmpty(examineeInfoVO.getMjzp())) {
									jkid = "17E25";
									jkms = "写入考生门禁照片";
								}
								else {
									jkid = "17E14";
									jkms = "写入视频认证";
								}
								dyjg = getDyjg(res);
							}
							
							if(param instanceof String) {
								jkid = "17E07";
								jkms = "获取当前可用车牌信息";
								dyjg = getDyjg(res);
							}
							
							if(param instanceof CheckresultVO) {
								jkid = "17E02";
								jkms = "写入检测结果";
								dyjg = getDyjg(res);
							}
							
							if(param instanceof VideoCheckQueryVO) {
								jkid = "17E15";
								jkms = "读取视频认证结果";
								dyjg = getDyjg(res);
							}
							
							
							//id
							callJyLog.setId(HXCoreUtil.getUuId());
							//调用结果
							callJyLog.setDyjg(dyjg);
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
							
							if(callJyLog!=null){
								if(callJyLogMapper.insertSelective(callJyLog)<1) {
									HXLogUtil.error(logger, "保存日志调用日志错误{0}", callJyLog);
								}
								}
								
							}
						});
				} catch (BasicClientException e) {
					HXLogUtil.error(logger, "执行保存日志调用日志线程发送错误{0}", e);
				}
				}
		HXLogUtil.debug(logger, "执行完成方法：{0},方法返回结果是:{1}", fullMethod, HXCoreUtil.getJsonString(res));
	}
	
	//获取调用结果
	public String getDyjg(Object res) {
		String dyjg = "0";
		if(res instanceof WebServiceResult) {
			WebServiceResult serviceResult = (WebServiceResult)res;
			if(serviceResult!=null && serviceResult.getHead()!=null && HXCoreUtil.isEquals("1", serviceResult.getHead().getCode())) {
				dyjg = "1";
			}
			else {
				dyjg = "0";
			}
		}else {
			RegisterResponse registerResponse = (RegisterResponse)res;
			if(registerResponse!=null && registerResponse.getCode() != null && HXCoreUtil.isEquals("1",registerResponse.getCode())) {
				dyjg = "1";
			}else {
				dyjg = "0";
			}
		}
		
		return dyjg;
		
	}
	
	
	
//	@Before("autoLogPrint()")
//	public void beforeCall(JoinPoint joinPoint) {
//		// 当前拦截的方法名字
//		String fullMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
//		// 方法入参
//		Object[] args = joinPoint.getArgs();
//		if (args != null && args.length > 0) {
//			ProcessBase processBase = (ProcessBase) args[0];
//		}
//		System.out.println(fullMethod);
//	}
	 
	
	
}
