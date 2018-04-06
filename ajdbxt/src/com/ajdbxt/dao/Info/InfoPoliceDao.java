package com.ajdbxt.dao.Info;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_police;

public interface InfoPoliceDao {
	public List<ajdbxt_police> findPoliceByDepartment(String department);
}
