package com.ajdbxt.action.Info;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;

import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;
import com.ajdbxt.service.Info.InfoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class InfoAction extends ActionSupport {
	
	private HttpServletRequest http_request;
	private HttpServletResponse http_response;
	private ajdbxt_info info;
	private InfoService infoService;
	private Page_list_caseInfoVo page_list_caseInfoVo;
	
	

	/*
	 * 录入页面
	 */
	public String page_CaseInput() {
		return "page_CaseInput";
	}
	
	public String listAll() {
		infoService.getAllCase();
		return null;
	}
	
	/*
	 * 案件列表页面
	 */
	public String page_CaseList() {
		return "page_CaseList";
	}
	
	/*
	 * 案件信息页面
	 */
	public String page_CaseInfo() {
		return "page_CaseInfo";
	}
	public void addCase() {
		GsonBuilder gsonBuilder =new GsonBuilder();
		gsonBuilder.setPrettyPrinting();//格式化json数据
		Gson gson=gsonBuilder.create();
		infoService.saveCase(info);
		try {
			
			http_response.setContentType("text/html;charset=utf-8");
			
			//此处要返回一个值班民警的gson对象
			
			http_response.getWriter().write(gson.toJson("success"));
		} catch (IOException e) {
			e.printStackTrace();
			try {
				http_response.getWriter().write(gson.toJson("error"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public void updateCase() throws IOException {
		GsonBuilder gsonBuilder =new GsonBuilder();
		gsonBuilder.setPrettyPrinting();//格式化json数据
		Gson gson=gsonBuilder.create();
		infoService.updateCase(info);
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson("success"));
	}
	
	
	public void deleteCase() throws IOException {
		GsonBuilder gsonBuilder =new GsonBuilder();
		gsonBuilder.setPrettyPrinting();//格式化json数据
		Gson gson=gsonBuilder.create();
		infoService.deleteCase(info.getAjdbxt_info_id());
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson("success"));
		
	}
	public void list() {
		
	}
	public void ListCaseInfoByPage() throws IOException {
		GsonBuilder gsonBuilder =new GsonBuilder();
		gsonBuilder.setPrettyPrinting();//格式化json数据
		Gson gson=gsonBuilder.create();
		page_list_caseInfoVo=infoService.showCaseList(page_list_caseInfoVo);
		http_response.setContentType("text/html;charset:utf-8");
		http_response.getWriter().write(gson.toJson(page_list_caseInfoVo));
		
		
	}
	public HttpServletRequest getHttp_request() {
		return http_request;
	}

	public void setHttp_request(HttpServletRequest http_request) {
		this.http_request = http_request;
	}
	
	public HttpServletResponse getHttp_response() {
		return http_response;
	}

	public void setHttp_response(HttpServletResponse http_response) {
		this.http_response = http_response;
	}

	public ajdbxt_info geInfo() {
		return info;
	}

	public void setInfo(ajdbxt_info caseInfo) {
		this.info = caseInfo;
	}

	public InfoService geInfoService() {
		return infoService;
	}

	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}

	public Page_list_caseInfoVo getPage_list_caseInfoVo() {
		return page_list_caseInfoVo;
	}

	public void setPage_list_caseInfoVo(Page_list_caseInfoVo page_list_caseInfoVo) {
		this.page_list_caseInfoVo = page_list_caseInfoVo;
	}


}
