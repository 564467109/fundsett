/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月7日  下午4:41:33
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * 计费策略基础测试类
 * 
 * @author guangyu@66money.com
 * @since 2015年5月7日 下午4:41:33
 * 
 */
public class FeeSuiteSelectorBaseTest {
	FeeSuiteInfo s1, s2, s3, s4;
	
	@Before
	public void setUp() {
		// long listid, String accountcode,String partner, int status, int feetype, String validdate
		s1 = new FeeSuiteInfo(1L, "000686", "LT0001", 0, FeeConst.FeeType.SAEL_SERVICE_FEE.getType(), "20150302");
		s2 = new FeeSuiteInfo(2L, "000686", "@", 0, FeeConst.FeeType.SAEL_SERVICE_FEE.getType(), "20150302");
		
		s3 = new FeeSuiteInfo(3L, "000686", "LT0001", 0, FeeConst.FeeType.SAEL_SERVICE_FEE.getType(), "20150504");
		s4 = new FeeSuiteInfo(4L, "000686", "@", 0, FeeConst.FeeType.SAEL_SERVICE_FEE.getType(), "20150504");
	}
	
	@Test
	public void mock() {
		List<FeeSuiteInfo> suites = Lists.newArrayList(s1, s2, s3, s4);
		FeeSuiteInfo ret1 = FeeSuiteSelector.select(suites, "LT0001", "20150506");
		assertEquals(ret1, s3);
		FeeSuiteInfo ret2 = FeeSuiteSelector.select(suites, "LT0001", "20150406");
		assertEquals(ret2, s1);
		
		suites.remove(0);
		ret1 = FeeSuiteSelector.select(suites, "LT0001", "20150406");
		assertEquals(ret1, s2);
	}
	
}
