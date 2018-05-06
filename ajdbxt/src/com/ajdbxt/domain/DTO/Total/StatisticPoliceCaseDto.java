package com.ajdbxt.domain.DTO.Total;

import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;

public class StatisticPoliceCaseDto {
	//警员
	private ajdbxt_police police;
	
	//办案单位
	private ajdbxt_department department;
	//行政主办案件
	private int adminMianCase;
	//行政协办案件
	private int adminAsistCase;
	//刑事主办
	private int crimalMainCase;
	//刑事协办
	private int crimalAsistCase;
	//主办案件平均分
	private double score_mian;
	
	public ajdbxt_police getPolice() {
		return police;
	}
	public void setPolice(ajdbxt_police police) {
		this.police = police;
	}
	public int getAdminMianCase() {
		return adminMianCase;
	}
	public void setAdminMianCase(int adminMianCase) {
		this.adminMianCase = adminMianCase;
	}
	public int getAdminAsistCase() {
		return adminAsistCase;
	}
	public void setAdminAsistCase(int adminAsistCase) {
		this.adminAsistCase = adminAsistCase;
	}
	public int getCrimalMainCase() {
		return crimalMainCase;
	}
	public void setCrimalMainCase(int crimalMainCase) {
		this.crimalMainCase = crimalMainCase;
	}
	public int getCrimalAsistCase() {
		return crimalAsistCase;
	}
	public void setCrimalAsistCase(int crimalAsistCase) {
		this.crimalAsistCase = crimalAsistCase;
	}
	public double getScore_mian() {
		return score_mian;
	}
	public void setScore_mian(double score_mian) {
		this.score_mian = score_mian;
	}
	public ajdbxt_department getDepartment() {
		return department;
	}
	public void setDepartment(ajdbxt_department department) {
		this.department = department;
	}

}
