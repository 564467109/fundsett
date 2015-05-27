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

import com.uusoft.fund.flow.dp.IPayGateInfo;
import com.uusoft.fund.payinfo.PaycenterInfo;
import com.uusoft.fund.payinfo.PaychannelInfo;

/**
 * 测试支付网点的hessian接口
 * @author guangyu@66money.com
 * @since 2015年5月25日 下午7:31:38
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class PayGateTest {
	
	@Test
	public void testCenter() {
		List<PaycenterInfo> list = payGateInfo.centers();
		int len = list.size();
		len++;
		
		Assert.assertNotEquals(len, 0);
	}
	
	@Test
	public void testChannel() {
		List<PaychannelInfo> list = payGateInfo.channels();
		int len = list.size();
		len++;
		
		Assert.assertNotEquals(len, 0);
	}
	
	@Resource(name = "paygate")
	@Autowired(required = false)
	protected IPayGateInfo payGateInfo;
}
