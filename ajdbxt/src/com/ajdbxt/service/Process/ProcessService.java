package com.ajdbxt.service.Process;

import com.ajdbxt.domain.DO.ajdbxt_process;
import com.ajdbxt.domain.DTO.Process.ProcessDTO;
import com.ajdbxt.domain.VO.Process.showProcessVO;

public interface ProcessService {
	public final static int rollback=1;
	public final static int question =2;
	public final static int case_end =3;
	public final static int punish=4;
	public final static int forceMeasure=5;
	public final static int result=6;
	public final static int fileBack=7;
	public final static int goods_lib=8;
	public final static int question_update=9;
	
	public ProcessDTO getSingleProcessByCaseId(String case_id);
	/**
	 * 流程更改方法
	 * @param process 流程对象
	 * @param list 
	 * @return 更改后的json
	 */
	public String update(ajdbxt_process process,int changeType);
	public showProcessVO getSomeProcessByShowProcessVO(showProcessVO processVO);
}
