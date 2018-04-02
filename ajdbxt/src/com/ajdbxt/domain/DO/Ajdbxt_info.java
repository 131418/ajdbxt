package com.ajdbxt.domain.DO;
/**
 * AjdbxtInfo entity. @author MyEclipse Persistence Tools
 */
public class Ajdbxt_info implements java.io.Serializable {

	// Fields

	private String ajdbxtInfoId;
	private String infoName;
	private String infoCategory;
	private String infoDepartment;
	private String infoCatchTime;
	private String infoMainPolice;
	private String infoAssistantPoliceOne;
	private String infoAssistantPoliceTwo;
	private String infoDepartmentLegalMember;
	private String infoDepartmentCaptain;
	private String infoLegalTeamMember;
	private String infoBureauLeader;
	private String infoGmtCeate;
	private String infoGmtModify;

	// Constructors

	/** default constructor */
	public Ajdbxt_info() {
	}

	/** minimal constructor */
	public Ajdbxt_info(String ajdbxtInfoId) {
		this.ajdbxtInfoId = ajdbxtInfoId;
	}

	/** full constructor */
	public Ajdbxt_info(String ajdbxtInfoId, String infoName,
			String infoCategory, String infoDepartment, String infoCatchTime,
			String infoMainPolice, String infoAssistantPoliceOne,
			String infoAssistantPoliceTwo, String infoDepartmentLegalMember,
			String infoDepartmentCaptain, String infoLegalTeamMember,
			String infoBureauLeader, String infoGmtCeate, String infoGmtModify) {
		this.ajdbxtInfoId = ajdbxtInfoId;
		this.infoName = infoName;
		this.infoCategory = infoCategory;
		this.infoDepartment = infoDepartment;
		this.infoCatchTime = infoCatchTime;
		this.infoMainPolice = infoMainPolice;
		this.infoAssistantPoliceOne = infoAssistantPoliceOne;
		this.infoAssistantPoliceTwo = infoAssistantPoliceTwo;
		this.infoDepartmentLegalMember = infoDepartmentLegalMember;
		this.infoDepartmentCaptain = infoDepartmentCaptain;
		this.infoLegalTeamMember = infoLegalTeamMember;
		this.infoBureauLeader = infoBureauLeader;
		this.infoGmtCeate = infoGmtCeate;
		this.infoGmtModify = infoGmtModify;
	}

	// Property accessors

	public String getAjdbxtInfoId() {
		return this.ajdbxtInfoId;
	}

	public void setAjdbxtInfoId(String ajdbxtInfoId) {
		this.ajdbxtInfoId = ajdbxtInfoId;
	}

	public String getInfoName() {
		return this.infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}

	public String getInfoCategory() {
		return this.infoCategory;
	}

	public void setInfoCategory(String infoCategory) {
		this.infoCategory = infoCategory;
	}

	public String getInfoDepartment() {
		return this.infoDepartment;
	}

	public void setInfoDepartment(String infoDepartment) {
		this.infoDepartment = infoDepartment;
	}

	public String getInfoCatchTime() {
		return this.infoCatchTime;
	}

	public void setInfoCatchTime(String infoCatchTime) {
		this.infoCatchTime = infoCatchTime;
	}

	public String getInfoMainPolice() {
		return this.infoMainPolice;
	}

	public void setInfoMainPolice(String infoMainPolice) {
		this.infoMainPolice = infoMainPolice;
	}

	public String getInfoAssistantPoliceOne() {
		return this.infoAssistantPoliceOne;
	}

	public void setInfoAssistantPoliceOne(String infoAssistantPoliceOne) {
		this.infoAssistantPoliceOne = infoAssistantPoliceOne;
	}

	public String getInfoAssistantPoliceTwo() {
		return this.infoAssistantPoliceTwo;
	}

	public void setInfoAssistantPoliceTwo(String infoAssistantPoliceTwo) {
		this.infoAssistantPoliceTwo = infoAssistantPoliceTwo;
	}

	public String getInfoDepartmentLegalMember() {
		return this.infoDepartmentLegalMember;
	}

	public void setInfoDepartmentLegalMember(String infoDepartmentLegalMember) {
		this.infoDepartmentLegalMember = infoDepartmentLegalMember;
	}

	public String getInfoDepartmentCaptain() {
		return this.infoDepartmentCaptain;
	}

	public void setInfoDepartmentCaptain(String infoDepartmentCaptain) {
		this.infoDepartmentCaptain = infoDepartmentCaptain;
	}

	public String getInfoLegalTeamMember() {
		return this.infoLegalTeamMember;
	}

	public void setInfoLegalTeamMember(String infoLegalTeamMember) {
		this.infoLegalTeamMember = infoLegalTeamMember;
	}

	public String getInfoBureauLeader() {
		return this.infoBureauLeader;
	}

	public void setInfoBureauLeader(String infoBureauLeader) {
		this.infoBureauLeader = infoBureauLeader;
	}

	public String getInfoGmtCeate() {
		return this.infoGmtCeate;
	}

	public void setInfoGmtCeate(String infoGmtCeate) {
		this.infoGmtCeate = infoGmtCeate;
	}

	public String getInfoGmtModify() {
		return this.infoGmtModify;
	}

	public void setInfoGmtModify(String infoGmtModify) {
		this.infoGmtModify = infoGmtModify;
	}

}