package com.ajdbxt.domain.VO.Process;

import java.util.List;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DO.ajdbxt_process;

public class showProcessVO {
	private int currPage;
	private int totalPage;
	private int count;
	private int pageSize;
	private String queryString;
	private List<ajdbxt_process> list_process;
	private List<ajdbxt_info> list;
	private List<ajdbxt_police> list_police;
	
	public List<ajdbxt_police> getList_police() {
		return list_police;
	}

	public void setList_police(List<ajdbxt_police> list_police) {
		this.list_police = list_police;
	}

	public List<ajdbxt_process> getList_process() {
		return list_process;
	}

	public void setList_process(List<ajdbxt_process> list_process) {
		this.list_process = list_process;
	}

	public List<ajdbxt_info> getList() {
		return list;
	}

	public void setList(List<ajdbxt_info> list) {
		this.list = list;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	@Override
	public String toString() {
		return "showProcessVO [currPage=" + currPage + ", totalPage=" + totalPage + ", count=" + count + ", pageSize="
				+ pageSize + ", queryString=" + queryString + ", list_process=" + list_process + ", list=" + list
				+ ", list_police=" + list_police + "]";
	}

	


}
