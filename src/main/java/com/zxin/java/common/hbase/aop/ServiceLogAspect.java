package com.zxin.java.common.hbase.aop;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zxin.java.common.hbase.util.JacksonUtils;

/**
 * 业务层日志记录
 * 
 * @author zxin
 */
@Aspect
@Component
public class ServiceLogAspect {

	private static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

	ThreadLocal<Long> startTime = new ThreadLocal<>();
	
	ThreadLocal<ObjectNode> rootNodeLocal = new ThreadLocal<ObjectNode>(){
		
		@Override
		protected ObjectNode initialValue() {
			return JacksonUtils.FACTORY.objectNode();
		}
	};
	
	@Pointcut("execution(* com.umpay.dps.primary.hbase.service.*.*(..))")
    public void service(){}
	
	
	@Before("service()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
		Signature signature = joinPoint.getSignature();
		startTime.set(System.currentTimeMillis());
		
		ObjectNode rootNode = rootNodeLocal.get();
//		rootNode.put("args", StringUtils.join(joinPoint.getArgs(), ","));
		rootNode.set("args", JacksonUtils.convertValue(joinPoint.getArgs()));
		rootNode.put("class", signature.getDeclaringTypeName());
		rootNode.put("method", signature.getName());
		rootNode.put("thread", Thread.currentThread().getName());
		rootNode.put("timestamp", startTime.get());
//		LoggerFactory.getLogger(signature.getDeclaringType()).info(JacksonUtils.generator(rootNode));
    }
	
	@AfterReturning(returning = "returnObject", pointcut = "service()")
    public void doAfterReturning(JoinPoint joinPoint, Object returnObject) throws Throwable {
		Signature signature = joinPoint.getSignature();
		
		ObjectNode rootNode = rootNodeLocal.get();
		rootNode.put("delay", System.currentTimeMillis() - startTime.get() + "");
		rootNode.set("return", JacksonUtils.convertValue(returnObject));
		LoggerFactory.getLogger(signature.getDeclaringType()).info(JacksonUtils.generator(rootNode));

		
    }
	
	@AfterThrowing(throwing="e", pointcut = "service()")
	public void doAfterThrowing(JoinPoint joinPoint, Exception e)throws Throwable {
		Signature signature = joinPoint.getSignature();
		
		ObjectNode rootNode = rootNodeLocal.get();
		rootNode.put("delay", System.currentTimeMillis() - startTime.get() + "");
		
		rootNode.put("Exception", e.getClass().getName());
		rootNode.put("message", e.getMessage());
		LoggerFactory.getLogger(signature.getDeclaringType()).warn(JacksonUtils.generator(rootNode));
		
	}
	
}
