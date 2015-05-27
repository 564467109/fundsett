/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月19日  下午5:29:59
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.dispatch;

import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uusoft.fund.flow.kcdata.BalFundInfo;

/**
 * @author  guangyu@66money.com
 * @since  2015年5月19日 下午5:29:59
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class BalPumpTest {
	
	@Ignore
	@Test
	public void test(){
		//balPump.pumpFromCoreDB("");
		String day = "20150209";
		//Collection<BalFundInfo> collect = balPump.pumpFromCoreDB(day);
//		balPump.deflateTo(collect, true, balPump.fsName("net", "bal", "all", day));
//		
//		Collection<BalFundInfo> collect2 = balPump.pumpFromFs(balPump.fsName("net", "bal", "all", day), true);
//		int len = collect2.size();
//		len++;
	}
	
//	@Test
//	public void testDes(){
//		String day = "20150209";
//		Collection<String> collect = balPump.listSubGroup("BAL", "FUND", day);
//		System.out.print(collect.toString());
//	}
//	
//	@Autowired(required=false)
//	private BalPump balPump;
}
