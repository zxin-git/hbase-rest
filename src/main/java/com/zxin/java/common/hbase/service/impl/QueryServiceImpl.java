package com.zxin.java.common.hbase.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;

import com.zxin.java.common.hbase.callback.RowMappers.FetchCellRowMapper;
import com.zxin.java.common.hbase.callback.RowMappers.ListCellRowMapper;
import com.zxin.java.common.hbase.config.HbaseConfig;
import com.zxin.java.common.hbase.model.CellModel;
import com.zxin.java.common.hbase.service.IQueryService;

/**
 * 
 * 
 * @author zxin
 */
@Service
public class QueryServiceImpl implements IQueryService{

	private static final Logger logger = LoggerFactory.getLogger(QueryServiceImpl.class);
	
	@Autowired
	private HbaseTemplate hbaseTemplate;
	
	@Autowired
	private HbaseConfig hbaseConfig;

	@Override
	public CellModel fetch(String tableName, String rowName, String family, String qualifier){
		if(StringUtils.isEmpty(qualifier) || StringUtils.isEmpty(family)) 
			throw new IllegalArgumentException("Qualifier or family is empty");
		
		if(StringUtils.isEmpty(tableName) || StringUtils.isEmpty(rowName)) 
			throw new IllegalArgumentException("Table or rowkey is empty");
		
//		try {
			return hbaseTemplate.get(tableName, rowName, family, qualifier, FetchCellRowMapper.INSTANCE);
//		} catch (DataAccessException e) {
//			logger.warn("", e);
//			throw e;
//		}
//		return null;
	}
	
	@Override
	public List<CellModel> get(String tableName, String rowName, String family){
		if(StringUtils.isEmpty(tableName) || StringUtils.isEmpty(rowName)) 
			throw new IllegalArgumentException("Table or rowkey is empty");
		
//		try {
			return hbaseTemplate.get(tableName, rowName, family, null, ListCellRowMapper.INSTANCE);
//		} catch (DataAccessException e) {
//			logger.warn("", e);
//		}
//		return new ArrayList<>();
	}

	@Override
	public List<List<CellModel>> scan(String tableName, String startRow, String endRow, String family, String qualifier){
		if(StringUtils.isEmpty(tableName))
			throw new IllegalArgumentException("Table is empty");
		
		if(StringUtils.isEmpty(startRow) || StringUtils.isEmpty(endRow)) 
			throw new IllegalArgumentException("StartRow or endRow is empty");
		
		Scan scan = scanBuilder(startRow, endRow, family, qualifier);
//		try {
			return hbaseTemplate.find(tableName, scan, ListCellRowMapper.INSTANCE);
//		} catch (DataAccessException e) {
//			logger.warn("", e);
//		}
//		return new ArrayList<>();
	}
	
	/**
	 * scan常用参数项建造器
	 * 更复杂的采用原生构建{@link Scan}
	 * 
	 * 1. 参数项为null 则忽略
	 * 2. row为 左闭右开区间
	 * 
	 * @param startRow
	 * @param endRow
	 * @param family
	 * @param qualifier
	 * @return
	 */
	private Scan scanBuilder(String startRow, String endRow, String family, String qualifier){
		Scan scan = new Scan();
		if(startRow != null){
			scan.withStartRow(Bytes.toBytes(startRow));
		}
		if(endRow != null){
			scan.withStopRow(Bytes.toBytes(endRow));
		}
		
		if(family != null){
			if(qualifier != null){
				scan.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
			}else{
				scan.addFamily(Bytes.toBytes(family));
			}
		}
		scan.setMaxResultSize(hbaseConfig.getScanMaxResultSize());
		return scan;
	}
	
}
