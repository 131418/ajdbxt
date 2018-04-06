package com.ajdbxt.dao.impl.Info;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ajdbxt.dao.Info.InfoPoliceDao;
import com.ajdbxt.domain.DO.ajdbxt_police;

public class InfoPoliceDaoImpl implements InfoPoliceDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<ajdbxt_police> findPoliceByDepartment(String department) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from ajdbxt_police where police_department=?");
		query.setString(0, department);
		return query.list();
	}
}
