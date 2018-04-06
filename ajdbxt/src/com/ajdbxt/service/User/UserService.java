package com.ajdbxt.service.User;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.VO.User.findPoliceByPageVO;

public interface UserService {
	
	public ajdbxt_police getUserById(String policeSerialNumber);

	Object login(String policeSerialNumber, String policePassword);

	String addPolice(ajdbxt_police ajdbxt_police);

	String deletePolice(ajdbxt_police ajdbxt_police);

	String updatePolice(ajdbxt_police ajdbxt_police);

	//List<Ajdbxt_police> findPoliceByPoliceDepartment(String policeDepartment);

	/*List<Ajdbxt_police> findAllPolice();*/

	/*List<Ajdbxt_police> findPoliceByPage(findPoliceByPageVO findPoliceByPage);*/
	
	public findPoliceByPageVO queryForPage(int pageSize, int currentPage);

	public findPoliceByPageVO queryForPageByDepartment(int pageSize, int currentPage, String department);

	public String changePassword(String ajdbxtPoliceId, String newPassword);

	
	

}
