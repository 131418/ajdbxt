package com.ajdbxt.domain.VO.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

	/*
	 * @Test 这是一个复制当前用户表然后重新生成新的含有id和时间的用户表的方法 public void Test_excelImport() {
	 * List<Ajdbxt_police> list = userService.findAllPolice(); for (Ajdbxt_police P
	 * : list) { userService.addPolice(P); } }
	 */

	// @Test
	// public void Test_addPolice() {
	// //
	// ajdbxtPoliceId,policeSerialNumber,policePassword,policeName,policeDepartment,policeDuty,policePhoneNumber,policeGmtCreat,policeGmtModify
	// ajdbxt_police ajdbxt_police = new ajdbxt_police(null, "999999", "999999",
	// "ss辉", "sss", "副局长", "18870581880",
	// null, null, "1");
	// System.out.println(userService.addPolice(ajdbxt_police));
	// // System.out.println("000000000000"+ajdbxt_police+"0000000000000000000");
	// }
	//
	// @Test
	// public void Test_updatePolice() {
	// ajdbxt_police ajdbxt_police = new
	// ajdbxt_police("c87bc848-4345-42e0-b64c-d388d7555802", "040800", "111111",
	// "易志伟", "法制大队", "xiao队长", "2", "18870581880", null, null);
	// System.out.println(userService.updatePolice(ajdbxt_police));
	// }

	/*
	 * @Test public void Test_findAllPolice() { List<Ajdbxt_police> findallpolice =
	 * userService.findAllPolice(); //将插叙那结果转jsOn System.out.println(new
	 * Gson().toJson(findallpolice)); }
	 */
	@Test
	public void Test_queryForPage() {
		findPoliceByPageVO findPoliceByPageVO = userService.queryForPage(10, 1, "");
		ajdbxt_police aj0 = null;
		policedptVO aj = null;
		for (Object ob : findPoliceByPageVO.getList()) {
			aj = (policedptVO) ob;
			aj0 = aj.getAjdbxt_police();
			aj0.setPolice_name(aj0.getPolice_name().replaceAll("", "<span style='color:red;'>1</span>"));
		}

		String redWord = new Gson().toJson(findPoliceByPageVO);
		// 把搜索关键字转换成红色
		// System.out.println(redWord.replaceAll("张", "<span
		// style='color:red;'>张</span>"));
	}

	@Test
	public void Test_login() {
		Object loginPolice = userService.login("040827", "111111");
		System.out.println(loginPolice);
		String result = null;
		if (loginPolice != null) {
			result = "success";
		} else {
			result = "error";
		}
		System.out.println(result);
	}

	@Test
	public void Test_queryForPageByDepartment() {
		findPoliceByPageVO currentpage = userService.queryForPageByDepartment(10, 1, "67ed5ab3-d773-4ac1-981b-2839ed0cec5c");
		System.out.println(new Gson().toJson(currentpage));
	}

	@Test
	public void Test_changePassword() {
		String result = userService.changePassword("1173da54-bd49-4e07-a037-fb0a6065ad1e", "222222");
		System.out.println(result);
	}

	@Test
	public void Test_batchDelete() {
		String[] ids = { "" };
		String result = userService.batchDelete(ids);
		System.out.println(result);
	}

	@Test
	public void Test_addDept() {
		ajdbxt_department ajdbxt_department = new ajdbxt_department(null, "测试组", null, null);
		System.out.println(userService.addDepartment(ajdbxt_department));
	}

	@Test
	public void Test_showdept() {
		findDepartmentByPageVO findDepartmentByPageVO = userService.findDepartmentByPage(10, 1);
		System.out.println(new Gson().toJson(findDepartmentByPageVO));
	}

	@Test
	public void count() {
		int totalCount = 0;
		int pageSize = 10;
		System.out.println((totalCount - 1) / pageSize + 1);
	}
	@Test
	public void testFindPoliceById() {
		policedptVO policeOne = userService.findPoliceById("129fb032-7286-48c7-8b7d-1bf710c9b598");
		System.out.println(new Gson().toJson(policeOne));
	}
	@Test
	public void addPolice() {
		// ajdbxtPoliceId,policeSerialNumber,policePassword,policeName,policeDepartment,policeDuty,policePhoneNumber,policeGmtCreat,policeGmtModify
				ajdbxt_police ajdbxt_police = new ajdbxt_police(null, "100007", "111111", "李世远", "4e0d2651-da70-4b6b-938a-451eb063d5e7", "组员", "15270634643",null, null, "2");
				System.out.println(userService.addPolice(ajdbxt_police));
				// System.out.println("000000000000"+ajdbxt_police+"0000000000000000000");
	}

}
