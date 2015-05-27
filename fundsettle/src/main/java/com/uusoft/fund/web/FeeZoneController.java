package com.uusoft.fund.web;

import java.math.BigDecimal;
import java.util.HashMap;
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
import com.uusoft.fund.entity.FeeZone;
import com.uusoft.fund.entity.FeeZonegroup;
import com.uusoft.fund.services.IFeePolicyService;
@Controller
@RequestMapping("/fee")
public class FeeZoneController {
	
	private static final Logger LOG=LoggerFactory.getLogger(FeeZoneController.class);
	
	@Resource
	private IFeePolicyService feePolicyService;

	/**
	 * 跳转到查询展示页面，分区
	 * @param feeZone
	 * @return
	 */
	@RequestMapping(value="/toQueryZone")
	public String toQueryZone(){
		LOG.info("跳转到查询展示页面，分区");
		String page="/fee/queryzone";
		return page;
	}
	
	@RequestMapping(value="/queryZone",produces="text/html;charset=utf-8")
	@ResponseBody
	public String queryZone(FeeZonegroup feeZonegroup,int page,int rows){
		LOG.info("跳转到查询展示页面，分区:feeZonegroup="+feeZonegroup);
		int begin=(page-1)*10;
		Map<String,Object> result=feePolicyService.queryZoneAndGroup(feeZonegroup, begin, rows);
		return JSONObject.toJSONString(result);
	}
	/**
	 * 跳转到费用分组页面
	 * @param model
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value="/toZone")
	public String toZone(Model model,Long groupId){
		LOG.info("跳转到费用分组页面");
		String page="/fee/addzone";
		Map<String,Object> resutlMap=feePolicyService.getCatalogByGroupId(groupId);
		model.addAttribute("resutlMap", resutlMap==null?new HashMap<String,Object>():resutlMap);
		return page;
	}
	@RequestMapping(value="/isZone",produces="text/html;charset=utf-8")
	@ResponseBody
	public String isZone(Long groupId){
		boolean flag=feePolicyService.isZone(groupId);
		if(flag){
			return JSONObject.toJSONString(ResultUtils.messageMap(false, 1, "该分组已存在分区信息", null));
		}
		return JSONObject.toJSONString(ResultUtils.messageMap(true, 0, "", groupId));
	}
	/**
	 * 新增策略
	 * @param feeZonecatalog
	 * @param feeZonegroup
	 * @param zone
	 * @return
	 */
	@RequestMapping(value="/addZones",produces="text/html;charset=utf-8")
	@ResponseBody
	public String addZones(String zone){
		LOG.info("新增策略参数:zone"+zone);
		try {
			List<FeeZone> zones=JSONObject.parseArray(zone, FeeZone.class);
			Map<String,Object> resultMap=feePolicyService.insertFeeZone(zones);
			return JSONObject.toJSONString(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(ResultUtils.messageMap(false, 1, "新增策略失败", null));
	}
	@RequestMapping(value="/toUpdateZone")
	public String toUpdateZone(Model model,Long groupId){
		LOG.info("修改策略参数:groupId="+groupId);
		String page="/fee/updatezone";
		Map<String,Object> resultMap=feePolicyService.queryZoneByGroupId(groupId);
		model.addAttribute(ResultUtils.SUCCESS, resultMap.get(ResultUtils.SUCCESS));
		model.addAttribute("feeZonegroup", resultMap.get(ResultUtils.CONTENT));
		return page;
	}
	@RequestMapping(value="/toSeeZone")
	public String toSeeZone(Model model,Long groupId){
		LOG.info("修改策略参数:groupId="+groupId);
		String page="/fee/seezone";
		Map<String,Object> resultMap=feePolicyService.queryZoneByGroupId(groupId);
		model.addAttribute(ResultUtils.SUCCESS, resultMap.get(ResultUtils.SUCCESS));
		model.addAttribute("feeZonegroup", resultMap.get(ResultUtils.CONTENT));
		return page;
	}
	/**
	 * 修改策略信息
	 * @param feeZonecatalog
	 * @param feeZonegroup
	 * @param zone
	 * @return
	 */
	@RequestMapping(value="/updateZones",produces="text/html;charset=utf-8")
	@ResponseBody
	public String updateZones(String zone){
		LOG.info("修改策略参数:zone="+zone);
		List<FeeZone> zones=JSONObject.parseArray(zone, FeeZone.class);
		try {
			Map<String,Object> resultMap=feePolicyService.updateFeeZone(zones);
			return JSONObject.toJSONString(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(ResultUtils.messageMap(false, 1, "修改策略失败", null));
	}
	/**
	 * 删除策略信息
	 * @param feeZonecatalog
	 * @param feeZonegroup
	 * @param zone
	 * @return
	 */
	@RequestMapping(value="/deleteZone",produces="text/html;charset=utf-8")
	@ResponseBody
	public String deleteZone(Long groupId){
		LOG.info("删除分区参数:groupId="+groupId);
		try {
			Map<String,Object> resultMap=feePolicyService.deleteFeeZone(groupId);
			return JSONObject.toJSONString(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(ResultUtils.messageMap(false, 1, "删除分区失败", null));
	}
	/**
	 * 删除策略信息
	 * @param feeZonecatalog
	 * @param feeZonegroup
	 * @param zone
	 * @return
	 */
	@RequestMapping(value="/compute",produces="text/html;charset=utf-8")
	@ResponseBody
	public String compute(BigDecimal amount,Long groupId){
		try {
			FeeZone feeZone=feePolicyService.getOneFeeZoneByGroupIdAndAmount(groupId, amount);
			return JSONObject.toJSONString(ResultUtils.messageMap(true, 0, "", feeZone));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(ResultUtils.messageMap(false, 1, "不能查询出对应的分区信息", null));
	}
}
