package com.ajdbxt.domain.VO.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ajdbxt.domain.VO.User.policedptVO;
import com.ajdbxt.action.User.UserAction;
import com.ajdbxt.domain.DO.ajdbxt_department;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.service.User.UserService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class JunitTest {
	@Resource
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Test
	public void Test_queryForPage() {
		findPoliceByPageVO findPoliceByPageVO = userService.queryForPage(10, 1,"张");
		ajdbxt_police aj0 =null;
		policedptVO aj =null;
		for(Object ob : findPoliceByPageVO.getList()) {
			aj=(policedptVO) ob;
			aj0 = aj.getAjdbxt_police();
			aj0.setPolice_name(aj0.getPolice_name().replaceAll("张", "<span style='color:red;'>张</span>"));
		}
		String  redWord = new Gson().toJson(findPoliceByPageVO);
		//把搜索关键字转换成红色
		System.out.println(redWord);
	}

	@Test
	public void Test_login() {
		Object loginPolice = userService.login("040827", "111111");
		System.out.println(loginPolice);
		String result = null;
		if (loginPolice !=null) {
			result = "success";
		} else {
			result = "error";
		}
		System.out.println(result);
	}

	@Test
	public void Test_queryForPageByDepartment() {
		findPoliceByPageVO currentpage = userService.queryForPageByDepartment(10, 1, "法制大队");
		System.out.println(new Gson().toJson(currentpage));
	}
	@Test
	public void Test_changePassword() {
		String result = userService.changePassword("1173da54-bd49-4e07-a037-fb0a6065ad1e", "222222");
		System.out.println(result);
	}
	@Test
	public void Test_batchDelete() {
		String[] ids = {""}; 
		String result = userService.batchDelete(ids);
		System.out.println(result);
	}
	@Test
	public void Test_addDept() {
		ajdbxt_department ajdbxt_department = new ajdbxt_department(null, "公安局", null, null);
		System.out.println(userService.addDepartment(ajdbxt_department));
	}
	@Test
	public void Test_showdept() {
		findDepartmentByPageVO findDepartmentByPageVO = userService.findDepartmentByPage(10, 1);
		System.out.println(new Gson().toJson(findDepartmentByPageVO));
	}
	@Test
	public void count(){
		int totalCount=9;
		System.out.println("jsdlsdfasdfj");
		int pageSize=10;
		System.out.println((totalCount-1)/pageSize + 1);
		System.out.println("jdsl;kafjl;idsaj");
	}

}
