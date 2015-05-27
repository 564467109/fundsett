/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月7日  上午11:00:46
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.util.List;

/**
 * 在同一suiteid中多个FeeDetailInfo中选择一个适合匹配的FeeDetailInfo来计算
 * 
 * @author guangyu@66money.com
 * @since 2015年5月7日 上午11:00:46
 * 
 */
public class FeeDetailSelector {
	/**
	 * 销售服务费选择
	 * 
	 * @param dts
	 * @return
	 */
	public static FeeDetailInfo saleServiceQuery(List<FeeDetailInfo> dts) {
		// 销售服务费计算策略中可变因素是使用工作日和销售服务费率，故只需返回第0个规则就好
		if (dts != null && dts.size() > 0)
			return dts.get(0);
		return null;
	}
	
	/**
	 * 客户服务费选择
	 * 
	 * @param dts
	 * @return
	 */
	public static FeeDetailInfo custServiceQuery(List<FeeDetailInfo> dts) {
		// 客户服务费计算策略中可变因素是使用工作日和客户服务费率，故只需返回第0个规则就好
		if (dts != null && dts.size() > 0)
			return dts.get(0);
		return null;
	}
	
	/**
	 * 交易手续费选择
	 * <p>
	 * dd
	 * </p>
	 * 
	 * @param dts
	 * @param businesscode 交易代码
	 * @param channelid 交易使用支付渠道
	 * @param paycenterid 交易使用支付网点
	 * @return
	 */
	public static FeeDetailInfo transTradeQuery(List<FeeDetailInfo> dts, int businesscode, String channelid, String paycenterid) {
		// TODO:优先寻找在businesscode及targetcode与传入值匹配的，然后找channelid即paycenterid与传入值匹配的记录，选择最匹配的记录返回
		return null;
	}
	
	/**
	 * 垫支利息费选择
	 * 
	 * @param dts
	 * @return
	 */
	public static FeeDetailInfo loaningQuery(List<FeeDetailInfo> dts) {
		// 垫支利息费计算策略中可变因素是使用利率的类型和浮动率，故只需返回第0个规则就好
		if (dts != null && dts.size() > 0)
			return dts.get(0);
		return null;
	}
	
	/**
	 * 监管费选择
	 * 
	 * @param dts
	 * @param channelid
	 * @param paycenterid
	 * @return
	 */
	public static FeeDetailInfo monitorQuery(List<FeeDetailInfo> dts, String channelid, String paycenterid) {
		// TODO:监管费费计算策略中可变因素是channelid即paycenterid与传入值匹配的记录
		// Table<String,String, FeeDetailInfo> table = Tables.newCustomTable();
		return null;
	}
}
