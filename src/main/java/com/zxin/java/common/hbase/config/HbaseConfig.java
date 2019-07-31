package com.zxin.java.common.hbase.config;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

@org.springframework.context.annotation.Configuration
public class HbaseConfig {

	private static final Logger logger = LoggerFactory.getLogger(HbaseConfig.class);
	
	@Value("${hbase.zookeeper.property.clientPort:2181}")
	private int clientPort;
	
	@Value("${hbase.zookeeper.quorum:}")
	private String quorum;
	
	@Value("${hbase.scan.maxResultSize:5000}")
	private long scanMaxResultSize;
	
	@Bean
	public Configuration configuration(){
		Configuration configuration = HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.quorum", quorum);
		configuration.setInt("hbase.zookeeper.property.clientPort", clientPort);

		return configuration;
	}
	
	@Bean
    public HbaseTemplate hbaseTemplate(){
        HbaseTemplate hbaseTemplate = new HbaseTemplate();
        hbaseTemplate.setConfiguration(configuration());
        hbaseTemplate.setAutoFlush(true);
        return hbaseTemplate;
    }

	@Bean
	public Connection connection() throws IOException{
		Connection connection = ConnectionFactory.createConnection(configuration());
		return connection;
	}
	
	@Bean
	public Admin hbaseAdmin() throws IOException{
		Admin admin = connection().getAdmin();
		return admin;
	}
	

	public int getClientPort() {
		return clientPort;
	}

	public String getQuorum() {
		return quorum;
	}

	public long getScanMaxResultSize() {
		return scanMaxResultSize;
	}
	
}
