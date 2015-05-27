/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月14日  下午2:37:41
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.probe;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uusoft.fund.common.StrUtils;
import com.uusoft.fund.dao.FeeSuiteMapper;
import com.uusoft.fund.settle.strategy.FeeSuiteInfo;

/**
 * 计费策略的探针，用于检查系统中各个策略设置的完成情况
 * 
 * @author guangyu@66money.com
 * @since 2015年5月14日 下午2:37:41
 *
 */
@Component
public class SuiteProbe {
	private Logger logger = LoggerFactory.getLogger(SuiteProbe.class);
	
	/**
	 * 获取在策略设置中涉及到的计算对象
	 * 
	 * @return
	 */
	public List<String> getAllCodesInEnd() {
		List<String> codes = jdbcTemplate.queryForList("SELECT DISTINCT accountcode FROM FEE_SUITE", String.class);
		logger.info("All codes are [" + codes.toString() + "]");
		return codes;
	}
	
	/**
	 * 获取在策略设置中涉及到的商户
	 * 
	 * @return
	 */
	public List<String> getAllPartnersInEnd() {
		List<String> codes = jdbcTemplate.queryForList("SELECT DISTINCT partner FROM FEE_SUITE", String.class);
		logger.info("All partner are [" + codes.toString() + "]");
		return codes;
	}
	
	/**
	 * 获取存在bookid未设置的策略记录
	 * 
	 * @return
	 */
	public List<FeeSuiteInfo> getUnfinishedSuites() {
		String sql = "SELECT * FROM FEE_SUITE WHERE bookid=0 OR bookid is null";
		List<FeeSuiteInfo> list = jdbcTemplate.query(sql, feeSuiteMapper);
		return list;
	}
	
	/**
	 * 获取bookid对应的策略详细分组已经停用的记录
	 * 
	 * @return
	 */
	public List<FeeSuiteInfo> getInvalidSuites() {
		String sql = "SELECT * FROM FEE_SUITE WHERE bookid=0 OR bookid IN (SELECT listid FROM FEE_BOOK WHERE statux!=0)";
		List<FeeSuiteInfo> list = jdbcTemplate.query(sql, feeSuiteMapper);
		return list;
	}
	
	/**
	 * 逐商户获取bookid对应的策略详细分组已经停用的记录
	 * 
	 * @return
	 */
	public List<FeeSuiteInfo> getInvalidSuitesByBranch(String branch) {
		String sql = "SELECT * FROM FEE_SUITE WHERE partner='%s' AND(bookid=0 OR bookid IN (SELECT listid FROM FEE_BOOK WHERE statux!=0))";
		List<FeeSuiteInfo> list = jdbcTemplate.query(String.format(sql, StrUtils.filterForSQL(branch)), feeSuiteMapper);
		return list;
	}
	
	/**
	 * 逐基金获取bookid对应的策略详细分组已经停用的记录
	 * 
	 * @return
	 */
	public List<FeeSuiteInfo> getInvalidSuitesByCode(String fundcode) {
		String sql = "SELECT * FROM FEE_SUITE WHERE accountcode='%s' AND(bookid=0 OR bookid IN (SELECT listid FROM FEE_BOOK WHERE statux!=0))";
		List<FeeSuiteInfo> list = jdbcTemplate.query(String.format(sql, StrUtils.filterForSQL(fundcode)), feeSuiteMapper);
		return list;
	}
	
	/**
	 * 获取指定商户设置的某中费用所涉及到的基金代码集合
	 * 
	 * @param branchcode 商户代码
	 * @param feetype 费用类型的枚举值
	 * @return
	 */
	public List<String> getSubCode(String branchcode, int feetype) {
		String sql = "SELECT DISTINCT accountcode FROM FEE_SUITE WHERE partner='%s' AND feetype='%s' AND statux=0";
		String realSql = String.format(sql, StrUtils.filterForSQL(branchcode), StrUtils.filterForSQL(String.valueOf(feetype)));
		if(logger.isInfoEnabled())
			logger.info("query sql=> [" + realSql +"] ");
		List<String> codes = jdbcTemplate.queryForList(realSql, String.class);
		
		return codes;
	}
	
	/**
	 * 获取指定基金设置的某中费用所涉及到的商户代码集合
	 * 
	 * @param fundcode 商户代码
	 * @param feetype 费用类型的枚举值
	 * @return
	 */
	public List<String> getSubBranch(String fundcode, int feetype) {
		String sql = "SELECT DISTINCT partner FROM FEE_SUITE WHERE accountcode='%s' AND feetype='%s' AND statux=0";
		String realSql = String.format(sql, StrUtils.filterForSQL(fundcode), StrUtils.filterForSQL(String.valueOf(feetype)));
		if(logger.isInfoEnabled())
			logger.info("query sql=> [" + realSql +"] ");
		List<String> codes = jdbcTemplate.queryForList(realSql, String.class);
		
		return codes;
	}
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	protected FeeSuiteMapper feeSuiteMapper = new FeeSuiteMapper();
}
