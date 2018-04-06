package com.ajdbxt.action.Info;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.service.spi.ServiceRegistryImplementor;

import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;
import com.ajdbxt.service.Info.InfoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class InfoAction extends ActionSupport {
	
	private ajdbxt_info info;
	private InfoService infoService;
	private Page_list_caseInfoVo page_list_caseInfoVo;
	
	

	/*
	 * 录入页面
	 */
	public String page_CaseInput() {
		return "page_CaseInput";
	}
	/**
	 * 得到全部案件信息链表
	 * @return
	 */
	public String listAll() {
		try {
			ServletActionContext.getResponse().getWriter().print(infoService.getAllCase());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
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
		
	}
	public void updateCase() throws IOException {
		GsonBuilder gsonBuilder =new GsonBuilder();
		gsonBuilder.setPrettyPrinting();//格式化json数据
		Gson gson=gsonBuilder.create();
		infoService.updateCase(info);
	}
	
	
	public void deleteCase() throws IOException {
		GsonBuilder gsonBuilder =new GsonBuilder();
		gsonBuilder.setPrettyPrinting();//格式化json数据
		Gson gson=gsonBuilder.create();
		infoService.deleteCase(info.getAjdbxt_info_id());		
	}
	public void list() {
		
	}
	public void ListCaseInfoByPage() throws IOException {
		GsonBuilder gsonBuilder =new GsonBuilder();
		gsonBuilder.setPrettyPrinting();//格式化json数据
		Gson gson=gsonBuilder.create();
		page_list_caseInfoVo=infoService.showCaseList(page_list_caseInfoVo);
		
		
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
