package com.wxhx.gate.plat.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.gate.plat.bean.exam.process.ProcessBase;


@Aspect
@Configuration
public class ExamProcessLogAopService {

	// 获取自动记录出入参的日志logger
	private static Logger logger = LoggerFactory.getLogger(ExamProcessLogAopService.class);

	/**
	 * 
	 * @Title: autoLogPrint @Description: 注解切点 @param: @return: void @throws
	 */
	@Pointcut("@annotation(com.wxhx.gate.plat.aop.annotation.ExamProcessLogSaveAnnotation)")
	public void autoLogPrint() {
	}
	
	
	@AfterReturning(pointcut = "autoLogPrint()", returning = "res")
	public void printAfterCallMethod(JoinPoint joinPoint, Object res) {
		// 当前拦截的方法名字
		String fullMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		//方法入参
		Object[] args = joinPoint.getArgs();
		if(args!=null && args.length>0) {
			ProcessBase processBase  = (ProcessBase) args[0];
		}
		
		
		HXLogUtil.debug(logger, "执行完成方法：{0},方法返回结果是:{1}", fullMethod, HXCoreUtil.getJsonString(res));
	}
	
	
	@Before("autoLogPrint()")
	public void beforeCall(JoinPoint joinPoint) {
		// 当前拦截的方法名字
		String fullMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		//方法入参
		Object[] args = joinPoint.getArgs();
		if(args!=null && args.length>0) {
			ProcessBase processBase  = (ProcessBase) args[0];
		}
		System.out.println(fullMethod);
	}
}
