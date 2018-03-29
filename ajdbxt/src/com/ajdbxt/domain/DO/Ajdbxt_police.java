package com.ajdbxt.domain.DO;

/**
 * AjdbxtPolice entity. @author MyEclipse Persistence Tools
 */

public class Ajdbxt_police implements java.io.Serializable {

	// Fields

	private String ajdbxtPoliceId;
	private String policeSerialNumber;
	private String policePassword;
	private String policeName;
	private String policeDepartment;
	private String policeDuty;
	private String policePhoneNumber;
	private String policeGmtCreate;
	private String policeGmtModify;
	private String policePower;

	// Constructors

	/** default constructor */
	public Ajdbxt_police() {
	}

	/** minimal constructor */
	public Ajdbxt_police(String ajdbxtPoliceId) {
		this.ajdbxtPoliceId = ajdbxtPoliceId;
	}
	/** full constructor */
	public Ajdbxt_police(String ajdbxtPoliceId, String policeSerialNumber,
			String policePassword, String policeName, String policeDepartment,
			String policeDuty, String policePhoneNumber,
			String policeGmtCreate, String policeGmtModify, String policePower) {
		this.ajdbxtPoliceId = ajdbxtPoliceId;
		this.policeSerialNumber = policeSerialNumber;
		this.policePassword = policePassword;
		this.policeName = policeName;
		this.policeDepartment = policeDepartment;
		this.policeDuty = policeDuty;
		this.policePhoneNumber = policePhoneNumber;
		this.policeGmtCreate = policeGmtCreate;
		this.policeGmtModify = policeGmtModify;
		this.policePower = policePower;
	}

	// Property accessors

	public String getAjdbxtPoliceId() {
		return this.ajdbxtPoliceId;
	}

	public void setAjdbxtPoliceId(String ajdbxtPoliceId) {
		this.ajdbxtPoliceId = ajdbxtPoliceId;
	}

	public String getPoliceSerialNumber() {
		return this.policeSerialNumber;
	}

	public void setPoliceSerialNumber(String policeSerialNumber) {
		this.policeSerialNumber = policeSerialNumber;
	}

	public String getPolicePassword() {
		return this.policePassword;
	}

	public void setPolicePassword(String policePassword) {
		this.policePassword = policePassword;
	}

	public String getPoliceName() {
		return this.policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	public String getPoliceDepartment() {
		return this.policeDepartment;
	}

	public void setPoliceDepartment(String policeDepartment) {
		this.policeDepartment = policeDepartment;
	}

	public String getPoliceDuty() {
		return this.policeDuty;
	}

	public void setPoliceDuty(String policeDuty) {
		this.policeDuty = policeDuty;
	}

	public String getPolicePhoneNumber() {
		return this.policePhoneNumber;
	}

	public void setPolicePhoneNumber(String policePhoneNumber) {
		this.policePhoneNumber = policePhoneNumber;
	}

	public String getPoliceGmtCreate() {
		return this.policeGmtCreate;
	}

	public void setPoliceGmtCreate(String policeGmtCreate) {
		this.policeGmtCreate = policeGmtCreate;
	}

	public String getPoliceGmtModify() {
		return this.policeGmtModify;
	}

	public void setPoliceGmtModify(String policeGmtModify) {
		this.policeGmtModify = policeGmtModify;
	}

	public String getPolicePower() {
		return policePower;
	}

	public void setPolicePower(String policePower) {
		this.policePower = policePower;
	}

}