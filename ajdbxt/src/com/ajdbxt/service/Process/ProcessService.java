package com.ajdbxt.service.Process;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_process;
import com.ajdbxt.domain.DTO.Process.ProcessDTO;
import com.ajdbxt.domain.VO.Process.showProcessVO;

public interface ProcessService {
	public static int rollback=1;
	public static int question =2;
	public static int case_end =3;
	public static int punish=4;
	
	public ProcessDTO getSingleProcessByCaseId(String case_id);
	/**
	 * 流程更改方法
	 * @param process 流程对象
	 * @param list 
	 * @return 更改后的json
	 */
	public String update(ajdbxt_process process,int changeType,String fielName);
	public showProcessVO getSomeProcessByShowProcessVO(showProcessVO processVO);
}

