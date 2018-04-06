package com.ajdbxt.domain.DTO.Process;

import com.ajdbxt.domain.DO.ajdbxt_police;
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
