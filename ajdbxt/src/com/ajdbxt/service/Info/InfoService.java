package com.ajdbxt.service.Info;

import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DTO.Process.ProcessDTO;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;

public interface InfoService {
	/**
	 * 得到所有案件信息
	 * @return
	 */
	public String twoceRank(ajdbxt_info caseInfo);
	public String save(ajdbxt_info caseInfo);
	public String getAllCase(Page_list_caseInfoVo infoVO);
	public String getLegalsAndLeadersAndDepartment();
	public void deleteCase(String caseInfo_id);
	public String saveCase(ajdbxt_info caseInfo);
	public ProcessDTO getSingleInfo(String info_id);
	public String getPolices(String info_department);
	public String update(ajdbxt_info info) ;
}

