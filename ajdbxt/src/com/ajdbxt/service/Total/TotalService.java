package com.ajdbxt.service.Total;

import com.ajdbxt.domain.VO.Total.FindInfoByPageVO;

public interface TotalService {

	FindInfoByPageVO listAllInfo(int pageSize, int currentPage);

	FindInfoByPageVO listInfoBySearch(int pageSize, int currentPage, String infoCategory, String infoCatchTime, String infoDepartment, String infoMainPolice, String infoAssistantPoliceOne, String infoAssistantPoliceTwo);

}
