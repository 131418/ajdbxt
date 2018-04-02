package com.ajdbxt.dao.Total;

import java.util.List;

import com.ajdbxt.domain.DO.Ajdbxt_info;

public interface TotalDao {

	List<Ajdbxt_info> listAllInfo(String hql, int offset, int length);

	int getCount(String hql);

	List<Ajdbxt_info> listInfoBySearch(String hql, int offset, int length);

}
