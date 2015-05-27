/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月5日  下午3:20:04
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * 费用常量定义
 * 
 * @author guangyu@66money.com
 * @since 2015年5月5日 下午3:20:04
 * 
 */
public class FeeConst {
	
	/** 默认生效日期[20141203] */
	public static String DEFAULT_VALIDDATE = "20141203";
	
	/** 默认受理渠道[@] */
	public static String DEFAULT_OPERWAY = "@";
	
	/** 默认合作商户[@] */
	public static String DEFAULT_PARTNER = "@";
	
	/**
	 * 费用类型
	 *
	 */
	public enum FeeType {
		
		/** 销售服务费[1] */
		SAEL_SERVICE_FEE(1,"销售服务费"),
		/** 客户维护务费[2] */
		CUSTOMER_HOLD_FEE(2,"客户维护务费"),
		/** 交易手续费[3] */
		TRANS_TRADE_FEE(3,"交易手续费"),
		/** 垫资利息费[4] */
		LOANING_INTEREST(4,"垫资利息费"),
		/** 监管费[5] */
		MONITOR_FEE(5,"监管费"),
		/** 转换费[6] */
		CONVER_FEE(6,"转换费");
		
		private int type;
		private String val;
		
		public int getType() {
			return type;
		}
		
		public void setType(int type) {
			this.type = type;
		}
		
		public String getVal() {
			return val;
		}

		public void setVal(String val) {
			this.val = val;
		}

		private FeeType(int type,String val) {
			this.type = type;
			this.val=val;
		}
		public static List<FeeType> getFeeTypes(){
			FeeType feeTypes[]=FeeType.values();
			List<FeeType> list=new ArrayList<FeeType>();
			for(int i=0;i<feeTypes.length;i++){
				list.add(feeTypes[i]);
			}
			return list;
		}
		public static FeeType getFeeType(int type){
			FeeType feeTypes[]=FeeType.values();
			for(int i=0;i<feeTypes.length;i++){
				if(feeTypes[i].getType()==type){
					return feeTypes[i];
				}
			}
			return null;
		}
	}
	
	/**
	 * 计费区间通配符
	 *
	 */
	public enum FeeSymbol {
		
		/** 计费分区下限通配符[^] */
		DOWN_TOKEN("^"),
		/** 计费分区上限通配符[$] */
		UP_TOKEN("$");
		
		private String symbol;
		
		public String getSymbol() {
			return symbol;
		}
		
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		
		private FeeSymbol(String symbol) {
			this.symbol = symbol;
		}
	}
	
	/**
	 * 费用基准
	 *
	 */
	public enum FeeDatum {
		
		/** 0默认计费 */
		DEF_FEE(0,"默认计费"),
		/** 1持仓市值 */
		POSITION(1,"持仓市值"),
		/** 2交易金额 */
		TRADE(2,"交易金额");
		
		private int type;
		private String value;
		
		private FeeDatum(int type,String value) {
			this.type = type;
			this.value = value;
		}
		
		public int getType() {
			return type;
		}
		
		public void setType(int type) {
			this.type = type;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	/**
	 * 计费策略
	 *
	 */
	public enum FeePolicy {
		
		/** 1固定费用 */
		FIXED_FEE(1,"固定费用"),
		/** 2固定比例 */
		FIXED_RATIO(2,"固定比例"),
		/** 3金、份额分段 */
		AMOUNT_LEVEL(3,"金、份额分段");
		
		private int type;
		private String value;
		
		public int getType() {
			return type;
		}
		
		public void setType(int type) {
			this.type = type;
		}
		
		private FeePolicy(int type,String value) {
			this.type = type;
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	/**
	 * 业务代码
	 *
	 */
	public enum BusinessType {
		
		/** 20认购 **/
		SUBSCRIBE(20,"认购"),
		/** 22申购 **/
		APPLY(22,"申购"),
		/** 24赎回 **/
		REDEEM(24,"赎回");
		
		private int type;
		private String value;
		
		public int getType() {
			return type;
		}
		
		public void setType(int type) {
			this.type = type;
		}
		
		private BusinessType(int type,String value) {
			this.type = type;
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	/**
	 * 费用详细中费用计算策略枚举（1固定费用，2固定比例，3金、份额分段）
	 * 
	 * @author guangyu@66money.com
	 * @since 2015年5月7日 上午10:23:28
	 * 
	 */
	public enum FeeDetailPolicyEnum {
		/** [1] 固定费用 */
		FIX_VAL(1,"固定费用"),
		/** [2] 固定比例 */
		FIX_RATE(2,"固定比例"),
		/** [3] 金、份额分段 */
		ZONE(3,"金、份额分段");
		
		private int val;
		private String des;
		
		/**
		 * @param idx
		 */
		FeeDetailPolicyEnum(int idx,String des) {
			val = idx;
			this.des = des;
		}
		
		/**
		 * 获取对应的int值
		 * 
		 * @return
		 */
		public int getVal() {
			return this.val;
		}

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}
		
	}
	
	/**
	 * 费用分区间计算策略枚举
	 * 
	 * @author guangyu@66money.com
	 * @since 2015年5月6日 下午7:20:56
	 * 
	 */
	public enum FeeZoneModeEnum {
		/** 按比例计算[0] */
		RATE(0,"按比例计算"),
		/** 按固定值计算[1] */
		FIXVAL(1,"按固定值计算");
		
		private int val;
		private String des;
		
		/**
		 * @param idx
		 */
		FeeZoneModeEnum(int idx,String des) {
			val = idx;
			this.des = des;
		}
		
		/**
		 * 获取对应的int值
		 * 
		 * @return
		 */
		public int getVal() {
			return this.val;
		}

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}
		
	}
	
}
