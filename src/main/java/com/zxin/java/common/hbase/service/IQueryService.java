package com.zxin.java.common.hbase.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zxin.java.common.hbase.model.CellModel;
import com.zxin.java.common.hbase.model.Column;

/**
 * hbase表数据查询接口
 * 未接入cell的version概念
 * 
 * @author zxin
 */
public interface IQueryService {
	

	/**
	 * 匹配具体的cell
	 * 未匹配返回null
	 * @param tableName 表名
	 * @param rowName	主键值
	 * @param familyName 列簇
	 * @param qualifier	列名
	 * @return
	 */
	CellModel fetch(String tableName, String rowName, String family, String qualifier);
	
	/**
	 * 获取rowkey下所有cell的集合
	 * 无匹配返回空集合
	 * @param tableName 表名
	 * @param rowName	主键
	 * @param family	列簇，可以为空
	 * @return
	 */
	List<CellModel> get(String tableName, String rowName, String family);
	
	
	/**
	 * 指定rowkey范围扫描 （必须输入扫描范围）
	 * 左闭右开区间
	 * 列名可以确定或为null
	 * 最外层list是区别了rowkey
	 * 内层list是区别了column
	 * 
	 * @param tableName
	 * @param family
	 * @param qualifier
	 * @param startRow 包含
	 * @param endRow 不包含
	 * @return
	 */
	List<List<CellModel>> scan(String tableName, String startRow, String endRow, String family, String qualifier);
	
	
	
	
}
