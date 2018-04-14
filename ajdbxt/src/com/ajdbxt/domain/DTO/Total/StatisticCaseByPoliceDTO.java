package com.ajdbxt.domain.DTO.Total;

import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DO.ajdbxt_process;

public class StatisticCaseByPoliceDTO {
	//民警
	private ajdbxt_police police;
	//案件
	private ajdbxt_info caseInfo;
	//案件流程
	private ajdbxt_process caseProcess;
	//办案单位
	private ajdbxt_department department;
	
	public ajdbxt_police getPolice() {
		return police;
	}
	public void setPolice(ajdbxt_police police) {
		this.police = police;
	}
	public ajdbxt_info getCaseInfo() {
		return caseInfo;
	}
	public void setCaseInfo(ajdbxt_info caseInfo) {
		this.caseInfo = caseInfo;
	}
	public ajdbxt_process getCaseProcess() {
		return caseProcess;
	}
	public void setCaseProcess(ajdbxt_process caseProcess) {
		this.caseProcess = caseProcess;
	}
	public ajdbxt_department getDepartment() {
		return department;
	}
	public void setDepartment(ajdbxt_department department) {
		this.department = department;
	}
	

}
