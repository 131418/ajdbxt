package com.ajdbxt.domain.DO;

public class ajdbxt_process {
	private String ajdbxt_process_id;//流程表id
	private String process_case_id;//案件id
	private String process_lengthen_subpoena;//是否延长传唤时间
	private String process_nonage;//是否未成年
	private String process_authenticate;//是否鉴定
	private String process_case_goods;//涉案财物
	private String process_detention;//是否行政拘留
	private String process_penalty;//是否罚款
	private String process_apply_right;//申请听证
	private String process_goods_in_lib;//涉案财物已入库
	private Integer process_question;//问题整改数量
	private Double process_score;//评分分数
	private String process_captain_check;//所队长审核
	private String process_file_hand;//案卷上交法制大队
	private String process_case_end;//结案
	private String process_gmt_create;//流程创建
	private String process_gmt_modify;//流程修改时间
	private Integer process_question_list;//提出问题数量
	/*
	 * 起诉结果：第一次补查，检察院撤回案件，撤案，审判
	 * 第一次补查结果：第一次补查，检察院撤回案件，撤案，审判
	 * 第二次补查结果：检察院撤回案件，撤案，审判
	 */ 
	private String process_search_result_one;//第一次补查结果
	private String process_search_result_two;//第二次补查结果
	private String process_is_rollback;//是否打回
	private String process_administrativ_warning;//行政警告
	private String process_community_abandon_drug;//社区戒毒
	private String process_mandatory_abandon_drug;//强制隔离戒毒
	private String process_lengthen_criminal_detention;//是否延长刑事拘留
	private String process_force_measure_one;//强制措施1
	private String process_force_measure_two;//强制措施2
	private String process_force_measure_three;//强制措施3
	private String process_result_of_prosecution;//强制措施4
	public String getAjdbxt_process_id() {
		return ajdbxt_process_id;
	}

	public void setAjdbxt_process_id(String ajdbxt_process_id) {
		this.ajdbxt_process_id = ajdbxt_process_id;
	}

	public String getProcess_case_id() {
		return process_case_id;
	}

	public void setProcess_case_id(String process_case_id) {
		this.process_case_id = process_case_id;
	}

	public String getProcess_lengthen_subpoena() {
		return process_lengthen_subpoena;
	}

	public void setProcess_lengthen_subpoena(String process_lengthen_subpoena) {
		this.process_lengthen_subpoena = process_lengthen_subpoena;
	}

	public String getProcess_nonage() {
		return process_nonage;
	}

	public void setProcess_nonage(String process_nonage) {
		this.process_nonage = process_nonage;
	}

	public String getProcess_authenticate() {
		return process_authenticate;
	}

	public void setProcess_authenticate(String process_authenticate) {
		this.process_authenticate = process_authenticate;
	}

	public String getProcess_case_goods() {
		return process_case_goods;
	}

	public void setProcess_case_goods(String process_case_goods) {
		this.process_case_goods = process_case_goods;
	}

	public String getProcess_detention() {
		return process_detention;
	}

	public void setProcess_detention(String process_detention) {
		this.process_detention = process_detention;
	}

	public String getProcess_penalty() {
		return process_penalty;
	}

	public void setProcess_penalty(String process_penalty) {
		this.process_penalty = process_penalty;
	}

	public String getProcess_apply_right() {
		return process_apply_right;
	}

	public void setProcess_apply_right(String process_apply_right) {
		this.process_apply_right = process_apply_right;
	}

	public String getProcess_goods_in_lib() {
		return process_goods_in_lib;
	}

	public void setProcess_goods_in_lib(String process_goods_in_lib) {
		this.process_goods_in_lib = process_goods_in_lib;
	}

	public Integer getProcess_question() {
		return process_question;
	}

	public void setProcess_question(Integer process_question) {
		this.process_question = process_question;
	}

	public Double getProcess_score() {
		return process_score;
	}

	public void setProcess_score(Double process_score) {
		this.process_score = process_score;
	}

	public String getProcess_captain_check() {
		return process_captain_check;
	}

	public void setProcess_captain_check(String process_captain_check) {
		this.process_captain_check = process_captain_check;
	}

	public String getProcess_file_hand() {
		return process_file_hand;
	}

	public void setProcess_file_hand(String process_file_hand) {
		this.process_file_hand = process_file_hand;
	}

	public String getProcess_case_end() {
		return process_case_end;
	}

	public void setProcess_case_end(String process_case_end) {
		this.process_case_end = process_case_end;
	}

	public String getProcess_gmt_create() {
		return process_gmt_create;
	}

	public void setProcess_gmt_create(String process_gmt_create) {
		this.process_gmt_create = process_gmt_create;
	}

	public String getProcess_gmt_modify() {
		return process_gmt_modify;
	}

	public void setProcess_gmt_modify(String process_gmt_modify) {
		this.process_gmt_modify = process_gmt_modify;
	}

	public Integer getProcess_question_list() {
		return process_question_list;
	}

	public void setProcess_question_list(Integer process_question_list) {
		this.process_question_list = process_question_list;
	}

	public String getProcess_search_result_one() {
		return process_search_result_one;
	}

	public void setProcess_search_result_one(String process_search_result_one) {
		this.process_search_result_one = process_search_result_one;
	}

	public String getProcess_search_result_two() {
		return process_search_result_two;
	}

	public void setProcess_search_result_two(String process_search_result_two) {
		this.process_search_result_two = process_search_result_two;
	}

	public String getProcess_is_rollback() {
		return process_is_rollback;
	}

	public void setProcess_is_rollback(String process_is_rollback) {
		this.process_is_rollback = process_is_rollback;
	}

	public String getProcess_administrativ_warning() {
		return process_administrativ_warning;
	}

	public void setProcess_administrativ_warning(String process_administrativ_warning) {
		this.process_administrativ_warning = process_administrativ_warning;
	}

	public String getProcess_community_abandon_drug() {
		return process_community_abandon_drug;
	}

	public void setProcess_community_abandon_drug(String process_community_abandon_drug) {
		this.process_community_abandon_drug = process_community_abandon_drug;
	}

	public String getProcess_mandatory_abandon_drug() {
		return process_mandatory_abandon_drug;
	}

	public void setProcess_mandatory_abandon_drug(String process_mandatory_abandon_drug) {
		this.process_mandatory_abandon_drug = process_mandatory_abandon_drug;
	}

	public String getProcess_lengthen_criminal_detention() {
		return process_lengthen_criminal_detention;
	}

	public void setProcess_lengthen_criminal_detention(String process_lengthen_criminal_detention) {
		this.process_lengthen_criminal_detention = process_lengthen_criminal_detention;
	}

	public String getProcess_force_measure_one() {
		return process_force_measure_one;
	}

	public void setProcess_force_measure_one(String process_force_measure_one) {
		this.process_force_measure_one = process_force_measure_one;
	}

	public String getProcess_force_measure_two() {
		return process_force_measure_two;
	}

	public void setProcess_force_measure_two(String process_force_measure_two) {
		this.process_force_measure_two = process_force_measure_two;
	}

	public String getProcess_force_measure_three() {
		return process_force_measure_three;
	}

	public void setProcess_force_measure_three(String process_force_measure_three) {
		this.process_force_measure_three = process_force_measure_three;
	}

	public String getProcess_result_of_prosecution() {
		return process_result_of_prosecution;
	}

	public void setProcess_result_of_prosecution(String process_result_of_prosecution) {
		this.process_result_of_prosecution = process_result_of_prosecution;
	}

	@Override
	public String toString() {
		return "ajdbxt_process [ajdbxt_process_id=" + ajdbxt_process_id + ", process_case_id=" + process_case_id
				+ ", process_lengthen_subpoena=" + process_lengthen_subpoena + ", process_nonage=" + process_nonage
				+ ", process_authenticate=" + process_authenticate + ", process_case_goods=" + process_case_goods
				+ ", process_detention=" + process_detention + ", process_penalty=" + process_penalty
				+ ", process_apply_right=" + process_apply_right + ", process_goods_in_lib=" + process_goods_in_lib
				+ ", process_question=" + process_question + ", process_score=" + process_score
				+ ", process_captain_check=" + process_captain_check + ", process_file_hand=" + process_file_hand
				+ ", process_case_end=" + process_case_end + ", process_gmt_create=" + process_gmt_create
				+ ", process_gmt_modify=" + process_gmt_modify + ", process_question_list=" + process_question_list
				+ ", process_search_result_one=" + process_search_result_one + ", process_search_result_two="
				+ process_search_result_two + ", process_is_rollback=" + process_is_rollback
				+ ", process_administrativ_warning=" + process_administrativ_warning
				+ ", process_community_abandon_drug=" + process_community_abandon_drug
				+ ", process_mandatory_abandon_drug=" + process_mandatory_abandon_drug
				+ ", process_lengthen_criminal_detention=" + process_lengthen_criminal_detention
				+ ", process_force_measure_one=" + process_force_measure_one + ", process_force_measure_two="
				+ process_force_measure_two + ", process_force_measure_three=" + process_force_measure_three
				+ ", process_result_of_prosecution=" + process_result_of_prosecution + "]";
	}

}
