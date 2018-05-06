package com.ajdbxt.dao.Total;

import java.util.List;
import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DTO.Total.StatisticCaseByPoliceDTO;
import com.ajdbxt.domain.VO.Total.page_eachPoliceCaseVO;
import com.ajdbxt.domain.VO.Total.DepartmentStatisticVo;

public interface Statistic {
	//得到所有的民警
	public List<ajdbxt_police> getPolice(DepartmentStatisticVo listPoliceCaseByPageAndSearchVO);
	
	//得到民警的不同案件类型的案件数量
	public int getAllCaseNumByPolice(DepartmentStatisticVo listPoliceCaseByPageAndSearchVO ,String police_id,String category);
	
	//得到某警员的所有案件
	public List<StatisticCaseByPoliceDTO> getStatisticCaseList(page_eachPoliceCaseVO eachPoliceCaseVO);
	
	//得到某警员的所有案件数
	public int getCaseRecords(page_eachPoliceCaseVO eachPoliceCaseVO);
	
	//得到对应民警的部门
	public List<ajdbxt_department> getDepartment(String police_department_id);
	
	//得到点击的民警名
	public ajdbxt_police getPoliceName(String police_id);

}
