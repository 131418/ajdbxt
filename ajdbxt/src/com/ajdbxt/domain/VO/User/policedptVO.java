package com.ajdbxt.domain.VO.User;

import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;

public class policedptVO {

	private ajdbxt_police ajdbxt_police;

	private ajdbxt_department ajdbxt_department;

	public policedptVO() {

		super();

	}

	public policedptVO(com.ajdbxt.domain.DO.ajdbxt_police ajdbxt_police,

			com.ajdbxt.domain.DO.ajdbxt_department ajdbxt_department) {

		this.ajdbxt_police = ajdbxt_police;

		this.ajdbxt_department = ajdbxt_department;

	}

	public ajdbxt_police getAjdbxt_police() {

		return ajdbxt_police;

	}

	public void setAjdbxt_police(ajdbxt_police ajdbxt_police) {

		this.ajdbxt_police = ajdbxt_police;

	}

	public ajdbxt_department getAjdbxt_department() {

		return ajdbxt_department;

	}

	public void setAjdbxt_department(ajdbxt_department ajdbxt_department) {

		this.ajdbxt_department = ajdbxt_department;

	}

}