package com.uusoft.fund.web;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uusoft.fund.common.sql.OperateDB;
import com.uusoft.fund.entity.FeeZone;
import com.uusoft.fund.flow.dp.BalPump;
import com.uusoft.fund.services.IFeeSuiteService;

@Controller
public class TestController {
	
	private static Logger log = LoggerFactory.getLogger(TestController.class);
	@Resource
	IFeeSuiteService IFeeSuiteService;
	
	@Resource
	OperateDB operateDB;
	
	@Autowired(required=true)
	private BalPump balPump;
	
	
	@RequestMapping("/test")
	@ResponseBody
	public void test(){
		log.info("==========分润系统使用 LogBack 输出===========");
		System.out.println("rwer");
		IFeeSuiteService.test();
		System.out.println(operateDB);
		FeeZone feeZone=new FeeZone();
		feeZone.setZoneid(9L);
		operateDB.insert(feeZone);
		List<FeeZone> feeZones=operateDB.queryByMulti(feeZone);
		System.out.println(feeZones);
	}
	
	@RequestMapping("/bal")
	@ResponseBody
	public void bal(){
		log.info("==========分润系统测试输出===========");
		String day = "20150520";
		Collection<String> collect = balPump.listSubGroup("BAL", "FUND", day);
		System.out.print(collect.toString());
	}
}
