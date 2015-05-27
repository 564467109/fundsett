/**
 * Copyright 2015 uusoft.com
 * All  right reserved.
 * Created on 2015年5月26日  下午6:11:58
 * Author:guangyu@66money.com
 * Rainveno@ 2015
 */
package com.uusoft.fund.settle.dispatch;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uusoft.fund.common.StrUtils;
import com.uusoft.fund.flow.dp.BalPump;
import com.uusoft.fund.flow.kcdata.BalFundInfo;
import com.uusoft.fund.settle.strategy.FeeConst;
import com.uusoft.fund.settle.strategy.FeeDetailInfo;
import com.uusoft.fund.settle.strategy.FeeDetailSelector;
import com.uusoft.fund.settle.strategy.FeeSuiteInfo;
import com.uusoft.fund.settle.strategy.FeeSuiteSelectorExt;
import com.uusoft.fund.settle.strategy.IRateCalc;

/**
 * @author guangyu@66money.com
 * @since 2015年5月26日 下午6:11:58
 *
 */
@Component
public class SaleAgent extends SuiteAgent {
	private Logger logger = LoggerFactory.getLogger(SaleAgent.class);
	
	// 智能机器人，计算商户与各个费用类型建立最优策略分划来完成每日的计算任务.
	
	public FeeDetailInfo strategy(String fundcode, String partner, String dt) {
		
		List<FeeSuiteInfo> suites = getSuites(FeeConst.FeeType.SAEL_SERVICE_FEE.getType(), fundcode, partner);
		FeeSuiteInfo suite = FeeSuiteSelectorExt.select(suites, partner, dt);
		if (suite != null) {
			long bookid = suite.getBookid();
			
			List<FeeDetailInfo> dets = getBookContent(bookid);
			FeeDetailInfo det = FeeDetailSelector.saleServiceQuery(dets);
//			if (det != null) {
//				int mode = det.getCalmode();
//				int policy = det.getCalpolicy();
//				IRateCalc calc = feeDetailFactory.buildFunc(det);
//				// TODO 使用上面三个变量来处理数据
//			}
			
			return det;
		}
		return null;
	}
	
	public void calc(String fundcode, String partner, String dt, String execdt){
		FeeDetailInfo det =null;
		if(!StrUtils.isEmpty(execdt))
			det = strategy(fundcode,partner, execdt);
		else
			det = strategy(fundcode,partner, dt);
		
		if(det!=null){
			int mode = det.getCalmode();
			int policy = det.getCalpolicy();
			IRateCalc calc = feeDetailFactory.buildFunc(det);
			
			// 使用上面三个变量来处理数据
			
			//准备数据
			if(FeeConst.FeeDatum.POSITION.getType() == mode){
				//TODO 持仓市值计算
				String relName = balPump.fsName(BalPump.DataType, BalPump.FundPrefix, fundcode, dt);
				Collection<BalFundInfo> collect = balPump.pumpFromFs(relName, true);
				BigDecimal total =new BigDecimal("0.0");
				for(BalFundInfo ins:collect){
					BigDecimal dec = calc.calc(ins.getFundvol().add(ins.getLast_fundvol()).add(ins.getTrd_fundvol()));
					total = total.add(dec);
					logger.info(total.toString());
				}
			}else if(FeeConst.FeeDatum.TRADE.getType() == mode){
				//TODO 交易金额计算
			}else{
				//TODO 默认计算
			}
		}
	}
	
	public List<String> fundcodes() {
		return null;
	}
	
	public List<String> salecodes() {
		return null;
	}
	
	public List<String> saleRelfunds() {
		return null;
	}
	
	@Autowired(required=false)
	protected BalPump balPump;
}
