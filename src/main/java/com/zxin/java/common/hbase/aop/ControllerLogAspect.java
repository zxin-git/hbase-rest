package com.zxin.java.common.hbase.aop;

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
import com.zxin.java.common.hbase.util.HbaseLoggerUtils;
import com.zxin.java.common.hbase.util.JacksonUtils;

@Aspect
@Component
public class ControllerLogAspect {

	private static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);
	
	ThreadLocal<Long> startTime = new ThreadLocal<>();
	
	ThreadLocal<ObjectNode> rootNodeLocal = new ThreadLocal<ObjectNode>(){
		
		@Override
		protected ObjectNode initialValue() {
			return JacksonUtils.FACTORY.objectNode();
		}
	};
	
	@Pointcut("execution(* com.umpay.dps.primary.hbase.controller.*.*(..))")
    public void controller(){}
	
	@Before("controller()")
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
//		HbaseLoggerUtils.RESP_LOGGER.info(JacksonUtils.generator(rootNode));
    }
	
	@AfterReturning(returning = "returnObject", pointcut = "controller()")
    public void doAfterReturning(JoinPoint joinPoint, Object returnObject) throws Throwable {
		Signature signature = joinPoint.getSignature();
		
		ObjectNode rootNode = rootNodeLocal.get();
		rootNode.put("delay", System.currentTimeMillis() - startTime.get() + "");
//		rootNode.put("method", signature.getName());
		rootNode.set("return", JacksonUtils.convertValue(returnObject));
		LoggerFactory.getLogger(signature.getDeclaringType()).info(JacksonUtils.generator(rootNode));
//		HbaseLoggerUtils.RESP_LOGGER.info(JacksonUtils.generator(rootNode));
//		HbaseLoggerUtils.clogger.info(JacksonUtils.JSON_MAPPER.writeValueAsString(returnObject));
		
    }
	
	@AfterThrowing(throwing="e", pointcut = "controller()")
	public void doAfterThrowing(JoinPoint joinPoint, Exception e)throws Throwable {
		Signature signature = joinPoint.getSignature();
		
		ObjectNode rootNode = rootNodeLocal.get();
		rootNode.put("delay", System.currentTimeMillis() - startTime.get() + "");
		
		rootNode.put("exception", e.getClass().getName());
		rootNode.put("message", e.getMessage());
		LoggerFactory.getLogger(signature.getDeclaringType()).warn(JacksonUtils.generator(rootNode));
		
	}

}
