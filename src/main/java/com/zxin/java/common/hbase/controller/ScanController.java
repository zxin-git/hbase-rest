package com.zxin.java.common.hbase.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zxin.java.common.hbase.service.IHbaseService;

/**
 * 
 * hbase rest加入了batch的概念
 * 
 * @author zxin
 */
@RestController
@RequestMapping("/scanner")
public class ScanController {

	private static final Logger logger = LoggerFactory.getLogger(ScanController.class);
	
	@Autowired
	private IHbaseService hbaseService;
	
	
	/**
	 * 精确列扫描
	 * @param table
	 * @param column  = family : qualifier 必输
	 * @param start	开始rowkey 必输 （包含）
	 * @param end	截止rowkey 必输 （不包含）
	 * @return map -> key:rowkey,  value:cellValue 
	 */
	@GetMapping("/fetch/tables/{table}")
	public Map<String, String> scanKnownCol(@PathVariable String table, 
			@RequestParam String start, @RequestParam String end, @RequestParam String column){
		return hbaseService.scanKnownCol(table, start, end, column);
	}
	
	/**
	 * 全列扫描
	 * @param table
	 * @param column  = family : qualifier
	 * @param start	开始rowkey 必输 （包含）
	 * @param end	截止rowkey 必输 （不包含）
	 * @return map -> key:rowkey, 	value:map -> (key,column -> value:cellValue 
	 */
	@GetMapping("/scan/tables/{table}")
	public Map<String, Map<String, String>> scan(@PathVariable String table, 
			@RequestParam String start, @RequestParam String end, @RequestParam(required = false) String column){
		return hbaseService.scan(table, start, end, column);
	}
	
}
