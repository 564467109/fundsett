package com.uusoft.fund.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.uusoft.fund.common.DtUtils;
import com.uusoft.fund.settle.strategy.FeeBookInfo;
import com.uusoft.fund.settle.strategy.FeeDetailInfo;
import com.uusoft.fund.settle.strategy.FeeSuiteInfo;
import com.uusoft.fund.settle.strategy.FeeConst.FeeType;
/**
 * @Discription 费用策略
 * @author 		zhengguo@66money.com
 * @time		2015年5月13日 下午3:20:41
 */
@Repository
public class FeeSuiteDao{
	
	private static Logger log = LoggerFactory.getLogger(FeeSuiteDao.class);
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	private FeeSuiteMapper feeSuiteMapper = new FeeSuiteMapper();
	private FeeDetailMapper feeDetailMapper = new FeeDetailMapper();
	public void test(){
		System.out.println(jdbcTemplate);
	}
	/**
	 * @Discription 查询费用策略信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月13日-下午12:25:36
	 * @return
	 */
	public List<Map<String,String>> queryFeeSuiteInfo(int feetype,String accountcode,String partner,String validdate,long pageIndex, long pageSize){
		List<String> slist = Lists.newArrayList();
		StringBuffer sql = new StringBuffer("SELECT fs.listid,fs.accountcode,fs.statux,fs.feetype,fs.partner,fs.validdate,fb.listid as bookid,fb.bookname FROM fee_suite fs,fee_book fb where fs.bookid=fb.listid ");
		if(-1 != feetype){
			sql.append(" and fs.feetype=?");
			slist.add(feetype+"");
		}
		if(StringUtils.hasText(accountcode)){
			sql.append(" and fs.accountcode=?");
			slist.add(accountcode);
		}
		if(StringUtils.hasText(partner)){
			sql.append(" and fs.partner=?");
			slist.add(partner);
		}
		if(StringUtils.hasText(validdate)){
			sql.append(" and fs.validdate=?");
			slist.add(validdate);
		}
		sql.append(" order by fs.validdate desc ");
		if(0 != pageIndex && 0 != pageSize){
			sql.append(" limit ").append((pageIndex-1)*pageSize).append(",").append(pageSize);
		}
		log.info("查询查询费用策略信息sql："+sql.toString());
		List<Map<String,String>> list = jdbcTemplate.query(sql.toString(), slist.toArray(), new RowMapper<Map<String,String>>(){
			@Override
			public Map<String, String> mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Map<String,String> map = new HashMap<String,String>();
				map.put("listid", rs.getLong("listid")+"");
				map.put("accountcode", rs.getString("accountcode"));
				map.put("statux", rs.getInt("statux")+"");
				FeeType[] ft = FeeType.values();
				for (FeeType feeType : ft) {
					if(feeType.getType() == rs.getInt("feetype")){
						map.put("feetypeN", feeType.getVal());
						break;
					}
				}
				map.put("feetype", rs.getInt("feetype")+"");
				map.put("partner", rs.getString("partner"));
				map.put("validdate", DtUtils.getDate(rs.getString("validdate")));
				map.put("bookid", rs.getLong("bookid")+"");
				map.put("bookname", rs.getString("bookname"));
				return map;
			}
			
		});
		return list;
	}
	/**
	 * @Discription 查询费用策略信息总数
	 * @author		zhengguo@66money.com
	 * @time		2015年5月13日-下午12:25:36
	 * @return
	 */
	public int queryFeeSuiteInfoCount(int feetype,String accountcode,String partner,String validdate){
		List<String> slist = Lists.newArrayList();
		StringBuffer sql = new StringBuffer("SELECT count(*) as count FROM fee_suite fs,fee_book fb where fs.bookid=fb.listid");
		if(-1 != feetype){
			sql.append(" and fs.feetype=?");
			slist.add(feetype+"");
		}
		if(StringUtils.hasText(accountcode)){
			sql.append(" and fs.accountcode=?");
			slist.add(accountcode);
		}
		if(StringUtils.hasText(partner)){
			sql.append(" and fs.partner=?");
			slist.add(partner);
		}
		if(StringUtils.hasText(validdate)){
			sql.append(" and fs.validdate=?");
			slist.add(validdate);
		}
		log.info("查询查询费用策略信息sql："+sql.toString());
		return jdbcTemplate.queryForObject(sql.toString(), slist.toArray(),Integer.class);
	}
	
	/**
	 * @Discription 保存费用策略
	 * @author		zhengguo@66money.com
	 * @time		2015年5月13日-下午12:09:14
	 * @param feeSuite
	 * @return
	 */
	public int saveFeeSuite(FeeSuiteInfo feeSuite){
		String sql = "insert into fee_suite(accountcode,statux,feetype,partner,validdate,bookid) values (?,?,?,?,?,?)";
		log.info("保存费用策略sql："+sql);
		return jdbcTemplate.update(sql, feeSuite.getAccountcode(),feeSuite.getStatus(),feeSuite.getFeetype(),feeSuite.getPartner(),feeSuite.getValiddate(),feeSuite.getBookid());
	}
	/**
	 * @Discription 变更费用策略
	 * @author		zhengguo@66money.com
	 * @time		2015年5月13日-下午1:15:57
	 * @param feeSuite
	 * @return
	 */
	public int modifyFeeSuite(FeeSuiteInfo feeSuite){
		String sql = "update fee_suite set accountcode=?,statux=?,feetype=?,partner=?,validdate=?,bookid=? where listid=?";
		log.info("变更费用策略sql："+sql);
		return jdbcTemplate.update(sql,feeSuite.getAccountcode(),feeSuite.getStatus(),feeSuite.getFeetype(),feeSuite.getPartner(),feeSuite.getValiddate(),feeSuite.getBookid(),feeSuite.getListid());
	}
	/**
	 * @Discription 保存费用详细信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月13日-下午3:18:53
	 * @param feeDetail
	 * @return
	 */
	public int saveFeeDetailInfo(FeeDetailInfo feeDetail){
		String sql = "insert into fee_detail(bookid,operway,paychannelid,paycenterid,businesscode,calmode,calpolicy,fixval,zoneid,leftclose) values (?,?,?,?,?,?,?,?,?,?)";
		log.info("保存费用策略详细信息sql："+sql);
		return jdbcTemplate.update(sql, feeDetail.getBookid(),feeDetail.getOperway(),feeDetail.getPaychannelid(),feeDetail.getPaycenterid(),feeDetail.getBusinesscode(),feeDetail.getCalmode(),feeDetail.getCalpolicy(),feeDetail.getFixval(),feeDetail.getZoneid(),feeDetail.getLeftclose());
	}
	
	/**
	 * @Discription 修改费用详细信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月13日-下午3:20:13
	 * @param feeDetail
	 * @return
	 */
	public int modifyFeeDetailInfo(FeeDetailInfo feeDetail){
		String sql = "update fee_detail set operway=?,paychannelid=?,paycenterid=?,businesscode=?,calmode=?,calpolicy=?,fixval=?,zoneid=?,leftclose=? where listid=?";
		log.info("修改费用策略详细信息sql："+sql);
		return jdbcTemplate.update(sql,feeDetail.getOperway(),feeDetail.getPaychannelid(),feeDetail.getPaycenterid(),feeDetail.getBusinesscode(),feeDetail.getCalmode(),feeDetail.getCalpolicy(),feeDetail.getFixval(),feeDetail.getZoneid(),feeDetail.getLeftclose(),feeDetail.getListid());
	}
	/**
	 * @Discription 查询费用策略详细信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月13日-下午3:54:03
	 * @param bookid
	 * @return
	 */
	public List<FeeDetailInfo> queryFeeDetailInfo(long bookid){
		String sql = "SELECT listid,bookid,paychannelid,operway,paycenterid,calmode,businesscode,calpolicy,fixval,zoneid,leftclose FROM fee_detail where bookid=?";
		log.info("查询费用策略详细信息sql："+sql);
		return jdbcTemplate.query(sql, new Object[]{bookid}, feeDetailMapper);
	}
	/**
	 * @Discription 保存费用策略分组
	 * @author		zhengguo@66money.com
	 * @time		2015年5月13日-下午8:22:49
	 * @param feeBook
	 * @return
	 */
	public int saveFeeBook(FeeBookInfo feeBook){
		String sql = "insert into fee_book(bookname,statux,feetype,createdate,isshow,note) values (?,?,?,?,?,?)";
		log.info("保存费用策略分组信息sql："+sql);
		return jdbcTemplate.update(sql, feeBook.getBookname(),feeBook.getStatus(),feeBook.getFeetype(),feeBook.getCreatedate(),feeBook.getShow(),feeBook.getNote());
	}
	/**
	 * @Discription 修改费用策略分组
	 * @author		zhengguo@66money.com
	 * @time		2015年5月13日-下午8:23:37
	 * @param feeBook
	 * @return
	 */
	public int modifyFeeBook(FeeBookInfo feeBook){
		String sql = "update fee_book set bookname=?,statux=?,feetype=?,isshow=?,note=? where listid=?";
		log.info("修改费用策略分组信息sql："+sql);
		return jdbcTemplate.update(sql, feeBook.getBookname(),feeBook.getStatus(),feeBook.getFeetype(),feeBook.getShow(),feeBook.getNote(),feeBook.getListid());
	}
	/**
	 * @Discription 查询策略分组信息
	 * @author		zhengguo@66money.com
	 * @time		2015年5月18日-下午2:57:23
	 * @return
	 */
	public List<FeeBookInfo> queryFeeBookInfo(FeeBookInfo info,boolean islike){
		FeeBookMapper mapper = new FeeBookMapper();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT  f.listid, f.bookname,  f.statux,  f.feetype,  f.createdate,  f.isshow, f.note FROM  fee_book f where f.statux=0");
		if(null != info){
			List<String> list = Lists.newArrayList();
			if(-1 != info.getShow()){
				sql.append(" and f.isshow=?");
				list.add(info.getShow()+"");
			}
			if(StringUtils.hasText(info.getBookname())){
				if(islike){
					sql.append(" and f.bookname=?");
					list.add(info.getBookname());
				}else{
					sql.append(" and f.bookname like ?");
					list.add("%"+info.getBookname()+"%");
				}
			}
			if(-1 != info.getFeetype()){
				sql.append(" and f.feetype=?");
				list.add(info.getFeetype()+"");
			}
			if(StringUtils.hasText(info.getCreatedate())){
				sql.append(" and f.createdate=?");
				list.add(info.getCreatedate());
			}
			sql.append(" order by f.createdate desc");
			log.info("查询策略分组信息 sql="+sql.toString());
			return jdbcTemplate.query(sql.toString(),list.toArray(), mapper);
		}else{
			log.info("查询策略分组信息sql："+sql);
			return jdbcTemplate.query(sql.toString(), mapper);
		}
	}
	/**
	 * @Discription 删除策略
	 * @author		zhengguo@66money.com
	 * @time		2015年5月22日-下午3:24:32
	 * @param listid
	 * @return
	 */
	public int delSuiteInfo(Long listid){
		log.info("delSuiteInfo() 删除策略：listid=="+listid);
		return jdbcTemplate.update("delete from fee_suite where listid=?", listid);
	}
}
