package com.zxin.java.common.hbase.service;

import java.util.List;
import java.util.Map;

import com.zxin.java.common.hbase.model.CellModel;

/**
 * 转换类型
 * 
 * @author zxin
 */
public interface IHbaseService {
	
	/**
	 * 精确匹配rowkey对应列的cell值
	 * 
	 * @param tableName
	 * @param rowName
	 * @param column
	 * @return
	 */
	String fetch(String tableName, String rowName, String column);
	
	/**
	 * 获取rowkey的相关列的cell
	 * 可以限制列簇，或所有列
	 * 
	 * @param tableName
	 * @param rowName
	 * @param family 列簇
	 * @return map -> key:column, value:cell 
	 */
	Map<String, String> get(String tableName, String rowName, String family);
	
	/**
	 * 全扫描, rowkey范围
	 * column可以为空
	 * 
	 * @param tableName
	 * @param startRow
	 * @param endRow
	 * @param column	可以为空
	 * @return map -> key:rowkey, value:map -> key:column, value:cell
	 */
	Map<String, Map<String, String>> scan(String tableName, String startRow, String endRow, String column);
	
	/**
	 * 
	 * 确定列扫描，rowkey范围的值
	 *  
	 * @param tableName
	 * @param startRow
	 * @param endRow
	 * @param column  不为空,且包含qualifier
	 * @return map -> key:rowkey, value:cell
	 */
	Map<String, String> scanKnownCol(String tableName, String startRow, String endRow, String column);
	
	
}
