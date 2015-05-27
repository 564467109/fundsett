/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月14日  下午4:26:16
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.probe;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.uusoft.fund.common.CollectUtils;
import com.uusoft.fund.common.NumberAxiz;
import com.uusoft.fund.settle.strategy.FeeZoneInfo;
import com.uusoft.fund.settle.strategy.ZoneGroupInfo;

/**
 * 分区的校验探针
 * 
 * @author guangyu@66money.com
 * @since 2015年5月14日 下午4:26:37
 *
 */
@Component
public class ZoneProbe {
	/**
	 * 是否完整分区覆盖检测
	 * 
	 * @param zones
	 * @return
	 */
	public boolean isValidZones(Collection<FeeZoneInfo> zones) {
		if (CollectUtils.isEmpty(zones))
			return false;
		Function<FeeZoneInfo, String[]> func = new Function<FeeZoneInfo, String[]>() {
			@Override
			public String[] apply(FeeZoneInfo info) {
				String down = info.getDownamount().trim();
				String up = info.getUpamount().trim();
				return new String[] { down, up };
			}
		};
		
		Collection<String[]> scopes = Collections2.transform(zones, func);
		return NumberAxiz.on().isPerfect(scopes);
	}
	
	/**
	 * 检查一个分类的分区的未完善情况，得到出问题的分区信息
	 * @param subCatalog
	 * @return &lt;[catalogid, zoneid, zonename]&gt;
	 */
	public Iterator<String[]> subCatalog(String subCatalog) {
		//TODO:获取相关Group定义
		List<ZoneGroupInfo> gps = Lists.newArrayList();
		//TODO:获取相关分区列表
		List<FeeZoneInfo> orgin = Lists.newArrayList();
		//得到orgin中各个分组中存在不完美分区的设置对应的zoneid集合
		final Set<Long> set = batCheckAxis(orgin);
		if(CollectUtils.isEmpty(set))
			return null;
		else{
			//过滤出在set中出现的分组记录
			UnmodifiableIterator<ZoneGroupInfo> it=  Iterators.filter(gps.iterator(), new Predicate<ZoneGroupInfo>() {
				@Override
				public boolean apply(ZoneGroupInfo info) {
					return set.contains(info.getListid());
				} 
			});
			
			//从分组记录中提取匹配的信息
			Function<ZoneGroupInfo, String[]> func = new Function<ZoneGroupInfo, String[]>() {
				@Override
				public String[] apply(ZoneGroupInfo info) {
					if(set.contains(info.getListid()))
						return new String[]{String.valueOf(info.getCatalogid()), String.valueOf(info.getListid()), info.getGroupname()};
					return null;
				}
			};
			Iterator<String[]> ret = Iterators.transform(it, func);
			
			return ret;
		}
	}
	
	/**
	 * 批处理检查不是完美分段的分区分组设置
	 * 
	 * @param zones
	 * @return
	 */
	public Set<Long> batCheckAxis(List<FeeZoneInfo> zones) {
		HashSet<Long> ret = Sets.newHashSet();
		
		// 分组
		ArrayListMultimap<Long, FeeZoneInfo> mmap = ArrayListMultimap.create();
		for (FeeZoneInfo info : zones) {
			mmap.put(info.getZoneid(), info);
		}
		
		// 逐组过滤
		Map<Long, Collection<FeeZoneInfo>> dict = mmap.asMap();
		for (Long key : dict.keySet()) {
			Collection<FeeZoneInfo> gp = dict.get(key);
			if (!isValidZones(gp)) {
				ret.add(key);
			}
		}
		
		return ret;
	}
	
}
