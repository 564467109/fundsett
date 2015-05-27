/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月14日  下午6:09:21
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.common;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.uusoft.fund.common.CollectUtils;
import com.uusoft.fund.common.NumberAxis;

/**
 * @author guangyu@66money.com
 * @since 2015年5月14日 下午6:09:21
 *
 */
public class NumberAxisTest {
	private NumberAxis axis = new NumberAxis();
	
	@Test
	public void testPerfectCheck() {
		NumberAxis axis = new NumberAxis();
		
		List<String[]> sp1 = Lists.newArrayList();
		List<String[]> sp2 = Lists.newArrayList();
		List<String[]> sp3 = Lists.newArrayList();
		List<String[]> sp4 = Lists.newArrayList();
		List<String[]> sp5 = Lists.newArrayList();
		
		String[] arr1 = new String[] { "^", "50" };
		String[] arr2 = new String[] { "50", "200" };
		String[] arr3 = new String[] { "200", "$" };
		String[] arr4 = new String[] { "200", "500" };
		String[] arr5 = new String[] { "200", "600" };
		
		sp1 = CollectUtils.addEles(sp1, new String[][] { arr1, arr2, arr3 });
		sp2 = CollectUtils.addEles(sp2, new String[][] { arr1, arr2 });
		sp3 = CollectUtils.addEles(sp3, new String[][] { arr3, arr2 });
		sp4 = CollectUtils.addEles(sp4, new String[][] { arr1, arr2, arr3, arr4 });
		sp5 = CollectUtils.addEles(sp5, new String[][] { arr1, arr2, arr5, arr4 });
		
		boolean bln = axis.isPerfect(sp1);
		Assert.assertTrue(bln);
		bln = axis.isPerfect(sp2);
		Assert.assertFalse(bln);
		bln = axis.isPerfect(sp3);
		Assert.assertFalse(bln);
		bln = axis.isPerfect(sp4);
		Assert.assertFalse(bln);
		bln = axis.isPerfect(sp5);
		Assert.assertFalse(bln);
		
	}
	
	/**
	 * 测试数轴运算中的比较器
	 */
	@Test
	public void testCompare() {
		int ret1 = axis.symbolCompare("^", "7");
		Assert.assertEquals(ret1, -1);
		
		ret1 = axis.symbolCompare("7", "^");
		Assert.assertEquals(ret1, 1);
		
		ret1 = axis.symbolCompare("7", "7");
		Assert.assertEquals(ret1, 0);
		ret1 = axis.symbolCompare("^", "^");
		Assert.assertEquals(ret1, 0);
		
		ret1 = axis.symbolCompare("7", "8");
		Assert.assertEquals(ret1, -1);
		
		ret1 = axis.symbolCompare("7", "$");
		Assert.assertEquals(ret1, -1);
		ret1 = axis.symbolCompare("$", "7");
		Assert.assertEquals(ret1, 1);
		
		ret1 = axis.symbolCompare("$", "$");
		Assert.assertEquals(ret1, 0);
		
		ret1 = axis.symbolCompare("^", "$");
		Assert.assertEquals(ret1, -1);
		ret1 = axis.symbolCompare("^1", "$");
		Assert.assertEquals(ret1, -1);
		
//		ret1 = axis.symbolCompare("a1", "7");
//		Assert.assertEquals(ret1, -1);
	}
}
