package com.ajdbxt.service.Process;

import com.ajdbxt.domain.DO.ajdbxt_process;

public interface ProcessService {
	public ajdbxt_process getSingleProcessByCaseId(String case_id);
	public boolean update(ajdbxt_process process);
	public boolean update(ajdbxt_process process,int send_massage_type);	
	public String getAllProcess();
}
