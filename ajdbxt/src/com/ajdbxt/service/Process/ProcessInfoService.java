package com.ajdbxt.service.Process;

import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;

public interface ProcessInfoService {
	public final static int CAPTAIN_CHECK=0;
	public final static int CASE_END=1;
	public final static int PROCESS_SCORE=2;
	public final static int PROCESS_QUESTION=3;
	public String getInfoList(int check_end_score_question ,String police_id,Page_list_caseInfoVo infoVO);
	public ajdbxt_info getSingleInfo(String process_id);
}

