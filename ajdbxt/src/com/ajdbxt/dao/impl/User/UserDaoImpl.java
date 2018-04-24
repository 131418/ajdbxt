package com.ajdbxt.dao.impl.User;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ajdbxt.dao.User.UserDao;
import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.VO.User.policedptVO;

public class UserDaoImpl implements UserDao {

	@Override
	public ajdbxt_police findPolice(String police_serial_number) {
		String hql = "from ajdbxt_police where police_serial_number = '" + police_serial_number + "'";
		Query query = getSession().createQuery(hql);
		ajdbxt_police p = (ajdbxt_police) query.uniqueResult();
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
	public boolean addPolice(ajdbxt_police ajdbxt_police) {
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
	public ajdbxt_police findPoliceByPoliceSerialNumber(String police_serial_number) {
		// TODO Auto-generated method stub
		String hql = "from ajdbxt_police where police_serial_number = '" + police_serial_number + "'";
		return (ajdbxt_police) getSession().createQuery(hql).uniqueResult();

	}

	@Override
	public boolean updatePolice(ajdbxt_police ajdbxt_police) {
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
	public List<policedptVO> queryForPage(String hql, int offset, int length) {
		System.out.println(hql);
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
	public List<policedptVO> queryForPageByDepartment(String hql, int offset, int length) {
		// TODO Auto-generated method stub
		Query q = getSession().createQuery(hql);
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}

	@Override
	public String changePassword(String ajdbxt_police_id, String newPassword) {
		// TODO Auto-generated method stub
		try {
			String hql = "update ajdbxt_police set police_password ='" + newPassword + "' where ajdbxt_police_id = '"
					+ ajdbxt_police_id + "'";
			getSession().createQuery(hql).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}

	@Override
	public String batchDelete(String[] ids) {
		// TODO Auto-generated method stub
		try {
			for (int i = 0; i < ids.length; i++) {
				// getSession().delete(ids[i]);自带方法只能引用对象
				String hql = "delete ajdbxt_police where ajdbxt_police_id = '" + ids[i] + "'";
				getSession().createQuery(hql).executeUpdate();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}

	@Override
	public List<ajdbxt_department> findDepartmentByPage(String hql, int offset, int length) {
		// TODO Auto-generated method stub
		Query q = getSession().createQuery(hql);
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}

	@Override
	public String addDepartment(ajdbxt_department ajdbxt_department) {
		// TODO Auto-generated method stub
		try {
			getSession().saveOrUpdate(ajdbxt_department);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "falied";
		}
		return "success";
	}

	@Override
	public policedptVO findPoliceById(String ajdbxt_police_id) {
		// TODO Auto-generated method stub
		String hql = "select new com.ajdbxt.domain.VO.User.policedptVO(p,d) from ajdbxt_police p,ajdbxt_department d where p.police_department = d.ajdbxt_department_id and p.ajdbxt_police_id =  '" +ajdbxt_police_id+ "' order by p.police_gmt_modify desc ";
		Query query = getSession().createQuery(hql);
		policedptVO policeOne = (policedptVO) query.uniqueResult();
		return policeOne;
	}

	@Override
	public String findDptByid(String department) {
		// TODO Auto-generated method stub
		String hql = "select department_name from ajdbxt_department where ajdbxt_department_id = '"+department+"'";
		Query query = getSession().createQuery(hql);
		String dpt = (String)query.uniqueResult();
		return dpt;
	}

	
}
