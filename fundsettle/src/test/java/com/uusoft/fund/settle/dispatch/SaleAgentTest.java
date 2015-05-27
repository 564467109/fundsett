/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月22日  下午5:19:53
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.dispatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author guangyu@66money.com
 * @since 2015年5月22日 下午5:19:53
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class SaleAgentTest {
	@Test
	public void aaa() {
	}
	// @Test
	// public void saleTest(){
	// suiteAgent.strategy("000686","353","20150501");
	// }
	
	// @Test
	// public void saleTest2(){
	// saleAgent.calc("000686","353","20150521","20150501");
	// }
	//
	// @Autowired(required=false)
	// protected SaleAgent saleAgent;
}
