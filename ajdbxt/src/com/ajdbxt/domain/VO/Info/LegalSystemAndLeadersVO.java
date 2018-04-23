package com.ajdbxt.domain.VO.Info;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;

public class LegalSystemAndLeadersVO {
	private List<ajdbxt_police> legals;
	private List<ajdbxt_police> leaders;
	private List<ajdbxt_department> departments;
	public List<ajdbxt_department> getDepartments() {
		return departments;
	}
	public void setDepartments(List<ajdbxt_department> departments) {
		this.departments = departments;
	}
	public List<ajdbxt_police> getLeaders() {
		return leaders;
	}
	public void setLeaders(List<ajdbxt_police> leaders) {
		this.leaders = leaders;
	}
	public List<ajdbxt_police> getLegals() {
		return legals;
	}
	public void setLegals(List<ajdbxt_police> legals) {
		this.legals = legals;
	}
	
}

