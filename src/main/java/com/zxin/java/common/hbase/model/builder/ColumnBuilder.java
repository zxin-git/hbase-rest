package com.zxin.java.common.hbase.model.builder;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.java.common.hbase.model.Column;
import com.zxin.java.common.hbase.util.HbaseContant;

/**
 * 构建column类
 * 
 * @author zxin
 */
public class ColumnBuilder {

	private static final Logger logger = LoggerFactory.getLogger(ColumnBuilder.class);

	public static Column build(String family, String qualifier){
		Column column = new Column();
		column.setFamily(family);
		column.setQualifier(qualifier);
		return column;
	}
	
	
	/**
	 * 解析column为列簇和列
	 * column 为null  ->  family:null, qualifier:null
	 * @param column 
	 * @return
	 */
	public static Column build(String column){
		String family = null;
		String qualifier = null;
		
		if(StringUtils.isNotEmpty(column)){
			String[] array = column.split(HbaseContant.COLUMN_FAMILY_DELIMITER);
			if(array.length > 2){
				throw new IllegalArgumentException("column包含多个':'");
			}
			
			family = array[0];
			
			if(array.length == 2){
				qualifier = array[1];
			}
		}
		return build(family, qualifier);
	}
	
}
