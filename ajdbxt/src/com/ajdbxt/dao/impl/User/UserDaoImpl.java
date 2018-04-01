package com.ajdbxt.dao.impl.User;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ajdbxt.dao.User.UserDao;
import com.ajdbxt.domain.DO.Ajdbxt_police;

public class UserDaoImpl implements UserDao {

	@Override
	public Ajdbxt_police findPolice(String policeSerialNumber) {
		String hql = "from Ajdbxt_police where policeSerialNumber = '"+ policeSerialNumber +"'";
		Query query = getSession().createQuery(hql);
		Ajdbxt_police p = (Ajdbxt_police) query.uniqueResult();
		return p;
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
			getSession().saveOrUpdate(ajdbxt_police);
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

/*	@Override
	public List<Ajdbxt_police> findPoliceByPoliceDepartment(String policeDepartment) {
		// TODO Auto-generated method stub
		String hql = "from Ajdbxt_police where policeDepartment  = '" + policeDepartment + "'";
		Query query = getSession().createQuery(hql);
		List<Ajdbxt_police> policeofdepartment = query.list();
		return policeofdepartment;
	}*/

/*	@SuppressWarnings("unchecked")
	@Override
	public List<Ajdbxt_police> findAllPolice() {
		String hql = "from  Ajdbxt_police";
		List<Ajdbxt_police> findallpolice = getSession().createQuery(hql).list();
		return findallpolice;
	}
*/

	@Override
	public List<Ajdbxt_police> queryForPage(String hql, int offset, int length) {
		// TODO Auto-generated method stub
		Query q = getSession().createQuery(hql);
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}

	@Override
	public int getCount(String hql) {
		// TODO Auto-generated method stub
		Query q = getSession().createQuery(hql);
		return Integer.parseInt(q.list().get(0).toString());
	}

	@Override
	public List<Ajdbxt_police> queryForPageByDepartment(String hql, int offset, int length) {
		// TODO Auto-generated method stub
		Query q = getSession().createQuery(hql);
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}

	@Override
	public String changePassword(String ajdbxtPoliceId,String newPassword) {
		// TODO Auto-generated method stub
		try {
			String hql = "update Ajdbxt_police set policePassword ='" + newPassword + "' where policeSerialNumber = '" + ajdbxtPoliceId + "'";
			getSession().createQuery(hql).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}

/*	@Override
	public List<Ajdbxt_police> findPoliceByPage(findPoliceByPageVO findPoliceByPage) {
		// TODO Auto-generated method stub
		String hql = "from Ajdbxt_police";
		return null;
	}*/

	/*
	 * @Override public void listPolice() { // TODO Auto-generated method stub
	 * Criteria criteria = getSession().createCriteria(Ajdbxt_police.class);
	 * List<Ajdbxt_police> list = criteria.list(); for(Ajdbxt_police
	 * ajdbxt_police:list) {
	 * 
	 * } }
	 */
}
