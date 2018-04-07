package com.ajdbxt.service.impl.Process;
import java.util.ArrayList;
import java.util.List;

import com.ajdbxt.dao.Process.ProcessDao;
import com.ajdbxt.dao.Process.ProcessDepartmentDao;
import com.ajdbxt.dao.Process.ProcessInfoDao;
import com.ajdbxt.dao.Process.ProcessPoliceDao;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DO.ajdbxt_process;
import com.ajdbxt.domain.DTO.Process.ProcessDTO;
import com.ajdbxt.domain.VO.Process.showProcessVO;
import com.ajdbxt.service.Process.ProcessService;

import util.JsonUtils;

public class ProcessServiceImpl implements ProcessService {
	private ProcessDao processDao;
	private ProcessInfoDao processInfoDao;
	private ProcessPoliceDao processPoliceDao;
	private ProcessDepartmentDao processDepartmentDao;
	
	public ProcessInfoDao getProcessInfoDao() {
		return processInfoDao;
	}

	public void setProcessInfoDao(ProcessInfoDao processInfoDao) {
		this.processInfoDao = processInfoDao;
	}

	
	public ProcessPoliceDao getProcessPoliceDao() {
		return processPoliceDao;
	}

	public void setProcessPoliceDao(ProcessPoliceDao processPoliceDao) {
		this.processPoliceDao = processPoliceDao;
	}

	public ProcessDepartmentDao getProcessDepartmentDao() {
		return processDepartmentDao;
	}

	public void setProcessDepartmentDao(ProcessDepartmentDao processDepartmentDao) {
		this.processDepartmentDao = processDepartmentDao;
	}

	public ProcessDao getProcessDao() {
		return processDao;
	}

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
		List<ProcessDTO> processDTOList=new ArrayList<ProcessDTO>();
		ProcessDTO processDTO;
		processVO.setCount(processDao.findAllProcess());
		int pages=processVO.getCount()/length;
		List<ajdbxt_process> processList=processDao.findSomeProcess(processVO.getCurrPage()*10, length);
		if(processVO.getCount()/length>0) {
			pages++;
		}
		processVO.setTotalPage(pages);
		for(ajdbxt_process process :processList) {
			processDTO=new ProcessDTO();
			processDTO.setProcess(process);
			processDTO.setInfo(processInfoDao.findInfoById(process.getProcess_case_id()));
			List<ajdbxt_police> police_list=new ArrayList<ajdbxt_police>();
			police_list.add(processPoliceDao.findPoliceById(processDTO.getInfo().getInfo_main_police()));
			police_list.add(processPoliceDao.findPoliceById(processDTO.getInfo().getInfo_assistant_police_one()));
			if(processDTO.getInfo().getInfo_assistant_police_two()!=null&&!processDTO.getInfo().getInfo_assistant_police_two().isEmpty()) {
				police_list.add(processPoliceDao.findPoliceById(processDTO.getInfo().getInfo_assistant_police_two()));
			}
			processDTO.setPolice(police_list);
			processDTO.setDepartment(processDepartmentDao.findDepartmentById(processDTO.getInfo().getInfo_department()));
			processDTOList.add(processDTO);
		}
		processVO.setList(processDTOList);
		return JsonUtils.toJson(processVO);
	}

}
