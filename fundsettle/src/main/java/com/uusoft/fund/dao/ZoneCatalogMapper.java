/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月14日  上午11:02:58
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.uusoft.fund.settle.strategy.ZoneCatalogInfo;

/**
 * @author  guangyu@66money.com
 * @since  2015年5月14日 上午11:02:58
 *
 */
public class ZoneCatalogMapper implements RowMapper<ZoneCatalogInfo>{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ZoneCatalogInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		ZoneCatalogInfo info  = new ZoneCatalogInfo();
		info.setDescr(rs.getString("descr"));
		info.setFeetype(rs.getInt("feetype"));
		info.setListid(rs.getLong("listid"));
		info.setNikename(rs.getString("nikename"));
		info.setNote(rs.getString("note"));
		info.setStatus(rs.getInt("statux"));
		info.setUfcatalog(rs.getString("ufcatalog"));
		
		return info;
	}
	
}
