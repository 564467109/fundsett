/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月7日  下午2:48:09
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.uusoft.fund.common.CollectUtils;

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
public class FeeSuiteSelectorExt {
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
			Table<String, String, FeeSuiteInfo> table = HashBasedTable.create();
			// 对validdate进行过滤，依次把记录分到森林的两个分支，与合作商完全匹配的一支（使用生效日期为子key），与合作商以通配符匹配的一支（使用生效日期为子key）
			for (FeeSuiteInfo ins : suites) {
				String cur = ins.getValiddate().toString();
				if (cur.compareTo(day) > 0)
					continue;
				else {
					String pt = ins.getPartner().trim();
					table.put(pt, cur, ins);// 商户,生效日期,记录
				}
			}
			
			FeeSuiteInfo ret = null;// 返回值
			Set<String> partners = table.rowKeySet();
			if (partners.contains(key)) {// 森林中如存在与合作商户完全匹配的一支则使用最近的日期即validdate中值最大的实例
				Map<String, FeeSuiteInfo> dict = table.row(key);
				String last = CollectUtils.getLastString(dict.keySet());
				ret = dict.get(last);
			} else {// 森林中不存在与合作商户完全匹配的一支，则使用通配符中一个分支则使用最近的日期即validdate中值最大的实例
				Map<String, FeeSuiteInfo> dict = table.row(FeeConst.DEFAULT_PARTNER);
				if (dict != null && dict.size() > 0) {
					String last = CollectUtils.getLastString(dict.keySet());
					ret = dict.get(last);
				}
			}
			table.clear();
			
			return ret;
		}
		
		return null;
	}
}
