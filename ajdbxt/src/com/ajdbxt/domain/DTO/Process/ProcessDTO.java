package com.ajdbxt.domain.DTO.Process;

import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_info;
import java.util.List;
import com.ajdbxt.domain.DO.ajdbxt_process;

public class ProcessDTO {
	/**
	 * 一个案件的信息
	 */
	private ajdbxt_info info;
	/**
	 * 该案件对应的流程
	 */
	private ajdbxt_process process;
	/**
	 * 办理该案件的警官
	 */
	private List<ajdbxt_police> police;
	//办案单位
	private ajdbxt_department department;
	private ajdbxt_police cap;
	private ajdbxt_police legal;
	private ajdbxt_police leader;
	private ajdbxt_police team_legal;
	public ajdbxt_police getLeader() {
		return leader;
	}
	public void setLeader(ajdbxt_police leader) {
		this.leader = leader;
	}
	public ajdbxt_police getTeam_legal() {
		return team_legal;
	}
	public void setTeam_legal(ajdbxt_police team_legal) {
		this.team_legal = team_legal;
	}
	public ajdbxt_police getLegal() {
		return legal;
	}
	public void setLegal(ajdbxt_police legal) {
		this.legal = legal;
	}
	
	public ajdbxt_police getCap() {
		return cap;
	}
	public void setCap(ajdbxt_police cap) {
		this.cap = cap;
	}
	public ajdbxt_department getDepartment() {
		return department;
	}
	public void setDepartment(ajdbxt_department department) {
		this.department = department;
	}
	public List<ajdbxt_police> getPolice() {
		return police;
	}
	public void setPolice(List<ajdbxt_police> police) {
		this.police = police;
	}
	public ajdbxt_process getProcess() {
		return process;
	}
	public void setProcess(ajdbxt_process process) {
		this.process = process;
	}
	public ajdbxt_info getInfo() {
		return info;
	}
	public void setInfo(ajdbxt_info info) {
		this.info = info;
	}
	
}

