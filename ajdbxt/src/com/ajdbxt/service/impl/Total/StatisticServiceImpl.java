package com.ajdbxt.service.impl.Total;

import java.util.ArrayList;
import java.util.List;

import com.ajdbxt.dao.Total.StatisticDao;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DTO.Total.StatisticCaseByPoliceDTO;
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
		System.out.println("拿到了"+listPoliceCaseNumByPageAndSearchVO.getSearchPolice());
		
		List<StatisticPoliceCaseNumDTO> list=new ArrayList<StatisticPoliceCaseNumDTO>();
		List<ajdbxt_police> listPolice=new ArrayList<ajdbxt_police>();
		listPolice=statisticDao.getPolice(listPoliceCaseNumByPageAndSearchVO);
		for(int i=0;i<listPolice.size();i++) {
			StatisticPoliceCaseNumDTO statisticPoliceNumDTO=new StatisticPoliceCaseNumDTO();
			statisticPoliceNumDTO.setPolice(listPolice.get(i));
			statisticPoliceNumDTO.setAdminCase(statisticDao.getAllCaseNumByPolice(listPoliceCaseNumByPageAndSearchVO, listPolice.get(i).getAjdbxt_police_id(), "行政案件"));
			statisticPoliceNumDTO.setCriminalCase(statisticDao.getAllCaseNumByPolice(listPoliceCaseNumByPageAndSearchVO, listPolice.get(i).getAjdbxt_police_id(), "刑事案件"));
			list.add(statisticPoliceNumDTO);
		}
		System.out.println("执行了赋值操作");
		
		/*if(listPoliceCaseNumByPageAndSearchVO.getSearchPolice() !=null && listPoliceCaseNumByPageAndSearchVO.getSearchPolice().length()>0) {
			for(StatisticPoliceCaseNumDTO listDto : list) {
				if(listDto.getPolice().getPolice_name().contains(listPoliceCaseNumByPageAndSearchVO.getSearchPolice())) {
					System.out.println(listDto.getPolice().getPolice_name());
					//警员名字变红
				}else {
					
					list.remove(listDto);
				}
				
			}
		}*/
		 
		
		//分页
		List<StatisticPoliceCaseNumDTO> newlist=new ArrayList<StatisticPoliceCaseNumDTO>();
		for(int i=(listPoliceCaseNumByPageAndSearchVO.getCurrePage()-1);
				i<(listPoliceCaseNumByPageAndSearchVO.getCurrePage()+listPoliceCaseNumByPageAndSearchVO.getPageSize());i++){
			System.out.println(i);
			System.out.println(list.size());
				if(i<list.size()) {
					newlist.add(list.get(i));
				}else {
					break;
				}
		}
		
		listPoliceCaseNumByPageAndSearchVO.setStatisticPoliceCaseNumDTO(newlist);
		System.out.println(list.toString());
		//总记录数;
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
		List<StatisticCaseByPoliceDTO> list=new ArrayList<StatisticCaseByPoliceDTO>();
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
