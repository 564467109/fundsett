/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月25日  下午7:31:38
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.dispatch;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uusoft.fund.flow.dp.ICode;
import com.uusoft.fund.payinfo.SaleOrgInfo;
import com.uusoft.fund.payinfo.TanoInfo;

/**
 * 测试基金代码和商户的hessian接口
 * @author guangyu@66money.com
 * @since 2015年5月25日 下午7:31:38
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class FundCodeTest {
	
	@Test
	public void testCenter() {
		List<SaleOrgInfo> list = code.orgs();
		int len = list.size();
		len++;
		
		Assert.assertNotEquals(len, 0);
	}
	
	@Test
	public void testChannel() {
		List<TanoInfo> list = code.codes();
		int len = list.size();
		len++;
		
		Assert.assertNotEquals(len, 0);
	}
	
	@Resource(name = "code")
	@Autowired(required = false)
	protected ICode code;
}
