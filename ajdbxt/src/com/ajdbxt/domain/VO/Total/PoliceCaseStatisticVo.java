package com.ajdbxt.domain.VO.Total;

import java.util.List;

import com.ajdbxt.domain.DTO.Total.StatisticCaseByPoliceDTO;
import com.ajdbxt.domain.DTO.Total.StatisticPoliceCaseDto;

public class PoliceCaseStatisticVo {
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
		
		//警员名
		private String policeName;
		
		//部门
		private String department;
		
		//排序字符串
		private String orderString;
		
		private List<StatisticPoliceCaseDto> statisticPoliceCaseDto;

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

		public String getPoliceName() {
			return policeName;
		}

		public void setPoliceName(String policeName) {
			this.policeName = policeName;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getOrderString() {
			return orderString;
		}

		public void setOrderString(String orderString) {
			this.orderString = orderString;
		}

		public List<StatisticPoliceCaseDto> getStatisticPoliceCaseDto() {
			return statisticPoliceCaseDto;
		}

		public void setStatisticPoliceCaseDto(List<StatisticPoliceCaseDto> statisticPoliceCaseDto) {
			this.statisticPoliceCaseDto = statisticPoliceCaseDto;
		}

}
