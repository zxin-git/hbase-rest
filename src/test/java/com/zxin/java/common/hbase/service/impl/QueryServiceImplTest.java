package com.zxin.java.common.hbase.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zxin.java.common.hbase.model.CellModel;
import com.zxin.java.common.hbase.service.IQueryService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QueryServiceImplTest {

	private static final Logger logger = LoggerFactory.getLogger(QueryServiceImplTest.class);

	@Autowired
	private IQueryService queryService;
	
	@Test
	public void testFetch() {
//		CellModel cellModel = queryService.fetch("t1", null, "f1", "q1");
//		CellModel cellModel = queryService.fetch("t1", "r1", "", "q1");
//		CellModel cellModel = queryService.fetch("t1", "r1", null, "q1");
//		CellModel cellModel = queryService.fetch("t1", "r1", "f1", "");
//		CellModel cellModel = queryService.fetch("t1", "r1", "f1", null);
		
//		CellModel cellModel = queryService.fetch("t1", "r1", "f144", "q8");
//		CellModel cellModel = queryService.fetch("t1", "r1", "f1", "q8");
		CellModel cellModel = queryService.fetch("t1", "r1", "f1", "q1");
		if(cellModel != null)
			logger.info("fetch:[{}]", cellModel.toString());
	}

	@Test
	public void testGet() {
//		List<CellModel> cells = queryService.get("t1", " ", null);
//		List<CellModel> cells = queryService.get("t1", "", null);
//		List<CellModel> cells = queryService.get("t1", null, null);
//		List<CellModel> cells = queryService.get("t1", "r1", null);
		List<CellModel> cells = queryService.get("t1", "r1", "f1");
		
//		List<CellModel> cells = queryService.get("t1", "r1", "f1:");
//		List<CellModel> cells = queryService.get("t1", "r1", "f1:q1");
		for (CellModel cellModel : cells) {
			logger.info("get:[{}]", cellModel.toString());
		}
	}

	@Test
	public void testScan() {
//		List<List<CellModel>> list = queryService.scan(null, "r1", "r2", null, null);
//		List<List<CellModel>> list = queryService.scan("t1", null, "r2", null, null);
//		List<List<CellModel>> list = queryService.scan("t1", "r1", null, null, null);
		
//		List<List<CellModel>> list = queryService.scan("t1", "r1", "r3", null, null);
//		List<List<CellModel>> list = queryService.scan("t1", "r1", "r3", "f1", null);
		List<List<CellModel>> list = queryService.scan("t1", "r1", "r3", "f1", "q1");
		for (List<CellModel> cells : list) {
			for (CellModel cellModel : cells) {
				logger.info("scan:[{}]", cellModel.toString());
			}
			logger.info("---------------------");
		}
	}

}
