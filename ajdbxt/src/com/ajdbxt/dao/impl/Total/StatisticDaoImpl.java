package com.ajdbxt.dao.impl.Total;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ajdbxt.dao.Total.StatisticDao;
import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DTO.Total.StatisticCaseByPoliceDTO;
import com.ajdbxt.domain.VO.Total.page_eachPoliceCaseVO;
import com.ajdbxt.domain.VO.Total.page_listPoliceCaseNumByPageAndSearchVO;

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

	
	/*
	 * 得到所有案件数量
	 * 警员的案件数量只跟时间有关
	 * 
	 */
	@Override
	public int getAllCaseNumByPolice(page_listPoliceCaseNumByPageAndSearchVO listPoliceCaseByPageAndSearchVO,String police_id,String category) {
		Session session=getSession();
		String start_time = "";
		String stop_time = "";
		Long i;
		String hql="select count(*) from ajdbxt_info where info_main_police='"+police_id+"'or info_assistant_police_one='"+police_id+"'"
				+ "or info_assistant_police_two='"+police_id+"' and info_category='"+category+"'" ;
		if(listPoliceCaseByPageAndSearchVO.getStart_time()!=null && listPoliceCaseByPageAndSearchVO.getStart_time().length()>0) {
			start_time=listPoliceCaseByPageAndSearchVO.getStart_time();
		}
		if(listPoliceCaseByPageAndSearchVO.getStop_time()!=null && listPoliceCaseByPageAndSearchVO.getStop_time().length()>0) {
			stop_time=listPoliceCaseByPageAndSearchVO.getStop_time();
		}
		hql+="and info_gmt_ceate >='"+start_time+"' and info_gmt_ceate <='"+stop_time+"'";
		Query query=session.createQuery(hql);
		System.out.println("统计案件数量"+hql);
		i=(Long) query.uniqueResult();
		return i.intValue();
	}

		//得到对应的民警
		@Override
		public List<ajdbxt_police> getPolice(page_listPoliceCaseNumByPageAndSearchVO listPoliceCaseByPageAndSearchVO) {
			System.out.println("dao"+listPoliceCaseByPageAndSearchVO.getSearchPolice());
			System.out.println(listPoliceCaseByPageAndSearchVO.getDepartment());
			Session session=getSession();
			String hql="from ajdbxt_police where 1=1 ";
			if(listPoliceCaseByPageAndSearchVO.getDepartment() !=null && listPoliceCaseByPageAndSearchVO.getDepartment().length()>0) {
				//实为id
				hql+="and police_department='"+listPoliceCaseByPageAndSearchVO.getDepartment() +"'";
			}
			if(listPoliceCaseByPageAndSearchVO.getSearchPolice() !=null && listPoliceCaseByPageAndSearchVO.getSearchPolice().trim().length()>0) {
				String serachPolice="%"+listPoliceCaseByPageAndSearchVO.getSearchPolice().trim()+"%";
				hql+="and police_name like'"+serachPolice+"'";
				
			}
			System.out.println(hql);
			Query query=session.createQuery(hql);
			List<ajdbxt_police> list=query.list();
			for(ajdbxt_police police:list) {
				if(listPoliceCaseByPageAndSearchVO.getSearchPolice() !=null && listPoliceCaseByPageAndSearchVO.getSearchPolice().trim().length()>0) {
					police.setPolice_name(police.getPolice_name().replaceAll(listPoliceCaseByPageAndSearchVO.getSearchPolice().trim(), 
						"<span style='color: #ff5063;'>" + listPoliceCaseByPageAndSearchVO.getSearchPolice().trim() + "</span>"));
				}
			}
			System.out.println("toString"+list.size());
			session.clear();
			return list;
		}
		
	/*
	 * 民警的案件列表
	*/
	@Override
	public List<StatisticCaseByPoliceDTO> getStatisticCaseList(page_eachPoliceCaseVO eachPoliceCaseVO) {
		Session session=getSession();
		List<StatisticCaseByPoliceDTO> listPoliceCase=new ArrayList<StatisticCaseByPoliceDTO>();
		String start_time = "";
		String stop_time = "";
		String hql="SELECT info.ajdbxt_info_id,info.info_name,info.info_category,mainP.police_name,"
				+ "fP1.police_name,fp2.police_name,pro.process_score,dep.department_name" + 
				"FROM ajdbxt_info info,ajdbxt_police mainP,ajdbxt_police fP1,ajdbxt_police fp2,ajdbxt_process pro,ajdbxt_department dep" + 
				"WHERE info.info_main_police = mainP.ajdbxt_police_id" + 
				"AND info.info_assistant_police_one = fP1.ajdbxt_police_id" + 
				"AND info.info_assistant_police_two = fp2.ajdbxt_police_id" + 
				"AND pro.process_case_id = info.ajdbxt_info_id" + 
				"AND mainP.police_department = dep.ajdbxt_department_id" + 
				"AND fP1.police_department = dep.ajdbxt_department_id" + 
				"AND fp2.police_department = dep.ajdbxt_department_id" + 
				"AND (mainP.ajdbxt_police_id = '"+eachPoliceCaseVO.getPolice_id()+"'" + 
				"OR fP1.ajdbxt_police_id = '"+eachPoliceCaseVO.getPolice_id()+"'" + 
				"OR fp2.ajdbxt_police_id = '"+eachPoliceCaseVO.getPolice_id()+"')";
		
		if(eachPoliceCaseVO.getCategory() !=null && eachPoliceCaseVO.getCategory().trim().length()>0) {
			hql+=" and info.info_category='"+eachPoliceCaseVO.getCategory().trim()+"'";	
		}
		if(eachPoliceCaseVO.getQueryCaseName() !=null && eachPoliceCaseVO.getQueryCaseName().trim().length()>0) {
			String info_name ="%" + eachPoliceCaseVO.getQueryCaseName().trim()+ "%";
			hql+=" and info.info_name like'"+info_name+"'";
			
		}
		if(eachPoliceCaseVO.getStart_time()!=null && eachPoliceCaseVO.getStart_time().length()>0) {
			start_time=eachPoliceCaseVO.getStart_time();
		}
		if(eachPoliceCaseVO.getStop_time()!=null && eachPoliceCaseVO.getStop_time().length()>0) {
			stop_time=eachPoliceCaseVO.getStop_time();
		}
		hql+=" and info.info_gmt_ceate>='"+start_time+"' and  info.info_gmt_ceate<='"+stop_time+"'"
				+ " order by info.info_gmt_ceate desc limit 1";
		System.out.println(hql);
		Query query=session.createQuery(hql);
		query.setFirstResult((eachPoliceCaseVO.getCurrePage() - 1) * eachPoliceCaseVO.getPageSize());
		query.setMaxResults(eachPoliceCaseVO.getPageSize());
		listPoliceCase=query.list();
		session.clear();
		return listPoliceCase;
	}

	/*
	 * 所有案件
	*/
	@Override
	public int getCaseRecords(page_eachPoliceCaseVO eachPoliceCaseVO) {
		Session session=getSession();
		Long lo;
		String start_time="";
		String stop_time="";
		String hql="SELECT count(*) FROM ajdbxt_info info,ajdbxt_police mainP,ajdbxt_police fP1,ajdbxt_police fp2,"
				+ "ajdbxt_process pro,ajdbxt_department dep" + 
				" WHERE info.info_main_police = mainP.ajdbxt_police_id" + 
				" AND info.info_assistant_police_one = fP1.ajdbxt_police_id" + 
				" AND info.info_assistant_police_two = fp2.ajdbxt_police_id" + 
				" AND pro.process_case_id = info.ajdbxt_info_id" + 
				" AND mainP.police_department = dep.ajdbxt_department_id" + 
				" AND fP1.police_department = dep.ajdbxt_department_id" + 
				" AND fp2.police_department = dep.ajdbxt_department_id" + 
				" AND (mainP.ajdbxt_police_id = '"+eachPoliceCaseVO.getPolice_id()+"'" + 
				" OR fP1.ajdbxt_police_id = '"+eachPoliceCaseVO.getPolice_id()+"'" + 
				" OR fp2.ajdbxt_police_id = '"+eachPoliceCaseVO.getPolice_id()+"')";
		
		if(eachPoliceCaseVO.getCategory() !=null && eachPoliceCaseVO.getCategory().trim().length()>0) {
			hql+=" and info.info_category='"+eachPoliceCaseVO.getCategory().trim()+"'";	
		}
		if(eachPoliceCaseVO.getQueryCaseName() !=null && eachPoliceCaseVO.getQueryCaseName().trim().length()>0) {
			String info_name ="%" + eachPoliceCaseVO.getQueryCaseName().trim()+ "%";
			hql+="and info.info_name like'"+info_name+"'";
			
		}
		if(eachPoliceCaseVO.getStart_time()!=null && eachPoliceCaseVO.getStart_time().length()>0) {
			start_time=eachPoliceCaseVO.getStart_time();
		}
		if(eachPoliceCaseVO.getStop_time()!=null && eachPoliceCaseVO.getStop_time().length()>0) {
			stop_time=eachPoliceCaseVO.getStop_time();
		}
		hql+="and info.info_gmt_ceate>='"+start_time+"' and  info.info_gmt_ceate<='"+stop_time+"'"
				+ "order by info.info_gmt_ceate desc limit 1";
		System.out.println(hql);
		Query query=session.createQuery(hql);
		lo=(long) query.uniqueResult();
		session.clear();
		return lo.intValue();
	}

	//得到对应的办案部门
	@Override
	public List<ajdbxt_department> getDepartment(String police_department_id) {
		Session session =getSession();
		String hql="from ajdbxt_department where ajdbxt_department_id='"+police_department_id+"'";
		Query query=session.createQuery(hql);
		List<ajdbxt_department> listDepartment=query.list();
		System.out.println(hql);
		session.clear();
		return listDepartment;
	}

}
