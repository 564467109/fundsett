/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月14日  上午11:08:53
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.uusoft.fund.settle.strategy.ZoneGroupInfo;

/**
 * @author  guangyu@66money.com
 * @since  2015年5月14日 上午11:08:53
 *
 */
public class ZoneGroupMapper implements RowMapper<ZoneGroupInfo>{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ZoneGroupInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		ZoneGroupInfo info = new ZoneGroupInfo();
		info.setListid(rs.getLong("listid"));
		info.setCatalogid(rs.getLong("catalogid"));
		info.setDescr(rs.getString("descr"));
		info.setGroupname(rs.getString("groupname"));
		info.setNikename(rs.getString("nikename"));
		info.setNote(rs.getString("note"));
		info.setStatus(rs.getInt("statux"));
		
		return info;
	}
	
}
