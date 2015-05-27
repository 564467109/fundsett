/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月7日  下午2:48:09
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 对FeeSuiteInfo中 accountcode和feetype字段与待处理对象一致的status为0值的suite的list进行匹配查询出最匹配的记录
 * 
 * <p>
 * <b>当前选择策略简要描述</b>比如对合作商户xxx相关的accountcode和feetype字段与待处理对象一致的status为0值的suite的list有四条记录如下:
 * <ul>
 * <li>1）生效日期为20150320且partner为xxx的记录A；</li>
 * <li>2）生效日期为20150325且partner为xxx的记录B;</li>
 * <li>3）生效日期为20150320且partner为@的记录C;</li>
 * <li>4）生效日期为20150330且partner为@的记录D。</li>
 * </ul>
 * 根据partner的适配原则分成如下两个分支的森林-->与partner强匹配的分支1={A,B};与partner相近匹配的分支2={C,D}。<br>
 * <ul>
 * <li>如果存在分支1，则对分支1中的元素筛选出生效日期靠后的记录B返回；</li>
 * <li>假设不存在分支1，则对分支2中的元素筛选出生效日期靠后的记录D返回；</li>
 * </ul>
 * </p>
 * <p>
 * 上面使用的策略是<B>强匹配优化</B>下面介绍下另一种策略<B>最新策略优先</B><br>
 * 具体执行即以生效日期来分成有三个分支的森林
 * <ul>
 * <li>20150320的分支3={A,C}
 * <li>20150325的分支4={B}
 * <li>20150330的分支5={D}
 * </ul>
 * 再次筛选则对日期靠后的分支5中取数据如存在partner为xxx的记录则返回，否则返回相似匹配的记录。 就本例而言返回值即D。若分支3是日期靠后的分支则返回A，因为A的匹配度更高。
 * </p>
 * 
 * @author guangyu@66money.com
 * @since 2015年5月7日 下午2:48:09
 * 
 */
@Deprecated
public class FeeSuiteSelector {
	/**
	 * 对FeeSuiteInfo中 accountcode和feetype字段与待处理对象一致的status为0值的suite的list进行匹配查询出最匹配的记录
	 * 
	 * @param suites
	 * @param partner
	 * @param dt
	 * @return
	 */
	public static FeeSuiteInfo select(List<FeeSuiteInfo> suites, String partner, String dt) {
		String key = partner.trim();
		if ("".equals(dt)) {
			// TODO：dt不应该为空，是否增加异常处理
			return null;
		}
		String day = dt.trim();
		if (suites != null && suites.size() > 0) {
			Map<String, Map<String, FeeSuiteInfo>> forest = Maps.newHashMap();
			// 对validdate进行过滤，依次把记录分到森林的两个分支，与合作商完全匹配的一支（使用生效日期为子key），与合作商以通配符匹配的一支（使用生效日期为子key）
			for (FeeSuiteInfo ins : suites) {
				String cur = ins.getValiddate().toString();
				if (cur.compareTo(day) > 0)
					continue;
				else {
					String pt = ins.getPartner().trim();
					if (pt.equals(key) || pt.equals(FeeConst.DEFAULT_PARTNER)) {// 保留与partner匹配的记录到森林
						addLeaf(forest, ins);
					}
				}
			}
			
			FeeSuiteInfo ret = null;// 返回值
			if (forest.containsKey(key)) {// 森林中如存在与合作商户完全匹配的一支则使用最近的日期即validdate中值最大的实例
				Map<String, FeeSuiteInfo> subs = forest.get(key);
				ret = getLastestIns(subs);
				
			} else {
				// 森林中不存在与合作商户完全匹配的一支，则使用通配符中一个分支则使用最近的日期即validdate中值最大的实例
				Map<String, FeeSuiteInfo> subs = forest.get(FeeConst.DEFAULT_PARTNER);
				ret = getLastestIns(subs);
			}
			forest.clear();
			
			return ret;
		}
		
		return null;
	}
	
	/**
	 * 给森林增加一片叶子
	 * 
	 * @param forest
	 * @param leaf
	 */
	private static void addLeaf(Map<String, Map<String, FeeSuiteInfo>> forest, FeeSuiteInfo leaf) {
		
		String cur = leaf.getValiddate().toString();
		String pt = leaf.getPartner().trim();
		
		if (forest.containsKey(pt))
			forest.get(pt).put(cur, leaf);
		else {
			Map<String, FeeSuiteInfo> sub = Maps.newHashMap();
			sub.put(cur, leaf);
			forest.put(pt, sub);
		}
	}
	
	/**
	 * 取树中validdate最新的记录
	 * 
	 * @param map 键值为validdate的map
	 * @return
	 */
	private static FeeSuiteInfo getLastestIns(Map<String, FeeSuiteInfo> map) {
		if (map == null || map.size() == 0)
			return null;
		else {
			List<String> days = Lists.newArrayList(map.keySet());//
			Collections.sort(days);
			Collections.reverse(days);
			return map.get(days.get(0));
		}
	}
}
