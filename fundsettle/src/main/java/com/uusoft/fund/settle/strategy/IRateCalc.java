/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月6日  下午4:32:42
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.math.BigDecimal;

/**
 * 执行费率区间计算接口
 * 
 * @author guangyu@66money.com
 * @since 2015年5月6日 下午4:32:42
 * 
 */
public interface IRateCalc {
	/**
	 * 执行费率区间计算接口
	 * 
	 * @param dec 输入数字
	 * @return 返回对应费用值结果
	 */
	public BigDecimal calc(BigDecimal dec);
}
