/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月13日  下午3:39:56
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.io.Serializable;

/**
 * 费用分区分类信息
 * 
 * @author guangyu@66money.com
 * @since 2015年5月13日 下午3:39:56
 *
 */
public class ZoneCatalogInfo implements Serializable {
	private static final long serialVersionUID = -5789472821829725913L;
	
	/** 主键 */
	private long listid;
	
	/** 费用类型 */
	private int feetype = 0;
	/** 自定义类型 */
	private String ufcatalog = "";
	/** 昵称 */
	private String nikename = "";
	/** 描述 */
	private String descr = "";
	/** 备注 */
	private String note = "";
	/** 状态-是否有效 */
	private int status = 0;
	
	/**
	 * @return the listid
	 */
	public long getListid() {
		return listid;
	}
	
	/**
	 * @param listid the listid to set
	 */
	public void setListid(long listid) {
		this.listid = listid;
	}
	
	/**
	 * @return the feetype
	 */
	public int getFeetype() {
		return feetype;
	}
	
	/**
	 * @param feetype the feetype to set
	 */
	public void setFeetype(int feetype) {
		this.feetype = feetype;
	}
	
	/**
	 * @return the ufcatalog
	 */
	public String getUfcatalog() {
		return ufcatalog;
	}
	
	/**
	 * @param ufcatalog the ufcatalog to set
	 */
	public void setUfcatalog(String ufcatalog) {
		this.ufcatalog = ufcatalog;
	}
	
	/**
	 * @return the nikename
	 */
	public String getNikename() {
		return nikename;
	}
	
	/**
	 * @param nikename the nikename to set
	 */
	public void setNikename(String nikename) {
		this.nikename = nikename;
	}
	
	/**
	 * @return the desc
	 */
	public String getDescr() {
		return descr;
	}
	
	/**
	 * @param desc the desc to set
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "{listid=" + listid + ", feetype=" + feetype
				+ ", ufcatalog=" + ufcatalog + ", nikename=" + nikename
				+ ", descr=" + descr + ", note=" + note + ", status=" + status
				+ "}";
	}
	
}
