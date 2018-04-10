package com.ajdbxt.action.Total;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.ajdbxt.domain.VO.Total.page_eachPoliceCaseVO;
import com.ajdbxt.domain.VO.Total.page_listPoliceCaseNumByPageAndSearchVO;
import com.ajdbxt.service.Total.StatisticService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StatisticAction {
	private HttpServletResponse http_response;
	private page_listPoliceCaseNumByPageAndSearchVO listPoliceCaseByPageAndSearchVO;
	private page_eachPoliceCaseVO listEachPoliceCaseVO;
	private StatisticService statisticService;
	
	/*
	 * 警员案件数量统计页面
	*/
	public String page_listPoliceCaseStatistics() {
		
		return "page_listPoliceCaseStatistics";
	}
	
	/*
	 * 警员案件列表
	*/
	public String page_listPoliceCase() {
		
		return "page_listPoliceCase";
	}
	
	/*
	 * 统计警员案件数量列表
	*/
	public void getListPoliceCaseStatistics() throws IOException {
		GsonBuilder gsonBuilder=new GsonBuilder();
		gsonBuilder.setPrettyPrinting();//格式化数据
		Gson gson=gsonBuilder.create();
		listPoliceCaseByPageAndSearchVO=statisticService.getlistPoliceCaseByPageAndSearchVO(listPoliceCaseByPageAndSearchVO);
		http_response.setCharacterEncoding("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(listPoliceCaseByPageAndSearchVO));
		
	}
	
	/*
	 * 警员案件列表
	 */
	public void getListPoiceCase() throws IOException {
		GsonBuilder gsonBuilder=new GsonBuilder();
		gsonBuilder.setPrettyPrinting();//格式化数据
		Gson gson=gsonBuilder.create();
		listEachPoliceCaseVO=statisticService.getPoliceCaseBYpageAndSearch(listEachPoliceCaseVO);
		http_response.setCharacterEncoding("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(listEachPoliceCaseVO));
		
	}
	
	
	public page_listPoliceCaseNumByPageAndSearchVO getListPoliceCaseByPageAndSearchVO() {
		return listPoliceCaseByPageAndSearchVO;
	}
	public void setListPoliceCaseByPageAndSearchVO(page_listPoliceCaseNumByPageAndSearchVO listPoliceCaseByPageAndSearchVO) {
		this.listPoliceCaseByPageAndSearchVO = listPoliceCaseByPageAndSearchVO;
	}
	public page_eachPoliceCaseVO getListEachPoliceCaseVO() {
		return listEachPoliceCaseVO;
	}
	public void setListEachPoliceCaseVO(page_eachPoliceCaseVO listEachPoliceCaseVO) {
		this.listEachPoliceCaseVO = listEachPoliceCaseVO;
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

}
