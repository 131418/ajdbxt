package com.ajdbxt.domain.DO;

public class ajdbxt_info {
	private String ajdbxt_info_id;   //案件信息id
	private String info_name;      //案件名称
	private String info_category;    //案件类别
	private String info_department;  //办案部门
	private String info_catch_time;  //抓获时间
	private String info_main_police; //主办民警
	private String info_assistant_police_one; //协办民警1
	private String info_assistant_police_two; //协办民警2
	private String info_department_legal_member; //所（队）法制员
	private String info_department_captain;  //所（队）长
	private String info_legal_team_membe;  //法制大队值班民警
	private String info_bureau_leader;  //值班局领导
	private String info_gmt_ceate;  //创建时间
	private String info_gmt_modify; //修改时间
	public String getAjdbxt_info_id() {
		return ajdbxt_info_id;
	}
	public void setAjdbxt_info_id(String ajdbxt_info_id) {
		this.ajdbxt_info_id = ajdbxt_info_id;
	}
	public String getInfo_name() {
		return info_name;
	}
	public void setInfo_name(String info_name) {
		this.info_name = info_name;
	}
	public String getInfo_category() {
		return info_category;
	}
	public void setInfo_category(String info_category) {
		this.info_category = info_category;
	}
	public String getInfo_department() {
		return info_department;
	}
	public void setInfo_department(String info_department) {
		this.info_department = info_department;
	}
	public String getInfo_catch_time() {
		return info_catch_time;
	}
	public void setInfo_catch_time(String info_catch_time) {
		this.info_catch_time = info_catch_time;
	}
	public String getInfo_main_police() {
		return info_main_police;
	}
	public void setInfo_main_police(String info_main_police) {
		this.info_main_police = info_main_police;
	}
	public String getInfo_assistant_police_one() {
		return info_assistant_police_one;
	}
	public void setInfo_assistant_police_one(String info_assistant_police_one) {
		this.info_assistant_police_one = info_assistant_police_one;
	}
	public String getInfo_assistant_police_two() {
		return info_assistant_police_two;
	}
	public void setInfo_assistant_police_two(String info_assistant_police_two) {
		this.info_assistant_police_two = info_assistant_police_two;
	}
	public String getInfo_department_legal_member() {
		return info_department_legal_member;
	}
	public void setInfo_department_legal_member(String info_department_legal_member) {
		this.info_department_legal_member = info_department_legal_member;
	}
	public String getInfo_department_captain() {
		return info_department_captain;
	}
	public void setInfo_department_captain(String info_department_captain) {
		this.info_department_captain = info_department_captain;
	}
	public String getInfo_legal_team_membe() {
		return info_legal_team_membe;
	}
	public void setInfo_legal_team_membe(String info_legal_team_membe) {
		this.info_legal_team_membe = info_legal_team_membe;
	}
	public String getInfo_bureau_leader() {
		return info_bureau_leader;
	}
	public void setInfo_bureau_leader(String info_bureau_leader) {
		this.info_bureau_leader = info_bureau_leader;
	}
	public String getInfo_gmt_ceate() {
		return info_gmt_ceate;
	}
	public void setInfo_gmt_ceate(String info_gmt_ceate) {
		this.info_gmt_ceate = info_gmt_ceate;
	}
	public String getInfo_gmt_modify() {
		return info_gmt_modify;
	}
	public void setInfo_gmt_modify(String info_gmt_modify) {
		this.info_gmt_modify = info_gmt_modify;
	}
	
	public ajdbxt_info(String ajdbxt_info_id, String info_name, String info_category, String info_department,
			String info_catch_time, String info_main_police, String info_assistant_police_one,
			String info_assistant_police_two, String info_department_legal_member, String info_department_captain,
			String info_legal_team_membe, String info_bureau_leader, String info_gmt_ceate, String info_gmt_modify) {
		super();
		this.ajdbxt_info_id = ajdbxt_info_id;
		this.info_name = info_name;
		this.info_category = info_category;
		this.info_department = info_department;
		this.info_catch_time = info_catch_time;
		this.info_main_police = info_main_police;
		this.info_assistant_police_one = info_assistant_police_one;
		this.info_assistant_police_two = info_assistant_police_two;
		this.info_department_legal_member = info_department_legal_member;
		this.info_department_captain = info_department_captain;
		this.info_legal_team_membe = info_legal_team_membe;
		this.info_bureau_leader = info_bureau_leader;
		this.info_gmt_ceate = info_gmt_ceate;
		this.info_gmt_modify = info_gmt_modify;
	}

}
