package com.ajdbxt.domain.VO.Total;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ajdbxt.service.Total.TotalService;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })

public class TestTotal {

	@Resource
	private TotalService totalService;

	public void setTotalService(TotalService totalService) {
		this.totalService = totalService;
	}
	
	@Test
	public void Test_listAll() {
		FindInfoByPageVO findInfoByPageVO = totalService.listAllInfo(10, 1);
		System.out.println(new Gson().toJson(findInfoByPageVO));
	}
	@Test
	public void Test_listBySearch() {
		FindInfoByPageVO findInfoByPageVO = totalService.listInfoBySearch(10, 1, "行政案件", "20081111", "丹江大队", "蔡威", "", "");
		System.out.println(new Gson().toJson(findInfoByPageVO));
	}

}
