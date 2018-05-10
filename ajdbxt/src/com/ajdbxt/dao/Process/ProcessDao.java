package com.ajdbxt.dao.Process;

import com.ajdbxt.domain.DO.ajdbxt_process;
import java.util.List;

public interface ProcessDao {
	public List<ajdbxt_process> findSomeProcess(int start,int length);
	public List<ajdbxt_process> findProcessByColumn(String Column,String key);
	public void updateProcess(ajdbxt_process process);
	public void saveProcess(ajdbxt_process process);
	public void deleteProcess(String case_id);
	public void saveProcessByCaseId(String case_id);
	public List<ajdbxt_process> findProcessByCaseId(String case_id);
	public int findAllProcess();
}

