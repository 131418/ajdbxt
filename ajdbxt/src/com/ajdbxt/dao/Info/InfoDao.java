package com.ajdbxt.dao.Info;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;

public interface InfoDao {
	
	public void saveCase(ajdbxt_info  caseInfo);
	
	public void updateCase(ajdbxt_info caseInfo);
	
	public void deleteCase(String caseInfo_id);
	
	public int findTotalCase(Page_list_caseInfoVo page_list_caseInfoVo);
	
	public List<ajdbxt_info> findCaseByPage(Page_list_caseInfoVo page_list_caseInfoVo);
	
	public ajdbxt_info findCaseById(String info_id);
	
	public ajdbxt_info findOneCase(ajdbxt_info caseInfo);
	
	public ajdbxt_info findCaseByCaseName(String info_name);
	
	public List<ajdbxt_info> findAllCaseIfo();
}
