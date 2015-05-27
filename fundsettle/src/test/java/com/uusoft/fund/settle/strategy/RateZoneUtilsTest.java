/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月6日  下午4:45:34
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.uusoft.fund.common.DtUtils;
import com.uusoft.fund.settle.strategy.FeeZoneInfo;
import com.uusoft.fund.settle.strategy.IRateCalc;
import com.uusoft.fund.settle.strategy.RateZoneFactory;

/**
 * @author guangyu@66money.com
 * @since 2015年5月6日 下午4:45:34
 * 
 */
public class RateZoneUtilsTest {
	
	private List<FeeZoneInfo> rules1 = Lists.newArrayList();
	private List<FeeZoneInfo> rules2 = Lists.newArrayList();
	
	@Before
	public void setUp() {
		rules1.add(new FeeZoneInfo("^", "50", 0, new BigDecimal("0.2")));
		rules1.add(new FeeZoneInfo("50", "200", 0, new BigDecimal("0.25")));
		rules1.add(new FeeZoneInfo("200", "1500", 0, new BigDecimal("0.3")));
		
		rules2.add(new FeeZoneInfo("^", "50", 0, new BigDecimal("0.3")));
		rules2.add(new FeeZoneInfo("50", "200", 0, new BigDecimal("0.2")));
		rules2.add(new FeeZoneInfo("200", "1500", 0, new BigDecimal("0.12")));
		rules2.add(new FeeZoneInfo("1500", "50000", 0, new BigDecimal("0.1")));
	}
	
	/**
	 * 测试multiMap
	 */
	@Test
	public void testMultiMap() {
		ArrayListMultimap<Long, FeeZoneInfo> mmap = ArrayListMultimap.create();
		List<FeeZoneInfo> orgin = Lists.newArrayList();
		for (FeeZoneInfo info : rules1)
			info.setZoneid(2L);
		for (FeeZoneInfo info : rules2)
			info.setZoneid(3L);
		orgin.addAll(rules1);
		orgin.addAll(rules2);
		for (FeeZoneInfo info : orgin) {
			mmap.put(info.getZoneid(), info);
		}
		
		Map<Long, Collection<FeeZoneInfo>> dict = mmap.asMap();
		Collection<FeeZoneInfo> collect2 = dict.get(2L);
		Collection<FeeZoneInfo> collect3 = dict.get(3L);
		Assert.assertEquals(rules1, collect2);
		Assert.assertEquals(rules2, collect3);
		
		Assert.assertNotEquals(rules1, collect3);
	}
	
	/**
	 * 测试多次创建运算式是否成功 Test method for {@link com.uusoft.fund.settle.strategy.RateZoneFactory#buildFunc(java.util.List)}.
	 */
	@Test
	public void testBuildFunc() {
		IRateCalc calc1 = RateZoneFactory.buildFunc(rules1, true);
		IRateCalc calc2 = RateZoneFactory.buildFunc(rules2, false);
		
		List<BigDecimal> listIn = Lists.newArrayList();
		listIn.add(new BigDecimal("1.23"));
		listIn.add(new BigDecimal("50"));
		listIn.add(new BigDecimal("60.23"));
		listIn.add(new BigDecimal("200"));
		listIn.add(new BigDecimal("250"));
		
		System.out.println("---------------calc1-----------------");
		for (BigDecimal di : listIn) {
			BigDecimal dec = calc1.calc(di);
			System.out.println(di.toString() + "   =>   " + dec.toString());
		}
		System.out.println("---------------calc2-----------------");
		for (BigDecimal di : listIn) {
			BigDecimal dec = calc2.calc(di);
			System.out.println(di.toString() + "   =>   " + dec.toString());
		}
		
		calc1 = RateZoneFactory.buildFunc(rules1, true);
		calc2 = RateZoneFactory.buildFunc(rules2, true);
		
		System.out.println("---------------calc1-----------------");
		for (BigDecimal di : listIn) {
			BigDecimal dec = calc1.calc(di);
			System.out.println(di.toString() + "   =>   " + dec.toString());
		}
		System.out.println("---------------calc2-----------------");
		for (BigDecimal di : listIn) {
			BigDecimal dec = calc2.calc(di);
			System.out.println(di.toString() + "   =>   " + dec.toString());
		}
	}
	
	/**
	 * 测试计算速度与普通计算速度的比较
	 */
	@Ignore
	@Test
	public void testSpeed() {
		
		IRateCalc calc1 = RateZoneFactory.buildFunc(rules1, true);
		
		List<BigDecimal> listIn = Lists.newArrayList();
		listIn.add(new BigDecimal("1.23"));
		listIn.add(new BigDecimal("50"));
		listIn.add(new BigDecimal("60.23"));
		listIn.add(new BigDecimal("200"));
		listIn.add(new BigDecimal("250"));
		
		List<BigDecimal> listMul = Lists.newArrayList();
		listMul.add(new BigDecimal("0.25"));
		listMul.add(new BigDecimal("0.3"));
		listMul.add(new BigDecimal("0.35"));
		listMul.add(new BigDecimal("0.45"));
		listMul.add(new BigDecimal("0.55"));
		
		testFunc(calc1, listIn, 100);
		testDirect(listMul, listIn, 100);
		System.out.println("");
		
		testFunc(calc1, listIn, 1000);
		testDirect(listMul, listIn, 1000);
		System.out.println("");
		
		testFunc(calc1, listIn, 1000);
		testDirect(listMul, listIn, 1000);
		System.out.println("");
		
		testFunc(calc1, listIn, 1000);
		testDirect(listMul, listIn, 1000);
		System.out.println("");
		
		testFunc(calc1, listIn, 1000);
		testDirect(listMul, listIn, 1000);
		System.out.println("");
		
		testFunc(calc1, listIn, 1000);
		testDirect(listMul, listIn, 1000);
		System.out.println("");
		
		testFunc(calc1, listIn, 10000);
		testDirect(listMul, listIn, 10000);
		System.out.println("");
		
		testFunc(calc1, listIn, 10000);
		testDirect(listMul, listIn, 10000);
		System.out.println("");
		
		testFunc(calc1, listIn, 10000);
		testDirect(listMul, listIn, 10000);
		System.out.println("");
		
		testFunc(calc1, listIn, 100000);
		testDirect(listMul, listIn, 100000);
		System.out.println("");
		
		testFunc(calc1, listIn, 100000);
		testDirect(listMul, listIn, 100000);
		System.out.println("");
		
		testFunc(calc1, listIn, 100000);
		testDirect(listMul, listIn, 100000);
		System.out.println("");
		
		testFunc(calc1, listIn, 100000);
		testDirect(listMul, listIn, 100000);
		System.out.println("");
		
	}
	
	/**
	 * 测试计算速度是否与分区段数线性相关
	 */
	@Test
	@Ignore
	public void testLinear() {
		
		List<FeeZoneInfo> rules1 = Lists.newArrayList();
		
		rules1.add(new FeeZoneInfo("^", "20", 0, new BigDecimal("0.2")));
		rules1.add(new FeeZoneInfo("20", "30", 0, new BigDecimal("0.25")));
		rules1.add(new FeeZoneInfo("30", "40", 0, new BigDecimal("0.25")));
		rules1.add(new FeeZoneInfo("40", "50", 0, new BigDecimal("0.25")));
		rules1.add(new FeeZoneInfo("50", "100", 0, new BigDecimal("0.25")));
		rules1.add(new FeeZoneInfo("100", "200", 0, new BigDecimal("0.25")));
		rules1.add(new FeeZoneInfo("200", "1500", 0, new BigDecimal("0.3")));
		
		IRateCalc calc1 = RateZoneFactory.buildFunc(rules1, true);
		
		IRateCalc calc2 = RateZoneFactory.buildFunc(rules2, true);
		
		List<BigDecimal> listIn = Lists.newArrayList();
		listIn.add(new BigDecimal("1.23"));
		listIn.add(new BigDecimal("20"));
		listIn.add(new BigDecimal("30.23"));
		listIn.add(new BigDecimal("55"));
		listIn.add(new BigDecimal("250"));
		
		int time = 1000;
		long use = testCalcSpec(calc2, listIn, time);
		System.out.println("level3 call ---------------" + time + "*5----------------- use time [" + use + "]ms");
		use = testCalcSpec(calc1, listIn, time);
		System.out.println("level7 call ---------------" + time + "*5----------------- use time [" + use + "]ms");
		
		System.out.println("");
		
		time = 10000;
		use = testCalcSpec(calc2, listIn, time);
		System.out.println("level3 call ---------------" + time + "*5----------------- use time [" + use + "]ms");
		use = testCalcSpec(calc1, listIn, time);
		System.out.println("level7 call ---------------" + time + "*5----------------- use time [" + use + "]ms");
		
		System.out.println("");
		
		time = 100000;
		use = testCalcSpec(calc2, listIn, time);
		System.out.println("level3 call ---------------" + time + "*5----------------- use time [" + use + "]ms");
		use = testCalcSpec(calc1, listIn, time);
		System.out.println("level7 call ---------------" + time + "*5----------------- use time [" + use + "]ms");
		
		System.out.println("");
		
		time = 1000000;
		use = testCalcSpec(calc2, listIn, time);
		System.out.println("level3 call ---------------" + time + "*5----------------- use time [" + use + "]ms");
		use = testCalcSpec(calc1, listIn, time);
		System.out.println("level7 call ---------------" + time + "*5----------------- use time [" + use + "]ms");
		
		System.out.println("");
		
	}
	
	/**
	 * @param calc
	 * @param listIn
	 * @param time
	 * @return
	 */
	protected long testCalcSpec(IRateCalc calc, List<BigDecimal> listIn, int time) {
		DateTime start = new DateTime();
		for (int icnt = 0; icnt < time; icnt++)
			for (BigDecimal di : listIn) {
				calc.calc(di);
			}
		
		return DtUtils.duration(start, true);
	}
	
	protected void testFunc(IRateCalc calc1, List<BigDecimal> listIn, int time) {
		DateTime start = new DateTime();
		System.out.print("func call ---------------" + time + "*5-----------------");
		for (int icnt = 0; icnt < time; icnt++)
			for (BigDecimal di : listIn) {
				calc1.calc(di);
			}
		
		System.out.println("use time [" + DtUtils.duration(start, true) + "]ms");
	}
	
	protected void testDirect(List<BigDecimal> listMul, List<BigDecimal> listIn, int time) {
		DateTime start = new DateTime();
		System.out.print("direct call ---------------" + time + "*5-----------------");
		for (int icnt = 0; icnt < time; icnt++)
			for (BigDecimal di : listIn) {
				// for(BigDecimal mul:listMul)
				// di.multiply(mul);
				listMul.get(0).multiply(di);
			}
		
		System.out.println("use time [" + DtUtils.duration(start, true) + "]ms");
	}
	
}
