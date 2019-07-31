package com.zxin.java.common.hbase.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zxin.java.common.hbase.service.IAdminService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminServiceImplTest {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImplTest.class);

	@Autowired
	private IAdminService adminService;
	
	@Test
	public void testVersion() {
		String version = adminService.version();
		logger.info("version:[{}]", version);
	}

	@Test
	public void testStatus() {
		String status = adminService.status();
		logger.info("status:[{}]", status);
	}

	@Test
	public void testProcesslist() {
//		String processlist = adminService.processlist();
//		logger.info("processlist:[{}]", processlist);
		logger.info("processlist:[{}]", "未实现");
	}

	@Test
	public void testDescribe() {
//		String describe = adminService.describe(" ");
		String describe = adminService.describe(null);
//		String describe = adminService.describe("ns1:t1");
//		String describe = adminService.describe("t2");
//		String describe = adminService.describe("t1");
		logger.info("describe:[{}]", describe);
	}

	@Test
	public void testExists() {
		boolean exists = adminService.exists("");
//		boolean exists = adminService.exists(null);
//		boolean exists = adminService.exists("t2");
//		boolean exists = adminService.exists("t1");
		logger.info("exists:[{}]", exists);
	}

	@Test
	public void testList() {
		List<String> tables = adminService.list();
		logger.info("list:[{}]", tables.toString());
		
	}

	@Test
	public void testList_regions() {
		List<String> regions = adminService.list_regions("t2");
//		List<String> regions = adminService.list_regions(null);
//		List<String> regions = adminService.list_regions("t1");
		logger.info("list_regions:[{}]", regions.toString());
	}

	@Test
	public void testLocate_region() {
//		String locate_region = adminService.locate_region("t1", "r1");
//		logger.info("locate_region:[{}]", locate_region);
		logger.info("locate_region:[{}]", "未实现");
	}

	@Test
	public void testList_namespace() {
		List<String> namespaces = adminService.list_namespace();
		logger.info("list_namespace:[{}]", namespaces);
	}

	@Test
	public void testList_namespace_tables() {
//		List<String> tables = adminService.list_namespace_tables("");
//		List<String> tables = adminService.list_namespace_tables("n1");
		List<String> tables = adminService.list_namespace_tables(null);
//		List<String> tables = adminService.list_namespace_tables("ns1");
//		List<String> tables = adminService.list_namespace_tables("hbase");
//		List<String> tables = adminService.list_namespace_tables("default");
		logger.info("list_namespace_tables:[{}]", tables);
	}

}
