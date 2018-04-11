package com.ajdbxt.domain.VO.Total;

import java.util.List;

import com.ajdbxt.domain.DTO.Total.StatisticPoliceCaseNumDTO;

public class page_listPoliceCaseNumByPageAndSearchVO {
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
			private String start_time = "0000-00-00";
			// 结束时间
			private String stop_time = "9999-99-99";
			//办案部门
			private String department;
			//查找民警
			private String searchPolice;
			
			private List<StatisticPoliceCaseNumDTO> statisticPoliceCaseNumDTO;

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

			public String getDepartment() {
				return department;
			}

			public void setDepartment(String department) {
				this.department = department;
			}
			

			public List<StatisticPoliceCaseNumDTO> getStatisticPoliceCaseNumDTO() {
				return statisticPoliceCaseNumDTO;
			}

			public void setStatisticPoliceCaseNumDTO(List<StatisticPoliceCaseNumDTO> statisticPoliceCaseNumDTO) {
				this.statisticPoliceCaseNumDTO = statisticPoliceCaseNumDTO;
			}

			public String getSearchPolice() {
				return searchPolice;
			}

			public void setSearchPolice(String searchPolice) {
				this.searchPolice = searchPolice;
			}
			
			
			
}
