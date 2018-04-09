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
	private Page_list_caseInfoVo infoVO;
	
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

	public Page_list_caseInfoVo getInfoVO() {
		return infoVO;
	}

	public void setInfoVO(Page_list_caseInfoVo infoVO) {
		this.infoVO = infoVO;
	}

	public ajdbxt_info getInfo() {
		return info;
	}

	public InfoService getInfoService() {
		return infoService;
	}
	/**
	 * 第一次调用获取主协办民警
	 * @param 所有的id，时间，主协办民警都是后台生成的所以不用传
	 * 
	 * 第二次调用获取协办民警二
	 * @param 把我前一次次传给你的对象添加信息后回传给我
	 * 
	 * 第三次保存案件信息，开启流程
	 * @param 把我前俩次传给你的对象添加信息后回传给我
	 */
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
	 * infoVO 是信息列表页面的VO类页面
	 * @return
	 */
	public String listAll() {
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(infoService.getAllCase(infoVO));
		} catch (IOException e) {
			new RuntimeException(e);
		}
		return null;
	}
	//得到法制员和局领导的方法
	public void lal() {
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(infoService.getLegalsAndLeaders());
		} catch (IOException e) {
			new RuntimeException(e);
		}
	}
	/**
	 * 录入案件信息
	 * @return
	 */
	public String add() {
		
		return null;
	}


}
