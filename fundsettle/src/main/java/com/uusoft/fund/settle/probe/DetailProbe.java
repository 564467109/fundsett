/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月15日  下午7:26:19
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.probe;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.uusoft.fund.settle.strategy.FeeConst;
import com.uusoft.fund.settle.strategy.FeeDetailInfo;
import com.uusoft.fund.settle.strategy.FeeZoneInfo;

/**
 * 计费策略详细对应的探针
 * 
 * @author guangyu@66money.com
 * @since 2015年5月15日 下午7:26:19
 *
 */
public class DetailProbe {
	private Logger logger = LoggerFactory.getLogger(DetailProbe.class);
	
	/**
	 * 检查各个策略详细是否完整对应费用字段
	 * 
	 * @param list
	 * @return
	 */
	public List<FeeDetailInfo> getUnfinishedDetail(List<FeeDetailInfo> list) {
		// TODO:获取被检查对象list
		
		final List<FeeDetailInfo> ret = Lists.newArrayList();
		for (FeeDetailInfo info : list) {
			if (isFinishedDetail(info))
				ret.add(info);
		}
		
		return ret;
	}
	
	/**
	 * 是否已经完成策略细节的设置
	 * 
	 * @param det
	 * @return 如果分区的指向的分组已经失效仍会返回真值
	 */
	private boolean isFinishedDetail(FeeDetailInfo det) {
		if (det == null)
			return false;
		
		if (FeeConst.FeePolicy.AMOUNT_LEVEL.getType() == det.getCalpolicy()) {
			// 使用分区分段功能，检查分区分段功能
			if (det.getZoneid() > 0)
				return true;
			return false;
		} else {
			// 使用定值或者定比例策略，检查是否设置了值
			// BigDecimal dec = det.getFixval();
			return true;
		}
	}
	
	/**
	 * 检查各个策略详细中使用分区设置中分区有问题的集合
	 * 
	 * @param list
	 */
	public List<FeeDetailInfo> getUnPerfectDetail(List<FeeDetailInfo> list) {
		
		List<FeeDetailInfo> ret = Lists.newArrayList();
		
		Set<Long> zg = Sets.newHashSet();
		// 把计算策略为分区的分成两种，没有设置好zoneid的直接加入ret,设置好zoneid的做下一步检查
		for (FeeDetailInfo info : list) {
			if (FeeConst.FeePolicy.AMOUNT_LEVEL.getType() == info.getCalpolicy()) {
				// 使用分区分段功能，检查分区分段功能
				if (info.getZoneid() > 0)
					ret.add(info);
				else
					zg.add(info.getZoneid());
			}
		}
		
		if (zg.size() > 0) {
			// TODO:获取zoneid为zg中的FeeZoneInfo的列表
			List<FeeZoneInfo> zones = Lists.newArrayList();
			
			ZoneProbe zp = new ZoneProbe();
			final Set<Long> bads = zp.batCheckAxis(zones);
			if (bads.size() > 0) {
				for (FeeDetailInfo info : list) {
					if (bads.contains(info.getListid()))
						ret.add(info);
				}
			}
			zones.clear();
		}
		
		return ret;
	}
}
