package com.ajdbxt.service.impl.Process;

import java.util.ArrayList;
import java.util.List;

import com.ajdbxt.dao.Process.ProcessDao;
import com.ajdbxt.dao.Process.ProcessDepartmentDao;
import com.ajdbxt.dao.Process.ProcessInfoDao;
import com.ajdbxt.dao.Process.ProcessPoliceDao;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DO.ajdbxt_process;
import com.ajdbxt.domain.DTO.Process.ProcessDTO;
import com.ajdbxt.domain.DTO.Process.ProcessInfoDTO;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;
import com.ajdbxt.domain.VO.Process.showProcessVO;
import com.ajdbxt.service.Process.ProcessInfoService;
import util.JsonUtils;

public class ProcessInfoServiceImpl implements ProcessInfoService {
	private ProcessDao processDao;
	private ProcessInfoDao processInfoDao;
	private ProcessPoliceDao processPoliceDao;
	private ProcessDepartmentDao processDepartmentDao;
	public ProcessDao getProcessDao() {
		return processDao;
	}

	public void setProcessDao(ProcessDao processDao) {
		this.processDao = processDao;
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


	public ProcessInfoDao getProcessInfoDao() {
		return processInfoDao;
	}


	public void setProcessInfoDao(ProcessInfoDao processInfoDao) {
		this.processInfoDao = processInfoDao;
	}


	@Override
	public String getInfoList(int check_end_score_question ,String police_id,Page_list_caseInfoVo infoVO) {
		List<ajdbxt_info> list;
		List<ProcessInfoDTO> case_list=new ArrayList<ProcessInfoDTO>();
		int length=infoVO.getPageSize();
		int nowPage=(infoVO.getCurrPage()-1)*length;
		int countList=0;
		int pages=0;
		switch (check_end_score_question) {
		case CAPTAIN_CHECK:
			list=processInfoDao.getInfoListCaptainCheck(police_id,nowPage,length);
			countList=processInfoDao.countInfoListCaptainCheck(police_id);
			break;
		case CASE_END:
			list=processInfoDao.getInfoListCaseEnd(police_id,nowPage,length);
			countList=processInfoDao.countInfoListCaseEnd(police_id);
			break;
		case PROCESS_SCORE:
			list=processInfoDao.getInfoListProcessScore(police_id,nowPage,length);
			countList=processInfoDao.countInfoListProcessScore(police_id);
			break;
		case PROCESS_QUESTION:
			list=processInfoDao.getInfoListProcessQuestion(police_id,nowPage,length);
			countList=processInfoDao.countInfoListProcessQuestion(police_id);
			break;
		default:
			list=processInfoDao.getInfoList(police_id,nowPage,length);
			countList=processInfoDao.countInfoList(police_id);
			break;
		}
		ProcessInfoDTO processInfo;
		List<ajdbxt_police> policeList;
		for(ajdbxt_info info:list) {
			processInfo=new ProcessInfoDTO();
			processInfo.setInfo(info);
			processInfo.setDepartment(processDepartmentDao.findDepartmentById(info.getInfo_department()));
			policeList=new ArrayList<ajdbxt_police>();
			policeList.add(processPoliceDao.findPoliceById(info.getInfo_main_police()));
			policeList.add(processPoliceDao.findPoliceById(info.getInfo_assistant_police_one()));
			String three=info.getInfo_assistant_police_two();
			if(three!=null&&three.isEmpty()==false) {
				policeList.add(processPoliceDao.findPoliceById(three));
			}
			processInfo.setPolice(policeList);
			case_list.add(processInfo);
		}
		pages=countList/length;
		if(countList%length>0) {
			pages++;
		}
		infoVO.setCountRecords(countList);
		infoVO.setTotalPages(pages);
		infoVO.setCaselist(case_list);
		return JsonUtils.toJson(infoVO);
	}


	@Override
	public ajdbxt_info getSingleInfo(String process_id) {
		return processInfoDao.findInfoById(process_id);
	}

	@Override
	public String getProcessList(int check_end_score_question, String police_id, showProcessVO processVO) {
		int length=processVO.getPageSize();
		List<ProcessDTO> processDTOList=new ArrayList<ProcessDTO>();
		ProcessDTO processDTO;
		int nowPage=processVO.getCurrPage();
		int countList=0;
		int pages=0;
		List<ajdbxt_info> list;
		switch (check_end_score_question) {
		case CAPTAIN_CHECK:
			list=processInfoDao.getInfoListCaptainCheck(police_id,nowPage,length);
			countList=processInfoDao.countInfoListCaptainCheck(police_id);
			break;
		case CASE_END:
			list=processInfoDao.getInfoListCaseEnd(police_id,nowPage,length);
			countList=processInfoDao.countInfoListCaseEnd(police_id);
			break;
		case PROCESS_SCORE:
			list=processInfoDao.getInfoListProcessScore(police_id,nowPage,length);
			countList=processInfoDao.countInfoListProcessScore(police_id);
			break;
		case PROCESS_QUESTION:
			list=processInfoDao.getInfoListProcessQuestion(police_id,nowPage,length);
			countList=processInfoDao.countInfoListProcessQuestion(police_id);
			break;
		default:
			list=processInfoDao.getInfoList(police_id,nowPage,length);
			countList=processInfoDao.countInfoList(police_id);
			break;
		}
		
		for(ajdbxt_info info : list) {
			processDTO=new ProcessDTO();
			processDTO.setInfo(info);
			processDTO.setDepartment(processDepartmentDao.findDepartmentById(info.getInfo_department()));
			List<ajdbxt_police> police_list=new ArrayList<ajdbxt_police>();
			police_list.add(processPoliceDao.findPoliceById(info.getInfo_main_police()));
			police_list.add(processPoliceDao.findPoliceById(info.getInfo_assistant_police_one()));
			if(info.getInfo_assistant_police_two()!=null&&!info.getInfo_assistant_police_two().isEmpty()) {
				police_list.add(processPoliceDao.findPoliceById(info.getInfo_assistant_police_two()));
			}
			processDTO.setPolice(police_list);
			processDTO.setProcess(processDao.findProcessByCaseId(info.getAjdbxt_info_id()).get(0));
			processDTOList.add(processDTO);
		}
		processVO.setList(processDTOList);
		return JsonUtils.toJson(processVO);
	}

}

