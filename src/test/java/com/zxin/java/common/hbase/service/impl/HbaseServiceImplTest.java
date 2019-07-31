package com.zxin.java.common.hbase.service.impl;

import static org.junit.Assert.*;

import java.util.Map;

import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zxin.java.common.hbase.service.IHbaseService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HbaseServiceImplTest {

	private static final Logger logger = LoggerFactory.getLogger(HbaseServiceImplTest.class);

	@Autowired
	private IHbaseService hbaseService;
	
	@Test()
	public void testFetch() {
		String cell = hbaseService.fetch("t1", "r1", "f1:q1");
		assertThat(cell, is("v1"));
		
		try {
			hbaseService.fetch("t1", "r1", "");
			hbaseService.fetch("t1", "r1", null);
			hbaseService.fetch("t1", "r1", "f1");
		} catch (Exception e) {
			assertThat(e, instanceOf(IllegalArgumentException.class));
		}
	}

	@Test
	public void testGet() {
//		Map<String, String> map = hbaseService.get("t1", "r1", null);
		Map<String, String> map = hbaseService.get("t1", "r1", "f1");
		logger.info("get:[{}]", map);
	}

	@Test
	public void testScan() {
		Map<String, Map<String, String>> map = hbaseService.scan("t1", "r1", "r1", null);
//		Map<String, Map<String, String>> map = hbaseService.scan("t1", "r1", "r4", null);
//		Map<String, Map<String, String>> map = hbaseService.scan("t1", "r1", "r4", "f1");
//		Map<String, Map<String, String>> map = hbaseService.scan("t1", "r1", "r4", "f1:q1");
		logger.info("scan:[{}]", map);
	}

	@Test
	public void testScanKnownCol() {
//		Map<String, String> map = hbaseService.scanKnownCol("t1", "r1", "r4", null);
//		Map<String, String> map = hbaseService.scanKnownCol("t1", "r1", "r4", "f1:");
		Map<String, String> map = hbaseService.scanKnownCol("t1", "r1", "r4", "f1:q1");
		logger.info("scan:[{}]", map);
	}


}
