package com.zxin.java.common.hbase.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HbaseLoggerUtils {

	private static final Logger logger = LoggerFactory.getLogger(HbaseLoggerUtils.class);
	
	public static final Logger REQ_LOGGER = LoggerFactory.getLogger("REQUEST_RECODE");
	
	public static final Logger RESP_LOGGER = LoggerFactory.getLogger("response_record");
	
}
