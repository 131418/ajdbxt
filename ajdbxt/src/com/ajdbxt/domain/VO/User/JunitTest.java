package com.ajdbxt.domain.VO.User;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ajdbxt.domain.DO.Ajdbxt_police;
import com.ajdbxt.service.User.UserService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;

import util.md5;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class JunitTest {
	@Resource
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

/*	@Test   这是一个复制当前用户表然后重新生成新的含有id和时间的用户表的方法
	public void Test_excelImport() {
		List<Ajdbxt_police> list = userService.findAllPolice();
		for (Ajdbxt_police P : list) {
			userService.addPolice(P);
		}
	}*/

	@Test
	public void Test_addPolice() {
		// ajdbxtPoliceId,policeSerialNumber,policePassword,policeName,policeDepartment,policeDuty,policePhoneNumber,policeGmtCreat,policeGmtModify
		Ajdbxt_police ajdbxt_police = new Ajdbxt_police(null, "999999", "999999", "ss辉", "sss", "副局长", "18870581880",
				null, null,"1");
		System.out.println(userService.addPolice(ajdbxt_police));
		//System.out.println("000000000000"+ajdbxt_police+"0000000000000000000");
	}

	@Test
	public void Test_deletePolice() {
		Ajdbxt_police ajdbxt_police = new Ajdbxt_police("7066a0c6-3615-469c-92ec-2cac763e23ad");
		System.out.println(userService.deletePolice(ajdbxt_police));
	}

	@Test
	public void Test_updatePolice() {
		Ajdbxt_police ajdbxt_police = new Ajdbxt_police("0b3bb7c7-d682-4011-823d-31a470b07dce", "040800", "111111",
				"易志伟", "法制大队", "大队长", "2", "18870581880", null, null);
		System.out.println(userService.updatePolice(ajdbxt_police));
	}
/*@Test
	public void Test_findAllPolice() {
		List<Ajdbxt_police> findallpolice = userService.findAllPolice();
		//将插叙那结果转jsOn
		System.out.println(new Gson().toJson(findallpolice));
	}*/
	@Test 
	public void Test_blurSearch() {
		Ajdbxt_police ajdbxt_police = new Ajdbxt_police("040914");
		List<Ajdbxt_police> blursearch = userService.blurSearch(ajdbxt_police);
		System.out.println(new Gson().toJson(blursearch));
		System.out.println("000000000000"+ajdbxt_police+"0000000000000000000");
	}
	@Test
	public void Test_queryForPage() {
		findPoliceByPageVO findPoliceByPageVO = new findPoliceByPageVO();
		/*List<Ajdbxt_police> currpage = userService.queryForPage(10, page);
		System.out.println(new Gson().toJson(currpage));*/
	}
	@Test
	public void Test_login() {
		Object loginPolice = userService.login("040827","11111");
		System.out.println(loginPolice);
		String result = null;
		if (loginPolice instanceof String) {
			result = "error";
		} else {
			result = "success";
		}
		System.out.println(result);
	}

}
