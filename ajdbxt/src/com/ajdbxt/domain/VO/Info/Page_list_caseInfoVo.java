package com.ajdbxt.domain.VO.Info;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DTO.Process.ProcessInfoDTO;

public class Page_list_caseInfoVo {
	private int currPage=1;
	private int totalPages=1;
	private int countRecords=0;
	private int pageSize=10;
	public List<ProcessInfoDTO> getCaselist() {
		return Caselist;
	}
	public void setCaselist(List<ProcessInfoDTO> caselist) {
		Caselist = caselist;
	}
	private List<ProcessInfoDTO> Caselist;
	
	
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCountRecords() {
		return countRecords;
	}
	public void setCountRecords(int countRecords) {
		this.countRecords = countRecords;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	

}
