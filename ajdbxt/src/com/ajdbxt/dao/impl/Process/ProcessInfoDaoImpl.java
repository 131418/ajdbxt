package com.ajdbxt.dao.impl.Process;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.ajdbxt.dao.Process.ProcessInfoDao;
import com.ajdbxt.domain.DO.ajdbxt_info;

public class ProcessInfoDaoImpl implements ProcessInfoDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public List<ajdbxt_info> getInfoListCaseEnd(String police_id) {
		Session session=sessionFactory.getCurrentSession();
		String hql=" from ajdbxt_info "
				+ "where info_main_police=? or info_assistant_police_one=?  or info_assistant_police_two=? or "
				+ "info_department_legal_member=? or info_department_captain=? or info_legal_team_member=?"
				+ "or info_bureau_leader=? and ajdbxt_info_id in (select process_case_id from ajdbxt_process where process_case_end='否')";
		Query query=session.createQuery(hql);
		for(int index=0;index<7;index++) {
			query.setString(index, police_id);
		}
		return query.list();
	}
	@Override
	public List<ajdbxt_info> getInfoListCaptainCheck(String police_id) {
		Session session=sessionFactory.getCurrentSession();
		String hql=" from ajdbxt_info "
				+ "where info_main_police=? or info_assistant_police_one=?  or info_assistant_police_two=? or "
				+ "info_department_legal_member=? or info_department_captain=? or info_legal_team_member=?"
				+ "or info_bureau_leader=? and ajdbxt_info_id in (select process_case_id from ajdbxt_process where process_captain_check='否')";
		Query query=session.createQuery(hql);
		for(int index=0;index<7;index++) {
			query.setString(index, police_id);
		}
		return query.list();
		
	}

}
