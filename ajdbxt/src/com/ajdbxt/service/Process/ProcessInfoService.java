package com.ajdbxt.service.Process;

public interface ProcessInfoService {
	public final static int CAPTAIN_CHECK=0;
	public final static int CASE_END=1;
	public final static int PROCESS_SCORE=2;
	public final static int PROCESS_QUESTION=3;
	public String getInfoList(int check_end_score_question,String police_id);
}
