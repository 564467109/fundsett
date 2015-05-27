package com.uusoft.fund.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uusoft.fund.common.DtUtils;
import com.uusoft.fund.dao.FeeSuiteDao;
import com.uusoft.fund.services.IFeeSuiteService;
import com.uusoft.fund.settle.strategy.FeeBookInfo;
import com.uusoft.fund.settle.strategy.FeeDetailInfo;
import com.uusoft.fund.settle.strategy.FeeSuiteInfo;
/**
 * 
 * @Discription 费用策略服务
 * @author 		zhengguo@66money.com
 * @time		2015年5月13日 下午3:22:34
 */

@Service
public class FeeSuiteServiceImpl implements IFeeSuiteService {
	
	private static Logger log = LoggerFactory.getLogger(FeeSuiteServiceImpl.class);
	
	@Resource
	private FeeSuiteDao feeSuiteDao;
	
	public void test(){
		feeSuiteDao.test();
	}

	@Override
	public List<Map<String,String>> queryFeeSuiteInfo(int feetype,String accountcode,String partner,String validdate,long pageIndex, long pageSize) {
		return feeSuiteDao.queryFeeSuiteInfo(feetype,accountcode,partner,validdate,pageIndex,pageSize);
	}

	@Override
	public List<FeeDetailInfo> queryFeeDetailInfo(long bookid) {
		return feeSuiteDao.queryFeeDetailInfo(bookid);
	}

	@Override
	public int queryFeeSuiteInfoCount(int feetype, String accountcode,
			String partner, String validdate) {
		return feeSuiteDao.queryFeeSuiteInfoCount(feetype,accountcode,partner,validdate);
	}

	@Override
	public List<FeeBookInfo> queryFeeBookInfo(FeeBookInfo info) {
		return feeSuiteDao.queryFeeBookInfo(info,false);
	}

	@Override
	public int saveFeeSuite(FeeSuiteInfo feeSuite) {
		return feeSuiteDao.saveFeeSuite(feeSuite);
	}

	@Transactional
	@Override
	public String saveFeeSuiteBookDetail(Map<String, String> paramap) {
		FeeBookInfo feeBook = new FeeBookInfo();
		feeBook.setBookname(paramap.get("bookname"));
		feeBook.setCreatedate(DtUtils.getShortStr(new Date()));//paramap.get("createdate").replaceAll("-", "").substring(0, 8)
		feeBook.setFeetype(Integer.parseInt(paramap.get("feetype")));
		feeBook.setStatus(Integer.parseInt(paramap.get("statux")));
		feeBook.setShow(Integer.parseInt(paramap.get("isshow")));
		feeBook.setNote("");
		int b = feeSuiteDao.saveFeeBook(feeBook);
		log.info("保存费用策略组结果："+b);
		if(b > 0){
			List<FeeBookInfo> list = feeSuiteDao.queryFeeBookInfo(feeBook,true);
			if(null != list && list.size() > 0){
				FeeBookInfo info = list.get(0);
				/*if("1".equals(isAddFeeSuite)){
					FeeSuiteInfo feeSuite = new FeeSuiteInfo();
					feeSuite.setAccountcode(paramap.get("accountcode"));
					feeSuite.setBookid(info.getListid());
					feeSuite.setFeetype(Integer.parseInt(paramap.get("feetype")));
					feeSuite.setPartner(paramap.get("partner"));
					feeSuite.setValiddate(paramap.get("validdate"));
					feeSuite.setStatus(0);
					int i = feeSuiteDao.saveFeeSuite(feeSuite);
					log.info("保存费用策略结果："+i);
				}*/
				FeeDetailInfo feeDetail = new FeeDetailInfo();
				feeDetail.setBookid(info.getListid());
				feeDetail.setBusinesscode(paramap.get("businesscode"));
				feeDetail.setCalmode(Integer.parseInt(paramap.get("calmode")));
				String calpolicy = paramap.get("calpolicy");
				feeDetail.setCalpolicy(Integer.parseInt(calpolicy));
				feeDetail.setFixval(new BigDecimal(paramap.get("fixval")));
				feeDetail.setOperway(paramap.get("operway"));
				feeDetail.setPaycenterid(paramap.get("paycenterid"));
				feeDetail.setPaychannelid(paramap.get("paychannelid"));
				if("3".equals(calpolicy)){
					feeDetail.setZoneid(Long.parseLong(paramap.get("zoneid")));
					feeDetail.setLeftclose(Integer.parseInt(paramap.get("leftclose")));
				}
				int d = feeSuiteDao.saveFeeDetailInfo(feeDetail);
				log.info("保存费用策略详细信息结果："+d);
				return info.getListid()+"";
			}
		}
		return null;
	}

	@Override
	public int modifyFeeSuiteBook(FeeBookInfo info) {
		return feeSuiteDao.modifyFeeBook(info);
	}

	@Override
	public int modifyFeeSuiteInfo(FeeSuiteInfo feeSuite) {
		return feeSuiteDao.modifyFeeSuite(feeSuite);
	}

	@Override
	public int delFeeSuiteInfo(Long listid) {
		return feeSuiteDao.delSuiteInfo(listid);
	}

	@Override
	public int saveFuiteDetails(Map<String, String> map) {
		FeeDetailInfo feeDetail = new FeeDetailInfo();
		feeDetail.setBookid(Integer.parseInt(map.get("bookid")));
		feeDetail.setBusinesscode(map.get("businesscode"));
		feeDetail.setCalmode(Integer.parseInt(map.get("calmode")));
		String calpolicy = map.get("calpolicy");
		feeDetail.setCalpolicy(Integer.parseInt(calpolicy));
		feeDetail.setFixval(new BigDecimal(map.get("fixval")));
		feeDetail.setOperway(map.get("operway"));
		feeDetail.setPaycenterid(map.get("paycenterid"));
		feeDetail.setPaychannelid(map.get("paychannelid"));
		if("3".equals(calpolicy)){
			feeDetail.setZoneid(Long.parseLong(map.get("zoneid")));
			feeDetail.setLeftclose(Integer.parseInt(map.get("leftclose")));
		}
		return feeSuiteDao.saveFeeDetailInfo(feeDetail);
	}

	@Override
	public int modifySuitDetails(Map<String, String> map) {
		FeeDetailInfo feeDetail = new FeeDetailInfo();
		feeDetail.setListid(Integer.parseInt(map.get("listid")));
		//feeDetail.setBookid(Integer.parseInt(map.get("bookid")));
		feeDetail.setBusinesscode(map.get("businesscode"));
		feeDetail.setCalmode(Integer.parseInt(map.get("calmode")));
		String calpolicy = map.get("calpolicy");
		feeDetail.setCalpolicy(Integer.parseInt(calpolicy));
		feeDetail.setFixval(new BigDecimal(map.get("fixval")));
		feeDetail.setOperway(map.get("operway"));
		feeDetail.setPaycenterid(map.get("paycenterid"));
		feeDetail.setPaychannelid(map.get("paychannelid"));
		if("3".equals(calpolicy)){
			feeDetail.setZoneid(Long.parseLong(map.get("zoneid")));
			feeDetail.setLeftclose(Integer.parseInt(map.get("leftclose")));
		}
		return feeSuiteDao.modifyFeeDetailInfo(feeDetail);
	}

	@Override
	public boolean copySuitBookInfo(Long bookid, int feetype,String bookname) {
		FeeBookInfo feeBook = new FeeBookInfo();
		feeBook.setBookname(bookname);
		feeBook.setCreatedate(DtUtils.getShortStr(new Date()));//paramap.get("createdate").replaceAll("-", "").substring(0, 8)
		feeBook.setFeetype(feetype);
		feeBook.setStatus(0);
		feeBook.setShow(0);
		feeBook.setNote("复制策略组"+bookname);
		int b = feeSuiteDao.saveFeeBook(feeBook);
		log.info("复制策略组结果："+b);
		if(b > 0){
			List<FeeDetailInfo> detailslist = queryFeeDetailInfo(bookid);
			if(null != detailslist && detailslist.size() > 0){
				List<FeeBookInfo> list = feeSuiteDao.queryFeeBookInfo(feeBook,true);
				if(null != list && list.size() > 0){
					FeeBookInfo info = list.get(0);
					for (FeeDetailInfo deta : detailslist) {
						FeeDetailInfo feeDetail = deta;
						feeDetail.setBookid(info.getListid());
						int d = feeSuiteDao.saveFeeDetailInfo(feeDetail);
						log.info("复制费用策略详细信息结果："+d);
					}
				}
			}
			return true;
		}
		return false;
	}
}
