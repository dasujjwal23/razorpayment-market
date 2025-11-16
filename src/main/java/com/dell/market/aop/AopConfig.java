package com.dell.market.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopConfig {
	
	Logger log=LoggerFactory.getLogger(AopConfig.class);

	@Around("@annotation(com.dell.market.service.TaskExecutionTime)")
	public Object getResponseTime(ProceedingJoinPoint pjp)
	{
		Object object=null;
		try {
			long start=System.currentTimeMillis();
			String method=pjp.getSignature().getName();
			String classname=pjp.getTarget().getClass().toString();
			object=pjp.proceed();
			long end=System.currentTimeMillis();
			log.info(classname+"."+method+":: Response time is observed:: "+(end-start)+" ms");
			return object;
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
}
