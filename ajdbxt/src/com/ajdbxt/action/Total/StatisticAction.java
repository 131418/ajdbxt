package com.ajdbxt.action.Total;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.ajdbxt.domain.DTO.Total.StatisticEachDepartmentCaseNumDTO;
import com.ajdbxt.service.Total.StatisticService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StatisticAction {
	private StatisticService statisticService;
	private HttpServletResponse http_response;
	private StatisticEachDepartmentCaseNumDTO statisticDto;
	
	/*
	 * 显示单位案件数量页面
	 */
    public String firstPage() {
    	return "statistic";
    }
    
   /*
    *  获得各单位的办案数
    */
	public void listDepartmentByCategory() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.create();
		statisticDto=(StatisticEachDepartmentCaseNumDTO) statisticService.getStatisticList();
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(statisticDto));
		
		
	}
	
	
	
	
	public StatisticService getStatisticService() {
		return statisticService;
	}
	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}


	public HttpServletResponse getHttp_response() {
		return http_response;
	}


	public void setHttp_response(HttpServletResponse http_response) {
		this.http_response = http_response;
	}


	public StatisticEachDepartmentCaseNumDTO getStatisticDto() {
		return statisticDto;
	}


	public void setStatisticDto(StatisticEachDepartmentCaseNumDTO statisticDto) {
		this.statisticDto = statisticDto;
	}

}
