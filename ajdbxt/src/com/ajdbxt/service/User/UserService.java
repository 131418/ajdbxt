package com.ajdbxt.service.User;

import java.util.List;

import com.ajdbxt.domain.DO.Ajdbxt_police;
import com.ajdbxt.domain.VO.User.findPoliceByPageVO;

public interface UserService {
	
	public Ajdbxt_police getUserById(String ajdbxt_police_id);

	Object login(String policeSerialNumber, String policePassword);

	String addPolice(Ajdbxt_police ajdbxt_police);

	String deletePolice(Ajdbxt_police ajdbxt_police);

	String updatePolice(Ajdbxt_police ajdbxt_police);

	//List<Ajdbxt_police> findPoliceByPoliceDepartment(String policeDepartment);

	/*List<Ajdbxt_police> findAllPolice();*/

	List<Ajdbxt_police> blurSearch(Ajdbxt_police ajdbxt_police);

	/*List<Ajdbxt_police> findPoliceByPage(findPoliceByPageVO findPoliceByPage);*/
	
	public findPoliceByPageVO queryForPage(int pageSize, int currentPage);

	public findPoliceByPageVO queryForPageByDepartment(int pageSize, int currentPage, String department);

	
	

}
