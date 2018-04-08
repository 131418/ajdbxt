package com.ajdbxt.dao.impl.Total;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ajdbxt.dao.Total.StatisticsDao;
import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DTO.Total.StatisticEachDepartmentCaseNumDTO;

public class StatisticsDaoImpl implements StatisticsDao {
	private StatisticEachDepartmentCaseNumDTO statisticDto;
	
	private SessionFactory sessionFactory;
	
	public StatisticEachDepartmentCaseNumDTO getStatisticDto() {
		return statisticDto;
	}
	public void setStatisticDto(StatisticEachDepartmentCaseNumDTO statisticDto) {
		this.statisticDto = statisticDto;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	} 
	public Session getSession() {

		return this.sessionFactory.getCurrentSession();
	}
	
	
	//通过案件类型获得该办案部门的办案数量
	@Override
	public int getTotalCaseByDepartmentAndCategory(String department,String category,StatisticEachDepartmentCaseNumDTO statisticDto) {
		String start_time = "0000-00-00";//开始时间
		String stop_time = "9999-99-99";//结束时间
		Session session = getSession();
		String hql="select count(*) from ajdbxt_info where ajdbxt_info.info_department='"+department+"'"
				+ "and ajdbxt_info.info_category='"+category+"'";
		if(statisticDto.getStart_time() !=null && statisticDto.getStart_time().trim().length()>0) {
			start_time=statisticDto.getStart_time();
		}
		if(statisticDto.getStop_time() !=null && statisticDto.getStop_time().trim().length()>0) {
			stop_time=statisticDto.getStop_time();
		}
		hql+="and info_gmt_ceate>='"+start_time+"'and info_gmt_ceat <='"+stop_time+"'";
		System.out.println(hql);
		Query query=session.createQuery(hql);
		int num=(Integer) query.uniqueResult();	
		session.clear();
		return num;
	}
	
	//获得所有办案部门
	@Override
	public List<ajdbxt_department> getAllDepartment() {
		Session session=getSession();
		List<ajdbxt_department> departmentList=new ArrayList<ajdbxt_department>();
		String hql="select department_name from ajdbxt_department";
		Query query=session.createQuery(hql);
		departmentList=query.list();
		session.clear();
		System.out.println(departmentList.toString());
		return departmentList;
	}
	

	@Override
	public List<StatisticEachDepartmentCaseNumDTO> CaseNumByDepartmentAndCategory(StatisticEachDepartmentCaseNumDTO statisticEachDepartmentCaseNumDTO) {
		Session session=getSession();
		List<StatisticEachDepartmentCaseNumDTO> list=new ArrayList<StatisticEachDepartmentCaseNumDTO>();
		StatisticEachDepartmentCaseNumDTO statistic=new StatisticEachDepartmentCaseNumDTO();
		List<ajdbxt_department> departmentList=getAllDepartment();
		for(int i=0;i<departmentList.size();i++) {
			statisticEachDepartmentCaseNumDTO.setDepartment(departmentList.get(i).toString());
			statisticEachDepartmentCaseNumDTO.setAdminnistrCaseNum(getTotalCaseByDepartmentAndCategory((departmentList.get(i).toString()),"行政案件",statistic));
			statisticEachDepartmentCaseNumDTO.setCriminalCaseNum(getTotalCaseByDepartmentAndCategory((departmentList.get(i).toString()),"刑事案件",statistic));
			list.add(statistic);
		}
		session.clear();
		return list;
	}
	
	//通过办案单位id获取办案单位
	@Override
	public ajdbxt_department getDepartmentById(String department_id) {
		Session session=getSession();
		String hql="select department_name from ajdbxt_department where ajdbxt_department_id='"+department_id+"'";
		Query query =session.createQuery(hql);
		ajdbxt_department department=(ajdbxt_department) query.uniqueResult();
		session.clear();
		return department;
	}

	

}
