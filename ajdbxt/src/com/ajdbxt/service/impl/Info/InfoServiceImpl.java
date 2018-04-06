package com.ajdbxt.service.impl.Info;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ajdbxt.dao.Info.InfoDao;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;
import com.ajdbxt.service.Info.InfoService;

import util.JsonUtils;

public class InfoServiceImpl implements InfoService {
	private InfoDao infoDao;
	private ajdbxt_info info;
	
	public InfoDao getInfoDao() {
		return infoDao;
	}

	public void setInfoDao(InfoDao infoDao) {
		this.infoDao = infoDao;
	}

	public ajdbxt_info getInfo() {
		return info;
	}

	public void setInfo(ajdbxt_info info) {
		this.info = info;
	}

	public InfoDao getCaseInfoDao() {
		return infoDao;
	}

	public void setCaseInfoDao(InfoDao caseInfoDao) {
		this.infoDao = caseInfoDao;
	}
	
	
	@Override
	public void saveCase(ajdbxt_info caseInfo) {
		caseInfo.setAjdbxt_info_id(UUID.randomUUID().toString());
		caseInfo.setInfo_gmt_ceate(util.Time.getStringSecond());
		caseInfo.setInfo_gmt_modify(caseInfo.getInfo_gmt_ceate());//保存时将修改时间设为创建时间
		infoDao.saveCase(caseInfo);
	}

	@Override
	public void updateCase(ajdbxt_info caseInfo) {
		// TODO Auto-generated method stub
		caseInfo.setInfo_gmt_modify(util.Time.getStringSecond());
		infoDao.updateCase(caseInfo);
		
	}

	@Override
	public void deleteCase(String caseInfo_id) {
		// TODO Auto-generated method stub
		infoDao.deleteCase(caseInfo_id);
	}


//	@Override
//	public Page_list_caseInfoVo showCaseList(Page_list_caseInfoVo page_list_caseInfoVo) {
//		List<ajdbxt_info> listCase=new ArrayList<ajdbxt_info>();
//		int totalRecords=caseInfoDao.getTotalCase(page_list_caseInfoVo);
//		Page_list_caseInfoVo caseInfoVo=new Page_list_caseInfoVo();
//		caseInfoVo.setCurrPage(page_list_caseInfoVo.getCurrPage());
//		caseInfoVo.setPageSize(page_list_caseInfoVo.getPageSize());
//		caseInfoVo.setCountRecords(totalRecords);
//		
//		caseInfoVo.setTotalPages((int)Math.ceil((totalRecords)/(page_list_caseInfoVo.getPageSize())));
//		if(page_list_caseInfoVo.getCurrPage()<=1) {
//			page_list_caseInfoVo.setHavePrePage(false);
//		}else {
//			page_list_caseInfoVo.setHavePrePage(true);
//		}
//		
//		if(page_list_caseInfoVo.getCurrPage()>=page_list_caseInfoVo.getTotalPages()) {
//			page_list_caseInfoVo.setHaveNexPage(false);
//		}else {
//			page_list_caseInfoVo.setHaveNexPage(true);
//		}
//		
//		listCase=caseInfoDao.getCaseByPage(page_list_caseInfoVo);
//		page_list_caseInfoVo.setCaselist(listCase);
//		
//		return page_list_caseInfoVo;
//	}

	@Override
	public ajdbxt_info findOneCase(ajdbxt_info caseInfo) {
		// TODO Auto-generated method stub
		return infoDao.findOneCase(caseInfo);
	}

	@Override
	public Page_list_caseInfoVo showCaseList(Page_list_caseInfoVo page_list_caseInfoVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllCase() {
		List list=infoDao.findAllCaseIfo();
		return JsonUtils.toJson(list);
	}


}
