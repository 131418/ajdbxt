package com.ajdbxt.service.Total;

import com.ajdbxt.domain.VO.Total.DepartmentStatisticVo;
import com.ajdbxt.domain.VO.Total.PoliceCaseStatisticVo;

public interface StatisticService {
	//得到办案部门案件列表
	public DepartmentStatisticVo statisticDepartmentCase(DepartmentStatisticVo departmentStatisticVo);
	
	//警员
	public PoliceCaseStatisticVo statisticPoliceCase(PoliceCaseStatisticVo policeCaseStatisticVo);

}
