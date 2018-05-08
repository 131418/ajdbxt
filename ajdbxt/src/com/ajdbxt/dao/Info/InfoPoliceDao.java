package com.ajdbxt.dao.Info;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_police;

public interface InfoPoliceDao {
	public List<ajdbxt_police> findPoliceByDepartment(String department);
	public ajdbxt_police findPoliceById(String police_id);
	public List<ajdbxt_police> findLegals();
	public List<ajdbxt_police> findLeaders();
	public ajdbxt_police findCaptainByDepartment(String department_id);
	public List<ajdbxt_police> findLegalOfDepartment();
}
