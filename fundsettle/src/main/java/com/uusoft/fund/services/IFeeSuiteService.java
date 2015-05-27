package com.uusoft.fund.services;

import java.util.List;
import java.util.Map;

import com.uusoft.fund.settle.strategy.FeeBookInfo;
import com.uusoft.fund.settle.strategy.FeeDetailInfo;
import com.uusoft.fund.settle.strategy.FeeSuiteInfo;

/**
 * @Discription 费用策略服务接口
 * @author 		zhengguo@66money.com
 * @time		2015年5月13日 下午3:24:33
 */
public interface IFeeSuiteService {

	void test();
	/**
	 * @Discription 查询费用策略信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月14日-下午4:07:21
	 * @param feetype		费用类型 -1：全部
	 * @param accountcode	处理对象
	 * @param partner		商户
	 * @param validdate		生效日期
	 * @param pageIndex		当前页数
	 * @param pageSize		每页条数
	 * @return
	 */
	List<Map<String,String>> queryFeeSuiteInfo(int feetype,String accountcode,String partner,String validdate,long pageIndex, long pageSize);
	/**
	 * @Discription 查询费用策略总数
	 * @author		zhengguo@66money.com
	 * @time		2015年5月14日-下午4:07:21
	 * @param feetype		费用类型	-1：全部
	 * @param accountcode	处理对象
	 * @param partner		商户
	 * @param validdate		生效日期
	 * @param pageIndex		当前页数
	 * @param pageSize		每页条数
	 * @return
	 */
	int queryFeeSuiteInfoCount(int feetype,String accountcode,String partner,String validdate);
	/**
	 * @Discription 查询费用策略详细信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月13日-下午2:52:10
	 * @param bookid
	 * @return
	 */
	List<FeeDetailInfo> queryFeeDetailInfo(long bookid);
	/**
	 * @Discription 查询策略分组
	 * @author		zhengguo@66money.com
	 * @time		2015年5月18日-下午2:54:39
	 * @return
	 */
	List<FeeBookInfo> queryFeeBookInfo(FeeBookInfo info);
	/**
	 * @Discription 保存费用策略信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月19日-下午5:57:42
	 * @param feeSuite
	 * @return
	 */
	int saveFeeSuite(FeeSuiteInfo feeSuite);
	/**
	 * @Discription 保存费用策略、组及详细信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月19日-下午6:00:03
	 * @param paramap
	 * @return
	 */
	String saveFeeSuiteBookDetail(Map<String,String> paramap);
	/**
	 * @Discription 修改策略分组信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月20日-下午7:19:32
	 * @param info
	 * @return
	 */
	int modifyFeeSuiteBook(FeeBookInfo info);
	/**
	 * @Discription 修改策略信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月20日-下午7:20:19
	 * @param feeSuite
	 * @return
	 */
	int modifyFeeSuiteInfo(FeeSuiteInfo feeSuite);
	/**
	 * @Discription 删除策略信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月22日-下午3:21:50
	 * @param listid
	 * @return
	 */
	int delFeeSuiteInfo(Long listid);
	/**
	 * @Discription 添加策略详细信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月22日-下午7:36:13
	 * @param map
	 * @return
	 */
	int saveFuiteDetails(Map<String,String> map);
	/**
	 * @Discription 修改详细策略信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月25日-下午8:05:12
	 * @param map
	 * @return
	 */
	int modifySuitDetails(Map<String,String> map);
	/**
	 * @Discription 复制策略组信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月26日-下午7:42:02
	 * @param bookid
	 * @param feetype
	 * @param bookname
	 * @return
	 */
	boolean copySuitBookInfo(Long bookid,int feetype,String bookname);
}
