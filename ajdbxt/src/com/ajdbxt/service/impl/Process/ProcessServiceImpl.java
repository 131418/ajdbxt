package com.ajdbxt.service.impl.Process;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.ajdbxt.dao.Info.InfoPoliceDao;
import com.ajdbxt.dao.Process.ProcessDao;
import com.ajdbxt.dao.Process.ProcessDepartmentDao;
import com.ajdbxt.dao.Process.ProcessInfoDao;
import com.ajdbxt.dao.Process.ProcessPoliceDao;
import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DO.ajdbxt_process;
import com.ajdbxt.domain.DTO.Process.ProcessDTO;
import com.ajdbxt.domain.VO.Process.showProcessVO;
import com.ajdbxt.service.Process.ProcessService;
import util.JsonUtils;
import util.MsgSend;
import util.SMSThread;

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
	public ProcessDTO getSingleProcessByCaseId(String case_id) {
		ProcessDTO processDTO=new ProcessDTO();
		List list=processDao.findProcessByCaseId(case_id);
		ajdbxt_process process;
		if(list.size()!=0) {
			process=(ajdbxt_process)list.get(0);
		}else {
			process=null;
		}
		ajdbxt_info info =processInfoDao.findInfoById(case_id);
		ajdbxt_department department=processDepartmentDao.findDepartmentById(info.getInfo_department());
		List<ajdbxt_police> policeList=new ArrayList<>();
		policeList.add(processPoliceDao.findPoliceById(info.getInfo_main_police()));
		if(info.getInfo_assistant_police_one()!=null
				&&!info.getInfo_assistant_police_one().equals("")
					&&!info.getInfo_assistant_police_one().equals("否")) {
			policeList.add(processPoliceDao.findPoliceById(info.getInfo_assistant_police_one()));
		}
		if(info.getInfo_assistant_police_two()!=null
				&&!info.getInfo_assistant_police_two().equals("")
				&&!info.getInfo_assistant_police_two().equals("否")) {
			policeList.add(processPoliceDao.findPoliceById(info.getInfo_assistant_police_two()));
		}
		ajdbxt_police cap=processPoliceDao.findPoliceById(info.getInfo_department_captain());
		processDTO.setCap(cap);
		processDTO.setDepartment(department);
		processDTO.setInfo(info);
		processDTO.setPolice(policeList);
		processDTO.setProcess(process);
		return processDTO;
	}
	@Override
	public String update(ajdbxt_process process,List<Integer> list) {
		processDao.updateProcess(process);
		return "";
	}
	
	@Override
	public boolean update(ajdbxt_process process, int send_massage_type) {
		ProcessDTO processDTO=new ProcessDTO();
		processDTO.setProcess(process);
		ajdbxt_info info=processInfoDao.findInfoById(process.getProcess_case_id());
		processDao.saveProcess(process);
		boolean caseFiled=false;
		if(info.getInfo_category().equals("行政案件")) {
			caseFiled=true;
		}
		ApplicationContext applicationContext=(ApplicationContext) ServletActionContext.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		switch (send_massage_type) {
		case PROCESS_FILE_HAND:
			new SMSThread(MsgSend.CASE_PAGE_HAND_IN_VOICE,info.getAjdbxt_info_id(),caseFiled,applicationContext).start();
			break;
		case PROCESS_LIVE_AT_HOME_UNDE_SURVEILLANCE:
			new SMSThread(MsgSend.MONITORING_LIVE_VOICE,info.getAjdbxt_info_id(),caseFiled,applicationContext).start();
			break;
		case PROCESS_CRIMINAL_DETENTION://刑事拘留
			
			break;
		case PROCESS_GET_KEEP_WAIT_INTERROGATE:
			new SMSThread(MsgSend.GET_KEEP_WAIT_EXAMINE_VOICE,info.getAjdbxt_info_id(),caseFiled,applicationContext).start();
			break;
		case PROCESS_DETENTION:
			new SMSThread(MsgSend.PUNISH_DETENTION_VOICE,info.getAjdbxt_info_id(),caseFiled,applicationContext).start();
			break;
		case PROCESS_ARREST:
			new SMSThread(MsgSend.SUE_RESULT_CATCH_POLICE_VOICE,info.getAjdbxt_info_id(),caseFiled,applicationContext).start();
			break;
		case PROCESS_PENALTY:
			new SMSThread(MsgSend.PUNISH_FINE_VOICE,info.getAjdbxt_info_id(),caseFiled,applicationContext).start();
			break;
		case PROCESS_TREATMENT_CATEGORY:
			if(process.getProcess_treatment_category().equals("社区戒毒")) {
				new SMSThread(MsgSend.COMMUNITY_ABANDON_DRUG_VOICE,info.getAjdbxt_info_id(),caseFiled,applicationContext).start();
			}else {
				new SMSThread(MsgSend.MANDATORY_ABANDON_DRUG_VOICE,info.getAjdbxt_info_id(),caseFiled,applicationContext).start();
			}
			break;
		}
		
		
		return true;
	}
	
	@Override
	public showProcessVO getSomeProcessByShowProcessVO(showProcessVO processVO) {
		int length=processVO.getPageSize();
		List<ProcessDTO> processDTOList=new ArrayList<ProcessDTO>();
		ProcessDTO processDTO;
		processVO.setCount(processDao.findAllProcess());
		int pages=processVO.getCount()/length;
		List<ajdbxt_process> processList=processDao.findSomeProcess((processVO.getCurrPage()-1)*10, length);
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
		return processVO;
	}
}

