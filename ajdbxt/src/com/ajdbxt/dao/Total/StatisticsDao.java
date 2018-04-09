package com.ajdbxt.dao.Total;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DTO.Total.StatisticEachDepartmentCaseNumDTO;
import com.ajdbxt.domain.VO.Total.FindPoliceCaseNumByPageVo;

public interface StatisticsDao {
	List<ajdbxt_department> getAllDepartment();
	
	List<StatisticEachDepartmentCaseNumDTO> CaseNumByDepartmentAndCategory();//办案单位分案件类别类别列表
	
	int getTotalCaseByDepartmentAndCategory(String department,String category,StatisticEachDepartmentCaseNumDTO statistic);
	
	ajdbxt_department getDepartmentById(String department_id);
	
	/*List<ajdbxt_police> getPoliceByDepartment();
	
	List<FindPoliceCaseNumByPageVo> CaseNumByPoliceAndCategory();//警员分类别列表
*/

}
