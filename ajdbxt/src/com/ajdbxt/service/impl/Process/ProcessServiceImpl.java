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
		ajdbxt_police teamLegal=processPoliceDao.findPoliceById(info.getInfo_department_legal_member());
		ajdbxt_police legal=processPoliceDao.findPoliceById(info.getInfo_legal_team_member());
		ajdbxt_police leader=processPoliceDao.findPoliceById(info.getInfo_bureau_leader());
		ajdbxt_police cap=processPoliceDao.findPoliceById(info.getInfo_department_captain());
		processDTO.setCap(cap);
		processDTO.setDepartment(department);
		processDTO.setInfo(info);
		processDTO.setLeader(leader);
		processDTO.setTeam_legal(teamLegal);
		processDTO.setLegal(legal);
		processDTO.setPolice(policeList);
		processDTO.setProcess(process);
		return processDTO;
	}
	@Override
	public String update(ajdbxt_process process, int changeType) {
		ProcessDTO processDTO=new ProcessDTO();
		processDTO.setProcess(process);
		ajdbxt_info info=processInfoDao.findInfoById(process.getProcess_case_id());
		processDTO.setInfo(info);
		processDao.saveProcess(process);
		boolean caseFiled=false;
		if(info.getInfo_category().equals("行政案件")) {
			caseFiled=true;
		}
		ApplicationContext applicationContext=(ApplicationContext) ServletActionContext.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		switch (changeType) {
		case case_end://结案通知上交案卷
			new SMSThread(MsgSend.CASE_END_VOICE, info.getAjdbxt_info_id(), caseFiled, applicationContext).start();
			break;
		case punish://处罚通知涉案财物入库
			new SMSThread(MsgSend.CASE_GOODS_LIB_VOICE, info.getAjdbxt_info_id(), caseFiled, applicationContext).start();
			break;
		case question://问题整改结束提醒处罚
			new SMSThread(MsgSend.QUESTION_UP_VOICE, info.getAjdbxt_info_id(), caseFiled, applicationContext).start();
			break;
		case rollback://打回完成通知提出问题
			new SMSThread(MsgSend.CASE_FILE_UP_VOICE, info.getAjdbxt_info_id(), caseFiled, applicationContext).start();
			break;	
		case result://起诉结果
			new SMSThread(MsgSend.CRIMINAL_SEARCH_BACK_VOICE, info.getAjdbxt_info_id(), caseFiled, applicationContext).start();
			break;
		case forceMeasure://强制措施，如果为拘留通知上交案卷
			new SMSThread(MsgSend.CRIMINAL_BAIL_VOICE, info.getAjdbxt_info_id(), caseFiled, applicationContext).start();
			break;
		case fileBack://延长期限通知拿回案卷
			new SMSThread(MsgSend.CRIMINAL_CASE_FILE_BACK_VOICE, info.getAjdbxt_info_id(), caseFiled, applicationContext).start();
			break;
		case goods_lib:
			if(!caseFiled) {
				new SMSThread(MsgSend.CASE_GOODS_LIB_VOICE, info.getAjdbxt_info_id(), caseFiled, applicationContext);
			}
		case question_update:
			new SMSThread(MsgSend.QUESTION_UPDATE_VOICE, info.getAjdbxt_info_id(), caseFiled, applicationContext);
		}	
		return JsonUtils.toJson(processDTO);
	}
	
	@Override
	public showProcessVO getSomeProcessByShowProcessVO(showProcessVO processVO) {
		int length=processVO.getPageSize();
		List<ProcessDTO> processDTOList=new ArrayList<ProcessDTO>();
		ProcessDTO processDTO;
		processVO.setCount(processDao.findAllProcess());
		int pages=processVO.getCount()/length;
		List<ajdbxt_process> processList=processDao.findSomeProcess((processVO.getCurrPage()-1)*10, length);
		if(processVO.getCount()%length>0) {
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

	@Override
	public String searchProcess(showProcessVO processVO) {
		int length=processVO.getPageSize();
		List<ProcessDTO> processDTOList=new ArrayList<ProcessDTO>();
		ProcessDTO processDTO;
		processVO.setCount(processInfoDao.countInfoByKey(processVO.getKey()));
		int pages=processVO.getCount()%length==0?processVO.getCount()/length:processVO.getCount()/length+1;
		processVO.setTotalPage(pages);
		List<ajdbxt_info> infoList=processInfoDao.findInfoByKey(processVO.getKey(),(processVO.getCurrPage()-1)*10, length);
		for(ajdbxt_info info :infoList) {
			processDTO=new ProcessDTO();
			processDTO.setInfo(info);
			processDTO.setProcess(processDao.findProcessByCaseId(info.getAjdbxt_info_id()).get(0));
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
