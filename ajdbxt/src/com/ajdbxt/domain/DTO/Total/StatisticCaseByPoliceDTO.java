package com.ajdbxt.domain.DTO.Total;

import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DO.ajdbxt_process;

public class StatisticCaseByPoliceDTO {
	//民警1
	private ajdbxt_police mainPolice;
	//民警2
	private ajdbxt_police insisPoliceOne;
	//民警3
	private ajdbxt_police insisPOliceTwo;
	//案件
	private ajdbxt_info caseInfo;
	//案件流程
	private ajdbxt_process caseProcess;
	//办案单位
	private ajdbxt_department department;
	public ajdbxt_police getMainPolice() {
		return mainPolice;
	}
	public void setMainPolice(ajdbxt_police mainPolice) {
		this.mainPolice = mainPolice;
	}
	public ajdbxt_police getInsisPoliceOne() {
		return insisPoliceOne;
	}
	public void setInsisPoliceOne(ajdbxt_police insisPoliceOne) {
		this.insisPoliceOne = insisPoliceOne;
	}
	public ajdbxt_police getInsisPOliceTwo() {
		return insisPOliceTwo;
	}
	public void setInsisPOliceTwo(ajdbxt_police insisPOliceTwo) {
		this.insisPOliceTwo = insisPOliceTwo;
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
