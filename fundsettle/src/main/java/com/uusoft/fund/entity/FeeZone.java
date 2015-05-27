package com.uusoft.fund.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.uusoft.fund.common.sql.Id;

public class FeeZone implements Serializable{
	private static final long serialVersionUID = 123432L;
	@Id("listid")
	private Long listid;
	private Long zoneid;
	private String upamount;
	private String downamount;
	private Integer valtype;
	private BigDecimal rateval;
	private Integer statux;
	public Long getListid() {
		return listid;
	}
	public void setListid(Long listid) {
		this.listid = listid;
	}
	public Long getZoneid() {
		return zoneid;
	}
	public void setZoneid(Long zoneid) {
		this.zoneid = zoneid;
	}
	public String getUpamount() {
		return upamount;
	}
	public void setUpamount(String upamount) {
		this.upamount = upamount;
	}
	public String getDownamount() {
		return downamount;
	}
	public void setDownamount(String downamount) {
		this.downamount = downamount;
	}
	public Integer getValtype() {
		return valtype;
	}
	public void setValtype(Integer valtype) {
		this.valtype = valtype;
	}
	public BigDecimal getRateval() {
		return rateval;
	}
	public void setRateval(BigDecimal rateval) {
		this.rateval = rateval;
	}
	
	public String toString() {
		return "{listid=" + listid + ", zoneid=" + zoneid
				+ ", upamount=" + upamount + ", downamount=" + downamount
				+ ", valtype=" + valtype + ", rateval=" + rateval
				+",statux="+statux+"}";
	}
	
	public FeeZone() {}
	public FeeZone(Long listid, Long zoneid, String upamount,
			String downamount, Integer valtype, BigDecimal rateval) {
		this.listid = listid;
		this.zoneid = zoneid;
		this.upamount = upamount;
		this.downamount = downamount;
		this.valtype = valtype;
		this.rateval = rateval;
	}
	public Integer getStatux() {
		return statux;
	}
	public void setStatux(Integer statux) {
		this.statux = statux;
	}
	
}
