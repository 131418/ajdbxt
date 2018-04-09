package com.ajdbxt.dao.Process;

import com.ajdbxt.domain.DO.ajdbxt_department;

public interface ProcessDepartmentDao {
	public ajdbxt_department findDepartmentById(String department_id);
}
