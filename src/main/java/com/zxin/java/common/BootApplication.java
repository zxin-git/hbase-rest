/**
 * 
 */
package com.zxin.java.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序入口
 * @author zxin
 */
@SpringBootApplication
public class BootApplication {

	private static final Logger logger = LoggerFactory.getLogger(BootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
	
}
