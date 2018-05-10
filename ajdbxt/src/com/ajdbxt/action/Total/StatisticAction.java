package com.ajdbxt.action.Total;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.ajdbxt.domain.DTO.Total.StatisticDepartmentCaseNumDTO;
import com.ajdbxt.domain.DTO.Total.StatisticPoliceCaseDto;
import com.ajdbxt.domain.VO.Total.DepartmentStatisticVo;
import com.ajdbxt.domain.VO.Total.PoliceCaseStatisticVo;
import com.ajdbxt.service.Total.StatisticService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class StatisticAction extends ActionSupport {
	
	private StatisticService statisticService;
	private StatisticDepartmentCaseNumDTO statisticDepartmentCaseNumDTO;
	private StatisticPoliceCaseDto statisticPoliceCaseDto;
	private HttpServletRequest http_request;
	private DepartmentStatisticVo departmentStatisticVo;
	private PoliceCaseStatisticVo policeCaseStatisticVo;
	

	/*
	 * 部门案件统计页面
	 */
	public String page_listDepartmentCaseStatistics() {

		return "page_listDepartmentCaseStatistics";
	}

	/*
	 * 警员案件统计列表
	 */
	public String page_listPoliceCase() {
		
		return "page_listPoliceCaseStatistic";
		
	}
	
	/*
	 * 移动端部门统计页面
	 */
	public String mobile_departmentStatistic() {
		
		return "mobile_departmentStatistic";
	}
	
	/*
	 * 移动端警员统计页面
	 */
	public String mobile_policeStatistic() {
		
		return "mobile_policeStatistic";
	}

	/*
	 * 部门统计列表
	 */
	public void getListDeparmentCaseStatistics() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化数据
		Gson gson = gsonBuilder.create();
		HttpServletResponse http_response;
		departmentStatisticVo = statisticService.statisticDepartmentCase(departmentStatisticVo);
		http_response = ServletActionContext.getResponse();
		http_response.setContentType("text/html;charset=utf-8");
		System.out.println(gson.toJson(departmentStatisticVo));
		PrintWriter pw = http_response.getWriter();
		pw.write(gson.toJson(departmentStatisticVo));
		pw.flush();
		pw.close();
	}

	/*
	 * 警员案件统计列表
	 */
	public void getListPoiceCaseStatistic() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化数据
		Gson gson = gsonBuilder.create();
		HttpServletResponse http_response;
		http_response = ServletActionContext.getResponse();
		policeCaseStatisticVo = statisticService.statisticPoliceCase(policeCaseStatisticVo);
		http_response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = http_response.getWriter();
		System.out.println(gson.toJson(policeCaseStatisticVo));
		pw.write(gson.toJson(policeCaseStatisticVo));
		pw.flush();
		pw.close();
	}
	public HttpServletRequest getHttp_request() {
		return http_request;
	}

	public void setHttp_request(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	public DepartmentStatisticVo getDepartmentStatisticVo() {
		return departmentStatisticVo;
	}

	public void setDepartmentStatisticVo(DepartmentStatisticVo departmentStatisticVo) {
		this.departmentStatisticVo = departmentStatisticVo;
	}

	public PoliceCaseStatisticVo getPoliceCaseStatisticVo() {
		return policeCaseStatisticVo;
	}

	public void setPoliceCaseStatisticVo(PoliceCaseStatisticVo policeCaseStatisticVo) {
		this.policeCaseStatisticVo = policeCaseStatisticVo;
	}

	public StatisticPoliceCaseDto getStatisticPoliceCaseDto() {
		return statisticPoliceCaseDto;
	}

	public void setStatisticPoliceCaseDto(StatisticPoliceCaseDto statisticPoliceCaseDto) {
		this.statisticPoliceCaseDto = statisticPoliceCaseDto;
	}

	public StatisticService getStatisticService() {
		return statisticService;
	}

	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

	public StatisticDepartmentCaseNumDTO getStatisticDepartmentCaseNumDTO() {
		return statisticDepartmentCaseNumDTO;
	}

	public void setStatisticDepartmentCaseNumDTO(StatisticDepartmentCaseNumDTO statisticDepartmentCaseNumDTO) {
		this.statisticDepartmentCaseNumDTO = statisticDepartmentCaseNumDTO;
	}

}
