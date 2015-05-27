/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月14日  下午2:44:35
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.probe;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uusoft.fund.settle.probe.SuiteProbe;
import com.uusoft.fund.settle.strategy.FeeSuiteInfo;

/**
 * @author  guangyu@66money.com
 * @since  2015年5月14日 下午2:44:35
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class SuiteProbeTest {
	@Autowired
	private SuiteProbe suiteProbe;
	/**
	 * Test method for {@link com.uusoft.fund.settle.probe.SuiteProbe#getAllCodes()}.
	 */
	@Test
	public void testGetAllCodesInEnd() {
		List<String> codes = suiteProbe.getAllCodesInEnd();
		Assert.assertNotNull(codes);
	}
	
	@Test
	public void testGetAllPartnersInEnd(){
		List<String> codes = suiteProbe.getAllPartnersInEnd();
		Assert.assertNotNull(codes);
	}
	
	@Test
	public void testGetUnfinishedSuites(){
		List<FeeSuiteInfo> codes = suiteProbe.getUnfinishedSuites();
		Assert.assertNotNull(codes);
	}
	
	@Test
	public void testGetInvalidSuites(){
		List<FeeSuiteInfo> codes = suiteProbe.getInvalidSuites();
		Assert.assertNotNull(codes);
	}
	
	
}
