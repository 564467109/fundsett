package com.uusoft.fund.web;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.uusoft.fund.common.ResultUtils;
import com.uusoft.fund.entity.FeeZonecatalog;
import com.uusoft.fund.services.IFeePolicyService;
import com.uusoft.fund.settle.strategy.FeeConst;
import com.uusoft.fund.settle.strategy.FeeConst.FeeType;

/**
 * 收费策略控制器
 * @author hejian
 *
 */
@Controller
@RequestMapping("/fee")
public class FeeCatalogController {
	
	private static final Logger LOG=LoggerFactory.getLogger(FeeCatalogController.class);
	
	@Resource
	private IFeePolicyService feePolicyService;
	
	
	@RequestMapping("/showAllIndex")
	public String showAllIndex(){
		LOG.info("显示费用分区分组分类列表页面");
		String page="/feeindex";
		return page;
	}
	/**
	 * 跳转到分区分类显示页面
	 * @return
	 */
	@RequestMapping("/toQueryCatalog")
	public String toQueryCatalog(Model model){
		LOG.info("跳转到分区分类显示页面");
		String page="/fee/querycatalog";
		model.addAttribute("feeType", FeeConst.FeeType.getFeeTypes());
		return page;
	}
	
	/**
	 * 显示收费策略列表
	 * @param feeZonecatalog
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/queryCatalog",produces="text/html;charset=utf-8")
	@ResponseBody
	public String queryCatalog(FeeZonecatalog feeZonecatalog,int page,int rows){
		LOG.info("显示类表查询参数:feeZonecatalog="+feeZonecatalog);
		int begin=(page-1)*10;
		Map<String,Object> resultMap=feePolicyService.queryCatalog(feeZonecatalog, begin, rows);
		LOG.info("显示类表查询返回:resultMap="+resultMap);
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 跳转到策略显示页面
	 * @return
	 */
	@RequestMapping("/index")
	public String toIndex(Model model){
		LOG.info("跳转到策略显示页面");
		String page="/fee/index";
		model.addAttribute("feeType", FeeConst.FeeType.getFeeTypes());
		return page;
	}
	
	/**
	 * 显示收费策略列表
	 * @param feeZonecatalog
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/show",produces="text/html;charset=utf-8")
	@ResponseBody
	public String showIndexData(FeeZonecatalog feeZonecatalog,int page,int rows){
		int begin=(page-1)*10;
		Map<String,Object> resultMap=feePolicyService.queryFeePolicy(feeZonecatalog, begin, rows);
		LOG.info("查询策略返回结果为:"+resultMap);
		return JSONObject.toJSONString(resultMap);
	}
	
	/**
	 * 跳转到策略新增页面
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Model model){
		LOG.info("跳转到策略新增页面");
		String page="/fee/addcatalog";
		model.addAttribute("feeType", FeeConst.FeeType.getFeeTypes());
		return page;
	}
	
	/**
	 * 新增策略
	 * @param feeZonecatalog
	 * @param feeZonegroup
	 * @param zone
	 * @return
	 */
	@RequestMapping(value="/addCatalog",produces="text/html;charset=utf-8")
	@ResponseBody
	public String addCatalog(FeeZonecatalog feeZonecatalog){
		LOG.info("新增策略参数:feeZonecatalog"+feeZonecatalog);
		try {
			Map<String,Object> resultMap=feePolicyService.insertZoneCatalog(feeZonecatalog);
			return JSONObject.toJSONString(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(ResultUtils.messageMap(false, 1, "新增策略失败", null));
	}
	
	/**
	 * 删除策略
	 * @param catalogId
	 * @return
	 */
	@RequestMapping(value="/deleteCatalog",produces="text/html;charset=utf-8")
	@ResponseBody
	public String deleteCatalog(Long catalogId){
		try {
			Map<String,Object> resultMap=feePolicyService.deleteZoneCatalog(catalogId);
			return JSONObject.toJSONString(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(ResultUtils.messageMap(false, 1, "修改策略失败", null));
	}
	
	/**
	 * 跳转到修改策略页面
	 * @param catalogId
	 * @return
	 */
	@RequestMapping("/toUpdateCatalog")
	public String toUpdateCatalog(Long catalogId,Model model){
		LOG.info("跳转到策略修改页面");
		String page="/fee/updatecatalog";
		Map<String,Object> resultMap=feePolicyService.queryZoneCatalog(catalogId);
		model.addAttribute(ResultUtils.SUCCESS, resultMap.get(ResultUtils.SUCCESS));
		if(!(Boolean)resultMap.get(ResultUtils.SUCCESS)){
			model.addAttribute(ResultUtils.MSG, resultMap.get(ResultUtils.MSG));
			return page;
		}
		FeeZonecatalog feeZonecatalog=(FeeZonecatalog)resultMap.get(ResultUtils.CONTENT);
		model.addAttribute("feeZonecatalog", feeZonecatalog);
		model.addAttribute("feeType", FeeConst.FeeType.getFeeTypes());
		boolean flag=feePolicyService.isexist(catalogId);
		model.addAttribute("flag", flag);
		model.addAttribute("feeName",FeeType.getFeeType(feeZonecatalog.getFeetype()).getVal());
		return page;
	}
	
	/**
	 * 修改策略信息
	 * @param feeZonecatalog
	 * @param feeZonegroup
	 * @param zone
	 * @return
	 */
	@RequestMapping(value="/updateCatalog",produces="text/html;charset=utf-8")
	@ResponseBody
	public String updateCatalog(FeeZonecatalog feeZonecatalog){
		LOG.info("修改策略参数:feeZonecatalog="+feeZonecatalog);
		try {
			Map<String,Object> resultMap=feePolicyService.updateZoneCatalog(feeZonecatalog);
			return JSONObject.toJSONString(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(ResultUtils.messageMap(false, 1, "修改策略失败", null));
	}
}
