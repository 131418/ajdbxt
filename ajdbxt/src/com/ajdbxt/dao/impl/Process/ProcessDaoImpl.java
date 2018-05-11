package com.ajdbxt.dao.impl.Process;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.ajdbxt.domain.DO.ajdbxt_process;

import util.TeamUtil;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ajdbxt.dao.Process.ProcessDao;

public class ProcessDaoImpl implements ProcessDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<ajdbxt_process> findProcessByColumn(String cloumn,String key) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criterria=session.createCriteria(ajdbxt_process.class).add(Restrictions.eq(cloumn, key));
		return criterria.list();
	}

	@Override
	public void updateProcess(ajdbxt_process process) {
		process.setProcess_gmt_modify(util.Time.getStringSecond());
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(process);
		session.flush();
	}

	@Override
	public void saveProcess(ajdbxt_process process) {
		process.setProcess_gmt_create(util.Time.getStringSecond());
		process.setProcess_gmt_modify(util.Time.getStringSecond());
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(process);
		session.flush();
	}

	@Override
	public void deleteProcess(String caseInfo_id) {
		Session session=sessionFactory.getCurrentSession();
		ajdbxt_process process=(ajdbxt_process) session.createCriteria(ajdbxt_process.class).add(Restrictions.eq("process_case_id",caseInfo_id)).uniqueResult();
		session.delete(process);
		session.flush();
	}

	@Override
	public void saveProcessByCaseId(String case_id) {
		if(findProcessByCaseId(case_id).size()<=0) {		
			ajdbxt_process process=new ajdbxt_process();
			process.setAjdbxt_process_id(UUID.randomUUID().toString());
			process.setProcess_gmt_create(TeamUtil.getStringSecond());
			process.setProcess_gmt_modify(TeamUtil.getStringSecond());
			process.setProcess_case_id(case_id);
			saveProcess(process);
		}
	}

	@Override
	public List<ajdbxt_process> findProcessByCaseId(String case_id) {
		return findProcessByColumn("process_case_id", case_id);
	}

	@Override
	public List<ajdbxt_process> findSomeProcess(int start, int length) {//找到结果且分页
		Session session=sessionFactory.getCurrentSession();
		Criteria cri=session.createCriteria(ajdbxt_process.class);
		cri.setFirstResult(start);
		cri.setMaxResults(length);
		cri.addOrder(Order.desc("process_gmt_modify"));
		return cri.list();
	}

	@Override
	public int findAllProcess() {
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery("from ajdbxt_process").list().size();
	}	
}
