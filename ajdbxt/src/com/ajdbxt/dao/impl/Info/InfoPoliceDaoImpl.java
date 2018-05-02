package com.ajdbxt.dao.impl.Info;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

	@Override
	public ajdbxt_police findPoliceById(String police_id) {
		Session session=sessionFactory.getCurrentSession();
		Criteria cri=session.createCriteria(ajdbxt_police.class);
		cri.add(Restrictions.eq("ajdbxt_police_id", police_id));
		return (ajdbxt_police) cri.uniqueResult();
	}

	@Override
	public List<ajdbxt_police> findLegals() {
		Session session=sessionFactory.getCurrentSession();
		Criteria cri=session.createCriteria(ajdbxt_police.class);
		cri.add(Restrictions.eq("police_department", "67ed5ab3-d773-4ac1-981b-2839ed0cec5c"));
		return cri.list();
	}

	@Override
	public List<ajdbxt_police> findLeaders() {
		String hql="from ajdbxt_police where  police_duty='负责人' or police_duty='副局长' ";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		return  query.list();
	}
	@Override
	public ajdbxt_police findCaptainByDepartment(String department_id) {
		String hql="from ajdbxt_police where police_department=? and police_duty='所队长' ";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		query.setString(0, department_id);
		return (ajdbxt_police) query.uniqueResult();
	}

	@Override
	public ajdbxt_police findLegalByDepartment(String department_id) {
		String hql="from ajdbxt_police where police_department=? and police_legaler='1'";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		query.setString(0, department_id);
		return (ajdbxt_police) query.uniqueResult();
	}
}

