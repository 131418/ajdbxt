package com.ajdbxt.action.Total;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ajdbxt.domain.DTO.Total.StatisticCaseByPoliceDTO;
import com.ajdbxt.domain.DTO.Total.StatisticPoliceCaseNumDTO;
import com.ajdbxt.domain.VO.Total.page_eachPoliceCaseVO;
import com.ajdbxt.domain.VO.Total.page_listPoliceCaseNumByPageAndSearchVO;
import com.ajdbxt.service.Total.StatisticService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class StatisticAction extends ActionSupport {
	/**
	 * 
	 */
	private page_listPoliceCaseNumByPageAndSearchVO listPoliceCaseByPageAndSearchVO;
	private page_eachPoliceCaseVO listEachPoliceCaseVO;
	private StatisticService statisticService;
	private StatisticPoliceCaseNumDTO statisticPoliceCaseNumDTO;
	private StatisticCaseByPoliceDTO statisticCaseByPoliceDTO;
	private HttpServletRequest http_request;

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
		http_request=ServletActionContext.getRequest();
		System.out.println(http_request.getParameter("police_id"));
		ActionContext.getContext().getValueStack().set("police_id", http_request.getParameter("police_id"));
		return "page_listPoliceCase";
		
	}

	/*
	 * 统计警员案件数量列表
	 */
	public void getListPoliceCaseStatistics() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化数据
		Gson gson = gsonBuilder.create();
		HttpServletResponse http_response;
		listPoliceCaseByPageAndSearchVO = statisticService
				.getlistPoliceCaseByPageAndSearchVO(listPoliceCaseByPageAndSearchVO);
		http_response = ServletActionContext.getResponse();
		http_response.setContentType("text/html;charset=utf-8");
		System.out.println(gson.toJson(listPoliceCaseByPageAndSearchVO));
		PrintWriter pw = http_response.getWriter();
		pw.write(gson.toJson(listPoliceCaseByPageAndSearchVO));
		pw.flush();
		pw.close();
	}

	/*
	 * 警员案件列表
	 */
	public void getListPoiceCase() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化数据
		Gson gson = gsonBuilder.create();
		HttpServletResponse http_response;
		http_response = ServletActionContext.getResponse();
		listEachPoliceCaseVO = statisticService.getPoliceCaseBYpageAndSearch(listEachPoliceCaseVO);
		http_response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = http_response.getWriter();
		String name=listEachPoliceCaseVO.getPoliceName();
		System.out.println(gson.toJson(listEachPoliceCaseVO));
		pw.write(gson.toJson(listEachPoliceCaseVO).replaceAll(name, "<span style='color: #ff5063;'>"+name+"</span>"));
		pw.flush();
		pw.close();
	}

	public page_listPoliceCaseNumByPageAndSearchVO getListPoliceCaseByPageAndSearchVO() {
		return listPoliceCaseByPageAndSearchVO;
	}

	public void setListPoliceCaseByPageAndSearchVO(
			page_listPoliceCaseNumByPageAndSearchVO listPoliceCaseByPageAndSearchVO) {
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

	public StatisticPoliceCaseNumDTO getStatisticPoliceCaseNumDTO() {
		return statisticPoliceCaseNumDTO;
	}

	public void setStatisticPoliceCaseNumDTO(StatisticPoliceCaseNumDTO statisticPoliceCaseNumDTO) {
		this.statisticPoliceCaseNumDTO = statisticPoliceCaseNumDTO;
	}

	public StatisticCaseByPoliceDTO getStatisticCaseByPoliceDTO() {
		return statisticCaseByPoliceDTO;
	}

	public void setStatisticCaseByPoliceDTO(StatisticCaseByPoliceDTO statisticCaseByPoliceDTO) {
		this.statisticCaseByPoliceDTO = statisticCaseByPoliceDTO;
	}

	public HttpServletRequest getHttp_request() {
		return http_request;
	}

	public void setHttp_request(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

}
