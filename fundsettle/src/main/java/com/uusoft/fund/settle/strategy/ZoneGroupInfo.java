/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月13日  下午6:43:57
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.io.Serializable;

/**
 * 费用分区分组信息
 * 
 * @author guangyu@66money.com
 * @since 2015年5月13日 下午6:43:57
 *
 */
public class ZoneGroupInfo implements Serializable {
	private static final long serialVersionUID = 2942362451185381723L;
	
	/** 主键 */
	private long listid;
	/** 所属分类id */
	private long catalogid = 0L;
	/** 分组名 */
	private String groupname = "";
	/** 分组昵称 */
	private String nikename = "";
	/** 描述信息 */
	private String descr = "";
	/** 作为扩展字段，如在界面展示引用情况使用，但在数据库中不对此字段予以持久化保存 */
	private String note = "";
	/** 状态-是否有效 */
	private int status = 0;
	/**组合区段信息,页面展示**/
	private String level;
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
	 * @return the catalogid
	 */
	public long getCatalogid() {
		return catalogid;
	}
	/**
	 * @param catalogid the catalogid to set
	 */
	public void setCatalogid(long catalogid) {
		this.catalogid = catalogid;
	}
	/**
	 * @return the groupname
	 */
	public String getGroupname() {
		return groupname;
	}
	/**
	 * @param groupname the groupname to set
	 */
	public void setGroupname(String groupname) {
		this.groupname = groupname;
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String toString() {
		return "{listid=" + listid + ", catalogid=" + catalogid
				+ ", groupname=" + groupname + ", nikename=" + nikename
				+ ", descr=" + descr + ", note=" + note + ", status=" + status
				+ ", level=" + level + "}";
	}
	
}
