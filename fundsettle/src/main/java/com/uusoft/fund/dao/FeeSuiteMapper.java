/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月13日  下午8:05:03
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.uusoft.fund.settle.strategy.FeeSuiteInfo;

/**
 * @author  guangyu@66money.com
 * @since  2015年5月13日 下午8:05:03
 *
 */
public class FeeSuiteMapper implements RowMapper<FeeSuiteInfo>{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public FeeSuiteInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		FeeSuiteInfo info = new FeeSuiteInfo();
		info.setListid(rs.getLong("listid"));
		info.setAccountcode(rs.getString("accountcode"));
		info.setStatus(rs.getInt("statux"));
		info.setFeetype(rs.getInt("feetype"));
		info.setPartner(rs.getString("partner"));
		info.setValiddate(rs.getString("validdate"));
		info.setBookid(rs.getLong("bookid"));
		
		return info;
	}
}
