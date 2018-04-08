package com.ajdbxt.service.impl.Process;

import java.util.List;
import com.ajdbxt.dao.Process.ProcessInfoDao;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;
import com.ajdbxt.service.Process.ProcessInfoService;
import com.google.gson.Gson;

import util.JsonUtils;

public class ProcessInfoServiceImpl implements ProcessInfoService {
	private ProcessInfoDao processInfoDao;
	
	public void setProcessInfoDao(ProcessInfoDao processInfoDao) {
		this.processInfoDao = processInfoDao;
	}


	@Override
	public String getInfoList(int check_end_score_question ,String police_id,Page_list_caseInfoVo infoVO) {
		List<ajdbxt_info> list;
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
		pages=countList/length;
		infoVO.setCountRecords(countList);
		infoVO.setTotalPages(pages);;
		infoVO.setCaselist(list);
		return JsonUtils.toJson(infoVO);
	}


	@Override
	public ajdbxt_info getSingleInfo(String process_id) {
		return processInfoDao.findInfoById(process_id);
	}

}
