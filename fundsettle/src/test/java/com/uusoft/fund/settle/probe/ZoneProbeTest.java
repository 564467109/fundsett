/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月15日  下午6:26:19
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.probe;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.uusoft.fund.settle.strategy.FeeZoneInfo;

/**
 * @author  guangyu@66money.com
 * @since  2015年5月15日 下午6:26:19
 *
 */
public class ZoneProbeTest {
	
	private List<FeeZoneInfo> rules1 = Lists.newArrayList();
	private List<FeeZoneInfo> rules2 = Lists.newArrayList();
	
	@Before
	public void setUp() {
		rules1.add(new FeeZoneInfo("^", "50", 0, new BigDecimal("0.2")));
		rules1.add(new FeeZoneInfo("50", "200", 0, new BigDecimal("0.25")));
		rules1.add(new FeeZoneInfo("200", "$", 0, new BigDecimal("0.3")));
		
		rules2.add(new FeeZoneInfo("^", "50", 0, new BigDecimal("0.3")));
		rules2.add(new FeeZoneInfo("50", "200", 0, new BigDecimal("0.2")));
		rules2.add(new FeeZoneInfo("200", "1500", 0, new BigDecimal("0.12")));
		rules2.add(new FeeZoneInfo("1500", "50000", 0, new BigDecimal("0.1")));
	}
	
	@Test
	public void batCheckAxisTest() {
		List<FeeZoneInfo> orgin = Lists.newArrayList();
		for (FeeZoneInfo info : rules1)
			info.setZoneid(2L);
		for (FeeZoneInfo info : rules2)
			info.setZoneid(3L);
		orgin.addAll(rules1);
		orgin.addAll(rules2);
		ZoneProbe probe = new ZoneProbe();
		Set<Long> set=probe.batCheckAxis(orgin);
		Assert.assertTrue(set.contains(3L));
		Assert.assertTrue(set.size() == 1);
	}
	
}
