package com.ajdbxt.service.Total;
import com.ajdbxt.domain.VO.Total.page_eachPoliceCaseVO;
import com.ajdbxt.domain.VO.Total.DepartmentStatisticVo;

public interface Statistic {
	
	//返回统计案件VO
	public DepartmentStatisticVo getlistPoliceCaseByPageAndSearchVO(DepartmentStatisticVo listPoliceCaseNumByPageAndSearchVO);
	
	//警员案件
	public page_eachPoliceCaseVO getPoliceCaseBYpageAndSearch(page_eachPoliceCaseVO listEachPoliceCaseVO);
}
