package com.ajdbxt.service.Process;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_process;
import com.ajdbxt.domain.DTO.Process.ProcessDTO;
import com.ajdbxt.domain.VO.Process.showProcessVO;

public interface ProcessService {
	public static final int PROCESS_DETENTION=1;
	public static final int PROCESS_PENALTY=2;
	public static final int PROCESS_TREATMENT_CATEGORY=3;
	public static final int PROCESS_CRIMINAL_DETENTION=4;
	public static final int PROCESS_ARREST=5;
	public static final int PROCESS_GET_KEEP_WAIT_INTERROGATE=6;
	public static final int PROCESS_LIVE_AT_HOME_UNDE_SURVEILLANCE=7;
	public static final int PROCESS_FILE_HAND=8;
	
	public ProcessDTO getSingleProcessByCaseId(String case_id);
	public String update(ajdbxt_process process,List<Integer> list);
	public showProcessVO getSomeProcessByShowProcessVO(showProcessVO processVO);
}

