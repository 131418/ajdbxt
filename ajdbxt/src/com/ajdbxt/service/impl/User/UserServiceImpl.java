package com.ajdbxt.service.impl.User;

import java.util.List;

import com.ajdbxt.dao.User.UserDao;
import com.ajdbxt.domain.DO.Ajdbxt_police;
import com.ajdbxt.domain.VO.User.findPoliceByPageVO;
import com.ajdbxt.service.User.UserService;

import util.TeamUtil;
import util.md5;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public String findPolice(String username, String password) {
		Ajdbxt_police ajdbxt_user = userDao.findPolice(username, password);
		return null;
	}

	@Override
	public Object login(String policeSerialNumber, String policePassword) {
		
		//用传过来的用户名密码查询，将得到的结果放到ajdbxt_user
		Ajdbxt_police ajdbxt_police = userDao.findPolice(policeSerialNumber,policePassword);
		//比对是否存在该用户，如果不存在则ajdbxt_user为空
		if(null==ajdbxt_police) {
			return  "error";
		}else {
			//将穿过来的密码进行加密，与ajdbxt_user里面的密码进行比对
			if(!md5.GetMD5Code(policePassword).equals(ajdbxt_police.getPolicePassword())) {
				return "error";
			}else {
				return  ajdbxt_police;
			}
		}
	}

	@Override
	public Ajdbxt_police getUserById(String ajdbxt_police_id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String addPolice(Ajdbxt_police ajdbxt_police) {
		
		Ajdbxt_police ajdbxt_police_number = userDao.findPoliceByPoliceSerialNumber(ajdbxt_police.getPoliceSerialNumber());
		//判断被添加用户是否已存在，不存在则执行
		if(null==ajdbxt_police_number) {	
			ajdbxt_police.setAjdbxtPoliceId(TeamUtil.getUuid());
			ajdbxt_police.setPoliceGmtCreate(TeamUtil.getStringSecond());
			ajdbxt_police.setPoliceGmtModify(TeamUtil.getStringSecond());
		
			//返回保存结果
			boolean result =  userDao.addPolice(ajdbxt_police);
			if(result) {
				return  "success";
			}
			return "error";
		}
		return "failed";
		
	}


	@Override
	public String deletePolice(Ajdbxt_police ajdbxt_police) {
		// TODO Auto-generated method stub
		boolean result =  userDao.deletePolice(ajdbxt_police);
		//三目运算符
		return result?"success":"error";
	}

	@Override
	public String updatePolice(Ajdbxt_police ajdbxt_police) {
		// TODO Auto-generated method stub
		boolean result = userDao.updatePolice(ajdbxt_police);
		return result?"success":"error";
	}

	@Override
	public List<Ajdbxt_police> findPoliceByPoliceDepartment(String policeDepartment) {
		// TODO Auto-generated method stub
		List<Ajdbxt_police> policeofdepartment  = userDao.findPoliceByPoliceDepartment(policeDepartment);
		return policeofdepartment;
	}

/*	@Override
	public List<Ajdbxt_police> findAllPolice() {
		List<Ajdbxt_police> findallpolice = userDao.findAllPolice();
		return findallpolice;
	}*/

	@Override
	public List<Ajdbxt_police> blurSearch(Ajdbxt_police ajdbxt_police) {
		// TODO Auto-generated method stub
		/*List<Ajdbxt_police> blursearch;
		blursearch = null;*/
		List<Ajdbxt_police> blursearch = userDao.blurSearch(ajdbxt_police);
		return blursearch;
	}

	@Override
	public List<Ajdbxt_police> findPoliceByPage(findPoliceByPageVO findPoliceByPage) {
		// TODO Auto-generated method stub
		List findPolicePyPage = userDao.findPoliceByPage(findPoliceByPage);
		return findPolicePyPage;
	}



}
