package com.ajdbxt.action.Process;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
		if(ajdbxtProcess.getProcess_case_end()!=null&&ajdbxtProcess.getProcess_case_end().equals("true")==false) {
			json=processInfoService.getInfoList(ProcessInfoService.CASE_END, police_id,infoVO);
		}else if(ajdbxtProcess.getProcess_captain_check()!=null&&ajdbxtProcess.getProcess_captain_check().equals("true")==false){
			json=processInfoService.getInfoList(ProcessInfoService.CAPTAIN_CHECK, police_id,infoVO);
		}else if(ajdbxtProcess.getProcess_score()!=null&&ajdbxtProcess.getProcess_score().equals("true")==false){
			json=processInfoService.getInfoList(ProcessInfoService.PROCESS_SCORE, police_id,infoVO);
		}else if(ajdbxtProcess.getProcess_question()!=null&&ajdbxtProcess.getProcess_question().equals("true")==false) {
			json=processInfoService.getInfoList(ProcessInfoService.PROCESS_QUESTION, police_id,infoVO);
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
	 * @param 修改后的流程对象
	 * 
	 */
	public void update() {
		noLogin();
		ajdbxt_process process=processService.getSingleProcessByCaseId(ajdbxtProcess.getProcess_case_id()).getProcess();
		Class clazz=ajdbxtProcess.getClass();
		Field [] f= clazz.getDeclaredFields();
		List<Integer> list=new ArrayList<>();//一些特殊的改变保存在这里
		for(Field field: f){
			field.setAccessible(true);//解锁
			try {
				Object o =field.get(ajdbxtProcess);
				if(o!=null&&(o.equals("")==false)) {
					field.set(process, o);
					switch (field.getName()) {
						case "process_case_end"://案件流程结束
							list.add(ProcessService.PROCESS_FILE_HAND);
							break;
						case "process_detention"://行政拘留
							list.add(ProcessService.PROCESS_DETENTION);
							break;
						case "process_penalty"://罚款
							list.add(ProcessService.PROCESS_PENALTY);
							break;
						case  "process_treatment_category"://戒毒
							list.add(ProcessService.PROCESS_TREATMENT_CATEGORY);
							break;
						case "process_criminal_detention"://刑事拘留
							list.add(ProcessService.PROCESS_CRIMINAL_DETENTION);
							break;
						case "process_arrest"://逮捕
							list.add(ProcessService.PROCESS_ARREST);
							break;
						case "process_get_keep_wait_interrogate"://取保候审
							list.add(ProcessService.PROCESS_GET_KEEP_WAIT_INTERROGATE);
							break;
						case "process_live_at_home_unde_surveillance"://监视居住
							list.add(ProcessService.PROCESS_LIVE_AT_HOME_UNDE_SURVEILLANCE);
							break;		
					}
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		String json=processService.update(process, list);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().println(json);
		} catch (IOException e) {
			new RuntimeException(e);
		}
		
	}
	
	/**
	 * 查看流程详情
	 * @return
	 */
	public void findSingle() {
		noLogin();
		String case_id=ajdbxtProcess.getProcess_case_id();
		ajdbxt_process process=processService.getSingleProcessByCaseId(case_id).getProcess();
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

