package com.ajdbxt.dao.User;

import java.util.List;

import com.ajdbxt.domain.DO.Ajdbxt_police;
import com.ajdbxt.domain.VO.User.findPoliceByPageVO;

public interface UserDao {

	Ajdbxt_police findPolice(String policeSerialNumber);

	boolean addPolice(Ajdbxt_police ajdbxt_police);

	boolean deletePolice(Ajdbxt_police ajdbxt_police);

	Ajdbxt_police findPoliceByPoliceSerialNumber(String policeSerialNumber);

	boolean updatePolice(Ajdbxt_police ajdbxt_police);
	/*void listPolice();*/

	/*List<Ajdbxt_police> findPoliceByPoliceDepartment(String policeDepartment);*/

	/*List<Ajdbxt_police> findAllPolice();*/

	List<Ajdbxt_police> blurSearch(Ajdbxt_police ajdbxt_police);

	/*List<Ajdbxt_police> findPoliceByPage(findPoliceByPageVO findPoliceByPage); */
	//分页查询
	public List<Ajdbxt_police> queryForPage(String hql,int offset,int length);
	//总记录条数
	public int getCount(String hql);

	List<Ajdbxt_police> queryForPageByDepartment(String hql, int offset, int length);
}
