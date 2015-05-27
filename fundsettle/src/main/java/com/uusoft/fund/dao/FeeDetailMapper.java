/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月14日  上午10:53:45
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.uusoft.fund.settle.strategy.FeeDetailInfo;

/**
 * @author  guangyu@66money.com
 * @since  2015年5月14日 上午10:53:45
 *
 */
public class FeeDetailMapper implements RowMapper<FeeDetailInfo>{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public FeeDetailInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		FeeDetailInfo info = new FeeDetailInfo();
		info.setBookid(rs.getLong("bookid"));
		info.setBusinesscode(rs.getString("businesscode"));
		info.setCalmode(rs.getInt("calmode"));
		info.setCalpolicy(rs.getInt("calpolicy"));
		info.setFixval(rs.getBigDecimal("fixval"));
		info.setLeftclose(rs.getInt("leftclose"));
		info.setListid(rs.getLong("listid"));
		info.setOperway(rs.getString("operway"));
		info.setPaychannelid(rs.getString("paychannelid"));
		info.setPaycenterid(rs.getString("paycenterid"));
		info.setZoneid(rs.getLong("zoneid"));
		
		return info;
	}
	
}

