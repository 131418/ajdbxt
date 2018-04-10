package com.ajdbxt.service.impl.Total;

import java.util.ArrayList;
import java.util.List;

import com.ajdbxt.dao.Total.StatisticDao;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DTO.Total.StatisticPoliceCaseNumDTO;
import com.ajdbxt.domain.VO.Total.page_eachPoliceCaseVO;
import com.ajdbxt.domain.VO.Total.page_listPoliceCaseNumByPageAndSearchVO;
import com.ajdbxt.service.Total.StatisticService;

public class StatisticServiceImpl implements StatisticService {
	private StatisticDao statisticDao;

	
	//统计案件数并分页
	@Override
	public page_listPoliceCaseNumByPageAndSearchVO getlistPoliceCaseByPageAndSearchVO(
			page_listPoliceCaseNumByPageAndSearchVO listPoliceCaseNumByPageAndSearchVO) {
		
		List<StatisticPoliceCaseNumDTO> list=new ArrayList<StatisticPoliceCaseNumDTO>();
		List<ajdbxt_police> listPolice=new ArrayList<ajdbxt_police>();
		listPolice=statisticDao.getAllPolice();
		for(int i=0;i<listPolice.size();i++) {
			StatisticPoliceCaseNumDTO statisticPoliceNumDTO=new StatisticPoliceCaseNumDTO();
			statisticPoliceNumDTO.setPolice(listPolice.get(i));
			statisticPoliceNumDTO.setAdminCase(statisticDao.getAllCaseNumByPolice(listPoliceCaseNumByPageAndSearchVO, listPolice.get(i).getAjdbxt_police_id(), "行政案件"));
			statisticPoliceNumDTO.setCriminalCase(statisticDao.getAllCaseNumByPolice(listPoliceCaseNumByPageAndSearchVO, listPolice.get(i).getAjdbxt_police_id(), "刑事案件"));
			list.add(statisticPoliceNumDTO);
		}
		System.out.println("执行了赋值操作");
		listPoliceCaseNumByPageAndSearchVO.setStatisticPoliceCaseNumDTO(list);
		
		if(listPoliceCaseNumByPageAndSearchVO.getSearchPolice() !=null && listPoliceCaseNumByPageAndSearchVO.getSearchPolice().length()>0) {
			for(StatisticPoliceCaseNumDTO police : list) {
				if(police.getPolice().getPolice_name().contains(listPoliceCaseNumByPageAndSearchVO.getSearchPolice())) {
					list.remove(police);
				}else {
					//警员名字变红
				}
				
			}
		}
		System.out.println(list.toString());
		//总记录数
		 int i= list.size();
		 listPoliceCaseNumByPageAndSearchVO.setTotalRecords(i);
		 listPoliceCaseNumByPageAndSearchVO.setTotalPages(((i-1)/listPoliceCaseNumByPageAndSearchVO.getPageSize())+1);
		 if(listPoliceCaseNumByPageAndSearchVO.getCurrePage()<=1) {
			 listPoliceCaseNumByPageAndSearchVO.setHasPrePage(false);
		 }else {
			 listPoliceCaseNumByPageAndSearchVO.setHasPrePage(true);
		 }
		 if(listPoliceCaseNumByPageAndSearchVO.getCurrePage()>=listPoliceCaseNumByPageAndSearchVO.getTotalPages()) {
			 listPoliceCaseNumByPageAndSearchVO.setHasNextPage(false);
		 }else {
			 listPoliceCaseNumByPageAndSearchVO.setHasNextPage(true);
		 }
		
		return listPoliceCaseNumByPageAndSearchVO;
	}

	//警员案件分页
	@Override
	public page_eachPoliceCaseVO getPoliceCaseBYpageAndSearch(page_eachPoliceCaseVO listEachPoliceCaseVO) {
		List list=new ArrayList();
		int i=statisticDao.getCaseRecords(listEachPoliceCaseVO);
		listEachPoliceCaseVO.setTotalRecords(i);
		listEachPoliceCaseVO.setTotalPages(((i-1)/listEachPoliceCaseVO.getPageSize())+1);
		 if(listEachPoliceCaseVO.getCurrePage()<=1) {
			 listEachPoliceCaseVO.setHasPrePage(false);
		 }else {
			 listEachPoliceCaseVO.setHasPrePage(true);
		 }
		 if(listEachPoliceCaseVO.getCurrePage()>=listEachPoliceCaseVO.getTotalPages()) {
			 listEachPoliceCaseVO.setHasNextPage(false);
		 }else {
			 listEachPoliceCaseVO.setHasNextPage(true);
		 }
		list=statisticDao.getStatisticCaseList(listEachPoliceCaseVO);
		listEachPoliceCaseVO.setCaseListByPolice(list);
		
		return listEachPoliceCaseVO;
	}

	
	public StatisticDao getStatisticDao() {
		return statisticDao;
	}

	public void setStatisticDao(StatisticDao statisticDao) {
		this.statisticDao = statisticDao;
	}



	
}
