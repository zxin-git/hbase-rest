package com.zxin.java.common.hbase.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * 
 * 
 * @author zxin
 */
public class JacksonUtils {

	/**
	 * json 转换器
	 */
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	
	/**
	 * xml 转换器
	 */
	public static final XmlMapper XML_MAPPER = new XmlMapper();
	
	
	
	public static final JsonNodeFactory FACTORY = new JsonNodeFactory(false);
	

	
	private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);
	
	/**
	 * 将树节点 转成 json
	 * @return
	 */
	public static String generator(JsonNode rootNode){
		StringWriter writer = new StringWriter();
		try {
			JsonFactory jsonFactory = new JsonFactory(); 
			JsonGenerator generator = jsonFactory.createGenerator(writer);
			JacksonUtils.JSON_MAPPER.writeTree(generator, rootNode);
		} catch (IOException e) {
			logger.warn("", e);
		}
		return writer.toString();
	}
	
	public static String json(Map<String, String> map){
		ObjectNode rootNode = FACTORY.objectNode();
		map.entrySet().forEach((entry)->{
			rootNode.put(entry.getKey(), entry.getValue());
		});
		return generator(rootNode);
	}
	
	
	public static String json(String... array){
		ObjectNode rootNode = FACTORY.objectNode();
		for (int i = 0; i < array.length; i++) {
			rootNode.put(i+"", array[i]);
		}
		return generator(rootNode);
	}
	
	
	public static JsonNode convertValue(Object object){
		return JSON_MAPPER.convertValue(object, JsonNode.class);
	}
	
}
