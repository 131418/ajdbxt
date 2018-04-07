package com.ajdbxt.action.Process;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.service.Process.ProcessInfoService;
import com.ajdbxt.service.Process.ProcessService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import util.JsonUtils;

import com.ajdbxt.domain.DO.ajdbxt_process;

public class ProcessAction  extends ActionSupport{
	private ProcessService processService;
	private ajdbxt_process ajdbxtProcess;
	private ProcessInfoService processInfoService;

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
	/**
	 * 的到与该警官相关的案件信息
	 * @param ajdbxtProcess.case_end="false" 查未结案的
	 * @param ajdbxtProcess.captain_check="false" 查未审核的
	 */
	public void getInfo() {
		Object o =ActionContext.getContext().getSession().get("loginPolice");//得到该警察
		ajdbxt_police police=(ajdbxt_police)o;
		String police_id=police.getAjdbxt_police_id();
		String json="";
		if(ajdbxtProcess.getProcess_case_end()!=null&&!ajdbxtProcess.getProcess_case_end().equals("true")) {
			json=processInfoService.getInfoList(ProcessInfoService.CASE_END, police_id);
		}else if(ajdbxtProcess.getProcess_captain_check()!=null&&!ajdbxtProcess.getProcess_captain_check().equals("true")){
			json=processInfoService.getInfoList(ProcessInfoService.CAPTAIN_CHECK, police_id);
		}else {
			json=processInfoService.getInfoList(100, police_id);
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
	 * 更新流程表
	 * @param ajdbxtProcess.*="*" 新值为：
	 * 
	 */
	public void update() {
		ajdbxt_process process=(ajdbxt_process)ActionContext.getContext().getSession().get("lookedProcess");
		Class clazz=ajdbxtProcess.getClass();
		Field [] f= clazz.getDeclaredFields();
		for(Field field: f){
			field.setAccessible(true);
			try {
				Object o =field.get(ajdbxtProcess);
				System.out.println(o);
				if(o!=null) {
					field.set(process, o);
					
//					switch (field.getName()) {
//					case "process_case_goods":
//						
//						break;
//
//					case "process_penalty":
//					case ""
//						break;
//					}
					
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println(process.toString());
		processService.update(process);
	}
	
	/**
	 * 查看流程详情
	 * @return
	 */
	public String findSingle() {
		String case_id=ajdbxtProcess.getProcess_case_id();
		ajdbxt_process process=processService.getSingleProcessByCaseId(case_id);
		ActionContext.getContext().getSession().put("lookedProcess", process);
		String processJson=JsonUtils.toJson(process);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(processJson);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public String findSome() {
		return null;
		
	}
	

}
