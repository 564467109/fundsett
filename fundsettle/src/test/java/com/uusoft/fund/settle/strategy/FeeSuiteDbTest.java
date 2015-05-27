/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月14日  下午2:00:01
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uusoft.fund.common.CollectUtils;
import com.uusoft.fund.dao.FeeSuiteMapper;

/**
 * @author  guangyu@66money.com
 * @since  2015年5月14日 下午2:00:01
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class FeeSuiteDbTest {
	
	/**
	 * 测试环境使用代码
	 */
	@Test
	public void envtest(){
		List<FeeSuiteInfo> list=jdbcTemplate.query("select *from FEE_SUITE", new FeeSuiteMapper());
		
		FeeSuiteInfo info = CollectUtils.getTopEl(list);
		System.out.print(info.toString());
	}
	
	@Qualifier(value="jdbcTemplate")
	@Resource
	private JdbcTemplate jdbcTemplate;
}
