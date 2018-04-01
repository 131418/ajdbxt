package com.ajdbxt.action.User;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import com.ajdbxt.domain.DO.Ajdbxt_police;
import com.ajdbxt.domain.VO.User.findPoliceByPageVO;
import com.ajdbxt.service.User.UserService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	private UserService userService;

	private Ajdbxt_police ajdbxt_police;// 前端传来 的信息封装到类里

	private findPoliceByPageVO findPoliceByPage;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String indexPage() {
		Ajdbxt_police loginPolice = (Ajdbxt_police) ActionContext.getContext().getSession().get("loginPolice");
		if (null == loginPolice) {
			return "login";//回到登录界面
		}
		return "index";
	}

	public void login() {
		try {
			// 获得返回的判断结果
			Object loginPolice = userService.login(ajdbxt_police.getPoliceSerialNumber(),ajdbxt_police.getPolicePassword());
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
		return "login";//回到登录界面
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
		Ajdbxt_police loginPolice = (Ajdbxt_police) ActionContext.getContext().getSession().get("loginPolice");
		String result = userService.changePassword(loginPolice.getAjdbxtPoliceId(),
				ajdbxt_police.getPolicePassword());
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
	 * 删除
	 * 
	 * @return success 删除成功
	 * @return error 删除失败
	 */
	public void deletePolice() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			String result = userService.deletePolice(ajdbxt_police);
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

	
	public String index() {
		return "index";
	}

	public String navbar() {
		return "navbar";
	}
	
	/*
	 * // 列出警员表 public void findAllPolice() { try { HttpServletResponse response =
	 * ServletActionContext.getResponse();
	 * response.setContentType("text/html;charset=utf-8"); List<Ajdbxt_police>
	 * findallpolice = userService.findAllPolice(); //将list对象进行json转换
	 * response.getWriter().write(new Gson().toJson(findallpolice)); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 */

	/*
	 * // 按部门查询警员信息 public void findPoliceBypoliceDepartment() { try {
	 * HttpServletResponse response = ServletActionContext.getResponse();
	 * response.setContentType("text/html;charset=utf-8"); Ajdbxt_police
	 * ajdbxt_police = (Ajdbxt_police)
	 * ActionContext.getContext().getSession().get("loginPolice");
	 * List<Ajdbxt_police> policeofdepartment = userService
	 * .findPoliceByPoliceDepartment(ajdbxt_police.getPoliceDepartment());
	 * response.getWriter().write(new Gson().toJson(policeofdepartment)); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 */

	// 分页查询
	public String queryForPage() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		this.findPoliceByPageVO = userService.queryForPage(10, currentPage);
		return "findPoliceByPageVO";
	}

	// 分页查询部门人员
	public String queryForPageByDepartment() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		Ajdbxt_police loginPolice = (Ajdbxt_police) ActionContext.getContext().getSession().get("loginPolice");
		String department = loginPolice.getPoliceDepartment();
		this.findPoliceByPageVO = userService.queryForPageByDepartment(10, currentPage, department);
		return "findPoliceByPageVO";
	}

	// Ajdbxt_police的getter\set方法
	public Ajdbxt_police getAjdbxt_police() {
		return ajdbxt_police;
	}

	public void setAjdbxt_police(Ajdbxt_police ajdbxt_police) {
		this.ajdbxt_police = ajdbxt_police;
	}

	/******************** 分页 ***********************/
	private int currentPage;
	private findPoliceByPageVO findPoliceByPageVO;

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

}
