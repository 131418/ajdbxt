package com.ajdbxt.domain.VO.User;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ajdbxt.service.Total.StatisticService;
import com.ajdbxt.service.User.UserService;
import com.google.gson.Gson;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class JunitTest {
	@Resource
	private UserService userService;
@Resource
	private StatisticService statisticService;

//	
//	/*
//	 * @Test 这是一个复制当前用户表然后重新生成新的含有id和时间的用户表的方法 public void Test_excelImport() {
//	 * List<Ajdbxt_police> list = userService.findAllPolice(); for (Ajdbxt_police P
//	 * : list) { userService.addPolice(P); } }
//	 */
//
//
//
//	public StatisticService getStatisticService() {
//		return statisticService;
//	}
//
//	public void setStatisticService(StatisticService statisticService) {
//		this.statisticService = statisticService;
//	}
//
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}
//
//	@Test
//	public void Test_addPolice() {
//		// ajdbxtPoliceId,policeSerialNumber,policePassword,policeName,policeDepartment,policeDuty,policePhoneNumber,policeGmtCreat,policeGmtModify
////		ajdbxt_police ajdbxt_police = new ajdbxt_police(null, "999999", "999999", "ss辉", "sss", "副局长", "18870581880",
////				null, null, "1");
//		//System.out.println(userService.addPolice(ajdbxt_police));
//		// System.out.println("000000000000"+ajdbxt_police+"0000000000000000000");
//		System.out.println("aa");
//	}
//
//	@Test
//	public void Test_updatePolice() {
//		System.out.println();
//		ajdbxt_police ajdbxt_police = new ajdbxt_police("c87bc848-4345-42e0-b64c-d388d7555802", "040800", "111111",
//				"易志伟", "法制大队", "xiao队长", "2", "18870581880", null, null);
//		System.out.println(userService.updatePolice(ajdbxt_police));
//	}
//
//	/*
//	 * @Test public void Test_findAllPolice() { List<Ajdbxt_police> findallpolice =
//	 * userService.findAllPolice(); //将插叙那结果转jsOn System.out.println(new
//	 * Gson().toJson(findallpolice)); }
//	 */
//	@Test
//	public void Test_queryForPage() {
//		findPoliceByPageVO findPoliceByPageVO = userService.queryForPage(10, 1,"");
//		String  redWord = new Gson().toJson(findPoliceByPageVO);
//		//把搜索关键字转换成红色
//		System.out.println(redWord);
//		//System.out.println(redWord.replaceAll("张", "<span style='color:red;'>张</span>"));
//	}
//
//	@Test
//	public void Test_login() {
//		Object loginPolice = userService.login("040827", "111111");
//		System.out.println(loginPolice);
//		String result = null;
//		if (loginPolice !=null) {
//			result = "success";
//		} else {
//			result = "error";
//		}
//		System.out.println(result);
//	}
//
//	@Test
//	public void Test_queryForPageByDepartment() {
//		findPoliceByPageVO currentpage = userService.queryForPageByDepartment(10, 1, "法制大队");
//		System.out.println(new Gson().toJson(currentpage));
//	}
//	@Test
//	public void Test_changePassword() {
//		String result = userService.changePassword("1173da54-bd49-4e07-a037-fb0a6065ad1e", "222222");
//		System.out.println(result);
//	}
//	@Test
//	public void Test_batchDelete() {
//		String[] ids = {""}; 
//		String result = userService.batchDelete(ids);
//		System.out.println(result);
//	}
//	@Test
//	public void Test_addDept() {
//		ajdbxt_department ajdbxt_department = new ajdbxt_department(null, "白源派出所", null, null);
//		System.out.println(userService.addDepartment(ajdbxt_department));
//	}
//	@Test
//	public void Test_showdept() {
//		findDepartmentByPageVO findDepartmentByPageVO = userService.findDepartmentByPage(10, 1);
//		System.out.println(new Gson().toJson(findDepartmentByPageVO));
//	}
@Test
public void e() {
	System.out.println("a");
}
}
