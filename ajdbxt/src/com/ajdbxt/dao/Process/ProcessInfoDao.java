package com.ajdbxt.dao.Process;

import java.util.List;
import com.ajdbxt.domain.DO.ajdbxt_info;

public interface ProcessInfoDao {
	public List<ajdbxt_info> getInfoListCaseEnd(String police_id); 
	public List<ajdbxt_info> getInfoListCaptainCheck(String police_id);
	public List<ajdbxt_info> getInfoList(String police_id);
}