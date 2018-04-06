package com.ajdbxt.service.Process;

public interface ProcessInfoService {
	public final static int CAPTAIN_CHECK=0;
	public final static int CASE_END=1;
	public String getInfoList(int check_end,String police_id);
}
