package com.ajdbxt.action.Total;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.VO.Total.FindInfoByPageVO;
import com.ajdbxt.service.Total.TotalService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class TotalAction extends ActionSupport {
	private  TotalService  totalService;
	
	private int currentPage;
	
	private FindInfoByPageVO findInfoByPageVO;
	
	private ajdbxt_info ajdbxt_info;
	
	public void setTotalService(TotalService totalService) {
		this.totalService = totalService;
	}
	
	
	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public FindInfoByPageVO getFindInfoByPageVO() {
		return findInfoByPageVO;
	}


	public void setFindInfoByPageVO(FindInfoByPageVO findInfoByPageVO) {
		this.findInfoByPageVO = findInfoByPageVO;
	}


	public ajdbxt_info getAjdbxt_info() {
		return ajdbxt_info;
	}


	public void setAjdbxt_info(ajdbxt_info ajdbxt_info) {
		this.ajdbxt_info = ajdbxt_info;
	}

	public String totalPage() {
		return "totalpage";
	}
	
	//列出所有案件信息
	public void listAllInfo() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			this.findInfoByPageVO = totalService.listAllInfo(10, currentPage);
			response.getWriter().write(new Gson().toJson(this.findInfoByPageVO));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//根据搜索内容列出案件信息
	public void listInfoBySearch() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			this.findInfoByPageVO = totalService.listInfoBySearch(10, currentPage,ajdbxt_info.getInfo_category(),ajdbxt_info.getInfo_catch_time(),ajdbxt_info.getInfo_department(),ajdbxt_info.getInfo_main_police(),ajdbxt_info.getInfo_assistant_police_one(),ajdbxt_info.getInfo_assistant_police_two());
			response.getWriter().write(new Gson().toJson(this.findInfoByPageVO));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
