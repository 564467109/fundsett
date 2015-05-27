package com.uusoft.fund.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.uusoft.fund.entity.FeeZone;
import com.uusoft.fund.entity.FeeZonecatalog;
import com.uusoft.fund.entity.FeeZonegroup;

public interface IFeePolicyService {
	/**
	 * 新增分区分类表
	 * @param feeZonecatalog
	 * @return
	 */
	public Map<String,Object> insertZoneCatalog(FeeZonecatalog feeZonecatalog);
	/**
	 * 新增分区分组表
	 * @param feeZonegroup
	 * @return
	 */
	public Map<String,Object> insertZoneGroup(FeeZonegroup feeZonegroup);
	/**
	 * 新增分组表
	 * @param zones
	 * @return
	 */
	public Map<String,Object> insertFeeZone(List<FeeZone> zones);
	/**
	 * 修改分区分类表
	 * @param feeZonecatalog
	 * @return
	 */
	public Map<String,Object> updateZoneCatalog(FeeZonecatalog feeZonecatalog);
	/**
	 * 修改分区分组表
	 * @param feeZonegroup
	 * @return
	 */
	public Map<String,Object> updateZoneGroup(FeeZonegroup feeZonegroup);
	/**
	 * 修改分组表
	 * @param zones
	 * @return
	 */
	public Map<String,Object> updateFeeZone(List<FeeZone> zones);
	/**
	 * 删除收费策略,对fee_zone表的操作是物理删除
	 * @param catalogId 分区分类表主键
	 * @return
	 */
	public Map<String,Object> deleteZoneCatalog(Long catalogId);
	/**
	 * 删除分区分组表
	 * @param groupId
	 * @return
	 */
	public Map<String,Object> deleteZoneGroup(Long groupId);
	/**
	 * 删除分组表
	 * @param groupId
	 * @return
	 */
	public Map<String,Object> deleteFeeZone(Long groupId);
	/**
	 * 查询收费策略
	 * @param feeZonecatalog
	 * @param begin
	 * @param end
	 * @return
	 */
	public Map<String,Object> queryFeePolicy(FeeZonecatalog feeZonecatalog,Integer begin,Integer rows);
	/**
	 * 根据分区费率表的主键查询策略信息，用于修改展示
	 * @param catalogId
	 * @return
	 */
	public Map<String, Object> queryZoneCatalog(Long catalogId);
	/**
	 * 查询分组分区表
	 * @param groupId
	 * @return
	 */
	public Map<String,Object> queryZoneGroup(Long groupId);
	/**
	 * 获取所有的分区分类数据
	 * @return
	 */
	public List<FeeZonecatalog> getAllCatalog();
	/**
	 * 根据分组id查询分区ID
	 * @param groupId
	 * @return
	 */
	public Map<String,Object> getCatalogByGroupId(Long groupId);
	/**
	 * 获得所有分区信息
	 * @return
	 */
	public List<FeeZonegroup> getAllGroupInfo();
	
	/**
	 * 获取分区分组信息用来展示信息
	 * @param feeZonegroup
	 * @return
	 */
	public Map<String,Object> getGroupPage(FeeZonegroup feeZonegroup,Integer begin,Integer rows);
	/**
	 * 根据分组ID得到跟与其一样的费用类型的分类ID
	 * @param groupId
	 * @return
	 */
	public List<Map<String,Object>> queryFeeType(Long groupId);
	/**
	 * 查询分区分类
	 * @param feeZonecatalog
	 * @param begin
	 * @param end
	 * @return
	 */
	public Map<String,Object> queryCatalog(FeeZonecatalog feeZonecatalog,Integer begin,Integer rows);
	/**
	 * 查询分类分区id是否被引用过
	 * @param catalogId
	 * @return true 存在引用，false不存在
	 */
	public boolean isexist(Long catalogId);
	/**
	 * 根据groupId查询对应下的分区信息和分类信息
	 * @param groupId
	 * @return
	 */
	public Map<String,Object> queryZoneByGroupId(Long groupId);
	/**
	 * 查询分组下的分区信息
	 * @param feeZonegroup
	 * @return
	 */
	public Map<String,Object> queryZoneAndGroup(FeeZonegroup feeZonegroup,Integer begin,Integer rows);
	/**
	 * 判断该分组下面是否存在分区信息 true存在
	 * @param zoneId
	 * @return
	 */
	public boolean isZone(Long zoneId);
	/**
	 * 根据分组ID和金额获取到最佳分区中某个区段
	 * @param groupId
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public FeeZone getOneFeeZoneByGroupIdAndAmount(Long groupId,BigDecimal amount) throws Exception;
}
