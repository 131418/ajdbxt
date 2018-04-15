package com.ajdbxt.domain.VO.Total;

import java.util.List;

import com.ajdbxt.domain.DTO.Total.StatisticCaseByPoliceDTO;

public class page_eachPoliceCaseVO {
	//当前页
	private int currePage = 1;
    //总记录数
	private int totalRecords = 0;
    //每页显示的记录数
	private int pageSize = 10;
	// 总页数
	private int totalPages = 1;
	// 上一页
	private boolean hasPrePage = false;
	// 下一页
	private boolean hasNextPage = false;
	// 开始时间
	private String start_time = "";
	// 结束时间
	private String stop_time = "";
	//案件名
	private String queryCaseName;
	
	//警员id
	private String police_id;
	
	//案件类别
	private String category;
	
	//返回的警员名
	private String policeName;
	
	private List<StatisticCaseByPoliceDTO> caseListByPolice;

	public int getCurrePage() {
		return currePage;
	}

	public void setCurrePage(int currePage) {
		this.currePage = currePage;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getStop_time() {
		return stop_time;
	}

	public void setStop_time(String stop_time) {
		this.stop_time = stop_time;
	}

	public String getQueryCaseName() {
		return queryCaseName;
	}

	public void setQueryCaseName(String queryCaseName) {
		this.queryCaseName = queryCaseName;
	}

	public String getPolice_id() {
		return police_id;
	}

	public void setPolice_id(String police_id) {
		this.police_id = police_id;
	}

	public List<StatisticCaseByPoliceDTO> getCaseListByPolice() {
		return caseListByPolice;
	}

	public void setCaseListByPolice(List<StatisticCaseByPoliceDTO> caseListByPolice) {
		this.caseListByPolice = caseListByPolice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPoliceName() {
		return policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

}
