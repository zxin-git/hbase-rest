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
 * 
 * @author zxin
 */
@RestController
@RequestMapping("/getter")
public class GetController {

	private static final Logger logger = LoggerFactory.getLogger(GetController.class);
	
	@Autowired
	private IHbaseService hbaseService;

	
	/**
	 * rowkey精确列的值
	 * @param table
	 * @param rowKey
	 * @param column
	 * @return
	 */
	@GetMapping("/fetch/tables/{table}/rows/{rowKey}/columns/{column}")
	public String fetch(@PathVariable String table, @PathVariable String rowKey, @PathVariable String column){
		return hbaseService.fetch(table, rowKey, column);
	}
	
	/**
	 * rowkey的所有列值对
	 * 可限制列簇 family
	 * 
	 * @param table
	 * @param rowKey
	 * @return map -> key:column , value:cellValue
	 */
	@GetMapping("/get/tables/{table}/rows/{rowKey}")
	public Map<String, String> get(@PathVariable String table, @PathVariable String rowKey, 
			@RequestParam(value = "family", required = false) String family){
		return hbaseService.get(table, rowKey, family);
	}
}
