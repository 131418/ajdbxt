package com.ajdbxt.service.impl.User;

/**
 * class  UserService
 * @date 2018/3/29
 * @date 2018/3/31
 * @author XJC
 */
import java.util.List;

import com.ajdbxt.dao.User.UserDao;
import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.VO.User.findDepartmentByPageVO;
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
	public Object login(String police_serial_number, String police_password) {

		// 用传过来的用户名密码查询，将得到的结果放到ajdbxt_user
		ajdbxt_police ajdbxt_police = userDao.findPolice(police_serial_number);
		// 比对是否存在该用户，如果不存在则ajdbxt_user为空
		if (null == ajdbxt_police) {
			return null;
		} else {
			// 将穿过来的密码进行加密，与ajdbxt_user里面的密码进行比对
			if (!md5.GetMD5Code(police_password).equals(ajdbxt_police.getPolice_password())) {
				return null;
			} else {
				return ajdbxt_police;
			}
		}
	}

	@Override
	public ajdbxt_police getUserById(String police_serial_number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addPolice(ajdbxt_police ajdbxt_police) {

		ajdbxt_police ajdbxt_police_number = userDao
				.findPoliceByPoliceSerialNumber(ajdbxt_police.getPolice_serial_number());
		// 判断被添加用户是否已存在，不存在则执行
		if (null == ajdbxt_police_number) {
			ajdbxt_police.setAjdbxt_police_id(TeamUtil.getUuid());
			ajdbxt_police.setPolice_gmt_create(TeamUtil.getStringSecond());
			ajdbxt_police.setPolice_gmt_modify(TeamUtil.getStringSecond());
			ajdbxt_police.setPolice_password(md5.GetMD5Code(ajdbxt_police.getPolice_password()));;
			// 返回保存结果
			boolean result = userDao.addPolice(ajdbxt_police);
			if (result) {
				return "success";
			}
			return "error";
		}
		return "failed";

	}

	@Override
	public String updatePolice(ajdbxt_police ajdbxt_police) {
		// TODO Auto-generated method stub
		ajdbxt_police.setPolice_gmt_modify(TeamUtil.getStringSecond());
		//ajdbxt_police.setPolice_password(md5.GetMD5Code(ajdbxt_police.getPolice_password()));
		boolean result = userDao.updatePolice(ajdbxt_police);
		return result ? "success" : "error";
	}

	@Override
	public findPoliceByPageVO queryForPage(int pageSize, int currentPage,String police_name) {
		// TODO Auto-generated method stub
		String hql_count= "select count(*) from ajdbxt_police";
		int count = userDao.getCount(hql_count); // 总记录数
		int totalPage = findPoliceByPageVO.countTotalPage(pageSize, count); // 总页数
		int offset = findPoliceByPageVO.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数
		int currentpage = findPoliceByPageVO.countCurrentPage(currentPage);
		String hql;
		//String  hql="from ajdbxt_police order by police_gmt_modify desc";
		if(police_name!=null||!"".equals(police_name)) {
			hql="from ajdbxt_police where police_name like '%"+police_name+"%' order by police_gmt_modify desc";
			List<ajdbxt_police> list = userDao.queryForPage(hql, offset, length);
		}
		hql="from ajdbxt_police order by police_gmt_modify desc";
		List<ajdbxt_police> list = userDao.queryForPage(hql, offset, length); // 该分页的记录
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
		String hql = "select count(*) from ajdbxt_police where police_department ='" + department + "'";
		int count = userDao.getCount(hql); // 总记录数
		int totalPage = findPoliceByPageVO.countTotalPage(pageSize, count); // 总页数
		int offset = findPoliceByPageVO.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数
		int currentpage = findPoliceByPageVO.countCurrentPage(currentPage);
		List<ajdbxt_police> list = userDao.queryForPageByDepartment(
				"from ajdbxt_police where police_department ='" + department + "'", offset, length); // 该分页的记录
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
	public String changePassword(String ajdbxtPoliceId, String newPassword) {
		// TODO Auto-generated method stub
		// 进行md5加密
		String result = userDao.changePassword(ajdbxtPoliceId, md5.GetMD5Code(newPassword));
		return result;
	}

	@Override
	public String batchDelete(String[] ids) {
		// TODO Auto-generated method stub
		if(ids==null||ids.length==0) {
			return null;
		}
		String result = userDao.batchDelete(ids);
		return result;
	}


	@Override
	public findDepartmentByPageVO findDepartmentByPage(int pageSize, int currentPage) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from ajdbxt_department";
		int count = userDao.getCount(hql); // 总记录数
		int totalPage = findDepartmentByPageVO.countTotalPage(pageSize, count); // 总页数
		int offset = findDepartmentByPageVO.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数
		int currentpage = findDepartmentByPageVO.countCurrentPage(currentPage);
		List<ajdbxt_department> list = userDao.findDepartmentByPage("from ajdbxt_department order by department_gmt_modify desc", offset, length); // 该分页的记录
		// 把分页信息保存到Bean中
		findDepartmentByPageVO findDepartmentByPageVO = new findDepartmentByPageVO();
		findDepartmentByPageVO.setPageSize(pageSize);
		findDepartmentByPageVO.setCurrentPage(currentpage);
		findDepartmentByPageVO.setAllRow(count);
		findDepartmentByPageVO.setTotalPage(totalPage);
		findDepartmentByPageVO.setList(list);
		findDepartmentByPageVO.init();
		return findDepartmentByPageVO;
	}

	@Override
	public String addDepartment(ajdbxt_department ajdbxt_department) {
		// TODO Auto-generated method stub
		ajdbxt_department.setAjdbxt_department_id(TeamUtil.getUuid());
		ajdbxt_department.setDepartment_gmt_create(TeamUtil.getStringSecond());
		ajdbxt_department.setDepartment_gmt_modify(TeamUtil.getStringSecond());
		String result = userDao.addDepartment(ajdbxt_department);
		return result;
	}

/*	@Override
	public findPoliceByPageVO fuzzySearch(int pageSize, int currentPage,String police_name) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from ajdbxt_police where police_name like '%"+police_name+"%'";
		int count = userDao.getCount(hql); // 总记录数
		int totalPage = findPoliceByPageVO.countTotalPage(pageSize, count); // 总页数
		int offset = findPoliceByPageVO.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数
		int currentpage = findPoliceByPageVO.countCurrentPage(currentPage);
		List<ajdbxt_police> list = userDao.fuzzySearch("from ajdbxt_police  where police_name like '%"+police_name+"%' order by police_gmt_modify desc", offset, length); // 该分页的记录
		// 把分页信息保存到Bean中
		findPoliceByPageVO findPoliceByPageVO = new findPoliceByPageVO();
		findPoliceByPageVO.setPageSize(pageSize);
		findPoliceByPageVO.setCurrentPage(currentpage);
		findPoliceByPageVO.setAllRow(count);
		findPoliceByPageVO.setTotalPage(totalPage);
		findPoliceByPageVO.setList(list);
		findPoliceByPageVO.init();
		return findPoliceByPageVO;
	}*/

}
