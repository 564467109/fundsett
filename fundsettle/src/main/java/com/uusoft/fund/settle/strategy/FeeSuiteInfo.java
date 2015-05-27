/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月5日  下午3:44:12
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.io.Serializable;

/**
 * 费用策略信息【封装了一段时间内的费用的组合信息】
 * 
 * @author guangyu@66money.com
 * @since 2015年5月5日 下午3:44:12
 * 
 */
public class FeeSuiteInfo implements Serializable {
	
	private static final long serialVersionUID = 4459195668961848960L;
	
	private long listid;// 主键
	/** 计算对象，一般为基金代码  */
	private String accountcode = "";
	private int status = 0;// 是否有效，为0正常，为1则逻辑删除
	/** 对应类型参考FeeConst中的定义, 计费类型，如销售服务费1，交易手续费2 */
	private int feetype = 0;//
	/** 合作商，如果是联泰则保持为空 */
	private String partner = "";
	/** 生效日期[如同20150504这样的yyyyMMdd格式] */
	private String validdate = FeeConst.DEFAULT_VALIDDATE;
	/** 策略分组id */
	private long bookid = 0L;
	
	public long getListid() {
		return listid;
	}
	
	public void setListid(long listid) {
		this.listid = listid;
	}
	
	public String getAccountcode() {
		return accountcode;
	}
	
	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}
	
	/**
	 * 是否有效，为0正常，为1则逻辑删除
	 * 
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * 是否有效，为0正常，为1则逻辑删除
	 * 
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * 对应类型参考FeeConst中的定义, 计费类型，如销售服务费1，交易手续费2
	 * 
	 * @return the feetype
	 */
	public int getFeetype() {
		return feetype;
	}
	
	/**
	 * 对应类型参考FeeConst中的定义, 计费类型，如销售服务费1，交易手续费2
	 * 
	 * @param feetype the feetype to set
	 */
	public void setFeetype(int feetype) {
		this.feetype = feetype;
	}
	
	/**
	 * 合作商，如果是联泰则保持为空
	 * 
	 * @return
	 */
	public String getPartner() {
		return partner;
	}
	
	/**
	 * 合作商，如果是联泰则保持为空
	 * 
	 * @param partner
	 */
	public void setPartner(String partner) {
		this.partner = partner;
	}
	
	/**
	 * 生效日期[如同20150504这样的yyyyMMdd格式]
	 * 
	 * @return the validdate
	 */
	public String getValiddate() {
		return validdate;
	}
	
	/**
	 * 生效日期[如同20150504这样的yyyyMMdd格式]
	 * 
	 * @param validdate the validdate to set
	 */
	public void setValiddate(String validdate) {
		this.validdate = validdate;
	}
	
	/**
	 * 策略分组id
	 * 
	 * @return the bookid
	 */
	public long getBookid() {
		return bookid;
	}
	
	/**
	 * 策略分组id
	 * 
	 * @param bookid the bookid to set
	 */
	public void setBookid(long bookid) {
		this.bookid = bookid;
	}
	
	/**
	 * 
	 */
	public FeeSuiteInfo() {
	}
	
	/**
	 * @param listid 主键
	 * @param accountcode 计费对象，如fundcode或者垫支行账户
	 * @param partner 合作商，如果是联泰则保持为空
	 * @param status 是否有效，为0正常，为1则逻辑删除
	 * @param feetype 计费类型，如销售服务费1，交易手续费2
	 * @param validdate 生效日期
	 */
	public FeeSuiteInfo(long listid, String accountcode, String partner, int status, int feetype, String validdate) {
		this.listid = listid;
		this.accountcode = accountcode;
		this.partner = partner;
		this.status = status;
		this.feetype = feetype;
		this.validdate = validdate;
	}
	
	/**
	 * @param listid 主键
	 * @param accountcode 计费对象，如fundcode或者垫支行账户
	 * @param partner 合作商，如果是联泰则保持为空
	 * @param status 是否有效，为0正常，为1则逻辑删除
	 * @param feetype 计费类型，如销售服务费1，交易手续费2
	 * @param validdate 生效日期
	 * @param bookid 策略分组id
	 */
	public FeeSuiteInfo(long listid, String accountcode, String partner, int status, int feetype, String validdate, long bookid) {
		this.listid = listid;
		this.accountcode = accountcode;
		this.partner = partner;
		this.status = status;
		this.feetype = feetype;
		this.validdate = validdate;
		this.bookid = bookid;
	}
	
	/** 格式化展示字符模板 */
	private final static String formatter = "listid:%s,validdate:%s,feetype:%s,partner:[%s],accountcode:%s,status:%s,bookid:%s";
	
	public String toString() {
		return String.format(formatter, this.listid, this.validdate, this.feetype, this.partner, this.accountcode, this.status, this.bookid);
	}
	
}
