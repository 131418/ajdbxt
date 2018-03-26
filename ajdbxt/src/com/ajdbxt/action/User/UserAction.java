package com.ajdbxt.action.User;



import com.ajdbxt.service.User.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	
	public  String login() {
System.out.println(username);
		String s = userService.findPolice(username, password);
		if ("1".equals(s)) {
			return "SUCCESS";
		} else {
			return "FALSE";
		}
	}
	
	private UserService userService;
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	

}
