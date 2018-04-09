package com.ajdbxt.service.Total;

import java.util.List;
import com.ajdbxt.domain.DTO.Total.StatisticPoliceCaseNumDTO;
import com.ajdbxt.domain.VO.Total.page_eachPoliceCaseVO;
import com.ajdbxt.domain.VO.Total.page_listPoliceCaseNumByPageAndSearchVO;

public interface StatisticService {
	
	//返回统计案件VO
	public page_listPoliceCaseNumByPageAndSearchVO getlistPoliceCaseByPageAndSearchVO(page_listPoliceCaseNumByPageAndSearchVO listPoliceCaseNumByPageAndSearchVO);
	
	//警员案件
	public page_eachPoliceCaseVO getPoliceCaseBYpageAndSearch(page_eachPoliceCaseVO listEachPoliceCaseVO);
}
