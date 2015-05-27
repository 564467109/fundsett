package com.uusoft.fund.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.uusoft.fund.entity.FeeZonegroup;
import com.uusoft.fund.services.IFeePolicyService;
import com.uusoft.fund.services.IFeeSuiteService;
import com.uusoft.fund.settle.strategy.FeeBookInfo;
import com.uusoft.fund.settle.strategy.FeeConst.BusinessType;
import com.uusoft.fund.settle.strategy.FeeConst.FeeDatum;
import com.uusoft.fund.settle.strategy.FeeConst.FeePolicy;
import com.uusoft.fund.settle.strategy.FeeConst.FeeType;
import com.uusoft.fund.settle.strategy.FeeDetailInfo;
import com.uusoft.fund.settle.strategy.FeeSuiteInfo;
/**
 * @Discription TODO
 * @author 		zhengguo@66money.com
 * @time		2015年5月14日 上午11:10:36
 */
@Controller
@RequestMapping("/feesuite")
public class FeeSuiteController {
	
	private static Logger log = LoggerFactory.getLogger(FeeSuiteController.class);
	
	@Resource
	private IFeeSuiteService feeSuiteService;
	@Resource
	private IFeePolicyService feePolicyService;
	
	/**
	 * @Discription 添加费用策略信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月14日-上午11:18:43
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String addFeeSuiteInfo(HttpServletRequest request,HttpServletResponse response){
		String flag = request.getParameter("flag");
		String feetype = request.getParameter("feetype");
		String partner = request.getParameter("partner");
		String accountcode = request.getParameter("accountcode");
		String validdate = request.getParameter("validdate");
		String bookid = request.getParameter("bookid");
		if(!org.springframework.util.StringUtils.hasText(bookid)){
			Map<String,String> paramap = Maps.newHashMap();
			paramap.put("feetype", feetype);
			paramap.put("bookname", request.getParameter("b_bookname"));
			paramap.put("operway", request.getParameter("operway"));
			paramap.put("paychannelid", request.getParameter("paychannelid"));
			paramap.put("paycenterid", request.getParameter("paycenterid"));
			paramap.put("businesscode", request.getParameter("businesscode"));
			paramap.put("calmode", request.getParameter("calmode"));
			paramap.put("calpolicy", request.getParameter("calpolicy"));
			paramap.put("fixval", request.getParameter("fixval"));
			paramap.put("zoneid", request.getParameter("zoneid"));
			paramap.put("leftclose", request.getParameter("leftclose"));
			paramap.put("statux", request.getParameter("statux"));
			paramap.put("isshow", request.getParameter("isshow"));
			paramap.put("createdate", request.getParameter("createdate"));
			/*paramap.put("partner", partner);
			paramap.put("accountcode", accountcode);
			paramap.put("validdate", validdate);*/
			bookid = feeSuiteService.saveFeeSuiteBookDetail(paramap);
		}
		if(StringUtils.isNotBlank(bookid) && StringUtils.isNotBlank(flag) && "1".equals(flag)){
			validdate = validdate.replaceAll("-", "").substring(0, 8);
			FeeSuiteInfo feeSuite = new FeeSuiteInfo();
			feeSuite.setAccountcode(accountcode);
			feeSuite.setBookid(Long.parseLong(bookid));
			feeSuite.setFeetype(Integer.parseInt(feetype));
			feeSuite.setPartner(partner);
			feeSuite.setStatus(0);
			feeSuite.setValiddate(validdate);
			feeSuiteService.saveFeeSuite(feeSuite);
		}
		return "";
	}
	/**
	 * @Discription 修改费用策略信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月14日-上午11:20:43
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/modifyfeesuite")
	@ResponseBody
	public String modifyFeeSuiteInfo(HttpServletRequest request,HttpServletResponse response){
		String listid = request.getParameter("listid");
		String accountcode = request.getParameter("accountcode");
		String statux = request.getParameter("statux");
		String feetype = request.getParameter("feetype");
		String partner = request.getParameter("partner");
		String bookid = request.getParameter("bookid");
		String validdate = request.getParameter("validdate");
		if(StringUtils.isNotBlank(validdate)){
			validdate = validdate.replaceAll("-", "").substring(0,8);
		}
		String flag = request.getParameter("flag");
		if(StringUtils.isNotBlank(flag) && "1".equals(flag)){
			bookid = "";
			Map<String,String> paramap = Maps.newHashMap();
			paramap.put("feetype", feetype);
			paramap.put("bookname", request.getParameter("b_bookname"));
			paramap.put("operway", request.getParameter("operway"));
			paramap.put("paychannelid", request.getParameter("paychannelid"));
			paramap.put("paycenterid", request.getParameter("paycenterid"));
			paramap.put("businesscode", request.getParameter("businesscode"));
			paramap.put("calmode", request.getParameter("calmode"));
			paramap.put("calpolicy", request.getParameter("calpolicy"));
			paramap.put("fixval", request.getParameter("fixval"));
			paramap.put("zoneid", request.getParameter("zoneid"));
			paramap.put("leftclose", request.getParameter("leftclose"));
			paramap.put("statux", request.getParameter("bstatux"));
			paramap.put("isshow", request.getParameter("isshow"));
			//paramap.put("createdate", request.getParameter("createdate"));
			bookid = feeSuiteService.saveFeeSuiteBookDetail(paramap);
		}
		Map<String,String> map = Maps.newHashMap();
		if(StringUtils.isNotBlank(bookid)){
			FeeSuiteInfo feeSuite = new FeeSuiteInfo();
			feeSuite.setListid(Long.parseLong(listid));
			feeSuite.setAccountcode(accountcode);
			feeSuite.setBookid(Long.parseLong(bookid));
			feeSuite.setFeetype(Integer.parseInt(feetype));
			feeSuite.setPartner(partner);
			feeSuite.setStatus(Integer.parseInt(statux));
			feeSuite.setValiddate(validdate);
			try {
				int u = feeSuiteService.modifyFeeSuiteInfo(feeSuite);
				log.info("修改费用策略信息结果："+u);
				if(u > 0){
					map.put("code", "0000");
					map.put("msg", "修改费用策略信息成功");
				}else{
					map.put("code", "1111");
					map.put("msg", "修改费用策略信息失败");
				}
			} catch (Exception e) {
				map.put("code", "1111");
				map.put("msg", "修改费用策略信息失败");
				log.error("修改费用策略信息发生应用异常："+e);
			}
		}else{
			map.put("code", "1111");
			map.put("msg", "修改费用策略信息失败");
		}
		return JSONObject.toJSONString(map);
	}
	/**
	 * @Discription 删除策略信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月22日-下午3:20:49
	 * @return
	 */
	@RequestMapping("/delfeesuite")
	@ResponseBody
	public String deleteSuiteInfo(HttpServletRequest request){
		Map<String,String> map = Maps.newHashMap();
		String listid = request.getParameter("listid");
		try {
			if(StringUtils.isNotBlank(listid)){
				int d = feeSuiteService.delFeeSuiteInfo(Long.parseLong(listid));
				if(d > 0){
					map.put("code", "0000");
					map.put("msg", "删除成功");
					return JSON.toJSONString(map);
				}
			}
			map.put("code", "1111");
			map.put("msg", "删除失败");
		} catch (NumberFormatException e) {
			map.put("code", "1111");
			map.put("msg", "删除发生NumberFormatException异常");
			log.error("删除发生应用异常："+e);
		}catch (Exception e) {
			map.put("code", "1111");
			map.put("msg", "删除发生应用异常");
			log.error("删除发生应用异常："+e);
		}
		return JSON.toJSONString(map);
	}
	/**
	 * @Discription 修改策略组信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月21日-上午10:40:53
	 * @return
	 */
	@RequestMapping(value = "modifyfeebook",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String modifySuiteBook(HttpServletRequest request,HttpServletResponse response){
		String listid = request.getParameter("listid");
		String feetype = request.getParameter("feetype");
		String bookname = request.getParameter("bookname");
		String status = request.getParameter("status");
		String show = request.getParameter("show");
		String note = request.getParameter("note");
		FeeBookInfo info = new FeeBookInfo();
		info.setListid(Long.parseLong(listid));
		info.setBookname(bookname);
		info.setFeetype(Integer.parseInt(feetype));
		info.setShow(Integer.parseInt(show));
		info.setStatus(Integer.parseInt(status));
		info.setNote(note);
		Map<String,String> map = Maps.newHashMap();
		try {
			int u = feeSuiteService.modifyFeeSuiteBook(info);
			log.info("修改策略组信息结果："+u);
			if(u > 0){
				map.put("code", "0000");
				map.put("msg", "修改策略组信息成功");
			}else{
				map.put("code", "1111");
				map.put("msg", "修改策略组信息失败");
			}
		} catch (Exception e) {
			map.put("code", "1111");
			map.put("msg", "修改策略组信息失败");
			log.error("修改策略组信息发生应用异常："+e);
		}
		return JSONObject.toJSONString(map);
	}
	/**
	 * @Discription 查询费用策略信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月14日-上午11:21:42
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "query",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String queryFeeSuiteInfo(HttpServletRequest request,HttpServletResponse response){
		String feetype = request.getParameter("feetype");
		String accountcode = request.getParameter("accountcode");
		String partner = request.getParameter("partner");
		String validdate = request.getParameter("validdate");
		if(StringUtils.isNotBlank(validdate)){
			validdate = validdate.replaceAll("-", "").substring(0,8);
		}
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		if(StringUtils.isBlank(feetype)){
			feetype = "-1";
		}
		if(StringUtils.isBlank(pageIndex)){
			pageIndex = "1";
		}
		if(StringUtils.isBlank(pageSize)){
			pageSize = "15";
		}
		Map<String,Object> map = Maps.newHashMap();
		try {
			int tatol = feeSuiteService.queryFeeSuiteInfoCount(Integer.parseInt(feetype), accountcode, partner, validdate);
			map.put("tatol", tatol);
			if(tatol > 0){
				List<Map<String,String>> list = feeSuiteService.queryFeeSuiteInfo(Integer.parseInt(feetype), accountcode, partner, validdate, Long.parseLong(pageIndex), Long.parseLong(pageSize));
				if(null != list && list.size() > 0){
					map.put("data", list);
				}
			}
			map.put("code", "0000");
			map.put("msg", "查询费用策略信息成功");
			log.info("queryFeeSuiteInfo()=="+map);
			return JSONObject.toJSONString(map);
		} catch (NumberFormatException e) {
			log.error("查询费用策略信息Number转换异常："+e);
			map.put("code", "0000");
			map.put("msg", "查询费用策略信息失败");
		} catch(Exception e1){
			log.error("查询费用策略信息发生应用异常："+e1);
			map.put("code", "1111");
			map.put("msg", "查询费用策略信息失败");
		}
		return JSONObject.toJSONString(map);
	}
	/**
	 * @Discription 查询策略分组信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月14日-上午11:21:42
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "suitebook",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String querySuiteBook(HttpServletRequest request,HttpServletResponse response){
		String feetype = request.getParameter("feetype");
		String bookname = request.getParameter("bookname");
		/*String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		if(StringUtils.isBlank(feetype)){
			feetype = "-1";
		}
		if(StringUtils.isBlank(pageIndex)){
			pageIndex = "1";
		}
		if(StringUtils.isBlank(pageSize)){
			pageSize = "15";
		}*/
		FeeBookInfo info = new FeeBookInfo();
		if(StringUtils.isNotBlank(feetype)){
			info.setFeetype(Integer.parseInt(feetype));
		}else{
			info.setFeetype(-1);
		}
		if(StringUtils.isNotBlank(bookname)){
			info.setBookname(bookname);
		}
		info.setShow(-1);
		Map<String,Object> map = Maps.newHashMap();
		try {
			List<FeeBookInfo> list = feeSuiteService.queryFeeBookInfo(info);
			if(null != list && list.size() > 0){
				map.put("data", list);
			}
			map.put("code", "0000");
			map.put("msg", "查询策略分组信息成功");
		} catch (Exception e) {
			log.error("查询策略分组信息发生应用异常："+e);
			map.put("code", "1111");
			map.put("msg", "查询策略分组信息失败");
		}
		log.info("queryFeeSuiteInfo()=="+map);
		return JSONObject.toJSONString(map);
	}
	/**
	 * @Discription 查询策略分组ID查询策略详细信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月14日-上午11:21:42
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "suitedetail",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String querySuiteDetail(HttpServletRequest request,HttpServletResponse response){
		String bookid = request.getParameter("bookid");
		Map<String,Object> map = Maps.newHashMap();
		try {
			List<FeeDetailInfo> list = feeSuiteService.queryFeeDetailInfo(Long.parseLong(bookid));
			if(null != list && list.size() > 0){
				map.put("data", list);
			}
			map.put("code", "0000");
			map.put("msg", "查询策略详细成功");
		} catch (Exception e) {
			log.error("查询策略详细信息发生应用异常："+e);
			map.put("code", "1111");
			map.put("msg", "查询策略详细信息失败");
		}
		log.info("queryFeeSuiteInfo()=="+map);
		return JSONObject.toJSONString(map);
	}
	/**
	 * @Discription 查询策略分组ID查询策略详细信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月14日-上午11:21:42
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "adddetail",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String saveSuiteDetails(HttpServletRequest request,HttpServletResponse response){
		String iseditor = request.getParameter("iseditor");
		Map<String,String> paramap = Maps.newHashMap();
		paramap.put("bookid", request.getParameter("bookid"));
		paramap.put("operway", request.getParameter("operway"));
		paramap.put("paychannelid", request.getParameter("paychannelid"));
		paramap.put("paycenterid", request.getParameter("paycenterid"));
		paramap.put("businesscode", request.getParameter("businesscode"));
		paramap.put("calmode", request.getParameter("calmode"));
		paramap.put("calpolicy", request.getParameter("calpolicy"));
		paramap.put("fixval", request.getParameter("fixval"));
		paramap.put("zoneid", request.getParameter("zoneid"));
		paramap.put("leftclose", request.getParameter("leftclose"));
		int s = 0;
		if("1".equals(iseditor)){//修改
			paramap.put("listid",request.getParameter("listid"));
			s = feeSuiteService.modifySuitDetails(paramap);
			log.info("修改费用策略详细信息结果："+s);
		}else{
			s = feeSuiteService.saveFuiteDetails(paramap);
			log.info("保存费用策略详细信息结果："+s);
		}
		Map<String,String> map = Maps.newHashMap();
		if(s > 0){
			map.put("code", "0000");
			map.put("msg", "保存费用策略详细信息成功");
		}else{
			map.put("code", "1111");
			map.put("msg", "保存费用策略详细信息失败");
		}
		log.info("saveSuiteDetails()=="+map);
		return JSONObject.toJSONString(map);
	}
	
	/**
	 * @Discription 费用策略信息查询条件
	 * @author		zhengguo@66money.com
	 * @time		2015年5月14日-下午6:05:29
	 * @return
	 */
	@RequestMapping("condition")
	@ResponseBody
	public String optionConditions(){
		Map<String,Object> map = Maps.newHashMap();
		FeeType[] ft = FeeType.values();
		Map<String,Object> feeTypemap = Maps.newHashMap();
		for (FeeType feeType : ft) {
			feeTypemap.put("key",feeType.ordinal());
			feeTypemap.put("value",feeType.getVal());
			map.put("feeType", feeTypemap);
		}
		String[] accountcode = new String[]{"000686","050001","050002","080001","360003"};
		map.put("accountcode", accountcode);
		Map<String,String> partnermap = Maps.newHashMap();
		partnermap.put("352", "联泰");
		partnermap.put("353", "投投");
		map.put("partner", partnermap);
		String jsonStr = JSONObject.toJSONString(map);
		log.info("jsonStr=="+jsonStr);
		return jsonStr;
	}
	/**
	 * @Discription 处理对象
	 * @author		zhengguo@66money.com
	 * @time		2015年5月15日-下午2:08:24
	 * @return
	 */
	@RequestMapping(value="accountcode",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String getPartner(){
		String[] accountcode = new String[]{"000686","050001","050002","080001","360003"};
		List<Map<String,Object>> list = Lists.newArrayList();
		Map<String,Object> feeTypemap = null;
		feeTypemap = Maps.newHashMap();
		feeTypemap.put("key","请选择");
		feeTypemap.put("value","");
		feeTypemap.put("selected",true);
		list.add(feeTypemap);
		for (String string : accountcode) {
			feeTypemap = Maps.newHashMap();
			feeTypemap.put("key",string);
			feeTypemap.put("value",string);
			list.add(feeTypemap);
		}
		String jsonStr = JSONArray.toJSONString(list);
		log.info("处理对象jsonStr=="+jsonStr);
		return jsonStr;
	}
	/**
	 * @Discription 商户
	 * @author		zhengguo@66money.com
	 * @time		2015年5月15日-下午2:08:24
	 * @return
	 */
	@RequestMapping(value="partner",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String getAccountcode(){
		Map<String,Object> partnermap = Maps.newHashMap();
		List<Map<String,Object>> list = Lists.newArrayList();
		partnermap.put("key", "353");
		partnermap.put("value", "投投");
		partnermap.put("selected",true);
		list.add(partnermap);
		partnermap = Maps.newHashMap();
		partnermap.put("key","352");
		partnermap.put("value","联泰");
		list.add(partnermap);
		String jsonStr = JSONArray.toJSONString(list);
		log.info("商户jsonStr=="+jsonStr);
		return jsonStr;
	}
	/**
	 * @Discription 费用类型
	 * @author		zhengguo@66money.com
	 * @time		2015年5月15日-下午2:08:24
	 * @return
	 */
	@RequestMapping(value="feetype",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String feeTypeCondition(HttpServletResponse response){
		FeeType[] ft = FeeType.values();
		List<Map<String,Object>> list = Lists.newArrayList();
		Map<String,Object> feeTypemap = null;
//		feeTypemap = Maps.newHashMap();
//		feeTypemap.put("value","");
//		feeTypemap.put("key","请选择");
//		feeTypemap.put("selected",true);
//		list.add(feeTypemap);
		for (FeeType feeType : ft) {
			feeTypemap = Maps.newHashMap();
			feeTypemap.put("value",feeType.getType()+"");
			feeTypemap.put("key",feeType.getVal());
			if(1 == feeType.ordinal()){
				list.add(feeTypemap);
				continue;
			}
			list.add(feeTypemap);
		}
		String jsonStr = JSONArray.toJSONString(list);
		log.info("费用类型jsonStr=="+jsonStr);
		return jsonStr;
	}
	/**
	 * @Discription 查询策略分组信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月18日-下午3:05:02
	 * @return
	 */
	@RequestMapping(value="feebook",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String queryFeeBook(HttpServletRequest request){
		FeeBookInfo bookinfo = new FeeBookInfo();
		String feetype = request.getParameter("feetype");
		if(StringUtils.isNotBlank(feetype)){
			bookinfo.setFeetype(Integer.parseInt(feetype));
		}else{
			bookinfo.setFeetype(-1);
		}
		bookinfo.setShow(0);
		List<FeeBookInfo> list = feeSuiteService.queryFeeBookInfo(bookinfo);
		String jsonStr = "";
		if(null != list && list.size() > 0){
			/*for (FeeBookInfo info : list) {
				map.put("listid", info.getListid());
				map.put("bookName", info.getBookname());
			}*/
			jsonStr = JSONArray.toJSONString(list);
			log.info("策略分组信息jsonStr=="+jsonStr);
		}else{
			jsonStr = JSONArray.toJSONString(new ArrayList<FeeBookInfo>(1));
		}
		return jsonStr;
	}
	/**
	 * @Discription 复制策略组信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月26日-下午7:18:04
	 * @param request
	 * @return
	 */
	@RequestMapping(value="copyfeebook",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String copySuitBook(HttpServletRequest request){
		String bookid = request.getParameter("listid");
		String feetype = request.getParameter("feetype");
		String bookname = request.getParameter("bookname");
		Map<String,String> map = Maps.newHashMap();
		try {
			boolean copy = feeSuiteService.copySuitBookInfo(Long.parseLong(bookid), Integer.parseInt(feetype),bookname);
			log.info("复制策略组信息结果："+copy);
			if(copy){
				map.put("code", "0000");
				map.put("msg", "复制策略组信息成功");
			}else{
				map.put("code", "1111");
				map.put("msg", "复制策略组信息失败");
			}
		} catch (NumberFormatException e) {
			log.error("复制策略组信息发生应用错误："+e);
			map.put("code", "1111");
			map.put("msg", "复制策略组信息失败");
		}catch (Exception e) {
			log.error("复制策略组信息发生应用错误："+e);
			map.put("code", "1111");
			map.put("msg", "复制策略组信息失败");
		}
		log.info("复制策略组信息返回结果："+map);
		return JSON.toJSONString(map);
	}
	/**
	 * @Discription 业务类型
	 * @author		zhengguo@66money.com
	 * @time		2015年5月19日-上午11:04:19
	 * @return
	 */
	@RequestMapping(value="business",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String getBusinessType(){
		BusinessType[] bustype = BusinessType.values();
		Map<String,Object> map = null;
		List<Map<String,Object>> list = Lists.newArrayList();
		for (BusinessType bt : bustype) {
			map = Maps.newHashMap();
			map.put("key", bt.getType());
			map.put("value", bt.getValue());
			list.add(map);
		}
		String jsonStr = JSONArray.toJSONString(list);
		log.info("业务类型jsonStr=="+jsonStr);
		return jsonStr;
	}
	/**
	 * @Discription 计费基准
	 * @author		zhengguo@66money.com
	 * @time		2015年5月19日-上午11:35:05
	 * @return
	 */
	@RequestMapping(value="feedatum",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String getFeeDatum(){
		Map<String,Object> map = null;
		List<Map<String,Object>> list = Lists.newArrayList();
		FeeDatum[] feeda = FeeDatum.values();
		for (int i = 0; i < feeda.length; i++) {
			FeeDatum feeDatum = feeda[i];
			map = Maps.newHashMap();
			map.put("key", feeDatum.getType()+"");
			map.put("value", feeDatum.getValue());
			if(0 == i){
				map.put("selected", true);
			}
			list.add(map);
		}
		String jsonStr = JSONArray.toJSONString(list);
		log.info("计费基准jsonStr=="+jsonStr);
		return jsonStr;
	}
	/**
	 * @Discription 计费策略
	 * @author		zhengguo@66money.com
	 * @time		2015年5月19日-上午11:38:01
	 * @return
	 */
	@RequestMapping(value="feepolicy",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String getFeePolicy(){
		Map<String,Object> map = null;
		List<Map<String,Object>> list = Lists.newArrayList();
		FeePolicy[] policy = FeePolicy.values();
		for (FeePolicy feePolicy : policy) {
			map = Maps.newHashMap();
			map.put("key", feePolicy.getType());
			map.put("value", feePolicy.getValue());
			list.add(map);
		}
		String jsonStr = JSONArray.toJSONString(list);
		log.info("计费策略jsonStr=="+jsonStr);
		return jsonStr;
	}
	/**
	 * @Discription 支付渠道
	 * @author		zhengguo@66money.com
	 * @time		2015年5月19日-下午1:58:10
	 * @return
	 */
	@RequestMapping(value="paychannel",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String getPayChannel(){
		Map<String,Object> map = null;
		List<Map<String,Object>> list = Lists.newArrayList();
		map = Maps.newHashMap();
		map.put("key", "@");
		map.put("value", "默认全部");
		list.add(map);
		map = Maps.newHashMap();
		map.put("key", "0010");
		map.put("value", "银联");
		list.add(map);
		map = Maps.newHashMap();
		map.put("key", "0108");
		map.put("value", "民生银行");
		list.add(map);
		map = Maps.newHashMap();
		map.put("key", "0205");
		map.put("value", "兰州银行");
		list.add(map);
		map = Maps.newHashMap();
		map.put("key", "0302");
		map.put("value", "通联");
		list.add(map);
		map = Maps.newHashMap();
		map.put("key", "0401");
		map.put("value", "富友");
		list.add(map);
		String jsonStr = JSONArray.toJSONString(list);
		log.info("支付渠道jsonStr=="+jsonStr);
		return jsonStr;
	}
	/**
	 * @Discription 支付网点
	 * @author		zhengguo@66money.com
	 * @time		2015年5月19日-下午2:03:34
	 * @return
	 */
	@RequestMapping(value="paycenter",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String getPayCenter(HttpServletRequest request){
		String channelId = request.getParameter("channelId");
		log.info("channelId=="+channelId);
		Map<String,Object> map = null;
		List<Map<String,Object>> list = Lists.newArrayList();
		map = Maps.newHashMap();
		map.put("id", "@");
		map.put("text", "默认全部");
		list.add(map);
		if("0010".equals(channelId)){
			map = Maps.newHashMap();
			map.put("id", "0103");
			map.put("text", "农业银行");
			list.add(map);
			map = Maps.newHashMap();
			map.put("id", "0104");
			map.put("text", "中国银行");
			list.add(map);
			map = Maps.newHashMap();
			map.put("id", "0202");
			map.put("text", "浦发银行");
			list.add(map);
			map = Maps.newHashMap();
			map.put("id", "0301");
			map.put("text", "交通银行");
			list.add(map);
			map = Maps.newHashMap();
			map.put("id", "0302");
			map.put("text", "中信银行");
			list.add(map);
			map = Maps.newHashMap();
			map.put("id", "0309");
			map.put("text", "兴业银行");
			list.add(map);
			map = Maps.newHashMap();
			map.put("id", "0303");
			map.put("text", "光大银行");
			list.add(map);
		}else if("0108".equals(channelId)){
			map = Maps.newHashMap();
			map.put("id", "8866");
			map.put("text", "民生银行");
			list.add(map);
		}else if("0205".equals(channelId)){
			map = Maps.newHashMap();
			map.put("id", "0501");
			map.put("text", "兰州银行");
			list.add(map);
		}else if("0302".equals(channelId)){
			map = Maps.newHashMap();
			map.put("id", "0105");
			map.put("text", "建设银行");
			list.add(map);
			map = Maps.newHashMap();
			map.put("id", "9013");
			map.put("text", "华夏银行");
			list.add(map);
			map = Maps.newHashMap();
			map.put("id", "9001");
			map.put("text", "光大银行");
			list.add(map);
		}else if("0401".equals(channelId)){
			map = Maps.newHashMap();
			map.put("id", "1111");
			map.put("text", "工商银行");
			list.add(map);
		}
		String jsonStr = JSONArray.toJSONString(list);
		log.info("支付网点jsonStr=="+jsonStr);
		return jsonStr;
	}
	/**
	 * @Discription 区间分组
	 * @author		zhengguo@66money.com
	 * @time		2015年5月19日-下午4:45:32
	 * @return
	 */
	@RequestMapping(value="zonegroup",produces="text/html; charset=UTF-8")
	@ResponseBody
	public String queryZoneGroup(){
		List<FeeZonegroup> zonegrouplist = Lists.newArrayList();
		String jsonStr = "";
		FeeZonegroup zonegroup = new FeeZonegroup();
		zonegroup.setCatalogid(0l);
		zonegroup.setGroupname("请选择");
		zonegrouplist.add(zonegroup);
		List<FeeZonegroup> list = feePolicyService.getAllGroupInfo();
		if(null != list && list.size() > 0){
			zonegrouplist.addAll(list);
		}
		jsonStr = JSONArray.toJSONString(zonegrouplist);
		log.info("区间分组jsonStr=="+jsonStr);
		return jsonStr;
	}
	/**
	 * @Discription 页面跳转用
	 * @author		zhengguo@66money.com
	 * @time		2015年5月20日-上午11:47:01
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="redirect",produces="text/html; charset=UTF-8")
	public String redirectpage(HttpServletRequest request,HttpServletResponse response){
		return "feesuite/"+request.getParameter("page");
	}
	
	public static void main(String[] args) {
		
	}
}
