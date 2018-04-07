package com.ajdbxt.service.impl.Process;
import java.util.List;

import com.ajdbxt.dao.Process.ProcessDao;
import com.ajdbxt.domain.DO.ajdbxt_process;
import com.ajdbxt.domain.VO.Process.showProcessVO;
import com.ajdbxt.service.Process.ProcessService;

import util.JsonUtils;

public class ProcessServiceImpl implements ProcessService {
	private ProcessDao processDao;
	
	public void setProcessDao(ProcessDao processDao) {
		this.processDao = processDao;
	}

	@Override
	public ajdbxt_process getSingleProcessByCaseId(String case_id) {
		List list=processDao.findProcessByCaseId(case_id);
		ajdbxt_process process;
		if(list.size()!=0) {
			process=(ajdbxt_process)list.get(0);
		}else {
			process=null;
		}
		return process;
	}
	@Override
	public boolean update(ajdbxt_process process) {
		processDao.updateProcess(process);
		return true;
	}
	@Override
	public boolean update(ajdbxt_process process, int send_massage_type) {
		
		return false;
	}

	@Override
	public String getSomeProcessByShowProcessVO(showProcessVO processVO) {
		int length=processVO.getPageSize();
		processVO.setList_process(processDao.findSomeProcess(0, length));
		
		return null;
	}

}
