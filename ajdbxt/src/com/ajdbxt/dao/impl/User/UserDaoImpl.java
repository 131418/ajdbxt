package com.ajdbxt.dao.impl.User;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ajdbxt.dao.User.UserDao;
import com.ajdbxt.domain.DO.Ajdbxt_police;
import com.ajdbxt.domain.DO.police;
import com.ajdbxt.domain.VO.User.findPoliceByPageVO;

public class UserDaoImpl implements UserDao {

	@Override
	public Ajdbxt_police findPolice(String username, String password) {
		String hql = "from police where username = '" + username + "' and password = '" + password + "'";
		Query query = getSession().createQuery(hql);
		police p = (police) query.uniqueResult();
		return null;
	}

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public boolean addPolice(Ajdbxt_police ajdbxt_police) {
		// TODO Auto-generated method stub
		try {
			getSession().save(ajdbxt_police);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deletePolice(Ajdbxt_police ajdbxtPolice) {
		// TODO Auto-generated method stub

		try {
			getSession().delete(ajdbxtPolice);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Ajdbxt_police findPoliceByPoliceSerialNumber(String policeSerialNumber) {
		// TODO Auto-generated method stub
		String hql = "from Ajdbxt_police where policeSerialNumber = '" + policeSerialNumber + "'";
		return (Ajdbxt_police) getSession().createQuery(hql).uniqueResult();

	}

	@Override
	public boolean updatePolice(Ajdbxt_police ajdbxt_police) {
		// TODO Auto-generated method stub
		try {
			getSession().update(ajdbxt_police);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Ajdbxt_police> findPoliceByPoliceDepartment(String policeDepartment) {
		// TODO Auto-generated method stub
		String hql = "from Ajdbxt_police where policeDepartment  = '" + policeDepartment + "'";
		Query query = getSession().createQuery(hql);
		List<Ajdbxt_police> policeofdepartment = query.list();
		return policeofdepartment;
	}

/*	@SuppressWarnings("unchecked")
	@Override
	public List<Ajdbxt_police> findAllPolice() {
		String hql = "from  Ajdbxt_police";
		List<Ajdbxt_police> findallpolice = getSession().createQuery(hql).list();
		return findallpolice;
	}
*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Ajdbxt_police> blurSearch(Ajdbxt_police ajdbxt_police) {
		// TODO Auto-generated method stub
		String hql = "from Ajdbxt_police where policeSerialNumber = '"+ajdbxt_police+"' or policeName like '%"+ajdbxt_police+"%'";//
		List<Ajdbxt_police> blursearch = getSession().createQuery(hql).list();
		return blursearch;
	}

	@Override
	public List<Ajdbxt_police> findPoliceByPage(findPoliceByPageVO findPoliceByPage) {
		// TODO Auto-generated method stub
		String hql = "from Ajdbxt_police";
		return null;
	}

	/*
	 * @Override public void listPolice() { // TODO Auto-generated method stub
	 * Criteria criteria = getSession().createCriteria(Ajdbxt_police.class);
	 * List<Ajdbxt_police> list = criteria.list(); for(Ajdbxt_police
	 * ajdbxt_police:list) {
	 * 
	 * } }
	 */
}