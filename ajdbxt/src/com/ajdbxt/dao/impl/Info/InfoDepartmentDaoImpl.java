package com.ajdbxt.dao.impl.Info;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.ajdbxt.dao.Info.InfoDepartmentDao;
import com.ajdbxt.domain.DO.ajdbxt_department;

public class InfoDepartmentDaoImpl implements InfoDepartmentDao {
	private SessionFactory sessionFactory ;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<ajdbxt_department> findAllDepartment() {
		Session session=sessionFactory.getCurrentSession();
		Criteria cri=session.createCriteria(ajdbxt_department.class);
		return cri.list();
	}

	@Override
	public ajdbxt_department findDepartmentById(String department_id) {
		Session session=sessionFactory.getCurrentSession();
		Criteria cri=session.createCriteria(ajdbxt_department.class);
		cri.add(Restrictions.eq("ajdbxt_department_id", department_id));
		return (ajdbxt_department) cri.uniqueResult();
	}

}
