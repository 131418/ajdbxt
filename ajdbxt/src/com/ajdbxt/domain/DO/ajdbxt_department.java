package com.ajdbxt.domain.DO;

public class ajdbxt_department {

	private String ajdbxt_department_id;

	private String department_name;
	
	private String department_gmt_create;
	
	private String department_gmt_modify;

	public ajdbxt_department(String ajdbxt_department_id, String department_name, String department_gmt_create,
			String department_gmt_modify) {
		super();
		this.ajdbxt_department_id = ajdbxt_department_id;
		this.department_name = department_name;
		this.department_gmt_create = department_gmt_create;
		this.department_gmt_modify = department_gmt_modify;
	}

	public ajdbxt_department() {
	}

	public String getAjdbxt_department_id() {
		return ajdbxt_department_id;
	}

	public void setAjdbxt_department_id(String ajdbxt_department_id) {
		this.ajdbxt_department_id = ajdbxt_department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getDepartment_gmt_create() {
		return department_gmt_create;
	}

	public void setDepartment_gmt_create(String department_gmt_create) {
		this.department_gmt_create = department_gmt_create;
	}

	public String getDepartment_gmt_modify() {
		return department_gmt_modify;
	}

	public void setDepartment_gmt_modify(String department_gmt_modify) {
		this.department_gmt_modify = department_gmt_modify;
	}

	@Override
	public String toString() {
		return "ajdbxt_department [ajdbxt_department_id=" + ajdbxt_department_id + ", department_name="
				+ department_name + ", department_gmt_create=" + department_gmt_create + ", department_gmt_modify="
				+ department_gmt_modify + "]";
	}
	
	
}
