package com.zxin.java.common.hbase.model.builder;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.java.common.hbase.model.Column;
import com.zxin.java.common.hbase.model.builder.ColumnBuilder;

public class ColumnBuilderTest {

	private static final Logger logger = LoggerFactory.getLogger(ColumnBuilderTest.class);

	@Test
	public void testBuildStringString() {
		assertNull(null);
	}

	@Test
	public void testBuildString() {
		Column col = ColumnBuilder.build(" ");
		assertThat(col.getFamily(), is(" "));
		assertNull(col.getQualifier());
		
		Column col1 = ColumnBuilder.build("");
		assertNull(col1.getFamily());
		assertNull(col1.getQualifier());
		
		Column col2 = ColumnBuilder.build(null);
		assertNull(col2.getFamily());
		assertNull(col2.getQualifier());
		
		Column col3 = ColumnBuilder.build("f1");
		assertThat(col3.getFamily(), is("f1"));
		assertNull(col3.getQualifier());
		
		Column col4 = ColumnBuilder.build("f1:");
		assertThat(col4.getFamily(), is("f1"));
		assertNull(col4.getQualifier());
		
		Column col5 = ColumnBuilder.build("f1:q1");
		assertThat(col5.getFamily(), is("f1"));
		assertThat(col5.getQualifier(), is("q1"));
	}

}
