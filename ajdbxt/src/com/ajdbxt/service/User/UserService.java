package com.ajdbxt.service.User;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.VO.User.findDepartmentByPageVO;
import com.ajdbxt.domain.VO.User.findPoliceByPageVO;
import com.ajdbxt.domain.VO.User.policedptVO;

public interface UserService {
	
	public ajdbxt_police getUserById(String police_serial_number);

	Object login(String police_serial_number, String police_password);

	String addPolice(ajdbxt_police ajdbxt_police);

	String updatePolice(ajdbxt_police ajdbxt_police);
	
	public findPoliceByPageVO queryForPage(int pageSize, int currentPage,String police_name);

	public findPoliceByPageVO queryForPageByDepartment(int pageSize, int currentPage, String department);

	public String changePassword(String ajdbxt_police_id, String newPassword);

	public String batchDelete(String[] ids);
	
	String addDepartment(ajdbxt_department ajdbxt_department);

	public findDepartmentByPageVO findDepartmentByPage(int pageSize, int currentPage);

	public policedptVO findPoliceById(String ajdbxt_police_id);

	//public findPoliceByPageVO fuzzySearch(int pageSize, int currentPage,String police_name);
	

}
