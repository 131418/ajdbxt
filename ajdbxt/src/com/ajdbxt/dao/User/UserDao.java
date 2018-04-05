package com.ajdbxt.dao.User;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.VO.User.findPoliceByPageVO;

public interface UserDao {

	ajdbxt_police findPolice(String police_serial_number);

	boolean addPolice(ajdbxt_police ajdbxt_police);

	ajdbxt_police findPoliceByPoliceSerialNumber(String police_serial_number);

	boolean updatePolice(ajdbxt_police ajdbxt_police);
	//分页查询
	public List<ajdbxt_police> queryForPage(String hql,int offset,int length);
	//总记录条数
	public int getCount(String hql);

	List<ajdbxt_police> queryForPageByDepartment(String hql, int offset, int length);

	String changePassword(String ajdbxt_police_id,String newPassword);

	String batchDelete(String[] ids);
}
