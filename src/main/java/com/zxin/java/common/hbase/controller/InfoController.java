package com.zxin.java.common.hbase.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxin.java.common.hbase.service.IAdminService;

/**
 * hbase基础结构数据
 * 
 * @author zxin
 */
@RestController
@RequestMapping("/info")
public class InfoController {

	private static final Logger logger = LoggerFactory.getLogger(InfoController.class);
	
	@Autowired
	private IAdminService adminService;
	
	@GetMapping("/version")
	public String version(){
		return adminService.version();
	}
	
	@GetMapping("/status")
	public String status(){
		return adminService.status();
	}
	
	
//	processlist
	
	@GetMapping("/desc/tables/{table}")
	public String describe(@PathVariable String table){
		return adminService.describe(table);
	}

	@GetMapping("/exists/tables/{table}")
	public boolean exists(@PathVariable String table){
		return adminService.exists(table);
	}

	
	
	@GetMapping("/list")
	public List<String> list(){
		return adminService.list();
	}
	
	@GetMapping("/list_regions/tables/{table}")
	public List<String> list_regions(@PathVariable String table){
		return adminService.list_regions(table);
	}
	
	@GetMapping("/locate_region/tables/{table}/rows/{rowKey}")
	public String locate_region(@PathVariable String table, @PathVariable String rowKey){
		return adminService.locate_region(table, rowKey);
	}
	
	@GetMapping("/list_namespace")
	public List<String> list_namespace(){
		return adminService.list_namespace();
	}
	
	@GetMapping("/list_namespace_tables/{namespace}")
	public List<String> list_namespace_tables(@PathVariable String namespace){
		return adminService.list_namespace_tables(namespace);
	}
	
}

