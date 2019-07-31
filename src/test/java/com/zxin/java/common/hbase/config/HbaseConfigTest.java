package com.zxin.java.common.hbase.config;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zxin.java.common.hbase.config.HbaseConfig;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HbaseConfigTest {

	private static final Logger logger = LoggerFactory.getLogger(HbaseConfigTest.class);

	@Autowired
	private HbaseConfig hbaseConfig;
	
	@Test
	public void testGetClientPort() {
		assertThat(hbaseConfig.getClientPort(), is(2181));
	}

	@Test
	public void testGetQuorum() {
//		assertThat(hbaseConfig.getQuorum(), is("10.10.67.44,10.10.67.48"));
		assertThat(hbaseConfig.getQuorum(), is("10.10.67.44"));
	}
	
	@Test
	public void testGetScanMaxResultSize() {
		assertThat(hbaseConfig.getScanMaxResultSize(), is(2000L));
//		assertThat(hbaseConfig.getScanMaxResultSize(), is(5000L));
	}
}
