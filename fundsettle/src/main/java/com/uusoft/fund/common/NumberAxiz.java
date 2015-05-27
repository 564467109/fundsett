/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月14日  下午5:30:25
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.common;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.uusoft.fund.settle.strategy.FeeConst;

/**
 * 数轴检测的静态方法，没有公有类构造器，只有静态构造方法。用来检查是否覆盖数轴问题
 * <p>
 * 用法 如下：
 * <ul>
 * <li>NumberAxiz.on().isPerfect(Collection&lt;String[]&gt;);</li>
 * <li>NumberAxiz.on(leftBorder, rightBorder).isPerfect(Collection&lt;String[]&gt;);</li>
 * <li>NumberAxiz.on().useForLeft(leftBorder).useForRight(rightBorder).isPerfect(Collection&lt;String[]&gt;);</li>
 * </ul>
 * </p>
 * 
 * @author guangyu@66money.com
 * @since 2015年5月14日 下午5:30:25
 *
 */
public class NumberAxiz {
	private String leftBorder = FeeConst.FeeSymbol.DOWN_TOKEN.getSymbol();
	private String rightBorder = FeeConst.FeeSymbol.UP_TOKEN.getSymbol();
	
	/**
	 * 数轴
	 */
	private NumberAxiz() {
	}
	
	/**
	 * 数轴
	 * 
	 * @param prototype
	 */
	private NumberAxiz(NumberAxiz prototype) {
		this.leftBorder = prototype.leftBorder;
		this.rightBorder = prototype.rightBorder;
	}
	
	/**
	 * 数轴
	 * 
	 * @param leftSymbol 左边界
	 * @param rightSymbol 右边界
	 */
	private NumberAxiz(String leftSymbol, String rightSymbol) {
		Preconditions.checkArgument(!StrUtils.isEmpty(leftSymbol), "left border can't be empty");
		Preconditions.checkArgument(!StrUtils.isEmpty(rightSymbol), "right border can't be empty");
		
		this.leftBorder = leftSymbol;
		this.rightBorder = rightSymbol;
	}
	
	/**
	 * 用做数轴的左边界
	 * 
	 * @param symbol
	 * @return
	 */
	public NumberAxiz useForLeft(final String symbol) {
		Preconditions.checkNotNull(symbol);
		this.leftBorder = symbol;
		return new NumberAxiz(this) {
			@Override
			public NumberAxiz useForLeft(final String nullText) {
				throw new UnsupportedOperationException("already specified leftBorder");
			}
		};
	}
	
	/**
	 * 用做数轴的右边界
	 * 
	 * @param symbol
	 * @return
	 */
	public NumberAxiz useForRight(final String symbol) {
		Preconditions.checkNotNull(symbol);
		this.rightBorder = symbol;
		return new NumberAxiz(this) {
			@Override
			public NumberAxiz useForRight(final String nullText) {
				throw new UnsupportedOperationException("already specified rightBorder");
			}
		};
	}
	
	/**
	 * 使用默认边界
	 * 
	 * @return
	 */
	public static NumberAxiz on() {
		return new NumberAxiz();
	}
	
	/**
	 * 设定边界
	 * 
	 * @param left 左边界
	 * @param right 右边界
	 * @return
	 */
	public static NumberAxiz on(String left, String right) {
		return new NumberAxiz(left, right) {
			@Override
			public NumberAxiz useForLeft(final String nullText) {
				throw new UnsupportedOperationException("already specified leftBorder");
			}
			
			@Override
			public NumberAxiz useForRight(final String nullText) {
				throw new UnsupportedOperationException("already specified rightBorder");
			}
		};
	}
	
	/**
	 * 给定scopes是否完美覆盖了整个数轴
	 * 
	 * @param scopes
	 * @return
	 */
	public boolean isPerfect(Collection<String[]> scopes) {
		// 如果scopes为空则不会整个覆盖数轴，如果scopes中只有一个元素则只有[^,$]能覆盖数轴，
		// 如果scopes存在多个元素则用私有办法来采用从头到尾的检查方法来完成检查.
		if (CollectUtils.isEmpty(scopes))
			return false;
		else {
			int len = scopes.size();
			if (len == 1) {
				String[] arr = scopes.iterator().next();
				if (leftBorder.equals(arr[0]) && rightBorder.equals(arr[1]))
					return true;
				else
					return false;
			} else {
				return isPerfectAxis(scopes);
			}
		}
	}
	
	/**
	 * 检查输入是否是一个完美的数轴，无遗漏无重叠
	 * 
	 * @param scopes
	 * @return
	 */
	private boolean isPerfectAxis(final Collection<String[]> scopes) {
		Preconditions.checkNotNull(scopes, "输入值为空");
		// 检查思路为把scopes中个值对放入到一个map，值对的左值和右值分别作为map的key和value。
		// 从左侧[^]开始取map中的value，取出value作为新的key来取新的value。
		// 经过一个迭代过程如能从[^]找到[$]则铺满整个数轴。
		// 在迭代中把匹配的值对同时删除，如果匹配完成应该没有遗留值对，否则存在重叠的区域。
		
		Map<String, String> biMap = new HashMap<String, String>();
		// 检查各个区间的边界是否是有效数字.如果在向数轴添加过程中出现重复key则必然重合，直接返回假
		Iterator<String[]> it = scopes.iterator();
		while (it.hasNext()) {
			String[] arr = it.next();
			if (isValidSymbol(arr[0]) && isValidSymbol(arr[1])) {
				if (biMap.containsKey(arr[0]))
					return false;
				else
					biMap.put(arr[0], arr[1]);
			} else
				return false;
		}
		it = null;
		
		// 从左到右的检查是否可到达正无穷大
		String in = leftBorder;
		String tar = "";
		while (biMap.containsKey(in)) {
			tar = biMap.get(in);
			biMap.remove(in);
			in = tar;
		}
		
		boolean ret = false;
		// 检查'穿线'的成果
		if (tar.equals(rightBorder) && biMap.size() == 0)
			ret = true;
		biMap.clear();
		biMap = null;
		
		return ret;
	}
	
	/**
	 * 是否是有效符号
	 * 
	 * @param str
	 * @return
	 */
	protected boolean isValidSymbol(String str) {
		if (leftBorder.equals(str) || rightBorder.equals(str))
			return true;
		
		return StrUtils.isDecimal(str);
	}
	
	/**
	 * 符号比较
	 * 
	 * @param num1
	 * @param num2
	 * @return 若第一个参数小于第二个参数返回-1，否则返回-1，相等返回0
	 */
	public int symbolCompare(String num1, String num2) {
		Preconditions.checkNotNull(num1, "[" + num1 + "]不能为空");
		Preconditions.checkNotNull(num2, "[" + num2 + "]不能为空");
		if (num1.equals(num2))
			return 0;
		if (leftBorder.equals(num1) || rightBorder.equals(num2))
			return -1;
		else if (leftBorder.equals(num2) || rightBorder.equals(num1))
			return 1;
		return new BigDecimal(num1).doubleValue() > new BigDecimal(num2).doubleValue() ? 1 : -1;
	}
}
