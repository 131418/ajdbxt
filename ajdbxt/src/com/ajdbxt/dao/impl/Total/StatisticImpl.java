package com.ajdbxt.dao.impl.Total;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ajdbxt.dao.Total.Statistic;
import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DO.ajdbxt_process;
import com.ajdbxt.domain.DTO.Total.StatisticCaseByPoliceDTO;
import com.ajdbxt.domain.VO.Total.page_eachPoliceCaseVO;
import com.ajdbxt.domain.VO.Total.DepartmentStatisticVo;

public class StatisticImpl implements Statistic {
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
	public int getAllCaseNumByPolice(DepartmentStatisticVo listPoliceCaseByPageAndSearchVO,String police_id,String category) {
		Session session=getSession();
		String start_time = "";
		String stop_time = "";
		Long i;
		String hql="select count(*) from ajdbxt_info where (info_main_police='"+police_id+"'or info_assistant_police_one='"+police_id+"'"
				+ "or info_assistant_police_two='"+police_id+"') and info_category='"+category+"'" ;
		if(listPoliceCaseByPageAndSearchVO.getStart_time()!=null && listPoliceCaseByPageAndSearchVO.getStart_time().length()>0) {
			start_time=listPoliceCaseByPageAndSearchVO.getStart_time();
		}
		if(listPoliceCaseByPageAndSearchVO.getStop_time()!=null && listPoliceCaseByPageAndSearchVO.getStop_time().length()>0) {
			stop_time=listPoliceCaseByPageAndSearchVO.getStop_time();
		}
			hql+=" and info_gmt_ceate between str_to_date('"+start_time+"', '%Y-%m-%d') and str_to_date('"+stop_time+"', '%Y-%m-%d')";
		Query query=session.createQuery(hql);
		System.out.println("统计案件数量"+hql);
		i=(Long) query.uniqueResult();
		return i.intValue();
	}
		//得到对应的民警
		@Override
		public List<ajdbxt_police> getPolice(DepartmentStatisticVo listPoliceCaseByPageAndSearchVO) {
			Session session=getSession();
			String hql="from ajdbxt_police where 1=1 ";
			Query query=session.createQuery(hql);
			List<ajdbxt_police> list=query.list();
			/*for(ajdbxt_police police:list) {
				if(listPoliceCaseByPageAndSearchVO.getSearchPolice() !=null && listPoliceCaseByPageAndSearchVO.getSearchPolice().trim().length()>0) {
					police.setPolice_name(police.getPolice_name().replaceAll(listPoliceCaseByPageAndSearchVO.getSearchPolice().trim(), 
						"<span style='color: #ff5063;'>" + listPoliceCaseByPageAndSearchVO.getSearchPolice().trim() + "</span>"));
				}
			}*/
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
				+ "fP1.police_name AS assistPoliceOne,fp2.police_name AS assistPoliceTwo,pro.process_score,dep.department_name" + 
				" FROM ajdbxt_info info LEFT JOIN ajdbxt_police mainP ON info.info_main_police=mainP.ajdbxt_police_id " + 
				" LEFT JOIN ajdbxt_police fP1 ON info.info_assistant_police_one=fP1.ajdbxt_police_id" + 
				" LEFT JOIN ajdbxt_police fP2 ON info.info_assistant_police_two=fP2.ajdbxt_police_id" + 
				" LEFT JOIN ajdbxt_process pro ON pro.process_case_id=info.ajdbxt_info_id" + 
				" LEFT JOIN ajdbxt_department dep ON (mainP.police_department=dep.ajdbxt_department_id" + 
				" OR fP1.police_department=dep.ajdbxt_department_id" + 
				" OR fp2.police_department=dep.ajdbxt_department_id)" + 
				" WHERE (mainP.ajdbxt_police_id = '"+eachPoliceCaseVO.getPolice_id()+"'" + 
				" OR fP1.ajdbxt_police_id = '"+eachPoliceCaseVO.getPolice_id()+"'" + 
				" OR fp2.ajdbxt_police_id = '"+eachPoliceCaseVO.getPolice_id()+"')";
		
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
			hql+=" and info.info_gmt_ceate between str_to_date('"+start_time+"', '%Y-%m-%d') and str_to_date('"+stop_time+"', '%Y-%m-%d')";
		System.out.println(hql);
		Query query=session.createSQLQuery(hql);
		query.setFirstResult((eachPoliceCaseVO.getCurrePage() - 1) * eachPoliceCaseVO.getPageSize());
		query.setMaxResults(eachPoliceCaseVO.getPageSize());
		List<Object> list=query.list();
		/*for(int i=0;i<list.size();i++) {
			StatisticCaseByPoliceDTO statisticCaseByPoliceDTO=new StatisticCaseByPoliceDTO();
			Object[] obj = (Object[])list.get(i);
		    System.out.println("----"+obj.length);
	       //民警1
	 		ajdbxt_police mainPolice=new ajdbxt_police();
	 		//民警2
	 		ajdbxt_police insisPoliceOne=new ajdbxt_police();
	 		//民警3
	 		ajdbxt_police insisPoliceTwo=new ajdbxt_police();
	 		//案件
	 		ajdbxt_info caseInfo=new ajdbxt_info();
	 		//案件流程
	 		ajdbxt_process caseProcess=new  ajdbxt_process();
	 		//办案单位
	 		ajdbxt_department department=new ajdbxt_department();
	 		caseInfo.setAjdbxt_info_id(obj[0].toString());
	 		caseInfo.setInfo_name(obj[1].toString());
	 		caseInfo.setInfo_category(obj[2].toString());
	 		mainPolice.setPolice_name(obj[3].toString());
	 		insisPoliceOne.setPolice_name(obj[4].toString());
	 		if(obj[5]==null) {
	 			insisPoliceTwo.setPolice_name();
	 		}else {
	 			insisPoliceTwo.setPolice_name(obj[5].toString());
	 		}
	 		if(obj[6]==null) {
	 			caseProcess.setProcess_score(" ");
	 		}else {
	 			caseProcess.setProcess_score(obj[6].toString());
	 		}
	 		
	 		department.setDepartment_name(obj[7].toString());
	 		statisticCaseByPoliceDTO.setCaseInfo(caseInfo);
	 		statisticCaseByPoliceDTO.setCaseProcess(caseProcess);
	 		statisticCaseByPoliceDTO.setDepartment(department);
	 		statisticCaseByPoliceDTO.setMainPolice(mainPolice);
	 		statisticCaseByPoliceDTO.setInsisPoliceOne(insisPoliceOne);
	 		statisticCaseByPoliceDTO.setInsisPOliceTwo(insisPoliceTwo);
	 		listPoliceCase.add(statisticCaseByPoliceDTO);
		}
		System.out.println(query.list().size());
		System.out.println("listPoliceCase"+listPoliceCase.size());
		session.clear();*/
		return listPoliceCase;
	}

	/*
	 * 所有案件
	*/
	@Override
	public int getCaseRecords(page_eachPoliceCaseVO eachPoliceCaseVO) {
		Session session=getSession();
		BigInteger  i;
		String start_time="";
		String stop_time="";
		String hql="SELECT count(*) FROM ajdbxt_info info LEFT JOIN ajdbxt_police mainP ON info.info_main_police=mainP.ajdbxt_police_id"+ 
				" LEFT JOIN ajdbxt_police fP1 ON info.info_assistant_police_one=fP1.ajdbxt_police_id" + 
				" LEFT JOIN ajdbxt_police fP2 ON info.info_assistant_police_two=fP2.ajdbxt_police_id" + 
				" LEFT JOIN ajdbxt_process pro ON pro.process_case_id=info.ajdbxt_info_id" + 
				" LEFT JOIN ajdbxt_department dep ON (mainP.police_department=dep.ajdbxt_department_id" + 
				" OR fP1.police_department=dep.ajdbxt_department_id" + 
				" OR fp2.police_department=dep.ajdbxt_department_id)" + 
				" WHERE (mainP.ajdbxt_police_id = '"+eachPoliceCaseVO.getPolice_id()+"'" + 
				" OR fP1.ajdbxt_police_id = '"+eachPoliceCaseVO.getPolice_id()+"'" + 
				" OR fp2.ajdbxt_police_id = '"+eachPoliceCaseVO.getPolice_id()+"')";
		
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
			hql+=" and info.info_gmt_ceate between str_to_date('"+start_time+"', '%Y-%m-%d') and str_to_date('"+stop_time+"', '%Y-%m-%d')";
		System.out.println(hql);
		Query query=session.createSQLQuery(hql);
		i=(BigInteger) query.uniqueResult();
		System.out.println("案件数量"+i);
		session.clear();
		return i.intValue();
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

	@Override
	public ajdbxt_police getPoliceName(String police_id) {
		Session session=getSession();
		String hql=" from ajdbxt_police where ajdbxt_police_id='"+police_id+"'";
		Query query =session.createQuery(hql);
		ajdbxt_police policeName=(ajdbxt_police) query.uniqueResult();
		return policeName;
	}

}
