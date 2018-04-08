package com.ajdbxt.dao.impl.Process;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import com.ajdbxt.dao.Process.ProcessDepartmentDao;
import com.ajdbxt.domain.DO.ajdbxt_department;

public class ProcessDepartmentDaoImpl implements ProcessDepartmentDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ajdbxt_department findDepartmentById(String department_id) {
		Session session=sessionFactory.getCurrentSession();
		Criteria cri=session.createCriteria(ajdbxt_department.class);
		cri.add(Restrictions.eq("ajdbxt_department_id", department_id));
		return (ajdbxt_department) cri.uniqueResult();
	}

}
