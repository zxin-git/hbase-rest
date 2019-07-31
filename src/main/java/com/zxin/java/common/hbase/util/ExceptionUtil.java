package com.zxin.java.common.hbase.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ExceptionUtil {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);

	
	public static ObjectNode node(Exception e){
		ObjectNode node = JacksonUtils.FACTORY.objectNode();
		node.put("class", e.getClass().getName());
		node.put("message", e.getMessage());
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		node.put("stack", sw.toString());
		return node;
	}
}
