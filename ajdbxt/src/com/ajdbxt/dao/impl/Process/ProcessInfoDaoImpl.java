package com.ajdbxt.dao.impl.Process;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.ajdbxt.dao.Process.ProcessInfoDao;
import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_info;

public class ProcessInfoDaoImpl implements ProcessInfoDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public List<ajdbxt_info> getInfoListCaseEnd(String police_id,int start,int length) {
		Session session=sessionFactory.getCurrentSession();
		String hql=" from ajdbxt_info "
				+ "where info_main_police=? or info_assistant_police_one=?  or info_assistant_police_two=? and ajdbxt_info_id in (select process_case_id from ajdbxt_process where process_case_end='否')";
		Query query=session.createQuery(hql);
		for(int index=0;index<3;index++) {
			query.setString(index, police_id);
		}
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}
	@Override
	public List<ajdbxt_info> getInfoListCaptainCheck(String police_id,int start,int length) {
		Session session=sessionFactory.getCurrentSession();
		String hql=" from ajdbxt_info "
				+ "where info_main_police=? or info_assistant_police_one=?  or info_assistant_police_two=? and ajdbxt_info_id in (select process_case_id from ajdbxt_process where process_captain_check='否')";
		Query query=session.createQuery(hql);
		for(int index=0;index<3;index++) {
			query.setString(index, police_id);
		}
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
		
	}


	@Override
	public List<ajdbxt_info> getInfoList(String police_id,int start,int length) {
		Session session=sessionFactory.getCurrentSession();
		String hql=" from ajdbxt_info "
				+ "where info_main_police=? or info_assistant_police_one=?  or info_assistant_police_two=?";
		Query query=session.createQuery(hql);
		for(int index=0;index<3;index++) {
			query.setString(index, police_id);
		}
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}



	@Override
	public List<ajdbxt_info> getInfoListProcessScore(String police_id,int start,int length) {
		Session session=sessionFactory.getCurrentSession();
		String hql=" from ajdbxt_info "
				+ "where info_main_police=? or info_assistant_police_one=?  or info_assistant_police_two=? and ajdbxt_info_id in (select process_case_id from ajdbxt_process where process_score='否')";
		Query query=session.createQuery(hql);
		for(int index=0;index<3;index++) {
			query.setString(index, police_id);
		}
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}


	@Override
	public List<ajdbxt_info> getInfoListProcessQuestion(String police_id,int start,int length) {
		Session session=sessionFactory.getCurrentSession();
		String hql=" from ajdbxt_info "
				+ "where info_main_police=? or info_assistant_police_one=?  or info_assistant_police_two=? and ajdbxt_info_id in (select process_case_id from ajdbxt_process where process_question='否')";
		Query query=session.createQuery(hql);
		for(int index=0;index<3;index++) {
			query.setString(index, police_id);
		}
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.list();
	}

	@Override
	public ajdbxt_info findInfoById(String info_id) {
		Session session=sessionFactory.getCurrentSession();
		Criteria cri=session.createCriteria(ajdbxt_info.class);
		cri.add(Restrictions.eq("ajdbxt_info_id", info_id));
		return (ajdbxt_info) cri.uniqueResult();
	}

	@Override
	public int countInfoListCaseEnd(String police_id) {
		Session session=sessionFactory.getCurrentSession();
		String hql=" from ajdbxt_info "
				+ "where info_main_police=? or info_assistant_police_one=?  or info_assistant_police_two=? and ajdbxt_info_id in (select process_case_id from ajdbxt_process where process_case_end='否')";
		Query query=session.createQuery(hql);
		for(int index=0;index<3;index++) {
			query.setString(index, police_id);
		}
		return query.list().size();
	}


	@Override
	public int countInfoListCaptainCheck(String police_id) {
		Session session=sessionFactory.getCurrentSession();
		String hql=" from ajdbxt_info "
				+ "where info_main_police=? or info_assistant_police_one=?  or info_assistant_police_two=? and ajdbxt_info_id in (select process_case_id from ajdbxt_process where process_captain_check='否')";
		Query query=session.createQuery(hql);
		for(int index=0;index<3;index++) {
			query.setString(index, police_id);
		}
		return query.list().size();
	}


	@Override
	public int countInfoListProcessScore(String police_id) {
		Session session=sessionFactory.getCurrentSession();
		String hql=" from ajdbxt_info "
				+ "where info_main_police=? or info_assistant_police_one=?  or info_assistant_police_two=? and ajdbxt_info_id in (select process_case_id from ajdbxt_process where process_score='否')";
		Query query=session.createQuery(hql);
		for(int index=0;index<3;index++) {
			query.setString(index, police_id);
		}
		return query.list().size();
	}


	@Override
	public int countInfoListProcessQuestion(String police_id) {
		Session session=sessionFactory.getCurrentSession();
		String hql=" from ajdbxt_info "
				+ "where info_main_police=? or info_assistant_police_one=?  or info_assistant_police_two=? and ajdbxt_info_id in (select process_case_id from ajdbxt_process where process_question='否')";
		Query query=session.createQuery(hql);
		for(int index=0;index<3;index++) {
			query.setString(index, police_id);
		}
		return query.list().size();
	}


	@Override
	public int countInfoList(String police_id) {
		Session session=sessionFactory.getCurrentSession();
		String hql=" from ajdbxt_info "
				+ "where info_main_police=? or info_assistant_police_one=?  or info_assistant_police_two=?";
		Query query=session.createQuery(hql);
		for(int index=0;index<3;index++) {
			query.setString(index, police_id);
		}
		return query.list().size();
	}

}
