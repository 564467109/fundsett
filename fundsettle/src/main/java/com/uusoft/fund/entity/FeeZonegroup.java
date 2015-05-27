package com.uusoft.fund.entity;

import java.io.Serializable;
import java.util.List;

import com.uusoft.fund.common.sql.Id;
import com.uusoft.fund.common.sql.NotColumn;

public class FeeZonegroup implements Serializable {

	private static final long serialVersionUID = 1456542567L;
	
	/** 主键 */
	@Id("listid")
	private Long listid;
	/** 所属分类id */
	private Long catalogid;
	/** 分组名 */
	private String groupname;
	/** 分组昵称 */
	private String nikename;
	/** 描述信息 */
	private String descr;
	/** 作为扩展字段，如在界面展示引用情况使用，但在数据库中不对此字段予以持久化保存 */
	private String note;
	/** 状态-是否有效 */
	private Integer statux;
	@NotColumn
	private List<FeeZone> feeZones;
	@NotColumn
	private String ufcatalog;
	public Long getListid() {
		return listid;
	}
	public void setListid(Long listid) {
		this.listid = listid;
	}
	public Long getCatalogid() {
		return catalogid;
	}
	public void setCatalogid(Long catalogid) {
		this.catalogid = catalogid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
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
		return "{listid=" + listid + ", catalogid=" + catalogid
				+ ", groupname=" + groupname + ", nikename=" + nikename
				+ ", descr=" + descr + ", note=" + note + ", statux=" + statux
				+ ", feeZones=" + feeZones + ", ufcatalog=" + ufcatalog + "}";
	}
	
	public String getUfcatalog() {
		return ufcatalog;
	}
	public void setUfcatalog(String ufcatalog) {
		this.ufcatalog = ufcatalog;
	}
	public List<FeeZone> getFeeZones() {
		return feeZones;
	}
	public void setFeeZones(List<FeeZone> feeZones) {
		this.feeZones = feeZones;
	}
	public FeeZonegroup() {}
	
}
