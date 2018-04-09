package com.ajdbxt.service.impl.Total;

import java.util.List;

import com.ajdbxt.dao.Total.TotalDao;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.VO.Total.FindInfoByPageVO;
import com.ajdbxt.service.Total.TotalService;

public class TotalServiceImpl implements TotalService {
	
	private TotalDao totalDao;

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public FindInfoByPageVO listAllInfo(int pageSize, int currentPage) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from ajdbxt_info";
		int count = totalDao.getCount(hql); // 总记录数
		int totalPage = FindInfoByPageVO.countTotalPage(pageSize, count); // 总页数
		int offset = FindInfoByPageVO.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数
		int currentpage = FindInfoByPageVO.countCurrentPage(currentPage);
		List<ajdbxt_info> list = totalDao.listAllInfo("from ajdbxt_info", offset, length); // 该分页的记录
		// 把分页信息保存到Bean中
		FindInfoByPageVO findInfoByPageVO = new FindInfoByPageVO();
		findInfoByPageVO.setPageSize(pageSize);
		findInfoByPageVO.setCurrentPage(currentpage);
		findInfoByPageVO.setTotalRecords(count);
		findInfoByPageVO.setTotalPage(totalPage);
		findInfoByPageVO.setList(list);
		findInfoByPageVO.init();
		return findInfoByPageVO;
	}

	@Override
	public FindInfoByPageVO listInfoBySearch(int pageSize, int currentPage, String info_category, String info_catch_time, String info_department, String info_main_police, String info_assistant_police_one, String info_assistant_police_two) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from ajdbxt_info";
		int count = totalDao.getCount(hql); // 总记录数
		int totalPage = FindInfoByPageVO.countTotalPage(pageSize, count); // 总页数
		int offset = FindInfoByPageVO.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数
		int currentpage = FindInfoByPageVO.countCurrentPage(currentPage);
		List<ajdbxt_info> list = totalDao.listInfoBySearch("from ajdbxt_info where info_category like '"+info_category+"' or info_catch_time = '"+info_catch_time+"' or info_department = '"+info_department+"' or info_main_police like '"+info_main_police+"' or info_assistant_police_one like '"+info_assistant_police_one+"' or info_assistant_police_two like '"+info_assistant_police_two+"'", offset, length); // 该分页的记录
		// 把分页信息保存到Bean中
		FindInfoByPageVO findInfoByPageVO = new FindInfoByPageVO();
		findInfoByPageVO.setPageSize(pageSize);
		findInfoByPageVO.setCurrentPage(currentpage);
		findInfoByPageVO.setTotalRecords(count);
		findInfoByPageVO.setTotalPage(totalPage);
		findInfoByPageVO.setList(list);
		findInfoByPageVO.init();
		return findInfoByPageVO;
	}

}
