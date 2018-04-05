package com.ajdbxt.dao.Total;

import java.util.List;

import com.ajdbxt.domain.DO.ajdbxt_info;

public interface TotalDao {

	List<ajdbxt_info> listAllInfo(String hql, int offset, int length);

	int getCount(String hql);

	List<ajdbxt_info> listInfoBySearch(String hql, int offset, int length);

}
