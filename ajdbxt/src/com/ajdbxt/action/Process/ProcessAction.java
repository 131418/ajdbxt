package com.ajdbxt.action.Process;

import java.io.IOException;
import java.lang.reflect.Field;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.ajdbxt.domain.DO.ajdbxt_info;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.service.Process.ProcessInfoService;
import com.ajdbxt.service.Process.ProcessService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import util.JsonUtils;
import com.ajdbxt.domain.DO.ajdbxt_process;
import com.ajdbxt.domain.DTO.Process.ProcessDTO;
import com.ajdbxt.domain.VO.Info.Page_list_caseInfoVo;
import com.ajdbxt.domain.VO.Process.showProcessVO;
import util.*;

public class ProcessAction  extends ActionSupport{
	private ProcessService processService;
	private ajdbxt_process ajdbxtProcess;
	private ProcessInfoService processInfoService;
	private showProcessVO processVO;
	private Page_list_caseInfoVo infoVO;
	public Page_list_caseInfoVo getInfoVO() {
		return infoVO;
	}

	public void setInfoVO(Page_list_caseInfoVo infoVO) {
		this.infoVO = infoVO;
	}

	public showProcessVO getProcessVO() {
		return processVO;
	}

	public void setProcessVO(showProcessVO processVO) {
		this.processVO = processVO;
	}

	public ProcessService getProcessService() {
		return processService;
	}

	public void setProcessService(ProcessService processService) {
		this.processService = processService;
	}

	public ajdbxt_process getAjdbxtProcess() {
		return ajdbxtProcess;
	}

	public void setAjdbxtProcess(ajdbxt_process ajdbxtProcess) {
		this.ajdbxtProcess = ajdbxtProcess;
	}

	public ProcessInfoService getProcessInfoService() {
		return processInfoService;
	}

	public void setProcessInfoService(ProcessInfoService processInfoService) {
		this.processInfoService = processInfoService;
	}
	public String page_process(){
		noLogin();
		return "processpage";
	}
	/**
	 * 的到与该警官相关的案件信息
	 * @param ajdbxtProcess.process_case_end="false" 查未结案的
	 * @param ajdbxtProcess.process_captain_check="false" 查未审核的
	 * @param ajdbxtProcess.process_score="false" 查未评分的
	 * @param ajdbxtProcess.process_question="false" 查未整改问题的
	 */
	public void getInfo() {
		noLogin();
		Object o =ActionContext.getContext().getSession().get("loginPolice");//得到该警察
		ajdbxt_police police=(ajdbxt_police)o;
		String police_id=police.getAjdbxt_police_id();
		String json="";
		System.out.println(ajdbxtProcess);
		if(ajdbxtProcess.getProcess_case_end()!=null&&ajdbxtProcess.getProcess_case_end().equals("true")==false) {
			json=processInfoService.getInfoList(ProcessInfoService.CASE_END, police_id,infoVO);
			ajdbxtProcess.setProcess_case_end(null);
		}else if(ajdbxtProcess.getProcess_captain_check()!=null&&ajdbxtProcess.getProcess_captain_check().equals("true")==false){
			json=processInfoService.getInfoList(ProcessInfoService.CAPTAIN_CHECK, police_id,infoVO);
			ajdbxtProcess.setProcess_captain_check(null);
		}else if(ajdbxtProcess.getProcess_score()!=null&&ajdbxtProcess.getProcess_score().equals("true")==false){
			json=processInfoService.getInfoList(ProcessInfoService.PROCESS_SCORE, police_id,infoVO);
			ajdbxtProcess.setProcess_score(null);
		}else if(ajdbxtProcess.getProcess_question()!=null&&ajdbxtProcess.getProcess_question().equals("true")==false) {
			json=processInfoService.getInfoList(ProcessInfoService.PROCESS_QUESTION, police_id,infoVO);
			ajdbxtProcess.setProcess_question(null);
		}else {
			json=processInfoService.getInfoList(100, police_id,infoVO);
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			//response.getWriter().print(json);
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	/**
	 * 更新流程表
	 * @param ajdbxtProcess.*="*" 新值为：
	 * 
	 */
	public void update() {
		noLogin();
		ajdbxt_process process=(ajdbxt_process)ActionContext.getContext().getSession().get("lookedProcess");
		int send_massage_type=0;
		Class clazz=ajdbxtProcess.getClass();
		Field [] f= clazz.getDeclaredFields();
		for(Field field: f){
			field.setAccessible(true);
			try {
				Object o =field.get(ajdbxtProcess);
//				if(o!=null) {
//					field.set(process, o);
//					switch (field.getName()) {
//						case "process_case_goods":
//							send_massage_type=MsgSend.CANCEL_DISPATCH;
//							break;
//	
//						case "process_penalty":
//						case ""
//							break;
//					}
//				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		processService.update(process);
	}
	
	/**
	 * 查看流程详情
	 * @return
	 */
	public void findSingle() {
		noLogin();
		String case_id=ajdbxtProcess.getProcess_case_id();
		ajdbxt_process process=processService.getSingleProcessByCaseId(case_id);
		ActionContext.getContext().getSession().put("lookedProcess", process);
		ajdbxt_info info=processInfoService.getSingleInfo(process.getProcess_case_id());
		ActionContext.getContext().getSession().put("lookedInfo", info);
		ProcessDTO processDTO=new ProcessDTO();
		processDTO.setInfo(info);
		processDTO.setProcess(process);
		String processInfoJson=JsonUtils.toJson(processDTO);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(processInfoJson);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void findSome() {
		noLogin();
		String json="";
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			json=JsonUtils.toJson(processService.getSomeProcessByShowProcessVO(processVO));
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			new RuntimeException(e);
		}
	}
	public void findAboutMeSome() {
		Object o=ActionContext.getContext().getSession().get("loginPolice");
		ajdbxt_police police=(ajdbxt_police)o;
		String police_id=police.getAjdbxt_police_id();
		String json="";
		if(ajdbxtProcess.getProcess_case_end()!=null&&ajdbxtProcess.getProcess_case_end().equals("true")==false) {
			json=processInfoService.getProcessList(ProcessInfoService.CASE_END, police_id,processVO);
			ajdbxtProcess.setProcess_case_end(null);
		}else if(ajdbxtProcess.getProcess_captain_check()!=null&&ajdbxtProcess.getProcess_captain_check().equals("true")==false){
			json=processInfoService.getProcessList(ProcessInfoService.CAPTAIN_CHECK, police_id,processVO);
			ajdbxtProcess.setProcess_captain_check(null);
		}else if(ajdbxtProcess.getProcess_score()!=null&&ajdbxtProcess.getProcess_score().equals("true")==false){
			json=processInfoService.getProcessList(ProcessInfoService.PROCESS_SCORE, police_id,processVO);
			ajdbxtProcess.setProcess_score(null);
		}else if(ajdbxtProcess.getProcess_question()!=null&&ajdbxtProcess.getProcess_question().equals("true")==false) {
			json=processInfoService.getProcessList(ProcessInfoService.PROCESS_QUESTION, police_id,processVO);
			ajdbxtProcess.setProcess_question(null);
		}else {
			json=processInfoService.getInfoList(100, police_id,infoVO);
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 跳转到办案流程列表页
	 * @return
	 */
	public String page_list_CaseProcess() {
		noLogin();
		return "page_list_CaseProcess";
	}
	
	
	/**
	 * 跳转都办案流程详情页
	 * @return
	 */
	public String page_CaseProcessInfo() {
		noLogin();
		return "page_CaseProcessInfo";
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
