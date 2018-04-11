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
	
	public List<ajdbxt_info> findSomeCase(int start,int length);
	
	public boolean isCaptainWorked(String captainId);

	public int countProcessByPoliceId(String ajdbxt_police_id);
	
	public int countAllCase();
}
