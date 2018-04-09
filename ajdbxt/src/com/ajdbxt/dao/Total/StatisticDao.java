package com.ajdbxt.dao.Total;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DTO.Total.StatisticCaseByPoliceDTO;
import com.ajdbxt.domain.VO.Total.page_eachPoliceCaseVO;
import com.ajdbxt.domain.VO.Total.page_listPoliceCaseNumByPageAndSearchVO;

public interface StatisticDao {
	//得到所有的民警
	public List<ajdbxt_police> getAllPolice();
	
	//得到民警的不同案件类型的案件数量
	public int getAllCaseNumByPolice(page_listPoliceCaseNumByPageAndSearchVO listPoliceCaseByPageAndSearchVO ,String police_id,String category);
	
	//得到某警员的所有案件
	public List<StatisticCaseByPoliceDTO> getStatisticCaseList(page_eachPoliceCaseVO eachPoliceCaseVO);
	
	//得到某警员的所有案件数
	public int getCaseRecords(page_eachPoliceCaseVO eachPoliceCaseVO);

}
