package com.ajdbxt.service.impl.User;

import java.util.List;

import com.ajdbxt.dao.User.UserDao;
import com.ajdbxt.domain.DO.ajdbxt_police;
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
	public Object login(String policeSerialNumber, String policePassword) {
		
		//用传过来的用户名密码查询，将得到的结果放到ajdbxt_user
		ajdbxt_police ajdbxt_police = userDao.findPolice(policeSerialNumber);
		//比对是否存在该用户，如果不存在则ajdbxt_user为空
		if(null==ajdbxt_police) {
			return null;
		}else {
			//将穿过来的密码进行加密，与ajdbxt_user里面的密码进行比对
			if(!md5.GetMD5Code(policePassword).equals(ajdbxt_police.getPolicePassword())) {
				return null;
			}else {
				return  ajdbxt_police;
			}
		}
	}

	@Override
	public ajdbxt_police getUserById(String policeSerialNumber) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String addPolice(ajdbxt_police ajdbxt_police) {
		
		ajdbxt_police ajdbxt_police_number = userDao.findPoliceByPoliceSerialNumber(ajdbxt_police.getPoliceSerialNumber());
		//判断被添加用户是否已存在，不存在则执行
		if(null==ajdbxt_police_number) {	
			ajdbxt_police.setAjdbxtPoliceId(TeamUtil.getUuid());
			ajdbxt_police.setPoliceGmtCreate(TeamUtil.getStringSecond());
			ajdbxt_police.setPoliceGmtModify(TeamUtil.getStringSecond());
			ajdbxt_police.setPolicePassword(md5.GetMD5Code(ajdbxt_police.getPolicePassword()));
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
	public String deletePolice(ajdbxt_police ajdbxt_police) {
		// TODO Auto-generated method stub
		boolean result =  userDao.deletePolice(ajdbxt_police);
		//三目运算符
		return result?"success":"error";
	}

	@Override
	public String updatePolice(ajdbxt_police ajdbxt_police) {
		// TODO Auto-generated method stub
		ajdbxt_police.setPoliceGmtModify(TeamUtil.getStringSecond());
		ajdbxt_police.setPolicePassword(md5.GetMD5Code(ajdbxt_police.getPolicePassword()));
		boolean result = userDao.updatePolice(ajdbxt_police);
		return result?"success":"error";
	}

/*	@Override
	public List<Ajdbxt_police> findPoliceByPoliceDepartment(String policeDepartment) {
		// TODO Auto-generated method stub
		List<Ajdbxt_police> policeofdepartment  = userDao.findPoliceByPoliceDepartment(policeDepartment);
		return policeofdepartment;
	}*/

/*	@Override
	public List<Ajdbxt_police> findAllPolice() {
		List<Ajdbxt_police> findallpolice = userDao.findAllPolice();
		return findallpolice;
	}*/

	@Override
	public findPoliceByPageVO queryForPage(int pageSize, int currentPage) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Ajdbxt_police";
		int count = userDao.getCount(hql); // 总记录数
		int totalPage = findPoliceByPageVO.countTotalPage(pageSize, count); // 总页数
		int offset = findPoliceByPageVO.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数
		int currentpage = findPoliceByPageVO.countCurrentPage(currentPage);
		List<ajdbxt_police> list = userDao.queryForPage("from Ajdbxt_police", offset, length); // 该分页的记录
		// 把分页信息保存到Bean中
		findPoliceByPageVO findPoliceByPageVO = new findPoliceByPageVO();
		findPoliceByPageVO.setPageSize(pageSize);
		findPoliceByPageVO.setCurrentPage(currentpage);
		findPoliceByPageVO.setAllRow(count);
		findPoliceByPageVO.setTotalPage(totalPage);
		findPoliceByPageVO.setList(list);
		findPoliceByPageVO.init();
		return findPoliceByPageVO;
	}

	@Override
	public findPoliceByPageVO queryForPageByDepartment(int pageSize, int currentPage, String department) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Ajdbxt_police where policeDepartment ='"+department+"'";
		int count = userDao.getCount(hql); // 总记录数
		int totalPage = findPoliceByPageVO.countTotalPage(pageSize, count); // 总页数
		int offset = findPoliceByPageVO.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数
		int currentpage = findPoliceByPageVO.countCurrentPage(currentPage);
		List<ajdbxt_police> list = userDao.queryForPageByDepartment("from Ajdbxt_police where policeDepartment ='"+department+"'", offset, length); // 该分页的记录
		// 把分页信息保存到Bean中
		findPoliceByPageVO findPoliceByPageVO = new findPoliceByPageVO();
		findPoliceByPageVO.setPageSize(pageSize);
		findPoliceByPageVO.setCurrentPage(currentpage);
		findPoliceByPageVO.setAllRow(count);
		findPoliceByPageVO.setTotalPage(totalPage);
		findPoliceByPageVO.setList(list);
		findPoliceByPageVO.init();
		return findPoliceByPageVO;
	}

	@Override
	public String changePassword(String ajdbxtPoliceId,String newPassword) {
		// TODO Auto-generated method stub
		//进行md5加密
		String result = userDao.changePassword(ajdbxtPoliceId,md5.GetMD5Code(newPassword));
		return result;
	}




}
