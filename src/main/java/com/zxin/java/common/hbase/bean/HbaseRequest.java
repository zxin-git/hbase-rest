package com.zxin.java.common.hbase.bean;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HbaseRequest {

	private final String sequence = UUID.randomUUID().toString();
	
	private String tableName;
	
	private String rowKey;
	
	private String family;
	
	private String qualifier;
	
	private String startRow;
	
	private String stopRow;


}
