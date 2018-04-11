package com.ajdbxt.service.Info;

import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DTO.Process.ProcessInfoDTO;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;

public interface InfoService {
	/**
	 * 得到所有案件信息
	 * @return
	 */
	public String twoceRank(ajdbxt_info caseInfo);
	public void save(ajdbxt_info caseInfo);
	public String getAllCase(Page_list_caseInfoVo infoVO);
	public String getLegalsAndLeadersAndDepartment();
	public void updateCase(ajdbxt_info caseInfo);
	public void deleteCase(String caseInfo_id);
	public String saveCase(ajdbxt_info caseInfo);
	public ProcessInfoDTO getSingleInfo(String info_id);
	public String getPolices(String info_department);
}

