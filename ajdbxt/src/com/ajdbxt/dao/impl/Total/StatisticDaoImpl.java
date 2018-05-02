package com.ajdbxt.dao.impl.Total;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ajdbxt.dao.Total.StatisticDao;
import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.VO.Total.DepartmentStatisticVo;
import com.ajdbxt.domain.VO.Total.PoliceCaseStatisticVo;

public class StatisticDaoImpl implements StatisticDao {
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//得到不同类型的案件数
	@Override
	public int findcase(DepartmentStatisticVo departmentStatisticVo,String category,String departmentId) {
		Session session=getSession();
		String start_time = "";
		String stop_time = "";
		Long i;
		String hql="select count(*) from ajdbxt_info where info_department='"+departmentId+"' and info_category='"+category+"' ";
		if(departmentStatisticVo.getStart_time()!=null && departmentStatisticVo.getStart_time().length()>0) {
			start_time=departmentStatisticVo.getStart_time();
		}
		if(departmentStatisticVo.getStop_time()!=null && departmentStatisticVo.getStop_time().length()>0) {
			stop_time=departmentStatisticVo.getStop_time();
		}
			hql+=" and info_gmt_ceate between str_to_date('"+start_time+"', '%Y-%m-%d') and str_to_date('"+stop_time+"', '%Y-%m-%d')";
		Query query=session.createQuery(hql);
		System.out.println("统计案件数"+hql);
		i=(Long) query.uniqueResult();
		session.clear();
		System.out.println(i);
		return i.intValue();
	}


	@Override
	public List<ajdbxt_department> findAllDepartment() {
		Session session=getSession();
		List<ajdbxt_department> departmentList=new ArrayList<ajdbxt_department>();
		String hql="from ajdbxt_department where 1=1";
		Query query=session.createQuery(hql);
		departmentList=query.list();
		session.clear();
		return departmentList;
	}
	
	/*统计部门分数*/
	@Override
	public double findTotalScoreByDepartment(DepartmentStatisticVo departmentStatisticVo,String departmentId) {
		Session session=getSession();
		String start_time = "";
		String stop_time = "";
		double totalScore=0.0;
		String hql="select IFNULL(SUM(pro.process_score), 0) from ajdbxt_process pro,ajdbxt_info info where pro.process_case_id=info.ajdbxt_info_id "
				+ "and info.info_department='"+departmentId+"'";
		if(departmentStatisticVo.getStart_time()!=null && departmentStatisticVo.getStart_time().length()>0) {
			start_time=departmentStatisticVo.getStart_time();
		}
		if(departmentStatisticVo.getStop_time()!=null && departmentStatisticVo.getStop_time().length()>0) {
			stop_time=departmentStatisticVo.getStop_time();
		}
		hql+=" and info.info_gmt_ceate between str_to_date('"+start_time+"', '%Y-%m-%d') and str_to_date('"+stop_time+"', '%Y-%m-%d')";
		Query query=session.createSQLQuery(hql);
		totalScore=(double) query.uniqueResult();
		System.out.println("总分"+totalScore);
		session.clear();
		return totalScore;
	}
	/*
	 * param:部门
	 * 所有警员*/
	@Override
	public List<ajdbxt_police> findAllPolice(PoliceCaseStatisticVo policeCaseStatisticVo) {
		Session session=getSession();
		List<ajdbxt_police> lisePolice=new ArrayList<ajdbxt_police>();
		System.out.println(policeCaseStatisticVo.getDepartment()+"----------------");
		String hql="from ajdbxt_police where 1=1";
		if(policeCaseStatisticVo.getDepartment()!=null && policeCaseStatisticVo.getDepartment().length()>0) {
			hql+="and police_department ='"+policeCaseStatisticVo.getDepartment()+"'";
		}
		Query query=session.createQuery(hql);
		System.out.println(hql);
		lisePolice=query.list();
		session.clear();
		return lisePolice;
	}
	
	
	/*统计主办案件数*/
	@Override
	public int findPoliceMainCaseNum(PoliceCaseStatisticVo policeCaseStatisticVo, String policeId, String category) {
		Session session=getSession();
		String start_time = "";
		String stop_time = "";
		Long i;
		String hql="select count(*) from ajdbxt_info where info_category='"+category+"' and info_main_police='"+policeId+"'";
		if(policeCaseStatisticVo.getStart_time()!=null && policeCaseStatisticVo.getStart_time().length()>0) {
			start_time=policeCaseStatisticVo.getStart_time();
		}
		if(policeCaseStatisticVo.getStop_time()!=null && policeCaseStatisticVo.getStop_time().length()>0) {
			stop_time=policeCaseStatisticVo.getStop_time();
		}
			hql+=" and info_gmt_ceate between str_to_date('"+start_time+"', '%Y-%m-%d') and str_to_date('"+stop_time+"', '%Y-%m-%d')";
		Query query=session.createQuery(hql);
		i=(Long) query.uniqueResult();
		session.clear();
		return i.intValue();
	}
	
	/*得到警员所有主办案件的总分
	 * 
	 */
	@Override
	public double findTotalScoreByPolice(PoliceCaseStatisticVo policeCaseStatisticVo,String policeId) {
		Session session=getSession();
		String start_time = "";
		String stop_time = "";
		double sumScore;
		String hql="select IFNULL(SUM(pro.process_score), 0) from ajdbxt_process pro,ajdbxt_info info where pro.process_case_id = info.ajdbxt_info_id "
				+ "AND  info.info_main_police = '"+policeId+"'";
		if(policeCaseStatisticVo.getStart_time() !=null && policeCaseStatisticVo.getStart_time().length()>0) {
			start_time=policeCaseStatisticVo.getStart_time();
		}
		if(policeCaseStatisticVo.getStop_time()!=null && policeCaseStatisticVo.getStop_time().length()>0) {
			stop_time=policeCaseStatisticVo.getStop_time();
		}
		hql+=" and info.info_gmt_ceate between str_to_date('"+start_time+"', '%Y-%m-%d') and str_to_date('"+stop_time+"', '%Y-%m-%d')";
		Query query=session.createSQLQuery(hql);
		sumScore=(double) query.uniqueResult();
		return sumScore;
	}
	
	/*统计协办案件*/
	@Override
	public int findPoliceAsistCaseNum(PoliceCaseStatisticVo policeCaseStatisticVo, String policeId, String category) {
		Session session=getSession();
		String start_time = "";
		String stop_time = "";
		Long i;
		String hql="select count(*) from ajdbxt_info where info_category='"+category+"' and "
				+ "(info_assistant_police_two = '"+policeId+"'" + "OR info_assistant_police_one='"+policeId+"')";
		if(policeCaseStatisticVo.getStart_time()!=null && policeCaseStatisticVo.getStart_time().length()>0) {
			start_time=policeCaseStatisticVo.getStart_time();
		}
		if(policeCaseStatisticVo.getStop_time()!=null && policeCaseStatisticVo.getStop_time().length()>0) {
			stop_time=policeCaseStatisticVo.getStop_time();
		}
			hql+=" and info_gmt_ceate between str_to_date('"+start_time+"', '%Y-%m-%d') and str_to_date('"+stop_time+"', '%Y-%m-%d')";
		Query query=session.createQuery(hql);
		i=(Long) query.uniqueResult();
		session.clear();
		return i.intValue();
	}

	@Override
	public ajdbxt_department findPoliceDepartment(String departmentId) {
		Session session=getSession();
		ajdbxt_department department=new ajdbxt_department();
		String hql="from ajdbxt_department where ajdbxt_department_id='"+departmentId+"'";
		Query query=session.createQuery(hql);
		department=(ajdbxt_department)query.uniqueResult();
		session.clear();
		return department;
	}
	

}
