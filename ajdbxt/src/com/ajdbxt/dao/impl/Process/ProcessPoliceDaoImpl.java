package com.ajdbxt.dao.impl.Process;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.ajdbxt.dao.Process.ProcessPoliceDao;
import com.ajdbxt.domain.DO.ajdbxt_police;

public class ProcessPoliceDaoImpl implements ProcessPoliceDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ajdbxt_police findPoliceById(String police_id) {
		Session session=sessionFactory.getCurrentSession();
		Criteria cri=session.createCriteria(ajdbxt_police.class);
		cri.add(Restrictions.eq("ajdbxt_police_id", police_id));
		return (ajdbxt_police) cri.uniqueResult();
	}

}
