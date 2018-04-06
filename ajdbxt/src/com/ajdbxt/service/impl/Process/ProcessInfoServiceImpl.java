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
	public String getInfoList(int check_end ,String police_id) {
		List<ajdbxt_info> list;
		if(check_end==CAPTAIN_CHECK) {
			list=processInfoDao.getInfoListCaptainCheck(police_id);
		}else {
			list=processInfoDao.getInfoListCaseEnd(police_id);
		}
		return JsonUtils.toJson(list);
	}

}
