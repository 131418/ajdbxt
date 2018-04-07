package com.ajdbxt.service.Info;

import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;

public interface InfoService {
	
	/*
	 * 添加案件信息
	 */
    public String saveCase(ajdbxt_info  caseInfo);
	/*
	 * 修改案件信息
	 */
	public void updateCase(ajdbxt_info caseInfo);
	/*
	 * 删除案件
	 */
	public void deleteCase(String caseInfo_id);
	
	/*
	 * 查看某案件
	 */
	public ajdbxt_info findOneCase(ajdbxt_info caseInfo);
	
	public Page_list_caseInfoVo showCaseList(Page_list_caseInfoVo page_list_caseInfoVo);
	/**
	 * 得到所有案件信息
	 * @return
	 */
	public String twoceRank(ajdbxt_info caseInfo);
	public void save(ajdbxt_info caseInfo);
	public String getAllCase(Page_list_caseInfoVo infoVO);
	public String getLegalsAndLeaders();
}
