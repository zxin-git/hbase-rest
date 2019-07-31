package com.zxin.java.common.hbase.model.builder;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.java.common.hbase.model.CellModel;

/**
 * 通过构造器将CellModel与 hbase依赖抽离
 * 
 * @author zxin
 */
public class CellModelBuilder {

	private static final Logger logger = LoggerFactory.getLogger(CellModelBuilder.class);

	/**
	 * 构建cellModel
	 * @param cell
	 * @return
	 */
	public static CellModel build(Cell cell) {
		return build(CellUtil.cloneRow(cell), CellUtil.cloneFamily(cell), CellUtil.cloneQualifier(cell), cell.getTimestamp(), CellUtil.cloneValue(cell));
	}

	public static CellModel build(byte[] rowKey, byte[] family, byte[] qualifier, long timestamp, byte[] value) {
		CellModel cellModel = new CellModel();
		cellModel.setRowKey(Bytes.toString(rowKey));
		cellModel.setColumn(Bytes.toString(KeyValue.makeColumn(family, qualifier)));
		cellModel.setTimestamp(timestamp);
		cellModel.setValue(Bytes.toString(value));
		return cellModel;
	}
}
