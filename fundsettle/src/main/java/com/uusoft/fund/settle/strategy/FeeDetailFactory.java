/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月7日  上午10:15:42
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.strategy;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.uusoft.fund.dao.FeeZoneMapper;

/**
 * 计费策略计算方法的生产工厂类
 * 
 * @author guangyu@66money.com
 * @since 2015年5月7日 上午10:15:42
 * 
 */
@Component
public class FeeDetailFactory {
	/**
	 * 使用传入的收费策略规则来生成计算方法的接口
	 * 
	 * @param rule
	 * @return
	 */
	public IRateCalc buildFunc(FeeDetailInfo rule) {
		IRateCalc calc = null;
		if (rule == null)
			return calc;
		else {
			final FeeDetailInfo det = rule;
			calc = new IRateCalc() {
				
				@Override
				public BigDecimal calc(BigDecimal dec) {
					if (det.getCalpolicy() == FeeConst.FeeDetailPolicyEnum.FIX_VAL.getVal())// 固定值
						return det.getFixval();
					else if (det.getCalpolicy() == FeeConst.FeeDetailPolicyEnum.FIX_RATE.getVal())// 固定费率
						return det.getFixval().multiply(dec);
					else if (det.getCalpolicy() == FeeConst.FeeDetailPolicyEnum.ZONE.getVal()) {// 分区段处理
						// 获取det.getZoneid()对应的zones予以优化
						List<FeeZoneInfo> zones = getZoneContext(det.getZoneid());
						return RateZoneFactory.buildFunc(zones, det.getLeftclose() == 1 ? true : false).calc(dec);
					} else
						return new BigDecimal("0.0");
				}
			};
			
		}
		
		return calc;
	}
	
	/**
	 * 获取费用分区中某一组的分区设置信息列表
	 * 
	 * @param zoneid
	 * @return
	 */
	private List<FeeZoneInfo> getZoneContext(long zoneid) {
		String bookSql = "SELECT * FROM FEE_ZONE WHERE zoneid='%s'";
		List<FeeZoneInfo> dets = jdbcTemplate.query(String.format(bookSql, zoneid), feeZoneMapper);
		
		return dets;
	}
	
	private FeeZoneMapper feeZoneMapper = new FeeZoneMapper();
	@Resource
	private JdbcTemplate jdbcTemplate;
}
