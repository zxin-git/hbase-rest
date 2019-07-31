package com.zxin.java.common.hbase.callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.hadoop.hbase.RowMapper;

import com.zxin.java.common.hbase.model.CellModel;
import com.zxin.java.common.hbase.model.Column;
import com.zxin.java.common.hbase.model.builder.CellModelBuilder;
import com.zxin.java.common.hbase.model.builder.ColumnBuilder;

/**
 * RowMapper常用回调类
 * 
 * 统一提供单例实现，
 * 防止创建多个对象耗费内存
 * 
 * {@link RowMapper}
 * 
 * @author zxin
 */
public class RowMappers {

	private static final Logger logger = LoggerFactory.getLogger(RowMappers.class);

	
	/**
	 * 匹配单一cell
	 * 没有返回null
	 * @author zxin
	 */
	public static class FetchCellRowMapper implements RowMapper<CellModel>{

		public static final FetchCellRowMapper INSTANCE = new FetchCellRowMapper();
		
		@Override
		public CellModel mapRow(Result result, int rowNum) throws Exception {
			List<Cell> cells = result.listCells();
			if(cells == null){
				return null;
			}
			Cell cell = cells.stream().findFirst().get();
			return CellModelBuilder.build(cell);
		}
		
	}
	
	/**
	 * 获取cellList
	 * 适用于同 rowkey 不同 column 的cells
	 * 
	 * @author zxin
	 */
	public static class ListCellRowMapper implements RowMapper<List<CellModel>>{
		
		public static final ListCellRowMapper INSTANCE = new ListCellRowMapper();
		
		private ListCellRowMapper() {};

		@Override
		public List<CellModel> mapRow(Result result, int rowNum) throws Exception {
			List<Cell> cells = result.listCells();
			if(cells == null){
				return new ArrayList<>();
			}
			
			List<CellModel> cellModels = new ArrayList<>(cells.size()); 
			cells.forEach((cell)->{
				cellModels.add(CellModelBuilder.build(cell));
			});
			return cellModels;
		}
		
	}
	
	
	

}
