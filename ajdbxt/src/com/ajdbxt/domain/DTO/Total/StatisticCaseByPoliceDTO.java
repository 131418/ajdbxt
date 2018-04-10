package com.ajdbxt.domain.DTO.Total;

import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DO.ajdbxt_process;

public class StatisticCaseByPoliceDTO {
	private ajdbxt_police police;
	private ajdbxt_info caseInfo;
	private ajdbxt_process caseProcess;
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
	

}
