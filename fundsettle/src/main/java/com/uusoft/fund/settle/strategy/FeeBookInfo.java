/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月13日  下午1:52:13
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.io.Serializable;

/**
 * 费用策略细节的分组信息
 * @author  guangyu@66money.com
 * @since  2015年5月13日 下午1:52:13
 *
 */
public class FeeBookInfo implements Serializable{
	private static final long serialVersionUID = -3215813548927011451L;
	
	/** 主键  */
	private long listid;
	/** 费用类型 */
	private int feetype=0;
	/** 分组昵称  */
	private String bookname ="";
	/** 创建日期  */
	private String createdate = "";
	/**  状态-是否有效 */
	private int status = 0;
	/** 是否作为标准显示  */
	private int show = 0;
	/** 作为扩展字段，如在界面展示引用情况使用，但在数据库中不对此字段予以持久化保存  */
	private String note ="";
	
	public FeeBookInfo(){}
	
	/**
	 * @param listid 费用策略分组编号
	 * @param bookname 费用策略分组名
	 * @param feetype 费用类型
	 * @param createdt 创建日期
	 * @param status 状态
	 * @param show 是否显示
	 */
	public FeeBookInfo(long listid, String bookname, int feetype, String createdt, int status, int show){
		this.listid = listid;
		this.bookname = bookname;
		this.feetype = feetype;
		this.createdate = createdt;
		this.status = status;
		this.show = show;				
	}
	
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
	 * @return the bookname
	 */
	public String getBookname() {
		return bookname;
	}
	/**
	 * @param bookname the bookname to set
	 */
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	/**
	 * @return the createdate
	 */
	public String getCreatedate() {
		return createdate;
	}
	/**
	 * @param createdate the createdate to set
	 */
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
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
	/**
	 * @return the show
	 */
	public int getShow() {
		return show;
	}
	/**
	 * @param show the show to set
	 */
	public void setShow(int show) {
		this.show = show;
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
	
	
}
