package com.zxin.java.common.hbase.controller;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GetControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(GetControllerTest.class);
	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Before
    public void before() {
        //获取mockmvc对象实例
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

	@Test
	public void testFetch() {
		try {
			String table = "t1";
			String rowKey = "r1";
			String column = "f1:q1";
			String url = String.format("/getter/fetch/tables/%s/rows/%s/columns/%s", table, rowKey, column);
			
			MockHttpServletRequestBuilder b = MockMvcRequestBuilders.get(url);
			MvcResult mvcResult = mockMvc.perform(b).andReturn();
			MockHttpServletResponse mvcResponse = mvcResult.getResponse();
 
			logger.info("Fetch:[{}]", mvcResponse.getContentAsString());
			assertEquals("请求错误", 200, mvcResponse.getStatus()); 
			assertThat(mvcResponse.getContentAsString(), is("v1"));
		} catch (UnsupportedEncodingException e) {
			logger.warn("", e);
		} catch (Exception e) {
			logger.warn("", e);
		}
	}

	@Test
	public void testGet() {
		try {
			String table = "t1";
			String rowKey = "r1";
			String family = "f1:q1";
			String url = String.format("/getter/get/tables/%s/rows/%s", table, rowKey);
			
			MockHttpServletRequestBuilder b = MockMvcRequestBuilders.get(url).param("family", family);
			MvcResult mvcResult = mockMvc.perform(b).andReturn();
			MockHttpServletResponse mvcResponse = mvcResult.getResponse();
 
			logger.info("Fetch:[{}]", mvcResponse.getContentAsString());
			assertEquals("请求错误", 200, mvcResponse.getStatus()); 
//			assertThat(mvcResponse.getContentAsString(), is(""));
		} catch (UnsupportedEncodingException e) {
			logger.warn("", e);
		} catch (Exception e) {
			logger.warn("", e);
		}
	}

}
