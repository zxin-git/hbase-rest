package com.zxin.java.common.hbase.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 * 
 * @author zxin
 */
@ControllerAdvice
public class CommonExceptionHandler {
	
	public static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);
 
//    /**
//     * 拦截Exception类的异常
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public void exceptionHandler(Exception e){
//    	logger.warn("", e);
////        return "error"; 
//    }
//    
    
}