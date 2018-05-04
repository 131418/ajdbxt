package com.ajdbxt.domain.DTO.Total;

import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;

public class StatisticDepartmentCaseNumDTO {
	//办案单位
	private  ajdbxt_department department;
	//行政案件
	private int adminCase;
	//刑事案件
	private int criminalCase;
	//平均分
	private String averageScore;
	//总案件数
	private int totalCase;
	
	public int getAdminCase() {
		return adminCase;
	}
	public void setAdminCase(int adminCase) {
		this.adminCase = adminCase;
	}
	public int getCriminalCase() {
		return criminalCase;
	}
	public void setCriminalCase(int criminalCase) {
		this.criminalCase = criminalCase;
	}
	public ajdbxt_department getDepartment() {
		return department;
	}
	public void setDepartment(ajdbxt_department department) {
		this.department = department;
	}
	public int getTotalCase() {
		return totalCase;
	}
	public void setTotalCase(int totalCase) {
		this.totalCase = totalCase;
	}
	public String getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(String averageScore) {
		this.averageScore = averageScore;
	}
}
