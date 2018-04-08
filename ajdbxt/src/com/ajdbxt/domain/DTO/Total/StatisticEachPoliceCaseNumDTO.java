package com.ajdbxt.domain.DTO.Total;

import com.ajdbxt.domain.DO.ajdbxt_police;

public class StatisticEachPoliceCaseNumDTO {
	private ajdbxt_police police; //警员
	private int adminnistrCaseNum;//行政案件
	private int  criminalCaseNum;//刑事案件
	
	public ajdbxt_police getPolice() {
		return police;
	}
	public void setPolice(ajdbxt_police police) {
		this.police = police;
	}
	public int getAdminnistrCaseNum() {
		return adminnistrCaseNum;
	}
	public void setAdminnistrCaseNum(int adminnistrCaseNum) {
		this.adminnistrCaseNum = adminnistrCaseNum;
	}
	public int getCriminalCaseNum() {
		return criminalCaseNum;
	}
	public void setCriminalCaseNum(int criminalCaseNum) {
		this.criminalCaseNum = criminalCaseNum;
	}
	

}
