package com.uusoft.fund.web;

import java.util.ArrayList;
import java.util.List;
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
import com.uusoft.fund.entity.FeeZonegroup;
import com.uusoft.fund.services.IFeePolicyService;
@Controller
@RequestMapping("/fee")
public class FeeGroupController {
	
	private static final Logger LOG=LoggerFactory.getLogger(FeeGroupController.class);
	
	@Resource
	private IFeePolicyService feePolicyService;
	
	/**
	 * 跳转到费用分区分组页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toGroupIndex")
	public String toGroupIndex(Model model){
		LOG.info("跳转到显示费用分区分组页面");
		String page="/fee/querygroup";
//		model.addAttribute("feeType", FeeConst.FeeType.getFeeTypes());
		return page;
	}
	
	@RequestMapping(value="/showGroupData",produces="text/html;charset=utf-8")
	@ResponseBody
	public String showGroupData(FeeZonegroup feeZonegroup,int page,int rows){
		int begin=(page-1)*10;
		Map<String,Object> resultMap=feePolicyService.getGroupPage(feeZonegroup,begin,rows);
		return JSONObject.toJSONString(resultMap);
	}
	
	@RequestMapping(value="/toGroup")
	public String toGroup(Model model){
		LOG.info("跳转到费用分区分组页面");
		String page="/fee/addgroup";
		List<FeeZonecatalog> feeZonecatalogs=feePolicyService.getAllCatalog();
		model.addAttribute("feeZonecatalogs", feeZonecatalogs==null||feeZonecatalogs.size()==0?new ArrayList<FeeZonecatalog>():feeZonecatalogs);
		return page;
	}
	/**
	 * 新增策略
	 * @param feeZonecatalog
	 * @param feeZonegroup
	 * @param zone
	 * @return
	 */
	@RequestMapping(value="/addGroup",produces="text/html;charset=utf-8")
	@ResponseBody
	public String addGroup(FeeZonegroup feeZonegroup){
		LOG.info("新增策略参数:feeZonegroup"+feeZonegroup);
		try {
			Map<String,Object> resultMap=feePolicyService.insertZoneGroup(feeZonegroup);
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
	@RequestMapping(value="/deleteGroup",produces="text/html;charset=utf-8")
	@ResponseBody
	public String deleteGroup(Long groupId){
		try {
			Map<String,Object> resultMap=feePolicyService.deleteZoneGroup(groupId);
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
	@RequestMapping("/toUpdateGroup")
	public String toUpdateGroup(Long groupId,Model model){
		LOG.info("跳转到策略修改页面");
		String page="/fee/updategroup";
		Map<String,Object> resultMap=feePolicyService.queryZoneGroup(groupId);
		List<Map<String,Object>> list=feePolicyService.queryFeeType(groupId);
		model.addAttribute(ResultUtils.SUCCESS, resultMap.get(ResultUtils.SUCCESS));
		if(!(Boolean)resultMap.get(ResultUtils.SUCCESS)){
			model.addAttribute(ResultUtils.MSG, resultMap.get(ResultUtils.MSG));
			return page;
		}
		FeeZonegroup feeZonegroup=(FeeZonegroup)resultMap.get(ResultUtils.CONTENT);
		model.addAttribute("feeZonegroup",feeZonegroup);
		model.addAttribute("feeZonecatalogs",list);
		return page;
	}
	/**
	 * 修改策略信息
	 * @param feeZonecatalog
	 * @param feeZonegroup
	 * @param zone
	 * @return
	 */
	@RequestMapping(value="/updateGroup",produces="text/html;charset=utf-8")
	@ResponseBody
	public String updateGroup(FeeZonegroup feeZonegroup){
		LOG.info("修改策略参数:feeZonegroup="+feeZonegroup);
		try {
			Map<String,Object> resultMap=feePolicyService.updateZoneGroup(feeZonegroup);
			return JSONObject.toJSONString(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(ResultUtils.messageMap(false, 1, "修改策略失败", null));
	}
}
