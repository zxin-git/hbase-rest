package com.zxin.java.common.hbase.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxin.java.common.hbase.service.IAdminService;

/**
 * 
 * 
 * @author zxin
 */
@Service
public class AdminServiceImpl implements IAdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private Admin admin;

	@Override
	public String version() {
		String hbaseVersion = null;
		try {
			hbaseVersion = admin.getClusterStatus().getHBaseVersion();
		} catch (IOException e) {
			logger.warn("", e);
		}
		return hbaseVersion;
	}

	@Override
	public String status() {
		String status = null;
		try {
			status = admin.getClusterStatus().toString();
		} catch (IOException e) {
			logger.warn("", e);
		}
		return status;
	}

	@Override
	public String processlist() {
		throw new RuntimeException("未实现");
	}

	@Override
	public String describe(String tableName) {
		String describe = null;
		if(StringUtils.isBlank(tableName)){
			return describe;
		}
		try {
			describe = admin.getTableDescriptor(TableName.valueOf(tableName)).toString();
		} catch (IOException e) {
			logger.warn("", e);
		}
		return describe;
	}

	@Override
	public boolean exists(String tableName) {
		boolean exists = false;
		if(StringUtils.isBlank(tableName)){
			return exists;
		}
		try {
			exists = admin.tableExists(TableName.valueOf(tableName));
		} catch (IOException e) {
			logger.warn("", e);
		}
		return exists;
	}

	@Override
	public List<String> list() {
		List<String> tables = new ArrayList<String>();
		try {
			TableName[] tableNames = admin.listTableNames();
			for (TableName tableName : tableNames) {
				tables.add(tableName.getNameAsString());
			}
		} catch (IOException e) {
			logger.warn("", e);
		}
		return tables;
	}

	@Override
	public List<String> list_regions(String tableName) {
		List<String> regions = new ArrayList<>();
		if(tableName != null){
			try {
				List<HRegionInfo> regionInfos = admin.getTableRegions(TableName.valueOf(tableName));
				if(regionInfos != null){
					regionInfos.forEach((regionInfo)->{
						regions.add(regionInfo.toString());
					});
				}
			} catch (IOException e) {
				logger.warn("", e);
			}
		}
		return regions;
	}

	@Override
	public String locate_region(String tableName, String rowKey) {
		throw new RuntimeException("未实现");
	}

	@Override
	public List<String> list_namespace() {
		List<String> namespaces = new ArrayList<>();
		try {
			NamespaceDescriptor[] namespaceDescriptors = admin.listNamespaceDescriptors();
			for (NamespaceDescriptor namespaceDescriptor : namespaceDescriptors) {
				namespaces.add(namespaceDescriptor.getName());
			}
		} catch (IOException e) {
			logger.warn("", e);
		}
		return namespaces;
	}

	@Override
	public List<String> list_namespace_tables(String namespace) {
		List<String> tables = new ArrayList<>();
		if(namespace == null){
			namespace = EMPTY;
		}
		try {
			TableName[] tableNames = admin.listTableNamesByNamespace(namespace);
			for (TableName tableName : tableNames) {
				tables.add(tableName.getNameAsString());
			}
		} catch (IOException e) {
			logger.warn("", e);
		}
		return tables;
	}
	
	private static final String EMPTY = "";

}
