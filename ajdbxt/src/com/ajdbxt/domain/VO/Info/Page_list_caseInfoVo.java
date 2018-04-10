package com.ajdbxt.domain.VO.Info;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_info;

public class Page_list_caseInfoVo {
	private int currPage=1;
	private int totalPages=1;
	private int countRecords=0;
	private int pageSize=10;
	private boolean havePrePage=false;
	private boolean haveNexPage=false;
	private List<ajdbxt_info> Caselist;
	
	
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
	public boolean isHavePrePage() {
		return havePrePage;
	}
	public void setHavePrePage(boolean havePrePage) {
		this.havePrePage = havePrePage;
	}
	public boolean isHaveNexPage() {
		return haveNexPage;
	}
	public void setHaveNexPage(boolean haveNexPage) {
		this.haveNexPage = haveNexPage;
	}
	public List<ajdbxt_info> getCaselist() {
		return Caselist;
	}
	public void setCaselist(List<ajdbxt_info> caselist) {
		Caselist = caselist;
	}
	

}
