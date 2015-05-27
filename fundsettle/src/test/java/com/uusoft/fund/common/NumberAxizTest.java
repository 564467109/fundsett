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
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * 数轴方法测试用例
 * 
 * @author guangyu@66money.com
 * @since 2015年5月14日 下午6:09:21
 *
 */
public class NumberAxizTest {
	List<String[]> sp1, sp2, sp3, sp4, sp5;
	
	@Before
	public void SetUp() {
		sp1 = Lists.newArrayList();
		sp2 = Lists.newArrayList();
		sp3 = Lists.newArrayList();
		sp4 = Lists.newArrayList();
		sp5 = Lists.newArrayList();
		
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
	}
	
	/**
	 * 常规正负无穷测试
	 */
	@Test
	public void testPerfectCommonCheck() {
		
		boolean bln = NumberAxiz.on().isPerfect(sp1);
		Assert.assertTrue(bln);
		bln = NumberAxiz.on().isPerfect(sp2);
		Assert.assertFalse(bln);
		bln = NumberAxiz.on().isPerfect(sp3);
		Assert.assertFalse(bln);
		bln = NumberAxiz.on().isPerfect(sp4);
		Assert.assertFalse(bln);
		bln = NumberAxiz.on().isPerfect(sp5);
		Assert.assertFalse(bln);
		
	}
	
	/**
	 * 指定区间数轴测试
	 */
	@Test
	public void testPerfectAbnormalCheck() {
		
		sp1 = Lists.newArrayList();
		sp2 = Lists.newArrayList();
		sp3 = Lists.newArrayList();
		sp4 = Lists.newArrayList();
		sp5 = Lists.newArrayList();
		
		String[] arr1 = new String[] { "1", "50" };
		String[] arr2 = new String[] { "50", "200" };
		String[] arr3 = new String[] { "200", "1000" };
		String[] arr4 = new String[] { "200", "500" };
		String[] arr5 = new String[] { "200", "600" };
		
		sp1 = CollectUtils.addEles(sp1, new String[][] { arr1, arr2, arr3 });
		sp2 = CollectUtils.addEles(sp2, new String[][] { arr1, arr2 });
		sp3 = CollectUtils.addEles(sp3, new String[][] { arr3, arr2 });
		sp4 = CollectUtils.addEles(sp4, new String[][] { arr1, arr2, arr3, arr4 });
		sp5 = CollectUtils.addEles(sp5, new String[][] { arr1, arr2, arr5, arr4 });
		
		boolean bln = NumberAxiz.on("1", "1000").isPerfect(sp1);
		Assert.assertTrue(bln);
		bln = NumberAxiz.on("1", "1000").isPerfect(sp2);
		Assert.assertFalse(bln);
		bln = NumberAxiz.on("1", "1000").isPerfect(sp3);
		Assert.assertFalse(bln);
		bln = NumberAxiz.on("1", "1000").isPerfect(sp4);
		Assert.assertFalse(bln);
		bln = NumberAxiz.on("1", "1000").isPerfect(sp5);
		Assert.assertFalse(bln);
		try {
			bln = NumberAxiz.on("1", "1000").useForLeft("1").useForRight("100").isPerfect(sp5);
			System.out.println("no catch ex. nopass");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(" catch ex. pass!!!");
		}
		
		try {
			bln = NumberAxiz.on("", "").useForLeft("1").useForRight("100").isPerfect(sp5);
			System.out.println("no catch ex. nopass");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(" catch ex. pass!!!");
		}
		
	}
	
	@Test
	public void testResetBorder() {
		sp1 = Lists.newArrayList();
		sp2 = Lists.newArrayList();
		sp3 = Lists.newArrayList();
		sp4 = Lists.newArrayList();
		sp5 = Lists.newArrayList();
		
		String[] arr1 = new String[] { "1", "50" };
		String[] arr2 = new String[] { "50", "200" };
		String[] arr3 = new String[] { "200", "1000" };
		String[] arr4 = new String[] { "200", "500" };
		String[] arr5 = new String[] { "200", "600" };
		
		sp1 = CollectUtils.addEles(sp1, new String[][] { arr1, arr2, arr3 });
		sp2 = CollectUtils.addEles(sp2, new String[][] { arr1, arr2 });
		sp3 = CollectUtils.addEles(sp3, new String[][] { arr3, arr2 });
		sp4 = CollectUtils.addEles(sp4, new String[][] { arr1, arr2, arr3, arr4 });
		sp5 = CollectUtils.addEles(sp5, new String[][] { arr1, arr2, arr5, arr4 });
		
		try {
			NumberAxiz.on("1", "1000").useForLeft("1").useForRight("100").isPerfect(sp5);
			System.out.println("no catch ex. nopass");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(" catch ex. pass!!!");
		}
		
		try {
			NumberAxiz.on("", "").useForLeft("1").useForRight("100").isPerfect(sp5);
			System.out.println("no catch ex. nopass");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(" catch ex. pass!!!");
		}
	}
	
	/**
	 * 环形测试
	 */
	@Test
	public void testPerfectRingCheck2() {
		sp1 = Lists.newArrayList();
		sp2 = Lists.newArrayList();
		sp3 = Lists.newArrayList();
		sp4 = Lists.newArrayList();
		sp5 = Lists.newArrayList();
		
		String[] arr1 = new String[] { "1", "50" };
		String[] arr2 = new String[] { "50", "200" };
		String[] arr3 = new String[] { "200", "1" };
		String[] arr4 = new String[] { "200", "500" };
		String[] arr5 = new String[] { "200", "600" };
		
		sp1 = CollectUtils.addEles(sp1, new String[][] { arr1, arr2, arr3 });
		sp2 = CollectUtils.addEles(sp2, new String[][] { arr1, arr2 });
		sp3 = CollectUtils.addEles(sp3, new String[][] { arr3, arr2 });
		sp4 = CollectUtils.addEles(sp4, new String[][] { arr1, arr2, arr3, arr4 });
		sp5 = CollectUtils.addEles(sp5, new String[][] { arr1, arr2, arr5, arr4 });
		
		boolean bln = NumberAxiz.on("1", "1").isPerfect(sp1);
		Assert.assertTrue(bln);
		bln = NumberAxiz.on("1", "1").isPerfect(sp2);
		Assert.assertFalse(bln);
		bln = NumberAxiz.on("1", "1").isPerfect(sp3);
		Assert.assertFalse(bln);
		bln = NumberAxiz.on("1", "1000").isPerfect(sp4);
		Assert.assertFalse(bln);
		bln = NumberAxiz.on("1", "1000").isPerfect(sp5);
		Assert.assertFalse(bln);
		
	}
	
}
