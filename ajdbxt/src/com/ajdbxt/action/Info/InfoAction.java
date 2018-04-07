package com.ajdbxt.action.Info;

import java.io.IOException;
import org.apache.struts2.ServletActionContext;
import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;
import com.ajdbxt.service.Info.InfoService;
import com.opensymphony.xwork2.ActionSupport;

public class InfoAction extends ActionSupport {
	private ajdbxt_info info;
	private InfoService infoService;
	private Page_list_caseInfoVo page_list_caseInfoVo;
	
	public String page_CaseInfo() {
		return "page_CaseInfo";
	}
	
	public ajdbxt_info geInfo() {
		return info;
	}
	public void setInfo(ajdbxt_info caseInfo) {
		this.info = caseInfo;
	}

	public InfoService geInfoService() {
		return infoService;
	}

	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}

	public Page_list_caseInfoVo getPage_list_caseInfoVo() {
		return page_list_caseInfoVo;
	}

	public void setPage_list_caseInfoVo(Page_list_caseInfoVo page_list_caseInfoVo) {
		this.page_list_caseInfoVo = page_list_caseInfoVo;
	}

	public void save() {
		String json="";
		if(info.getInfo_assistant_police_one()==null&&info.getInfo_assistant_police_one().isEmpty()) {
			json=infoService.saveCase(info);
			try {
				ServletActionContext.getResponse().getWriter().print(json);
			} catch (IOException e) {
				new RuntimeException(e);
			}
		}else if(info.getInfo_assistant_police_two()==null&&info.getInfo_assistant_police_two().isEmpty()){
			json=infoService.twoceRank(info);
			try {
				ServletActionContext.getResponse().getWriter().print(json);
			} catch (IOException e) {
				new RuntimeException(e);
			}
			
		}else {
			infoService.save(info);
		}
	}
	/**
	 * 得到全部案件信息链表
	 * @return
	 */
	public String listAll() {
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(infoService.getAllCase());
		} catch (IOException e) {
			new RuntimeException(e);
		}
		return null;
	}
	/**
	 * 录入案件信息
	 * @return
	 */
	public String add() {
		
		return null;
	}


}
