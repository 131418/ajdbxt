package com.ajdbxt.domain.DTO.Process;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DO.ajdbxt_police;

public class ProcessInfoDTO {
	/**
	 * 一个案件的信息
	 */
	private ajdbxt_info info;
	/**
	 * 该案件对应的流程
	 */
	private List<ajdbxt_police> police;
	public ajdbxt_info getInfo() {
		return info;
	}
	public void setInfo(ajdbxt_info info) {
		this.info = info;
	}
	public List<ajdbxt_police> getPolice() {
		return police;
	}
	public void setPolice(List<ajdbxt_police> police) {
		this.police = police;
	}
	public ajdbxt_department getDepartment() {
		return department;
	}
	public void setDepartment(ajdbxt_department department) {
		this.department = department;
	}
	//办案单位
	private ajdbxt_department department;
}

