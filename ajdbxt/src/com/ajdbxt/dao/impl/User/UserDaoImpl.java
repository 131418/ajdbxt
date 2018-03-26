package com.ajdbxt.dao.impl.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ajdbxt.dao.User.UserDao;
import com.ajdbxt.domain.DO.police;

public class UserDaoImpl implements UserDao {

	@Override
	public String findPolice(String username, String password) {
		String hql = "from police where username = '" + username + "' and password = '" + password + "'";
		Query query = getSession().createQuery(hql);
		police p = (police)query.uniqueResult();
		if (p == null) {
			return "2"; // 用户名不存在
		} else {
			return "1"; // 用户名存在
		}
	}

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
