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
		Criteria criterria=session.createCriteria(ajdbxt_process.class);
		return criterria.add(Restrictions.eq(cloumn, key)).list();
	}
	
	@Override
	public List<ajdbxt_process> findProcessByKey(String key) {
		Session session=sessionFactory.getCurrentSession();
		String hql="from Process process where process.ajdbxt_process_id like '"+key+
				"' or process.process_case_id like '"+key+
				"' or process.process_lengthen_subpoena like '"+key+
				"' or process.process_nonage like '"+key+
				"' or process.process_authenticate like '"+key+
				"' or process.process_case_goods like '"+key+
				"' or process.process_detention like '"+key+
				"' or process.process_penalty like '"+key+
				"' or process.process_treatment_category like '"+key+
				"' or process.process_case_id like '"+key+
				"' or process.process_apply_right like '"+key+
				"' or process.process_accpet_right like '"+key+
				"' or process.process_question like '"+key+
				"' or process.process_score like '"+key+
				"' or process.process_captain_check like '"+key+
				"' or process.process_file_hand like '"+key+
				"' or process.process_case_end like '"+key+
				"' or process.process_gmt_modify like '"+key+
				"' or process.process_gmt_create like '"+key+"'";
		Query query=session.createQuery(hql);
		return query.list();
	}

	@Override
	public void updateProcess(ajdbxt_process process) {
		Session session=sessionFactory.getCurrentSession();
		session.update(process);
		session.flush();
	}

	@Override
	public void saveProcess(ajdbxt_process process) {
		Session session=sessionFactory.getCurrentSession();
		session.save(process);
		session.flush();
	}

	@Override
	public void deleteProcess(ajdbxt_process process) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(process);
		session.flush();
	}

	@Override
	public void saveProcessByCaseId(String case_id) {
		ajdbxt_process process=new ajdbxt_process();
		process.setAjdbxt_process_id(UUID.randomUUID().toString());
		process.setProcess_gmt_create(TeamUtil.getStringSecond());
		process.setProcess_gmt_modify(TeamUtil.getStringSecond());
		Class clazz=process.getClass();
		Field[] fields=clazz.getDeclaredFields();
		for(Field field:fields) {
			field.setAccessible(true);
			try {
				if(field.get(process)==null||field.get(process).equals("")) {
					field.set(process, "否");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		process.setProcess_case_id(case_id);
		saveProcess(process);
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

