package com.ajdbxt.dao.Total;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.VO.Total.DepartmentStatisticVo;
import com.ajdbxt.domain.VO.Total.PoliceCaseStatisticVo;

public interface StatisticDao {
	//通过办案部门统计案件数量
	public int findcase(DepartmentStatisticVo departmentStatisticVo,String category,String departmentId);
	
	//得到所有的办案单位
	public List<ajdbxt_department> findAllDepartment();
	
	//得到办案单位的所有案件的分数
	public double findTotalScoreByDepartment(DepartmentStatisticVo departmentStatisticVo,String departmentId);
	
	//得到警员
	public List<ajdbxt_police> findAllPolice(PoliceCaseStatisticVo policeCaseStatisticVo);
	
	//统计警员主办案件数
	public int findPoliceMainCaseNum(PoliceCaseStatisticVo policeCaseStatisticVo,String policeId,String category);
	
	//统计警员协办阿纳金
	public int findPoliceAsistCaseNum(PoliceCaseStatisticVo policeCaseStatisticVo,String policeId,String category);
	
	//得到警员所有主办的案件案件的分数
	public double findTotalScoreByPolice(PoliceCaseStatisticVo policeCaseStatisticVo,String policeId);
	

}
