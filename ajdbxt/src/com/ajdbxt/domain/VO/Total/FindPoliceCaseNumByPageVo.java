package com.ajdbxt.domain.VO.Total;

import java.util.List;

import com.ajdbxt.domain.DTO.Total.StatisticEachPoliceCaseNumDTO;

public class FindPoliceCaseNumByPageVo {
	//当前
	private int currPage = 1;
    //总记录数
	private int totalRecords = 0;
    //每页显示的页数
	private int pageSize = 10;
	// 总页数
	private int totalPages = 1;
	// 上一页
	private boolean hasPrePage = false;
	// 下一页
	private boolean haseNextPage = false;
	// 开始时间
	private String start_time = "0000-00-00";

	// 结束时间
	private String stop_time = "9999-99-99";
	
	private List<StatisticEachPoliceCaseNumDTO> statisticEachPoliceCaseNumDTO;

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
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

	public boolean isHaseNextPage() {
		return haseNextPage;
	}

	public void setHaseNextPage(boolean haseNextPage) {
		this.haseNextPage = haseNextPage;
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

	public List<StatisticEachPoliceCaseNumDTO> getStatisticEachPoliceCaseNumDTO() {
		return statisticEachPoliceCaseNumDTO;
	}

	public void setStatisticEachPoliceCaseNumDTO(List<StatisticEachPoliceCaseNumDTO> statisticEachPoliceCaseNumDTO) {
		this.statisticEachPoliceCaseNumDTO = statisticEachPoliceCaseNumDTO;
	}

}
