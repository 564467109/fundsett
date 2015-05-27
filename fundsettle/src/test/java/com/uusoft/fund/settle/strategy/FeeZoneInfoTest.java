/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月6日  下午3:18:29
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

/**
 * @author  guangyu@66money.com
 * @since  2015年5月6日 下午3:18:29
 *
 */
public class FeeZoneInfoTest{
	FeeZoneInfo zi1 = new FeeZoneInfo() ;
	FeeZoneInfo zi2 = new FeeZoneInfo() ;
	FeeZoneInfo zi3 = new FeeZoneInfo() ;
	@Before
	public void setUp() {
        zi1=new FeeZoneInfo(1, "^", "50", 1, new BigDecimal("0.1"));
        zi2=new FeeZoneInfo(1, "50", "500", 0, new BigDecimal("0.05"));
        zi3=new FeeZoneInfo(1, "500", "$", 1, new BigDecimal("0.5"));
    }

	
	@Test
	public void testFeeZoneInfo() {
		System.out.println(zi1.toString());
	}
	
	@Test
	public void testToString() {
		System.out.println(zi1.toString());
	}
	
	@Test
	public void testToUfString() {
		System.out.println("left close range");
		System.out.println(zi1.toUfString(true));
		System.out.println(zi2.toUfString(true));
		System.out.println(zi3.toUfString(true));
		System.out.println("right close range");
		System.out.println(zi1.toUfString(false));
		System.out.println(zi2.toUfString(false));
		System.out.println(zi3.toUfString(false));
	}
	
}
