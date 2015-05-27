/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月14日  上午11:24:56
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.uusoft.fund.settle.strategy.FeeZoneInfo;

/**
 * 分区信息的ORM映射工具类
 * 
 * @author guangyu@66money.com
 * @since 2015年5月14日 上午11:24:56
 *
 */
public class FeeZoneMapper implements RowMapper<FeeZoneInfo> {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public FeeZoneInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		FeeZoneInfo info = new FeeZoneInfo();
		info.setListid(rs.getLong("listid"));
		info.setDownamount(rs.getString("downamount"));
		info.setUpamount(rs.getString("upamount"));
		info.setValtype(rs.getInt("valtype"));
		info.setRateval(rs.getBigDecimal("rateval"));
		info.setZoneid(rs.getLong("zoneid"));
		
		return info;
	}
	
}
