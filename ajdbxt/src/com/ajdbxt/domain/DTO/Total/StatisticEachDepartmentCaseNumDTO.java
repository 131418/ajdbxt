package com.ajdbxt.domain.DTO.Total;

public class StatisticEachDepartmentCaseNumDTO {
	
	private String department;//办案部门
	private int adminnistrCaseNum;//行政案件
	private int  criminalCaseNum;//刑事案件
	private String start_time = "0000-00-00";//开始时间
	private String stop_time = "9999-99-99";//结束时间
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
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
	
	

}
