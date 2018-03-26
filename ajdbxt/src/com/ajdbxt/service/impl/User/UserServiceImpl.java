package com.ajdbxt.service.impl.User;

import com.ajdbxt.dao.User.UserDao;
import com.ajdbxt.service.User.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public String findPolice(String username, String password) {
		return userDao.findPolice(username, password);
	}

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
