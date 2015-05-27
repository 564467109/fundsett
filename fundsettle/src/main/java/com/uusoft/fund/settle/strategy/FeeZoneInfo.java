/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月5日  下午5:11:16
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.io.Serializable;
import java.math.BigDecimal;

import com.google.common.base.Joiner;

/**
 * 费用分区间计算策略的信息
 * 
 * @author guangyu@66money.com
 * @since 2015年5月5日 下午5:11:16
 * 
 */
public class FeeZoneInfo implements Serializable {
	private static final long serialVersionUID = 48656632451043318L;
	
	/** 主键 */
	private long listid;
	/** 费用分区编号，与FeeDetailInfo中的zoneid相对,与ZoneGroupInfo中的listid对应 */
	private long zoneid = 0L;
	/** 上限 */
	private String upamount = "";
	/** 下限 */
	private String downamount = "";
	/** 设置值的类型（0-比例 ，1-固定值） */
	private int valtype = 0;
	/** 设置值 */
	private BigDecimal rateval = new BigDecimal("0.0");
	
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
	 * 费用分区编号，与FeeDetailInfo中的zoneid相对应
	 * 
	 * @return the zoneid
	 */
	public long getZoneid() {
		return zoneid;
	}
	
	/**
	 * 费用分区编号，与FeeDetailInfo中的zoneid相对应
	 * 
	 * @param zoneid the zoneid to set
	 */
	public void setZoneid(long zoneid) {
		this.zoneid = zoneid;
	}
	
	/**
	 * 上限
	 * 
	 * @return the upamount
	 */
	public String getUpamount() {
		return upamount;
	}
	
	/**
	 * 上限
	 * 
	 * @param upamount the upamount to set
	 */
	public void setUpamount(String upamount) {
		this.upamount = upamount;
	}
	
	/**
	 * 下限
	 * 
	 * @return the downamount
	 */
	public String getDownamount() {
		return downamount;
	}
	
	/**
	 * 下限
	 * 
	 * @param downamount the downamount to set
	 */
	public void setDownamount(String downamount) {
		this.downamount = downamount;
	}
	
	/**
	 * 设置值的类型（0-比例 ，1-固定值）
	 * 
	 * @return the valtype
	 */
	public int getValtype() {
		return valtype;
	}
	
	/**
	 * 设置值的类型（0-比例 ，1-固定值）
	 * 
	 * @param valtype the valtype to set
	 */
	public void setValtype(int valtype) {
		this.valtype = valtype;
	}
	
	/**
	 * @return the rateval
	 */
	public BigDecimal getRateval() {
		return rateval;
	}
	
	/**
	 * @param rateval the rateval to set
	 */
	public void setRateval(BigDecimal rateval) {
		this.rateval = rateval;
	}
	
	/**
	 * 
	 */
	public FeeZoneInfo() {
	}
	
	/**
	 * @param upamount 上限（应该是数字，或者是上限的通配符$）
	 * @param valtype 设置值的类型（0-比例 ，1-固定值）
	 * @param downamount 下限（应该是数字，或者是上限的通配符^）
	 * @param rateval 设置值
	 */
	public FeeZoneInfo(String downamount, String upamount, int valtype, BigDecimal rateval) {
		this.upamount = upamount;
		this.downamount = downamount;
		this.valtype = valtype;
		this.rateval = rateval;
	}
	
	/**
	 * @param zoneid 费用分区编号，与FeeDetailInfo中的zoneid相对应
	 * @param downamount 下限（应该是数字，或者是上限的通配符^）
	 * @param upamount 上限（应该是数字，或者是上限的通配符$）
	 * @param valtype 设置值的类型（0-比例 ，1-固定值）
	 * @param rateval 设置值
	 */
	public FeeZoneInfo(long zoneid, String downamount, String upamount, int valtype, BigDecimal rateval) {
		this(downamount, upamount, valtype, rateval);
		this.zoneid = zoneid;
	}
	
	/**
	 * @param listid id值，默认传0
	 * @param zoneid 费用分区编号，与FeeDetailInfo中的zoneid相对应
	 * @param downamount 下限（应该是数字，或者是上限的通配符^）
	 * @param upamount 上限（应该是数字，或者是上限的通配符$）
	 * @param valtype 设置值的类型（0-比例 ，1-固定值）
	 * @param rateval 设置值
	 */
	public FeeZoneInfo(long listid, long zoneid, String downamount, String upamount, int valtype, BigDecimal rateval) {
		this(downamount, upamount, valtype, rateval);
		this.zoneid = zoneid;
		this.listid = listid;
	}
	
	/** 格式化展示字符模板 */
	private final static String formatTemplate = "listid:%s,zoneid:%s,upamount:%s,downamount:%s,valtype:%s,rateval:%s";
	
	public String toString() {
		return String.format(formatTemplate, listid, zoneid, upamount, downamount, valtype, rateval);
	}
	
	/**
	 * 展示用户友好的内容显示
	 * 
	 * @param blnLeftClose 是否左封闭区间，否则是右封闭区间
	 * @return
	 */
	public String toUfString(boolean blnLeftClose) {
		String[] arr = new String[4];
		if (blnLeftClose) {
			// 根据上下限的存在把条件表达式元素拼出来
			if (FeeConst.FeeSymbol.DOWN_TOKEN.getSymbol().equals(downamount)) {
				arr[0] = "[ ";
				arr[1] = "M";
			} else {
				arr[0] = "[ " + downamount.trim();
				arr[1] = "<=M";
			}
			if (FeeConst.FeeSymbol.UP_TOKEN.getSymbol().equals(upamount)) {
				arr[2] = " ]";
			} else {
				arr[2] = "<" + upamount.trim() + " ]";
			}
		} else {
			// 根据上下限的存在把条件表达式元素拼出来
			if (FeeConst.FeeSymbol.DOWN_TOKEN.getSymbol().equals(downamount)) {
				arr[0] = "[ ";
				arr[1] = "M";
			} else {
				arr[0] = "[ " + downamount.trim();
				arr[1] = "<M";
			}
			if (FeeConst.FeeSymbol.UP_TOKEN.getSymbol().equals(upamount)) {
				arr[2] = " ]";
			} else {
				arr[2] = "<=" + upamount.trim() + " ]";
			}
		}
		
		// 根据求值方式把结果给展示出来
		if (valtype == 0)
			arr[3] = " -> M*" + rateval.toString();
		else
			arr[3] = " -> " + rateval.toString();
		
		return Joiner.on("").skipNulls().join(arr);
	}
	
}
