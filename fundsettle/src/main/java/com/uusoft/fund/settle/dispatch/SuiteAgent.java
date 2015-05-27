/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月18日  下午9:33:47
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.dispatch;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uusoft.fund.dao.FeeDetailMapper;
import com.uusoft.fund.dao.FeeSuiteMapper;
import com.uusoft.fund.flow.dp.ICode;
import com.uusoft.fund.settle.strategy.FeeDetailFactory;
import com.uusoft.fund.settle.strategy.FeeDetailInfo;
import com.uusoft.fund.settle.strategy.FeeSuiteInfo;

/**
 * 策略优化机器人的基类，执行实际的运算
 * 
 * @author guangyu@66money.com
 * @since 2015年5月18日 下午9:33:47
 *
 */
@Component
public class SuiteAgent {
	
	/**
	 * 获取费用策略
	 * 
	 * @param feetype
	 * @param fundcode
	 * @param partner
	 * @return
	 */
	protected List<FeeSuiteInfo> getSuites(int feetype, String fundcode, String partner) {
		String sql = "SELECT * FROM FEE_SUITE t WHERE t.accountcode='%s' AND partner='%s' AND feetype=%d AND statux=0";
		List<FeeSuiteInfo> suites = jdbcTemplate.query(String.format(sql, fundcode, partner, feetype), feeSuiteMapper);
		
		return suites;
	}
	
	/**
	 * 获取费用策略
	 * 
	 * @param feetype
	 * @param fundcode
	 * @param partner
	 * @return
	 */
	protected List<FeeSuiteInfo> getSuites(int feetype, String fundcode) {
		String sql = "SELECT * FROM FEE_SUITE t WHERE t.accountcode='%s' AND feetype=%d AND statux=0";
		List<FeeSuiteInfo> suites = jdbcTemplate.query(String.format(sql, fundcode, feetype), feeSuiteMapper);
		
		return suites;
	}
	
	/**
	 * 获取策略对应的详细设置信息列表
	 * 
	 * @param bookid 详细策略组id
	 * @return
	 */
	protected List<FeeDetailInfo> getBookContent(long bookid) {
		String bookSql = "SELECT * FROM FEE_DETAIL WHERE bookid='%s'";
		List<FeeDetailInfo> dets = jdbcTemplate.query(String.format(bookSql, bookid), feeDetailMapper);
		
		return dets;
	}
	
	private FeeSuiteMapper feeSuiteMapper = new FeeSuiteMapper();
	private FeeDetailMapper feeDetailMapper = new FeeDetailMapper();
	
	@Autowired
	protected ICode code;
	@Autowired
	protected FeeDetailFactory feeDetailFactory;
	@Resource
	protected JdbcTemplate jdbcTemplate;
}
