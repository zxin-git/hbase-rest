package com.zxin.java.common.hbase.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.util.Bytes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

/**
 * 
 * 拼装cell返回类
 * 
 * @author zxin
 */
@Data
@JacksonXmlRootElement(localName = "Cell")
@XmlAccessorType(XmlAccessType.FIELD)
public class CellModel {

	@XmlAttribute
	@JacksonXmlProperty
	private String column;
	
	@JsonIgnore
	private long timestamp;

	@XmlValue
	private String value;
	
	private String rowKey;



}
