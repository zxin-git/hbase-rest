package com.zxin.java.common.hbase.service;

import java.util.List;

/**
 * hbase表结构查询接口
 * 
 * @author zxin
 */
public interface IAdminService {

//==============general=================================	
	String version();
	
	String status();
	
	String processlist();
	
//==============ddl=================================	
	String describe(String tableName);
	
	boolean exists(String tableName);
	
	/**
	 * 查询所有表
	 * @return
	 */
	List<String> list();
	
	List<String> list_regions(String tableName);
	
	String locate_region(String tableName, String rowKey);
	

//===============namespace====================================
	/**
	 * 
	 * @return namespaces
	 */
	List<String> list_namespace();
	
	/**
	 * 
	 * @param namespace
	 * @return tables
	 */
	List<String> list_namespace_tables(String namespace);
	
//===============dml====================================	
//	long count();
	
	
	
	
}
