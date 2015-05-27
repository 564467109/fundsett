/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月14日  上午10:33:52
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.uusoft.fund.settle.strategy.FeeBookInfo;

/**
 * @author  guangyu@66money.com
 * @since  2015年5月14日 上午10:33:52
 *
 */
public class FeeBookMapper implements RowMapper<FeeBookInfo>{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public FeeBookInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		FeeBookInfo info = new FeeBookInfo();
		//info.setBookid(rs.getLong("bookid"));
		info.setBookname(rs.getString("bookname"));
		info.setCreatedate(rs.getString("createdate"));
		info.setFeetype(rs.getInt("feetype"));
		info.setListid(rs.getLong("listid"));
		info.setNote(rs.getString("note"));
		info.setShow(rs.getInt("isshow"));
		info.setStatus(rs.getInt("statux"));
		
		return info;
	}
	
}
