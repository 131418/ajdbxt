package com.ajdbxt.domain.DTO.Total;

import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;

public class StatisticPoliceCaseNumDTO {
	//民警对象
	private ajdbxt_police  police;
	
	//办案单位
	private  ajdbxt_department department;
	
	//行政案件
	private int adminCase;
	//刑事案件
	private int criminalCase;
	
	public ajdbxt_police getPolice() {
		return police;
	}
	public void setPolice(ajdbxt_police police) {
		this.police = police;
	}
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
}
