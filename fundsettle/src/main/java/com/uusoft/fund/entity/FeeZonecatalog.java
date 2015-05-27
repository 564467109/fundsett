package com.uusoft.fund.entity;

import java.io.Serializable;

import com.uusoft.fund.common.sql.Id;

public class FeeZonecatalog implements Serializable{
	private static final long serialVersionUID = 1231245645L;
	
	/** 主键 */
	@Id("listid")
	private Long listid;
	/** 费用类型 */
	private Integer feetype;
	/** 自定义类型 */
	private String ufcatalog;
	/** 昵称 */
	private String nikename;
	/** 描述 */
	private String descr;
	/** 备注 */
	private String note;
	/** 状态-是否有效 */
	private Integer statux;
	
	public Long getListid() {
		return listid;
	}
	public void setListid(Long listid) {
		this.listid = listid;
	}
	public Integer getFeetype() {
		return feetype;
	}
	public void setFeetype(Integer feetype) {
		this.feetype = feetype;
	}
	public String getUfcatalog() {
		return ufcatalog;
	}
	public void setUfcatalog(String ufcatalog) {
		this.ufcatalog = ufcatalog;
	}
	public String getNikename() {
		return nikename;
	}
	public void setNikename(String nikename) {
		this.nikename = nikename;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getStatux() {
		return statux;
	}
	public void setStatux(Integer statux) {
		this.statux = statux;
	}
	public String toString() {
		return "{listid=" + listid + ", feetype=" + feetype
				+ ", ufcatalog=" + ufcatalog + ", nikename=" + nikename
				+ ", descr=" + descr + ", note=" + note + ", statux=" + statux
				+ "}";
	}
	
	public FeeZonecatalog() {}
	
}
