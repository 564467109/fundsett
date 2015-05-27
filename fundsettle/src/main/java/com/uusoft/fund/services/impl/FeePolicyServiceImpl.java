package com.uusoft.fund.services.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.uusoft.fund.common.ResultUtils;
import com.uusoft.fund.common.sql.OperateDB;
import com.uusoft.fund.dao.ZoneCatalogMapper;
import com.uusoft.fund.dao.ZoneGroupMapper;
import com.uusoft.fund.entity.FeeDetail;
import com.uusoft.fund.entity.FeeZone;
import com.uusoft.fund.entity.FeeZonecatalog;
import com.uusoft.fund.entity.FeeZonegroup;
import com.uusoft.fund.services.IFeePolicyService;
import com.uusoft.fund.settle.strategy.FeeConst.FeeSymbol;
import com.uusoft.fund.settle.strategy.ZoneCatalogInfo;
import com.uusoft.fund.settle.strategy.ZoneGroupInfo;
@Service
public class FeePolicyServiceImpl extends OperateDB implements
		IFeePolicyService {
	
	private static final Logger LOG=LoggerFactory.getLogger(FeePolicyServiceImpl.class);
	/**
	 * 新增分区分类表
	 * @param feeZonecatalog
	 * @return
	 */
	public Map<String,Object> insertZoneCatalog(FeeZonecatalog feeZonecatalog){
		insert(feeZonecatalog);//返回费用分区分类ID
		return ResultUtils.messageMap(true, 0, "新增成功", null);
	}
	/**
	 * 新增分区分组表
	 * @param feeZonegroup
	 * @return
	 */
	public Map<String,Object> insertZoneGroup(FeeZonegroup feeZonegroup){
		insert(feeZonegroup);//返回费用分区分类ID
		return ResultUtils.messageMap(true, 0, "新增成功", null);
	}
	/**
	 * 新增分组表
	 * @param zones
	 * @return
	 */
	public Map<String,Object> insertFeeZone(List<FeeZone> zones){
		Long zoneId=zones.get(0).getZoneid();
		boolean flag=isZone(zoneId);
		if(flag){
			return ResultUtils.messageMap(false, 1, "新增失败,该分组下已存在分区信息", null);
		}
		insertBatch(zones);
		return ResultUtils.messageMap(true, 0, "新增成功", null);
	}
	/**
	 * 修改分区分类表
	 * @param feeZonecatalog
	 * @return
	 */
	public Map<String,Object> updateZoneCatalog(FeeZonecatalog feeZonecatalog){
		updateById(feeZonecatalog);
		return ResultUtils.messageMap(true, 0, "修改成功", null);
	}
	/**
	 * 修改分区分组表
	 * @param feeZonegroup
	 * @return
	 */
	public Map<String,Object> updateZoneGroup(FeeZonegroup feeZonegroup){
		updateById(feeZonegroup);
		return ResultUtils.messageMap(true, 0, "修改成功", null);
	}
	/**
	 * 修改分组表
	 * @param zones
	 * @return
	 */
	public Map<String,Object> updateFeeZone(List<FeeZone> zones){
		String sql="update FEE_ZONE set statux=1 where zoneid="+zones.get(0).getZoneid();
		getJdbcTemplate().update(sql);
		LOG.info("删除分区信息:sql={"+sql+"}");
		insertBatch(zones);
		return ResultUtils.messageMap(true, 0, "修改成功", null);
	}
	/**
	 * 删除收费策略,对fee_zone表的操作是物理删除
	 * @param catalogId 分区分类表主键
	 * @return
	 */
	public Map<String,Object> deleteZoneCatalog(Long catalogId){
		FeeZonecatalog feeZonecatalog=new FeeZonecatalog();
		feeZonecatalog.setListid(catalogId);
		feeZonecatalog.setStatux(1);
		updateById(feeZonecatalog);
		FeeZonegroup feeZonegroup=new FeeZonegroup();
		feeZonegroup.setCatalogid(catalogId);
		feeZonegroup.setStatux(0);
		List<FeeZonegroup> feeZonegroups=queryByMulti(feeZonegroup);
		if(feeZonegroups==null||feeZonegroups.size()==0){
			return ResultUtils.messageMap(true, 0, "删除成功", null);
		}
		for(int i=0;i<feeZonegroups.size();i++){
			feeZonegroup=feeZonegroups.get(i);
			Long groupId=feeZonegroup.getListid();
			FeeDetail feeDetail=new FeeDetail();
			feeDetail.setZoneid(groupId);
			List<FeeDetail> feeDetails=queryByMulti(feeDetail);
			if(feeDetails!=null&&feeDetails.size()>0){
				feeZonecatalog.setStatux(0);
				updateById(feeZonecatalog);
				return ResultUtils.messageMap(false, 1, "删除失败,该分类下存在已经被引用的分组", null);
			}
		}
		String sql="update FEE_ZONEGROUP set statux=1 where catalogid="+catalogId;
		getJdbcTemplate().update(sql);//删除组
		for(int i=0;i<feeZonegroups.size();i++){//删除区
			String sql1="update FEE_ZONE set statux=1 where zoneid="+feeZonegroups.get(i).getListid();
			getJdbcTemplate().update(sql1);
		}
		return ResultUtils.messageMap(true, 0, "删除成功", null);
	}
	/**
	 * 删除分区分组表
	 * @param groupId
	 * @return
	 */
	public Map<String,Object> deleteZoneGroup(Long groupId){
		FeeDetail feeDetail=new FeeDetail();
		feeDetail.setZoneid(groupId);
		List<FeeDetail> list=queryByMulti(feeDetail);
		if(list!=null&list.size()>0){
			return ResultUtils.messageMap(false, 1, "删除失败,此分区已被关联", null);
		}
		FeeZonegroup feeZonegroup=new FeeZonegroup();
		feeZonegroup.setListid(groupId);
		feeZonegroup.setStatux(1);
		updateById(feeZonegroup);
		String sql="update FEE_ZONE set statux=1 where zoneid="+groupId;
		LOG.info("删除分区表数据:sql={"+sql+"}");
		getJdbcTemplate().update(sql);
		return ResultUtils.messageMap(true, 0, "删除成功", null);
	}
	/**
	 * 删除分组表
	 * @param feeZone
	 * @return
	 */
	public Map<String,Object> deleteFeeZone(Long groupId){
		FeeDetail feeDetail=new FeeDetail();
		feeDetail.setZoneid(groupId);
		List<FeeDetail> feeDetails=queryByMulti(feeDetail);
		if(feeDetails!=null&&feeDetails.size()>0){
			return ResultUtils.messageMap(false, 0, "该分区已被引用,故不能删除", null);
		}
		String sql="update FEE_ZONE set statux=1 where zoneid="+groupId;
		LOG.info("删除分区表数据:sql={"+sql+"}");
		getJdbcTemplate().update(sql);
		return ResultUtils.messageMap(true, 0, "删除成功", null);
	}
	/**
	 * 查询收费策略
	 * @param feeZonecatalog
	 * @param begin
	 * @param end
	 * @return
	 */
	public Map<String,Object> queryFeePolicy(FeeZonecatalog feeZonecatalog,Integer begin,Integer rows){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		String sql="select b.* from FEE_ZONECATALOG a,FEE_ZONEGROUP b where a.statux=0 and b.statux=0 and a.listid=b.catalogid";
		String countSql="select count(1) from FEE_ZONECATALOG a,FEE_ZONEGROUP b where a.statux=0 and b.statux=0 and a.listid=b.catalogid";
		String listSql="";
		String nikeNameSql="";
		String feeTypeSql="";
		if(feeZonecatalog.getListid()!=null){//组装查询条件
			listSql=" and a.listid="+feeZonecatalog.getListid();
		}
		if(feeZonecatalog.getNikename()!=null&&!"".equals(feeZonecatalog.getNikename())){
			nikeNameSql=" and a.nikename like '%"+feeZonecatalog.getNikename()+"%'";
		}
		if(feeZonecatalog.getFeetype()!=null){
			feeTypeSql=" and a.feetype="+feeZonecatalog.getFeetype();
		}
		String condition=listSql+nikeNameSql+feeTypeSql;
		sql+=(condition+" limit "+begin+","+rows);
		countSql+=condition;
		LOG.info("查询分类下分组信息:sql={"+sql+"}");
		LOG.info("查询分类下分组总条数信息:sql={"+countSql+"}");
		Integer count=getJdbcTemplate().queryForObject(countSql, Integer.class);
		List<ZoneGroupInfo> zoneGroupInfos=getJdbcTemplate().query(sql, new ZoneGroupMapper());
		resultMap.put("total", count==null?0:count);
		resultMap.put("rows", zoneGroupInfos==null?new ArrayList<ZoneGroupInfo>():zoneGroupInfos);
		return resultMap;
		
	}
	/**
	 * 根据分区费率表的主键查询策略信息，用于修改展示
	 * @param catalogId
	 * @return
	 */
	public Map<String, Object> queryZoneCatalog(Long catalogId) {
		LOG.info("查询策略信息:catalogId="+catalogId);
		FeeZonecatalog feeZonecatalog=new FeeZonecatalog();
		feeZonecatalog.setListid(catalogId);
		feeZonecatalog.setStatux(0);
		feeZonecatalog=queryById(feeZonecatalog);
		if(feeZonecatalog==null){
			LOG.info("策略信息有误:catalogId="+catalogId);
			return ResultUtils.messageMap(false, 1, "信息有误,没有要查询的策略信息", null);
		}
		return ResultUtils.messageMap(true, 0, "", feeZonecatalog);
	}
	public Map<String,Object> queryZoneGroup(Long groupId){
		LOG.info("查询策略信息:groupId="+groupId);
		FeeZonegroup feeZonegroup=new FeeZonegroup();
		feeZonegroup.setStatux(0);
		feeZonegroup.setListid(groupId);
		feeZonegroup=queryById(feeZonegroup);
		if(feeZonegroup==null){
			LOG.info("策略信息有误:groupId="+groupId);
			return ResultUtils.messageMap(false, 1, "信息有误,没有要查询的策略信息", null);
		}
		return ResultUtils.messageMap(true, 0, "", feeZonegroup);
	}
	/**
	 * 得到所有分类分组信息
	 */
	public List<FeeZonecatalog> getAllCatalog(){
		FeeZonecatalog feeZonecatalog=new FeeZonecatalog();
		feeZonecatalog.setStatux(0);
		List<FeeZonecatalog> feeZonecatalogs=queryByMulti(feeZonecatalog);
		return feeZonecatalogs;
	}
	/**
	 * 根据分组id查询分区ID
	 * @param groupId
	 * @return
	 */
	public Map<String,Object> getCatalogByGroupId(Long groupId){
		String sql="select a.*,b.groupname,b.listid groupId from FEE_ZONECATALOG a,FEE_ZONEGROUP b where a.statux=0 and b.statux=0 and a.listid=b.catalogid and b.listid="+groupId;
		LOG.info("根据分组信息查询分区ID:sql="+sql);
		Map<String,Object> resultMap=getJdbcTemplate().queryForMap(sql);
		LOG.info("结果:"+resultMap);
		return resultMap;
	}
	/**
	 * 获取所有分组信息
	 */
	public List<FeeZonegroup> getAllGroupInfo(){
		FeeZonegroup feeZonegroup=new FeeZonegroup();
		feeZonegroup.setStatux(0);
		return queryByMulti(feeZonegroup);
	}
	/**
	 * 获取分区分组信息用来展示信息
	 * @param feeZonegroup
	 * @return
	 */
	public Map<String,Object> getGroupPage(FeeZonegroup feeZonegroup,Integer begin,Integer rows){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		String sql="select * from FEE_ZONEGROUP where statux=0";
		String countSql="select count(1) from FEE_ZONEGROUP where statux=0";
		String condition="";
		if(feeZonegroup.getListid()!=null){
			condition+=" and listid="+feeZonegroup.getListid();
		}
		if(feeZonegroup.getGroupname()!=null&&!"".equals(feeZonegroup.getGroupname())){
			condition+=" and groupname like '%"+feeZonegroup.getGroupname()+"%'";
		}
		if(feeZonegroup.getNikename()!=null&&!"".equals(feeZonegroup.getNikename())){
			condition+=" and nikename like '%"+feeZonegroup.getNikename()+"%'";
		}
		sql+=(condition+" limit "+begin+","+rows);
		countSql+=condition;
		LOG.info("查询分区分组信息:sql={"+sql+"}");
		LOG.info("查询分区分组总条数信息:sql={"+countSql+"}");
		List<ZoneGroupInfo> zoneGroupInfos=getJdbcTemplate().query(sql, new ZoneGroupMapper());
		Long count=getJdbcTemplate().queryForObject(countSql, Long.class);
		if(zoneGroupInfos==null){
			resultMap.put("total", count==null?0:count);
			resultMap.put("rows", new ArrayList<ZoneGroupInfo>());
			return resultMap;
		}
		for(int i=0;i<zoneGroupInfos.size();i++){
			ZoneGroupInfo zoneGroupInfo=zoneGroupInfos.get(i);
			String sql1="select listid from FEE_DETAIL where zoneid="+zoneGroupInfo.getListid();
			LOG.info("查询引用情况:sql={"+sql1+"}");
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<Long> list=getJdbcTemplate().query(sql1,new RowMapper() {

				public Object mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					return rs.getLong("listid");
				}
			});
			if(list!=null&&list.size()>0){
				zoneGroupInfo.setNote(list.toString());
			}
		}
		resultMap.put("total", count);
		resultMap.put("rows", zoneGroupInfos);
		return resultMap;
	}
	/**
	 * 根据分组ID得到跟与其一样的费用类型的分类ID
	 * @param groupId
	 * @return
	 */
	public List<Map<String,Object>> queryFeeType(Long groupId){
		String sql="select distinct b.feetype from FEE_ZONEGROUP a,FEE_ZONECATALOG b where a.statux=0 and b.statux=0 and a.catalogid=b.listid and a.listid="+groupId;
		LOG.info("查询分组ID对应的费用类型"+sql);
		Integer feeType=getJdbcTemplate().queryForObject(sql, Integer.class);
		String sql1="select listid,ufcatalog from FEE_ZONECATALOG where statux=0 and feetype="+feeType;
		LOG.info("查询出费用类型对应的分组ID"+sql1);
		List<Map<String,Object>> list=getJdbcTemplate().queryForList(sql1);
		return list;
	}
	/**
	 * 查询分区分类
	 * @param feeZonecatalog
	 * @param begin
	 * @param end
	 * @return
	 */
	public Map<String,Object> queryCatalog(FeeZonecatalog feeZonecatalog,Integer begin,Integer rows){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		String sql="select * from FEE_ZONECATALOG a where statux=0";
		String sqlCount="select count(1) from FEE_ZONECATALOG a where statux=0";
		String condition="";
		if(feeZonecatalog.getListid()!=null){//组装查询条件
			condition+=" and a.listid="+feeZonecatalog.getListid();
		}
		if(feeZonecatalog.getNikename()!=null&&!"".equals(feeZonecatalog.getNikename())){
			condition+=" and a.nikename like '%"+feeZonecatalog.getNikename()+"%'";
		}
		if(feeZonecatalog.getFeetype()!=null){
			condition+=" and a.feetype="+feeZonecatalog.getFeetype();
		}
		sql+=(condition+" limit "+begin+","+rows);;
		sqlCount+=condition;
		LOG.info("查询分区分组信息:sql={"+sql+"}");
		LOG.info("查询分区分组总条数信息:sql={"+sqlCount+"}");
		List<ZoneCatalogInfo> zoneCatalogInfo=getJdbcTemplate().query(sql, new ZoneCatalogMapper());
		Long count=getJdbcTemplate().queryForObject(sqlCount, Long.class);
		resultMap.put("total", count==null?0:count);
		resultMap.put("rows", zoneCatalogInfo==null?new ArrayList<ZoneCatalogInfo>():zoneCatalogInfo);
		return resultMap;
	}
	/**
	 * 查询分类分区id是否被引用过
	 * @param catalogId
	 * @return true 存在引用，false不存在
	 */
	public boolean isexist(Long catalogId){
		String sql="select count(1) from FEE_ZONEGROUP a,FEE_DETAIL b where a.statux=0 and a.listid=b.zoneid and a.catalogid="+catalogId;
		int count=getJdbcTemplate().queryForObject(sql, Integer.class);
		return (count>0);
	}
	/**
	 * 根据groupId查询对应下的分区信息和分类信息
	 * @param groupId
	 * @return
	 */
	public Map<String,Object> queryZoneByGroupId(Long groupId){
		LOG.info("查询分区信息:groupId="+groupId);
		FeeZonegroup feeZonegroup=new FeeZonegroup();
		feeZonegroup.setListid(groupId);
		feeZonegroup.setStatux(0);
		feeZonegroup=queryById(feeZonegroup);
		if(feeZonegroup==null){
			LOG.info("传入的参数有误,未查询到数据:groupId="+groupId);
			return ResultUtils.messageMap(false, 1, "传入的参数有误", null);
		}
		FeeZone feeZone=new FeeZone();
		feeZone.setZoneid(groupId);
		feeZone.setStatux(0);
		List<FeeZone> feeZones=queryByMulti(feeZone);
		if(feeZones==null||feeZones.size()==0){
			LOG.info("传入的参数有误,未查询到数据:groupId="+groupId);
			return ResultUtils.messageMap(false, 2, "传入的参数有误", null);
		}
		feeZonegroup.setFeeZones(feeZones);
		Long catalogId=feeZonegroup.getCatalogid();
		FeeZonecatalog feeZonecatalog=new FeeZonecatalog();
		feeZonecatalog.setListid(catalogId);
		feeZonecatalog=queryById(feeZonecatalog);
		String ufcatalog=feeZonecatalog.getUfcatalog();
		feeZonegroup.setUfcatalog(ufcatalog);
		return ResultUtils.messageMap(true, 0, "", feeZonegroup);
	}
	/**
	 * 查询分组下的分区信息
	 * @param feeZonegroup
	 * @return
	 */
	public Map<String,Object> queryZoneAndGroup(FeeZonegroup feeZonegroup,Integer begin,Integer rows){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		String sql = "select distinct a.* from FEE_ZONEGROUP a,FEE_ZONE b where a.statux=b.statux and a.statux=0 and a.listid=b.zoneid";
		String sqlCouont="select count(1) from FEE_ZONEGROUP a,FEE_ZONE b where a.statux=b.statux and a.statux=0 and a.listid=b.zoneid";
		String condition="";
		if(feeZonegroup.getListid()!=null){
			condition+=" and a.listid="+feeZonegroup.getListid();
		}
		if(feeZonegroup.getGroupname()!=null&&!"".equals(feeZonegroup.getGroupname())){
			condition+=" and a.groupname like '%"+feeZonegroup.getGroupname()+"%'";
		}
		if(feeZonegroup.getNikename()!=null&&!"".equals(feeZonegroup.getNikename())){
			condition+=" and a.nikename like '%"+feeZonegroup.getNikename()+"%'";
		}
		sql+=(condition+" limit "+begin+","+rows);
		sqlCouont+=condition;
		LOG.info("sql={"+sql+"}");
		LOG.info("sqlCouont={"+sqlCouont+"}");
		List<ZoneGroupInfo> zoneGroupInfos=getJdbcTemplate().query(sql, new ZoneGroupMapper());
		Integer count=getJdbcTemplate().queryForObject(sqlCouont, Integer.class);
		resultMap.put("total", count);
		resultMap.put("rows", zoneGroupInfos==null||zoneGroupInfos.size()==0?new ArrayList<ZoneGroupInfo>():zoneGroupInfos);
		return resultMap;
	}
	/**
	 * 判断该分组下面是否存在分区信息 true存在
	 * @param zoneId
	 * @return
	 */
	public boolean isZone(Long zoneId){
		FeeZone feeZone=new FeeZone();
		feeZone.setZoneid(zoneId);
		feeZone.setStatux(0);
		List<FeeZone> feeZones=queryByMulti(feeZone);
		if(feeZones!=null&&feeZones.size()>0){
			return true;
		}
		return false;
	}
	
	public FeeZone getOneFeeZoneByGroupIdAndAmount(Long groupId,BigDecimal amount) throws Exception{
		FeeZone feeZone=new FeeZone();
		feeZone.setZoneid(groupId);
		feeZone.setStatux(0);
		List<FeeZone> feeZones=queryByMulti(feeZone);
		if(feeZones!=null&&feeZones.size()>0){//[a,b)
			return getBestZone(feeZones, amount);
		}
		String sql=
				"SELECT DISTINCT ABS(B.LISTID) FROM FEE_ZONECATALOG A,FEE_ZONEGROUP B,FEE_ZONE C WHERE A.STATUX=0 AND B.STATUX=0 AND C.STATUX=0 "+
				" AND A.LISTID=B.CATALOGID AND B.LISTID=C.ZONEID AND A.FEETYPE=(SELECT A.FEETYPE FROM FEE_ZONECATALOG A,FEE_ZONEGROUP B"+
				" WHERE A.LISTID=B.CATALOGID AND B.LISTID=?) ORDER BY B.LISTID DESC LIMIT 0,1";
		LOG.info("由于没有直接关联到分区,故查询同分类下的最近分组;sql={"+sql+"},参数:"+groupId);
		Long zoneId=getJdbcTemplate().queryForObject(sql,new Object[]{groupId},Long.class);
		if(zoneId==null){
			throw new Exception("获取最佳分区失败,没有直接关联的分组且该分组对应的分类也不存在分区信息,groupId="+groupId);
		}
		feeZone.setZoneid(zoneId);
		feeZones=queryByMulti(feeZone);
		return getBestZone(feeZones, amount);
	}
	private FeeZone getBestZone(List<FeeZone> feeZones,BigDecimal amount) throws Exception{
		for(int i=0;i<feeZones.size();i++){
			FeeZone feeZone=feeZones.get(i);
			String upAmount=feeZone.getUpamount();
			String downAmount=feeZone.getDownamount();
			if(FeeSymbol.UP_TOKEN.getSymbol().equals(upAmount)){//上线为$,则比较下限
				BigDecimal downDecimal=new BigDecimal(downAmount);
				if(amount.compareTo(downDecimal)!=-1){//大于或等于
					return feeZone;
				}
			}else if(FeeSymbol.DOWN_TOKEN.getSymbol().equals(downAmount)){//下限为^
				BigDecimal upDecimal=new BigDecimal(upAmount);
				if(amount.compareTo(upDecimal)==-1){//小于上限
					return feeZone;
				}
			}else{
				BigDecimal downDecimal=new BigDecimal(downAmount);
				BigDecimal upDecimal=new BigDecimal(upAmount);
				if(amount.compareTo(downDecimal)!=-1&&amount.compareTo(upDecimal)==-1){//大于等于下限小于上限
					return feeZone;
				}
			}
		}
		throw new Exception("获取最佳分区失败");
	}
}
