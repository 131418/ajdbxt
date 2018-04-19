package com.ajdbxt.service.Total;

import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.VO.Total.page_eachPoliceCaseVO;
import com.ajdbxt.domain.VO.Total.page_listPoliceCaseNumByPageAndSearchVO;

public interface StatisticService {
	
	//返回统计案件VO
	public page_listPoliceCaseNumByPageAndSearchVO getlistPoliceCaseByPageAndSearchVO(page_listPoliceCaseNumByPageAndSearchVO listPoliceCaseNumByPageAndSearchVO);
	
	//警员案件
	public page_eachPoliceCaseVO getPoliceCaseBYpageAndSearch(page_eachPoliceCaseVO listEachPoliceCaseVO);
}
