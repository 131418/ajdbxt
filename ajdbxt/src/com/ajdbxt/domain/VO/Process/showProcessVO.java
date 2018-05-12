package com.ajdbxt.domain.VO.Process;

import java.util.List;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DO.ajdbxt_process;
import com.ajdbxt.domain.DTO.Process.ProcessDTO;

public class showProcessVO {
	private String key;//搜索关键字

	//d当前页
	private int currPage;
	//总页
	private int totalPage;
	//总条
	private int count;
	//每页条书
	private int pageSize=20;
	//案件所有
	private List<ProcessDTO> list;
	
	public List<ProcessDTO> getList() {
		return list;
	}

	public void setList(List<ProcessDTO> list) {
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}

