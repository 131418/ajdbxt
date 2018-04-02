package com.ajdbxt.service.impl.Total;

import java.util.List;

import com.ajdbxt.dao.Total.TotalDao;
import com.ajdbxt.domain.DO.Ajdbxt_info;
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
		String hql = "select count(*) from Ajdbxt_info";
		int count = totalDao.getCount(hql); // 总记录数
		int totalPage = FindInfoByPageVO.countTotalPage(pageSize, count); // 总页数
		int offset = FindInfoByPageVO.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数
		int currentpage = FindInfoByPageVO.countCurrentPage(currentPage);
		List<Ajdbxt_info> list = totalDao.listAllInfo("from Ajdbxt_info", offset, length); // 该分页的记录
		// 把分页信息保存到Bean中
		FindInfoByPageVO findInfoByPageVO = new FindInfoByPageVO();
		findInfoByPageVO.setPageSize(pageSize);
		findInfoByPageVO.setCurrentPage(currentpage);
		findInfoByPageVO.setAllRow(count);
		findInfoByPageVO.setTotalPage(totalPage);
		findInfoByPageVO.setList(list);
		findInfoByPageVO.init();
		return findInfoByPageVO;
	}

	@Override
	public FindInfoByPageVO listInfoBySearch(int pageSize, int currentPage, String infoCategory, String infoCatchTime, String infoDepartment, String infoMainPolice, String infoAssistantPoliceOne, String infoAssistantPoliceTwo) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Ajdbxt_info";
		int count = totalDao.getCount(hql); // 总记录数
		int totalPage = FindInfoByPageVO.countTotalPage(pageSize, count); // 总页数
		int offset = FindInfoByPageVO.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数
		int currentpage = FindInfoByPageVO.countCurrentPage(currentPage);
		List<Ajdbxt_info> list = totalDao.listInfoBySearch("from Ajdbxt_info where infoCategory like '"+infoCategory+"' or infoCatchTime = '"+infoCatchTime+"' or infoDepartment = '"+infoDepartment+"' or infoMainPolice like '"+infoMainPolice+"' or infoAssistantPoliceOne like '"+infoAssistantPoliceOne+"' or infoAssistantPoliceTwo like '"+infoAssistantPoliceTwo+"'", offset, length); // 该分页的记录
		// 把分页信息保存到Bean中
		FindInfoByPageVO findInfoByPageVO = new FindInfoByPageVO();
		findInfoByPageVO.setPageSize(pageSize);
		findInfoByPageVO.setCurrentPage(currentpage);
		findInfoByPageVO.setAllRow(count);
		findInfoByPageVO.setTotalPage(totalPage);
		findInfoByPageVO.setList(list);
		findInfoByPageVO.init();
		return findInfoByPageVO;
	}

}
