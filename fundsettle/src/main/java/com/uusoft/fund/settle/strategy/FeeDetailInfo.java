/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月5日  下午4:07:04
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 费用信息细节
 * 
 * @author guangyu@66money.com
 * @since 2015年5月5日 下午4:07:04
 *
 */
public class FeeDetailInfo implements Serializable {
	
	private static final long serialVersionUID = 3336256945343088300L;
	
	/** 主键 */
	private long listid;
	/** 策略分组id */
	private long bookid = 0L;
	/** 受理渠道，如柜台，WEB交易等 */
	private String operway = FeeConst.DEFAULT_OPERWAY;
	/** 支付网点代码 */
	private String paycenterid = "";
	/** 支付渠道代码 */
	private String paychannelid = "";
	/** 业务代码[如20认购22申购24赎回] */
	private String businesscode = "";
	/** 计费基准（0默认计费，1持仓市值，2交易金额） */
	private int calmode = 0;
	/** 计费策略（1固定费用，2固定比例，3金、份额分段） */
	private int calpolicy = 0;
	/** 固定值（固定值或者固定比例） */
	private BigDecimal fixval = new BigDecimal("0.0");
	/** 费用分段编号（0表示无分段设置） */
	private long zoneid = 0L;
	/** 分区左封闭 */
	private int leftclose = 1;
	
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
	 * 策略分组id
	 * 
	 * @return the suiteid
	 */
	public long getBookid() {
		return bookid;
	}
	
	/**
	 * 策略分组id
	 * 
	 * @param bookid
	 */
	public void setBookid(long bookid) {
		this.bookid = bookid;
	}
	
	/**
	 * @return the operway
	 */
	public String getOperway() {
		return operway;
	}
	
	/**
	 * @param operway the operway to set
	 */
	public void setOperway(String operway) {
		this.operway = operway;
	}
	
	/**
	 * @return the paycenterid
	 */
	public String getPaycenterid() {
		return paycenterid;
	}
	
	/**
	 * @param paycenterid the paycenterid to set
	 */
	public void setPaycenterid(String paycenterid) {
		this.paycenterid = paycenterid;
	}
	
	/**
	 * @return the paychannelid
	 */
	public String getPaychannelid() {
		return paychannelid;
	}
	
	/**
	 * @param paychannelid the paychannelid to set
	 */
	public void setPaychannelid(String paychannelid) {
		this.paychannelid = paychannelid;
	}
	
	/**
	 * @return the businesscode
	 */
	public String getBusinesscode() {
		return businesscode;
	}
	
	/**
	 * @param businesscode the businesscode to set
	 */
	public void setBusinesscode(String businesscode) {
		this.businesscode = businesscode;
	}
	
	/**
	 * 计费基准（0默认计费，1持仓市值，2交易金额）
	 * 
	 * @return the calmode
	 */
	public int getCalmode() {
		return calmode;
	}
	
	/**
	 * 计费基准（0默认计费，1持仓市值，2交易金额）
	 * 
	 * @param calmode the calmode to set
	 */
	public void setCalmode(int calmode) {
		this.calmode = calmode;
	}
	
	/**
	 * 计费策略（1固定费用，2固定比例，3金、份额分段）
	 * 
	 * @return the calpolicy
	 */
	public int getCalpolicy() {
		return calpolicy;
	}
	
	/**
	 * 计费策略（1固定费用，2固定比例，3金、份额分段）
	 * 
	 * @param calpolicy the calpolicy to set
	 */
	public void setCalpolicy(int calpolicy) {
		this.calpolicy = calpolicy;
	}
	
	/**
	 * @return the fixval
	 */
	public BigDecimal getFixval() {
		return fixval;
	}
	
	/**
	 * @param fixval the fixval to set
	 */
	public void setFixval(BigDecimal fixval) {
		this.fixval = fixval;
	}
	
	/**
	 * @return the zoneid
	 */
	public long getZoneid() {
		return zoneid;
	}
	
	/**
	 * @param zoneid the zoneid to set
	 */
	public void setZoneid(long zoneid) {
		this.zoneid = zoneid;
	}
	
	/**
	 * 分区左封闭
	 * 
	 * @return the leftclose
	 */
	public int getLeftclose() {
		return leftclose;
	}
	
	/**
	 * 分区左封闭
	 * 
	 * @param leftclose the leftclose to set
	 */
	public void setLeftclose(int leftclose) {
		this.leftclose = leftclose;
	}
	
}
