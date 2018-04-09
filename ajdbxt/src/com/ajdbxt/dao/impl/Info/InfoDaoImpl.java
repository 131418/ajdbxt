<<<<<<< HEAD
package com.ajdbxt.dao.impl.Info;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import com.ajdbxt.dao.Info.InfoDao;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;

public class InfoDaoImpl implements InfoDao {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public  Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	

	@Override
	public void updateCase(ajdbxt_info caseInfo) {
		Session session=this.getSession();
		session.saveOrUpdate(caseInfo);
		session.flush();
	}

	@Override
	public void deleteCase(String  caseInfo_id) {
		String hql="delete from ajdbxt_info where ajdbxt_info_id='"+caseInfo_id+"'";
		this.getSession().createQuery(hql).executeUpdate();

	}

	@Override
	public ajdbxt_info findOneCase(ajdbxt_info caseInfo) {
		// TODO Auto-generated method stub
		String hql=" from ajdbxt_info ai where ai.ajdbxt_info_id='"+caseInfo.getAjdbxt_info_id()+"'";
		caseInfo=(ajdbxt_info) this.getSession().createQuery(hql).uniqueResult();
		return caseInfo;
	}

	@Override
	public void saveCase(ajdbxt_info caseInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int findTotalCase(Page_list_caseInfoVo page_list_caseInfoVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ajdbxt_info> findCaseByPage(Page_list_caseInfoVo page_list_caseInfoVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ajdbxt_info findCaseById(String info_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ajdbxt_info findCaseByCaseName(String info_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCaptainWorked(String captainId) {
		boolean work=false;
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from ajdbxt_info where INFO_MAIN_POLICE=?");
		query.setString(0, captainId);
		if(query.list().isEmpty()) {
			work=true;
		}
		return work;
	}

	@Override
	public int countProcessByPoliceId(String ajdbxt_police_id) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from ajdbxt_info where info_main_police=? or info_assistant_police_one=? or info_assistant_police_two=?");
		for(int index=0;index<3;index++) {
			query.setString(index, ajdbxt_police_id);
		}
		return query.list().size();
	}

	@Override
	public List<ajdbxt_info> findSomeCase(int start, int length) {
		Session session=sessionFactory.getCurrentSession();
		Criteria cri=session.createCriteria(ajdbxt_info.class);
		cri.setFirstResult(start);
//		cri.setFetchSize(length);
		cri.setMaxResults(length);

		cri.addOrder(Order.desc("info_gmt_modify"));

		return cri.list();
	}

	@Override
	public int countAllCase() {
		Session session=sessionFactory.getCurrentSession();
		Criteria cri=session.createCriteria(ajdbxt_info.class);
		return cri.list().size();
	}

}
=======
package com.ajdbxt.dao.impl.Info;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import com.ajdbxt.dao.Info.InfoDao;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;

public class InfoDaoImpl implements InfoDao {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public  Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	

	@Override
	public void updateCase(ajdbxt_info caseInfo) {
		Session session=this.getSession();
		session.saveOrUpdate(caseInfo);
		session.flush();
	}

	@Override
	public void deleteCase(String  caseInfo_id) {
		String hql="delete from ajdbxt_info where ajdbxt_info_id='"+caseInfo_id+"'";
		this.getSession().createQuery(hql).executeUpdate();

	}

	@Override
	public ajdbxt_info findOneCase(ajdbxt_info caseInfo) {
		// TODO Auto-generated method stub
		String hql=" from ajdbxt_info ai where ai.ajdbxt_info_id='"+caseInfo.getAjdbxt_info_id()+"'";
		caseInfo=(ajdbxt_info) this.getSession().createQuery(hql).uniqueResult();
		return caseInfo;
	}

	@Override
	public void saveCase(ajdbxt_info caseInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int findTotalCase(Page_list_caseInfoVo page_list_caseInfoVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ajdbxt_info> findCaseByPage(Page_list_caseInfoVo page_list_caseInfoVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ajdbxt_info findCaseById(String info_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ajdbxt_info findCaseByCaseName(String info_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCaptainWorked(String captainId) {
		boolean work=false;
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from ajdbxt_info where INFO_MAIN_POLICE=?");
		query.setString(0, captainId);
		if(query.list().isEmpty()) {
			work=true;
		}
		return work;
	}

	@Override
	public int countProcessByPoliceId(String ajdbxt_police_id) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from ajdbxt_info where info_main_police=? or info_assistant_police_one=? or info_assistant_police_two=?");
		for(int index=0;index<3;index++) {
			query.setString(index, ajdbxt_police_id);
		}
		return query.list().size();
	}

	@Override
	public List<ajdbxt_info> findSomeCase(int start, int length) {
		Session session=sessionFactory.getCurrentSession();
		Criteria cri=session.createCriteria(ajdbxt_info.class);
		cri.setFirstResult(start);
//		cri.setFetchSize(length);
		cri.setMaxResults(length);
		cri.addOrder(Order.desc("info_gmt_modify"));
		return cri.list();
	}

	@Override
	public int countAllCase() {
		Session session=sessionFactory.getCurrentSession();
		Criteria cri=session.createCriteria(ajdbxt_info.class);
		return cri.list().size();
	}

}
>>>>>>> LSY
