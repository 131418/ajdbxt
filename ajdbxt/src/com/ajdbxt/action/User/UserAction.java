package com.ajdbxt.action.User;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.VO.User.findDepartmentByPageVO;
import com.ajdbxt.domain.VO.User.findPoliceByPageVO;
import com.ajdbxt.domain.VO.User.policedptVO;
import com.ajdbxt.service.User.UserService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport {

	private UserService userService;

	private ajdbxt_police ajdbxt_police;// 前端传来 的信息封装到类里
	
	public ajdbxt_department ajdbxt_department;

	private int currentPage;
	
	private findPoliceByPageVO findPoliceByPageVO;
	
	private findDepartmentByPageVO findDepartmentByPageVO;
	
	private String ids[];
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public findPoliceByPageVO getFindPoliceByPageVO() {
		return findPoliceByPageVO;
	}

	public void setFindPoliceByPageVO(findPoliceByPageVO findPoliceByPageVO) {
		this.findPoliceByPageVO = findPoliceByPageVO;
	}

	// Ajdbxt_police的getter\set方法
	public ajdbxt_police getAjdbxt_police() {
		System.out.println(211);
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


	public String[] getIds() {
		return ids;
	}


	public void setIds(String[] ids) {
		this.ids = ids;
	}

	

	public String indexPage() {
		ajdbxt_police loginPolice = (ajdbxt_police) ActionContext.getContext().getSession().get("loginPolice");
		if (null == loginPolice) {
			return "login";//回到登录界面
		}
		return "index";
	}
	public String Nav() {
		return "Nav";
	}
	public String userPage() {
		return "userpage";
	}
	public String index() {
		return "index";
	}
	public String navbar() {
		return "navbar";
	}

	public void login() {
		try {
			// 获得返回的判断结果
			Object loginPolice = userService.login(ajdbxt_police.getPolice_serial_number(),ajdbxt_police.getPolice_password());
			String result = null;
			if (loginPolice != null) {
				// 将登陆用户的所有信息放入session
				ActionContext.getContext().getSession().put("loginPolice", loginPolice);
				// Ajdbxt_police loginPolice_online = (Ajdbxt_police) loginPolice;

				result = "success";// 登录成功
			} else {
				result = "error";// 用户名或密码错误

			}
			/*
			 * 张斌说，一定要写这一行代码，不论有没有中文
			 */
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 登出
	public String loginout() {
		ActionContext.getContext().getSession().remove("loginPolice");
		//ActionContext.getContext().getSession().clear();
		return "login";//回到登录界面
	}
	//获取权限
	public void getPower() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			policedptVO loginPolice = (policedptVO) ActionContext.getContext().getSession().get("loginPolice");
			response.getWriter().write(new Gson().toJson(loginPolice));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 更改密码
	 * 
	 * @return success 更改成功
	 * @return failed 更改失败
	 */
	public void changePassword() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		ajdbxt_police loginPolice = (ajdbxt_police) ActionContext.getContext().getSession().get("loginPolice");
		String result = userService.changePassword(loginPolice.getAjdbxt_police_id(),ajdbxt_police.getPolice_password());
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加操作
	 * 
	 * @return success 添加成功
	 * @return failed 用户已存在
	 * 		error 添加失败
	 */
	public void addPolice() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			String result = userService.addPolice(ajdbxt_police);
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 修改
	 * 
	 * @return success 修改成功
	 * @return error 修改失败
	 */
	public void updatePolice() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			String result = userService.updatePolice(ajdbxt_police);
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 分页查询
	public void queryForPage() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			String policeName = this.findPoliceByPageVO.getPolice_name();
			findPoliceByPageVO queryForPage = userService.queryForPage(10, this.findPoliceByPageVO.getCurrentPage(),policeName);
			if(policeName!=null&&!"".equals(policeName)) {
				ajdbxt_police aj0 =null;
				policedptVO aj =null;
				for(Object ob : queryForPage.getList()) {
					aj=(policedptVO) ob;
					aj0 = aj.getAjdbxt_police();
					aj0.setPolice_name(aj0.getPolice_name().replaceAll(policeName, "<span style='color:red;'>"+policeName+"</span>"));
				}
			}
			String  redWord = new Gson().toJson(queryForPage);
			//把搜索关键字转换成红色
			response.getWriter().write(redWord);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 分页查询部门人员
	public void queryForPageByDepartment() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			ajdbxt_police loginPolice = (ajdbxt_police) ActionContext.getContext().getSession().get("loginPolice");
			String department = loginPolice.getPolice_department();
			String policeName = this.findPoliceByPageVO.getPolice_name();
			findPoliceByPageVO findByDpt = userService.queryForPageByDepartment(10, currentPage, department, policeName);
			if(policeName!=null&&!"".equals(policeName)) {
				ajdbxt_police aj0 =null;
				policedptVO aj =null;
				for(Object ob : findByDpt.getList()) {
					aj=(policedptVO) ob;
					aj0 = aj.getAjdbxt_police();
					aj0.setPolice_name(aj0.getPolice_name().replaceAll(policeName, "<span style='color:red;'>"+policeName+"</span>"));
				}
			}
			String  redWord = new Gson().toJson(findByDpt);
			//把搜索关键字转换成红色
			response.getWriter().write(redWord);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 批量删除
	 * 
	 * @return success 删除成功
	 * @return failed 删除失败
	 * null 数组为空
	 */
	public void batchDelete() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			String result = userService.batchDelete(ids);
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获取部门表
	public void findDepartmentByPage() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			this.findDepartmentByPageVO = userService.findDepartmentByPage(99999999,currentPage);
			response.getWriter().write(new Gson().toJson(this.findDepartmentByPageVO));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//增加部门
	public void addDepartment() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			String result = userService.addDepartment(ajdbxt_department);
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//根据id查询单挑记录
	public void findPoliceById() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			policedptVO policeOne = userService.findPoliceById(ajdbxt_police.getAjdbxt_police_id());
			response.getWriter().write(new Gson().toJson(policeOne));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//通过id查找部门
	public void findDptByid() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			ajdbxt_police loginPolice = (ajdbxt_police) ActionContext.getContext().getSession().get("loginPolice");
			String department = loginPolice.getPolice_department();
			String dpt = userService.findDptByid(department);
			response.getWriter().write(dpt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
