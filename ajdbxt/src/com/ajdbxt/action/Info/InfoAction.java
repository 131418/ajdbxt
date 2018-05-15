package com.ajdbxt.action.Info;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DO.ajdbxt_process;
import com.ajdbxt.domain.DTO.Process.ProcessDTO;
import com.ajdbxt.domain.DTO.Process.ProcessInfoDTO;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;
import com.ajdbxt.service.Info.InfoService;
import com.ajdbxt.service.Process.ProcessService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import util.JsonUtils;
import util.MsgSend;
import util.SMSThread;

public class InfoAction extends ActionSupport {
	private ajdbxt_info info;
	private InfoService infoService;
	private Page_list_caseInfoVo infoVO;
	
	public String page_CaseInfo() {
		return "page_CaseInfo";
	}
	public String page_CaseDetails() {
		return "page_CaseDetails";
	}
	public String page_mobileCaseList(){
		return "page_mobileCaseList";
	}
	public String page_mobile_caseOneDetails(){
		return "page_mobile_caseOneDetails";
	}
	public String page_mobile_caseAdd(){
		return "page_mobile_caseAdd";
	}
	public String page_mobile_caseProcess(){
		return "page_mobile_caseProcess";
	}
	public String page_mobile_caseUpdate(){
		return "page_mobile_caseUpdate";
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
	 * @param 所有的id，时间，主协办民警都是后台生成的所以不用传，你要传给我部门id
	 * 
	 * 第二次调用获取协办民警二,如果
	 * @param 把我前一次次传给你的对象添加信息后回传给我
	 * 
	 */
	public void save() {
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		noLogin();
		String json="";
			if(info.getInfo_assistant_police_one()==null||info.getInfo_assistant_police_one().isEmpty()) {
				json=infoService.saveCase(info);
			}else{
				json=infoService.twoceRank(info);
			}
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			new RuntimeException(e);
		}
	}
	//专门用来保存新案件
	public void saveCase() {
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		noLogin();
		String json="";
		json=infoService.save(info);
		try {
			ServletActionContext.getResponse().getWriter().print(json);
			ServletActionContext.getResponse().getWriter().print("success");
		} catch (IOException e) {
			new RuntimeException(e);
		}
	}
	/**
	 * infoVO 是信息列表页面的VO类页面
	 * @return
	 */
	public void listAll() {
		noLogin();
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(infoService.getAllCase(infoVO));
		} catch (IOException e) {
			new RuntimeException(e);
		}
	}
	//得到法制员和局领导和部门的方法
	public void lal() {
		noLogin();
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(infoService.getLegalsAndLeadersAndDepartment());
		} catch (IOException e) {
			new RuntimeException(e);
		}
	}
	/**
	 * 录入案件信息
	 * @return
	 */
	public void add() {
		noLogin();
	}
	
	/**
	 * 得到单个案件信息、
	 * @param info.ajxdbxt_info_id
	 */
	public void getSingleInfo() {
		ProcessDTO processDTO =infoService.getSingleInfo(info.getAjdbxt_info_id());
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		String json=JsonUtils.toJson(processDTO);
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			new RuntimeException(e);
			System.out.println(e.getMessage());
		}
	}
	/**
	 * 删除单个案件信息
	 * @param info.ajxdbxt_info_id
	 */
	public void delete() {
		infoService.deleteCase(info.getAjdbxt_info_id());
		try {
			ServletActionContext.getResponse().getWriter().print("success");
		} catch (IOException e) {
			new RuntimeException(e);
			System.out.println(e.getMessage());
		}
	}
	/**
	 * 更新案件信息
	 * @param 修改后的info的所有
	 */
	public void update() {
		ProcessDTO getProcessDTO=infoService.getSingleInfo(info.getAjdbxt_info_id());
		ajdbxt_info getInfo=getProcessDTO.getInfo();
		Class clazz=info.getClass();
		Field [] f= clazz.getDeclaredFields();
		for(Field field: f){
			field.setAccessible(true);
			try {
				Object o =field.get(info);
				if(o!=null) {//这里应该写取消指派和更改指派的逻辑
					Object old=field.get(o);
					field.set(getInfo, o);
					switch (field.getName()) {
						case "info_main_police":
							
							break;
						case "info_assistant_police_one":
							
							break;
						case "info_assistant_police_two":
							break;
					}
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		String Json=infoService.update(info);
		try {
			ServletActionContext.getResponse().getWriter().print(Json);
			ServletActionContext.getResponse().getWriter().print("success");
		} catch (IOException e) {
			new RuntimeException(e);
			System.out.println(e.getMessage());
		}
	}
	/**
	 * 得到同部门的警察
	 */
	public void getPolices() {
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(infoService.getPolices(info.getInfo_department()));
		} catch (IOException e) {
			new RuntimeException(e);
		}
	}
	private void noLogin() {
		if(ActionContext.getContext().getSession().get("loginPolice")==null) {
			try {
				ServletActionContext.getResponse().sendRedirect("/ajdbxt/login.jsp");
			} catch (IOException e) {
				new RuntimeException(e);
			}
		}
	}
}

