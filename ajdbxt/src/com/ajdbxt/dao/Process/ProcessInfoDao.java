package com.ajdbxt.dao.Process;

import java.util.List;
import com.ajdbxt.domain.DO.ajdbxt_info;

public interface ProcessInfoDao {
	public List<ajdbxt_info> getInfoListCaseEnd(String police_id,int start,int length); 
	public List<ajdbxt_info> getInfoListCaptainCheck(String police_id,int start,int length);
	public List<ajdbxt_info> getInfoListProcessScore(String police_id,int start,int length);
	public List<ajdbxt_info> getInfoListProcessQuestion(String police_id,int start,int length);
	public List<ajdbxt_info> getInfoList(String police_id,int start,int length);
	public ajdbxt_info findInfoById(String info_id);
	public int  countInfoListCaseEnd(String police_id); 
	public int countInfoListCaptainCheck(String police_id);
	public int countInfoListProcessScore(String police_id);
	public int countInfoListProcessQuestion(String police_id);
	public int countInfoList(String police_id);

}