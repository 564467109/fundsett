package com.uusoft.fund.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.uusoft.fund.settle.strategy.FeeConst;

public class FeeDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 134556L;
	/** 主键 */
	private Long listid;
	/** 策略分组id */
	private Long bookid;
	/** 受理渠道，如柜台，WEB交易等 */
	private String operway;
	/** 支付网点代码 */
	private String paycenterid;
	/** 支付渠道代码 */
	private String paychannelid;
	/** 业务代码[如20认购22申购24赎回] */
	private String businesscode;
	/** 计费基准（0默认计费，1持仓市值，2交易金额） */
	private Integer calmode;
	/** 计费策略（1固定费用，2固定比例，3金、份额分段） */
	private Integer calpolicy;
	/** 固定值（固定值或者固定比例） */
	private BigDecimal fixval;
	/** 费用分段编号（0表示无分段设置） */
	private Long zoneid;
	/** 分区左封闭 */
	private Integer leftclose;
	public Long getListid() {
		return listid;
	}
	public void setListid(Long listid) {
		this.listid = listid;
	}
	public Long getBookid() {
		return bookid;
	}
	public void setBookid(Long bookid) {
		this.bookid = bookid;
	}
	public String getOperway() {
		return operway;
	}
	public void setOperway(String operway) {
		this.operway = operway;
	}
	public String getPaycenterid() {
		return paycenterid;
	}
	public void setPaycenterid(String paycenterid) {
		this.paycenterid = paycenterid;
	}
	public String getPaychannelid() {
		return paychannelid;
	}
	public void setPaychannelid(String paychannelid) {
		this.paychannelid = paychannelid;
	}
	public String getBusinesscode() {
		return businesscode;
	}
	public void setBusinesscode(String businesscode) {
		this.businesscode = businesscode;
	}
	public Integer getCalmode() {
		return calmode;
	}
	public void setCalmode(Integer calmode) {
		this.calmode = calmode;
	}
	public Integer getCalpolicy() {
		return calpolicy;
	}
	public void setCalpolicy(Integer calpolicy) {
		this.calpolicy = calpolicy;
	}
	public BigDecimal getFixval() {
		return fixval;
	}
	public void setFixval(BigDecimal fixval) {
		this.fixval = fixval;
	}
	public Long getZoneid() {
		return zoneid;
	}
	public void setZoneid(Long zoneid) {
		this.zoneid = zoneid;
	}
	public Integer getLeftclose() {
		return leftclose;
	}
	public void setLeftclose(Integer leftclose) {
		this.leftclose = leftclose;
	}
	public String toString() {
		return "{listid=" + listid + ", bookid=" + bookid
				+ ", operway=" + operway + ", paycenterid=" + paycenterid
				+ ", paychannelid=" + paychannelid + ", businesscode="
				+ businesscode + ", calmode=" + calmode + ", calpolicy="
				+ calpolicy + ", fixval=" + fixval + ", zoneid=" + zoneid
				+ ", leftclose=" + leftclose + "}";
	}
	
}
