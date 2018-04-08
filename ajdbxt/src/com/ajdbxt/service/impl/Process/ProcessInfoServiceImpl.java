package com.ajdbxt.service.impl.Process;

import java.util.List;
import com.ajdbxt.dao.Process.ProcessInfoDao;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.service.Process.ProcessInfoService;
import com.google.gson.Gson;

import util.JsonUtils;

public class ProcessInfoServiceImpl implements ProcessInfoService {
	private ProcessInfoDao processInfoDao;
	
	public void setProcessInfoDao(ProcessInfoDao processInfoDao) {
		this.processInfoDao = processInfoDao;
	}


	@Override
	public String getInfoList(int check_end_score_question ,String police_id) {
		List<ajdbxt_info> list;
		switch (check_end_score_question) {
		case CAPTAIN_CHECK:
			list=processInfoDao.getInfoListCaptainCheck(police_id);
			break;
		case CASE_END:
			list=processInfoDao.getInfoListCaseEnd(police_id);
			break;
		case PROCESS_SCORE:
			list=processInfoDao.getInfoListProcessScore(police_id);
			break;
		case PROCESS_QUESTION:
			list=processInfoDao.getInfoListProcessQuestion(police_id);
			break;
		default:
			list=processInfoDao.getInfoList(police_id);
			break;
		}
		return JsonUtils.toJson(list);
	}

}
