package com.ajdbxt.service.impl.Total;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ajdbxt.domain.DTO.Total.StatisticEachDepartmentCaseNumDTO;
import com.ajdbxt.service.Total.StatisticService;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext*.xml"})
public class TestSatisticService {
	
	@Resource
	private StatisticService statisticService;
	
	@Test
	public void test() {
		
		StatisticEachDepartmentCaseNumDTO statisticDto=(StatisticEachDepartmentCaseNumDTO) statisticService.getStatisticList();
		
		System.out.println(new Gson().toJson(statisticDto));
		
	}

	public StatisticService getStatisticService() {
		return statisticService;
	}

	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

}
