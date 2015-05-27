/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月6日  下午4:39:28
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.math.BigDecimal;
import java.util.List;

/**
 * 计费区间策略计算方法的生产工厂类
 * 
 * @author guangyu@66money.com
 * @since 2015年5月6日 下午4:39:28
 * 
 */
public class RateZoneFactory {
	
	/**
	 * 使用传入的区间划分规则来生成计算方法的接口
	 * @param rules
	 * @param blnLeftClose 是否左封闭
	 * @return
	 */
	public static IRateCalc buildFunc(List<FeeZoneInfo> rules, final boolean blnLeftClose) {
		
		final List<FeeZoneInfo> rulx = rules;
		
		IRateCalc rule = new IRateCalc() {
			
			@Override
			public BigDecimal calc(BigDecimal di) {
				BigDecimal ret = new BigDecimal("0.0");
				double dec = di.doubleValue();
				for (FeeZoneInfo fi : rulx) {
					if (isBetween(fi, blnLeftClose, dec)) {
						if (fi.getValtype() == FeeConst.FeeZoneModeEnum.RATE.getVal())// 按比例收取
							return di.multiply(fi.getRateval());
						else
							// 固定值
							return fi.getRateval();
					}
				}
				return ret;
			}
			
			/**
			 * val是否在fi的控制范围内
			 * @param fi
			 * @param blnLeft 是否左封闭
			 * @param val
			 * @return
			 */
			private boolean isBetween(FeeZoneInfo fi, boolean blnLeft, final double val) {
				String down = fi.getDownamount().trim();
				String up = fi.getUpamount().trim();
				
				if (FeeConst.FeeSymbol.DOWN_TOKEN.getSymbol().equals(down)) {
					if(FeeConst.FeeSymbol.UP_TOKEN.getSymbol().equals(up))//修正区间中同时存在^和$的情况
						return true;
					
					if (val < new BigDecimal(up).doubleValue())
						return true;
					else
						return false;
				} else if (new BigDecimal(down).doubleValue() <= val)
					return true;
				
				return false;
			}
		};
		
		return rule;
	}
}
