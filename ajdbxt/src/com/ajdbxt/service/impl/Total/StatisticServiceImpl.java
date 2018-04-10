package com.ajdbxt.service.impl.Total;

import java.util.List;

import com.ajdbxt.dao.Total.StatisticsDao;
import com.ajdbxt.domain.DTO.Total.StatisticEachDepartmentCaseNumDTO;
import com.ajdbxt.service.Total.StatisticService;

public class StatisticServiceImpl implements StatisticService {
	private StatisticsDao statisticsDao;
	
	public StatisticsDao getStatisticsDao() {
		return statisticsDao;
	}


	public void setStatisticsDao(StatisticsDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}


	
	@Override
	public List<StatisticEachDepartmentCaseNumDTO> getStatisticList() {
		
		return statisticsDao.CaseNumByDepartmentAndCategory();
	}



	
	

}
