package com.zxin.java.common.hbase.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxin.java.common.hbase.model.CellModel;
import com.zxin.java.common.hbase.model.Column;
import com.zxin.java.common.hbase.model.builder.ColumnBuilder;
import com.zxin.java.common.hbase.service.IHbaseService;
import com.zxin.java.common.hbase.service.IQueryService;

@Service
public class HbaseServiceImpl implements IHbaseService {

	private static final Logger logger = LoggerFactory.getLogger(HbaseServiceImpl.class);

	@Autowired
	private IQueryService queryService;
	

	@Override
	public String fetch(String tableName, String rowName, String column) {
		Column col = ColumnBuilder.build(column);
		CellModel cell = queryService.fetch(tableName, rowName, col.getFamily(), col.getQualifier());
		
		if(cell != null){
			return cell.getValue();
		}
		return null;
	}

	@Override
	public Map<String, String> get(String tableName, String rowName, String family) {
		List<CellModel> cells = queryService.get(tableName, rowName, family);
		return colCellMap(cells);
	}

	@Override
	public Map<String, Map<String, String>> scan(String tableName, String startRow, String endRow, String column) {
		Column col = ColumnBuilder.build(column);
		List<List<CellModel>> rows = queryService.scan(tableName, startRow, endRow, col.getFamily(), col.getQualifier());
		
		Map<String, Map<String, String>> rowMap = new HashMap<>();
		if(rows != null){
			for (List<CellModel> cols : rows) {
				if(cols == null || cols.size() == 0){
					continue;
				}
				
				Map<String, String> colMap = new HashMap<>();
				for (CellModel cell : cols) {
					colMap.put(cell.getColumn(), cell.getValue());
				}
				rowMap.put(cols.get(0).getRowKey(), colMap);
			}
		}
		return rowMap;
	}
	

	@Override
	public Map<String, String> scanKnownCol(String tableName, String startRow, String endRow, String column) {
		Column col = ColumnBuilder.build(column);
		if(StringUtils.isEmpty(col.getQualifier()))
			throw new IllegalArgumentException("Qualifier for scanKnownCol method is empty");
		
		List<List<CellModel>> rows = queryService.scan(tableName, startRow, endRow, col.getFamily(), col.getQualifier());
		
		Map<String, String> rowMap = new HashMap<>();
		if(rows != null){
			for (List<CellModel> cols : rows) {
				if(cols == null || cols.size() == 0){
					continue;
				}
				CellModel cell = cols.stream().findFirst().get();
				rowMap.put(cell.getRowKey(), cell.getValue());
			}
		}
		return rowMap;
	}
	

	/**
	 * 将column级别的cell列表，转换成map
	 * @param colCells 
	 * @return	map -> key:column, value:cell 
	 */
	Map<String, String> colCellMap(List<CellModel> colCells){
		Map<String, String> map = new HashMap<>();
		if(colCells != null){
			for (CellModel cell : colCells) {
				map.put(cell.getColumn(), cell.getValue());
			}
		}
		return map;
	}

}
