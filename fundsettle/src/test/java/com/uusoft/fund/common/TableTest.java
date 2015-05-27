/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月8日  下午1:20:39
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.common;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * 测试guava中Table的测试用例
 * @author guangyu@66money.com
 * @since 2015年5月8日 下午1:20:39
 * 
 */
public class TableTest {
	@Test
	public void mock(){
		Table<String, String, Float> weightedGraph = HashBasedTable.create();
		weightedGraph.put("a", "1", 4f);
		weightedGraph.put("a", "2", 20f);
		weightedGraph.put("b", "2", 5f);
		
		Map<String, Float> map = weightedGraph.row("a"); // returns a Map mapping 1 to 4, 2 to 20
		System.out.println(map.toString());
		map = weightedGraph.column("2"); // returns a Map mapping a to 20, b to 5
		System.out.println(map.toString());
	}
}
