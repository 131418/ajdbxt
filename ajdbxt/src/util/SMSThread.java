package util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.context.ApplicationContext;
import com.ajdbxt.domain.DO.ajdbxt_police;
import com.ajdbxt.domain.DO.ajdbxt_process;
import com.ajdbxt.domain.DTO.Process.ProcessDTO;
import com.ajdbxt.service.Process.ProcessService;

public class SMSThread extends Thread implements Serializable{//为了使其序列化
	private String tpl_id;
	private String CASE_ID;
	private boolean caseFiled;
	private ApplicationContext applicationCotext; 
	/**
	 * @param tpl_id 模板id
	 * @param CASE_ID 案件信息id
	 * @param caseFiled 案件实现（true为行政案件，false为刑事案件）
	 * @param processService 流程业务
	 */
	public SMSThread( String tpl_id,String CASE_ID,boolean caseFiled,ApplicationContext applicationContext) {
		this.tpl_id = tpl_id;
		this.CASE_ID=CASE_ID;
		this.caseFiled=caseFiled;
		this.applicationCotext=applicationContext;
	}
	
	public void run() {
		try {
			isWorking();//休息时间不干活谢谢
			switch (tpl_id) {
			case MsgSend.SUBPOENA_A_SUSPECT_VOICE://传唤嫌疑人
				subpoenaASuspect();
				if(caseFiled)
					checkCaseEnd();
				break;	
			case MsgSend.CASE_ROLLBACK_VOICE://回退
				rollBack();
				break;
			case MsgSend.QUESTION_UP_VOICE://案件整改完成
				questionChange();
				break;
			case MsgSend.CASE_GOODS_LIB_VOICE:
				if(caseFiled){
					punishMan();//如果为行政案件这这样处理
				}else {
					caseGoods();
				}
				break;
			case MsgSend.CASE_FILE_UP_VOICE:
				caseEnd();
				break;
			case MsgSend.CRIMINAL_SEARCH_BACK_VOICE:
				result();
				break;
			case MsgSend.CRIMINAL_BAIL_VOICE:
				forceMeasure();
				break;
			case MsgSend.CRIMINAL_CASE_FILE_BACK_VOICE:
				fileBack();
				break;
			case MsgSend.QUESTION_UPDATE_VOICE:
				questionUpdate();
				break;
			}	
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	/*
	 * 传唤嫌疑人
	 */
	private void subpoenaASuspect() throws InterruptedException {
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process =processDTO.getProcess();
		int hour=caseFiled?8:12 ;
		try {
			Date date= DateFormat.getDateInstance().parse(processDTO.getInfo().getInfo_catch_time());
			hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY)-date.getHours()>0?
					caseFiled? 8-(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)-date.getHours()):
						12-(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)-date.getHours()):0;
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			if(process.getProcess_lengthen_subpoena()==null||process.getProcess_lengthen_subpoena().isEmpty()) {
				List<ajdbxt_police> policeList=processDTO.getPolice();
				String [] params= {processDTO.getInfo().getInfo_name()};
				for(ajdbxt_police police:policeList) {
					String num=police.getPolice_phone_number();
					List<String> tel=new ArrayList<>();
					tel.add(num);
					MsgSend.doSendSimple(params, tel, MsgSend.SUBPOENA_A_SUSPECT);
					MsgSend.doSendVoiceSimple(params, num, MsgSend.SUBPOENA_A_SUSPECT_VOICE);
				}
				List<String> tel=new ArrayList<>();
				tel.add(processDTO.getCap().getPolice_phone_number());
				tel.add(processDTO.getLeader().getPolice_phone_number());
				tel.add(processDTO.getLegal().getPolice_phone_number());
				tel.add(processDTO.getTeam_legal().getPolice_phone_number());
				MsgSend.doSendSimple(params,tel , MsgSend.SUBPOENA_A_SUSPECT);
				tel.remove(processDTO.getLeader().getPolice_phone_number());//不给局长发语音
				for(String num:tel) {
					MsgSend.doSendVoiceSimple(params, num, MsgSend.SUBPOENA_A_SUSPECT_VOICE);
				}
			}
			if(caseFiled) {
				waitTime(hour);
			}else {
				waitTime(hour);
			}
			subpoenaASuspectTimeOut();
		}
	}
	/*
	 * 超时
	 */
	private void subpoenaASuspectTimeOut() throws InterruptedException {//超时操作
		if(caseFiled) {
			for(int index=0;index<16;index++) {
				ProcessDTO processDTO=getProcessDTO();
				ajdbxt_process process =processDTO.getProcess();
				if(process.getProcess_lengthen_subpoena()==null||process.getProcess_lengthen_subpoena().isEmpty()) {
					List<ajdbxt_police> policeList=processDTO.getPolice();
					for(ajdbxt_police police:policeList) {
						String name=police.getPolice_name();
						String num=police.getPolice_phone_number();
						String [] params= {name,processDTO.getInfo().getInfo_name()};
						List<String> tel=new ArrayList<>();
						tel.add(num);
						MsgSend.doSendSimple(params, tel, MsgSend.SUBPOENA_A_SUSPECT_TIME_OUT);
						MsgSend.doSendVoiceSimple(params, num, MsgSend.SUBPOENA_A_SUSPECT_TIME_OUT_VOICE);
					}
					waitTime(1);
				}else {
					break;
				}
			}
		}else {
			for(int index=0;index<16;index++) {
				ProcessDTO processDTO=getProcessDTO();
				ajdbxt_process process =processDTO.getProcess();
				if(process.getProcess_lengthen_subpoena()==null||process.getProcess_lengthen_subpoena().isEmpty()) {
					List<ajdbxt_police> policeList=processDTO.getPolice();
					for(ajdbxt_police police:policeList) {
						String name=police.getPolice_name();
						String num=police.getPolice_phone_number();
						String [] params= {name,processDTO.getInfo().getInfo_name()};
						List<String> tel=new ArrayList<>();
						tel.add(num);
						MsgSend.doSendSimple(params, tel, MsgSend.CRIMINAL_SUBPOENA_A_SUSPECT_TIME_OUT);
						MsgSend.doSendVoiceSimple(params, num, MsgSend.CRIMINAL_SUBPOENA_A_SUSPECT_TIME_OUT_VOICE);
					}
					waitTime(1);
				}else {
					break;
				}
			}
		}
		
	}
	/*
	 * 回退
	 */
	private void rollBack() throws InterruptedException{
		for(int index=0;index<24;index++) {
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_is_rollback()==null||process.getProcess_is_rollback().isEmpty()||process.getProcess_is_rollback().equals("待处理")) {
				ajdbxt_police police=processDTO.getLegal();
				String num=police.getPolice_phone_number();
				String [] params= {processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(num);
				MsgSend.doSendSimple(params, tel, MsgSend.CASE_ROLLBACK);
				MsgSend.doSendVoiceSimple(params, num, MsgSend.CASE_ROLLBACK_VOICE);
				waitTime(1);
			}else if(process.getProcess_is_rollback().equals("是")){
				rollBackUpdate();
				break;
			}else {
				rollBackOver();
				break;
			}
		}
	}
	/*
	 * 回退提醒修改
	 */
	private void rollBackUpdate() throws InterruptedException {
		for(int index=0;index<24;index++) {
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_is_rollback().equals("是")) {
				List<ajdbxt_police> policeList=processDTO.getPolice();
				for(ajdbxt_police police:policeList) {
					String name=police.getPolice_name();
					String num=police.getPolice_phone_number();
					String [] params= {name,processDTO.getInfo().getInfo_name()};
					List<String> tel=new ArrayList<>();
					tel.add(num);
					MsgSend.doSendSimple(params, tel, MsgSend.CASE_ROLLBACK_UPDATE);
					MsgSend.doSendVoiceSimple(params, num, MsgSend.CASE_ROLLBACK_UPDATE_VOICE);
				}
				waitTime(1);
			}else {
				process.setProcess_is_rollback("待处理");
				applicationCotext.getBean(ProcessService.class).update(process, -1);
				rollBackOver();
				break;
			}
		}
	}
	/*
	 * 回退修改完成
	 */
	private void rollBackOver() throws InterruptedException {
		for(int index=0;index<16;index++) {
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_is_rollback().equals("待处理")) {
				ajdbxt_police police=processDTO.getLegal();
				String num=police.getPolice_phone_number();
				String [] params= {processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(num);
				MsgSend.doSendSimple(params, tel, MsgSend.CASE_ROLLBACK_OVER);
				MsgSend.doSendVoiceSimple(params, num, MsgSend.CASE_ROLLBACK_OVER_VOICE);
				waitTime(1);
			}else if(process.getProcess_is_rollback().equals("否")){
				break;
			}else {
				break;
			}
		}
	}
	/*
	 * 通知民警整改问题
	 */
	private void questionUpdate() {
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process =processDTO.getProcess();
		if(process.getProcess_question_list()!=null&&process.getProcess_question_list().intValue()>0) {
			List<ajdbxt_police> policeList=processDTO.getPolice();
			String []params= {processDTO.getInfo().getInfo_name(),process.getProcess_question_list().toString()};
			List<String> tel=new ArrayList<>();
			for(ajdbxt_police police:policeList) {
				tel.add(police.getPolice_phone_number());
			}
			MsgSend.doSendSimple(params, tel, MsgSend.QUESTION_UPDATE);
			for(String t:tel) {
				MsgSend.doSendVoiceSimple(params, t, MsgSend.CASE_ROLLBACK_UPDATE_VOICE);
			}
		}
	}
	/*
	 * 问题整改
	 */
	private void questionChange() throws InterruptedException {
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_question()!=null) {//如果整改数量不为空
				List<String> tel=new ArrayList<>();
				String [] params= {processDTO.getInfo().getInfo_name(),process.getProcess_question_list().toString(),process.getProcess_question().toString()};
				if(process.getProcess_question().intValue()!=process.getProcess_question_list().intValue()) {
					tel.add(processDTO.getLeader().getPolice_phone_number());
					MsgSend.doSendSimple(params, tel, MsgSend.QUESTION_UP_LEADER);
//					MsgSend.doSendVoiceSimple(params, processDTO.getLeader().getPolice_phone_number(), MsgSend.QUESTION_UP_LEADER_VOICE);
					tel=new ArrayList<>();
					tel.add(processDTO.getCap().getPolice_phone_number());
					MsgSend.doSendSimple(params, tel, MsgSend.QUESTION_UP_CHECK);
					MsgSend.doSendVoiceSimple(params, processDTO.getLeader().getPolice_phone_number(), MsgSend.QUESTION_UP_CHECK_VOICE);
				}
				for(int index=0;index<16;index++) {
					processDTO=getProcessDTO();
					process=processDTO.getProcess();
					if(process.getProcess_administrativ_warning()==null
							&&process.getProcess_detention()==null
							&&process.getProcess_community_abandon_drug()==null
							&&process.getProcess_mandatory_abandon_drug()==null
							&&process.getProcess_penalty()==null) {//如果没有处罚
						List<ajdbxt_police> policeList=processDTO.getPolice();
						for(ajdbxt_police police:policeList) {
							String name=police.getPolice_name();
							String num=police.getPolice_phone_number();
							String []param= {processDTO.getInfo().getInfo_name()};
							tel=new ArrayList<>();
							tel.add(num);
							MsgSend.doSendSimple(param, tel, MsgSend.QUESTION_UP);
							MsgSend.doSendVoiceSimple(param, num, MsgSend.QUESTION_UP_VOICE);
						}
						waitTime(4);
					}
					
				}
			}
			
	}
	/*
	 * 处罚
	 */
	private void punishMan() throws InterruptedException {
		int index=0;
		for(;index<6;index++) {
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_case_goods().equals("有")&&
					(process.getProcess_goods_in_lib()==null||process.getProcess_goods_in_lib().equals("否"))) {
				String [] params= {processDTO.getInfo().getInfo_name()};
				List<ajdbxt_police> policeList=processDTO.getPolice();
				for(ajdbxt_police police:policeList) {
					String num=police.getPolice_phone_number();
					List<String> tel=new ArrayList<>();
					tel.add(num);
					MsgSend.doSendSimple(params, tel, MsgSend.CASE_GOODS_LIB);
					MsgSend.doSendVoiceSimple(params, num, MsgSend.CASE_GOODS_LIB_VOICE);
				}
				List<String> tel=new ArrayList<>();
				tel.add(processDTO.getLegal().getPolice_phone_number());
				MsgSend.doSendSimple(params, tel, MsgSend.CASE_GOODS_LIB_CHECK);
				MsgSend.doSendVoiceSimple(params, processDTO.getLegal().getPolice_phone_number(), MsgSend.CASE_GOODS_LIB_CHECK_VOICE);
				waitTime(4);
			}else {
				break;
			}
		}
		if(index>=6) {//超时通知局队长
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_case_goods().equals("有")&&
					(process.getProcess_goods_in_lib()==null||process.getProcess_goods_in_lib().equals("否"))) {
				String [] params= {processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(processDTO.getLeader().getPolice_phone_number());
				tel.add(processDTO.getCap().getPolice_phone_number());
				MsgSend.doSendSimple(params, tel, MsgSend.CASE_GOODS_LIB_OUT);
				tel.remove(processDTO.getLeader().getPolice_phone_number());//不给局长发语音
				for(String num:tel) {
					MsgSend.doSendVoiceSimple(params, num, MsgSend.CASE_GOODS_LIB_OUT_VOICE);
				}
			}
		}
	}
	/*
	 * 结案后案卷上交操作
	 */
	private void caseEnd() throws InterruptedException {
		int index=0;
		for(;index<7;index++) {
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_file_hand()==null||process.getProcess_file_hand().equals("否")) {
				String [] params= {processDTO.getInfo().getInfo_name()};
				List<ajdbxt_police> policeList=processDTO.getPolice();
				for(ajdbxt_police police:policeList) {
					String num=police.getPolice_phone_number();
					List<String> tel=new ArrayList<>();
					tel.add(num);
					MsgSend.doSendSimple(params, tel, MsgSend.CASE_FILE_UP);
					MsgSend.doSendVoiceSimple(params, num, MsgSend.CASE_FILE_UP_VOICE);
				}
				List<String> tel=new ArrayList<>();
				tel.add(processDTO.getLegal().getPolice_phone_number());
				MsgSend.doSendSimple(params, tel, MsgSend.CASE_FILE_UP_CHECK);
				MsgSend.doSendVoiceSimple(params, processDTO.getLegal().getPolice_phone_number(), MsgSend.CASE_FILE_UP_CHECK_VOICE);
				waitTime(24);
			}else {
				break;
			}
		}
		if(index>=7) {//超时通知局队长
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_case_goods().equals("有 ")&&
					(process.getProcess_goods_in_lib()==null||process.getProcess_goods_in_lib().equals("否"))) {
				String [] params= {processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(processDTO.getLeader().getPolice_phone_number());
				tel.add(processDTO.getCap().getPolice_phone_number());
				MsgSend.doSendSimple(params, tel, MsgSend.CASE_FILE_UP_OUT);
				tel.remove(processDTO.getLeader().getPolice_phone_number());//不给局长发语音
				for(String num:tel) {
					MsgSend.doSendVoiceSimple(params, num, MsgSend.CASE_FILE_UP_OUT_VOICE);
				}
			}
		}
	}
	/*
	 * 涉案财物
	 */
	private void caseGoods() throws InterruptedException {
		punishMan();
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process =processDTO.getProcess();
		process.setProcess_case_goods("无");
		applicationCotext.getBean(ProcessService.class).update(process, -1);
	}
	
	private void checkCaseEnd() throws InterruptedException {
		new Thread(new Runnable() {
			public void run() {
				ProcessDTO processDTO=getProcessDTO();
				ajdbxt_process process =processDTO.getProcess();
				int hour=caseFiled?18:999999 ;
				try {
					Date date= DateFormat.getDateInstance().parse(processDTO.getInfo().getInfo_catch_time());
					hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY)-date.getHours()>0?18-(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)-date.getHours()):0;
				} catch (ParseException e) {
					e.printStackTrace();
				}finally {
					try {
						this.wait(hour*60*60*1000);
						for(int index=0;index<4;index++) {
							if(process.getProcess_case_end()==null||process.getProcess_case_end().isEmpty()) {
								List<ajdbxt_police> policeList=processDTO.getPolice();
								String [] params= {processDTO.getInfo().getInfo_name(),hour+""};
								for(ajdbxt_police police:policeList) {
									String num=police.getPolice_phone_number();
									List<String> tel=new ArrayList<>();
									tel.add(num);
									MsgSend.doSendSimple(params, tel, MsgSend.CASE_END);
									MsgSend.doSendVoiceSimple(params, num, MsgSend.CASE_END_VOICE);
								}
							}
							hour+=2;
							this.wait(2*60*60*1000);
						}
					} catch (InterruptedException e) {
						
					}
					
				}
			}
			
		}).start();;
	}
	
	/*
	 * 案卷上交后触发，拿回案卷
	 */
	private void fileBack() throws InterruptedException {
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process=processDTO.getProcess();
			if(process.getProcess_file_hand().equals("是")) {
				if(process.getProcess_lengthen_criminal_detention().equals("7")) {//延长七天
					waitTime(4*24);
					for(int index=0;index<=6;index++) {
						processDTO=getProcessDTO();
						process=processDTO.getProcess();
						if(process.getProcess_file_hand().equals("是")) {
							List<ajdbxt_police> policeList=processDTO.getPolice();
							String [] params= {processDTO.getInfo().getInfo_name()};
							for(ajdbxt_police police:policeList) {
								String num=police.getPolice_phone_number();
								List<String> tel=new ArrayList<>();
								tel.add(num);
								MsgSend.doSendSimple(params, tel, MsgSend.CRIMINAL_CASE_FILE_BACK);
								MsgSend.doSendVoiceSimple(params, num, MsgSend.CRIMINAL_CASE_FILE_BACK_VOICE);
							}
							waitTime(4);
						}
					}
				}else if(process.getProcess_lengthen_criminal_detention().equals("30")) {//延长30天
					waitTime(7*24);
					for(int index=0;index<=6;index++) {
						processDTO=getProcessDTO();
						process=processDTO.getProcess();
						if(process.getProcess_file_hand().equals("是")) {
							List<ajdbxt_police> policeList=processDTO.getPolice();
							String [] params= {processDTO.getInfo().getInfo_name()};
							for(ajdbxt_police police:policeList) {
								String num=police.getPolice_phone_number();
								List<String> tel=new ArrayList<>();
								tel.add(num);
								MsgSend.doSendSimple(params, tel, MsgSend.CRIMINAL_CASE_FILE_BACK);
								MsgSend.doSendVoiceSimple(params, num, MsgSend.CRIMINAL_CASE_FILE_BACK_VOICE);
							}
							waitTime(4);
						}
					}
				}else if(process.getProcess_lengthen_criminal_detention().equals("60")) {
					waitTime(50*24);
					for(int index=0;index<=6;index++) {
						processDTO=getProcessDTO();
						process=processDTO.getProcess();
						if(process.getProcess_file_hand().equals("是")) {
							List<ajdbxt_police> policeList=processDTO.getPolice();
							String [] params= {processDTO.getInfo().getInfo_name()};
							for(ajdbxt_police police:policeList) {
								String num=police.getPolice_phone_number();
								List<String> tel=new ArrayList<>();
								tel.add(num);
								MsgSend.doSendSimple(params, tel, MsgSend.CRIMINAL_CASE_FILE_BACK);
								MsgSend.doSendVoiceSimple(params, num, MsgSend.CRIMINAL_CASE_FILE_BACK_VOICE);
							}
							waitTime(4);
						}
					}
				}
			}

	}
	/*
	 * 强制措施
	 */
	private void forceMeasure() throws InterruptedException {
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process=processDTO.getProcess();
		forceMeasureNotify();
		if(process.getProcess_result_of_prosecution()!=null) {//第四次强制措施
			switch (process.getProcess_result_of_prosecution()) {
			case "起诉":
			case "撤案":
				caseEnd();
				break;

			}
		}else if(process.getProcess_force_measure_three()!=null) {//第三次强制措施
			switch (process.getProcess_force_measure_three()) {
			case "刑转治":
			case "起诉":
			case "撤案":
				caseEnd();
				break;
			case "取保候审":
				bail();
				break;
			}
		}else if(process.getProcess_force_measure_two()!=null) {//第二次强制措施
			switch (process.getProcess_force_measure_two()) {
			case "拘留":
				caseEnd();
				waitTime(3*24);
				detentionTimeOut();
				break;
			case "监视居住":
				monitoringLive();
				break;
			case "取保候审":
				bail();
				break;
			case "逮捕":
				arrest();
				break;
			}
		}else if(process.getProcess_force_measure_one()!=null) {//第一次强制措施
			fileUp();
			switch (process.getProcess_force_measure_one()) {
			case "拘留":
				caseEnd();
				waitTime(3*24);
				detentionTimeOut();
				break;
			case "监视居住":
				monitoringLive();
				break;
			case "取保候审":
				bail();
				break;
			}
		}
	}
	private void forceMeasureNotify() {
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process=processDTO.getProcess();
		List<String> polices=new ArrayList<>();
		polices.add(processDTO.getCap().getPolice_phone_number());
		polices.add(processDTO.getLegal().getPolice_phone_number());
		String [] params= {processDTO.getInfo().getInfo_name()};
		MsgSend.doSendSimple(params, polices, MsgSend.FORCE_MEASURE);
		for(String tel:polices) {
			MsgSend.doSendVoiceSimple(params, tel, MsgSend.FORCE_MEASURE_VOICE);
		}
	}
	private void fileUp() throws InterruptedException {
		int index=0;
		waitTime(12);
		for(;index<6;index++) {
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_file_hand()==null||process.getProcess_file_hand().equals("否")) {
				String [] params= {processDTO.getInfo().getInfo_name()};
				List<ajdbxt_police> policeList=processDTO.getPolice();
				for(ajdbxt_police police:policeList) {
					String num=police.getPolice_phone_number();
					List<String> tel=new ArrayList<>();
					tel.add(num);
					MsgSend.doSendSimple(params, tel, MsgSend.CASE_FILE_UP);
					MsgSend.doSendVoiceSimple(params, num, MsgSend.CASE_FILE_UP_VOICE);
				}
				List<String> tel=new ArrayList<>();
				tel.add(processDTO.getLegal().getPolice_phone_number());
				MsgSend.doSendSimple(params, tel, MsgSend.CASE_FILE_UP_CHECK);
				MsgSend.doSendVoiceSimple(params, processDTO.getLegal().getPolice_phone_number(), MsgSend.CASE_FILE_UP_CHECK_VOICE);
				waitTime(4);
			}else {
				break;
			}
		}
		if(index>6) {//超时通知局队长
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_case_goods().equals("有 ")&&
					(process.getProcess_goods_in_lib()==null||process.getProcess_goods_in_lib().equals("否"))) {
				String [] params= {processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(processDTO.getLeader().getPolice_phone_number());
				tel.add(processDTO.getCap().getPolice_phone_number());
				MsgSend.doSendSimple(params, tel, MsgSend.CASE_FILE_UP_OUT);
				tel.remove(processDTO.getLeader().getPolice_phone_number());//不给局长发语音
				for(String num:tel) {
					MsgSend.doSendVoiceSimple(params, num, MsgSend.CASE_FILE_UP_OUT_VOICE);
				}
			}
		}
		waitTime(60);
		for(index=0;index<6;index++) {
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_file_hand().equals("是")&&
					!(process.getProcess_lengthen_criminal_detention().equals("7")||process.getProcess_lengthen_criminal_detention().equals("30"))) {
				String [] params= {processDTO.getInfo().getInfo_name()};
				List<ajdbxt_police> policeList=processDTO.getPolice();
				for(ajdbxt_police police:policeList) {
					String num=police.getPolice_phone_number();
					List<String> tel=new ArrayList<>();
					tel.add(num);
					MsgSend.doSendSimple(params, tel, MsgSend.CRIMINAL_CASE_FILE_BACK);
					MsgSend.doSendVoiceSimple(params, num, MsgSend.CRIMINAL_CASE_FILE_BACK_VOICE);
				}
				waitTime(4);
			}else {
				break;
			}
		}
		if(index>6) {//超时通知局队长
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_file_hand().equals("是")&&
					!(process.getProcess_lengthen_criminal_detention().equals("7")||process.getProcess_lengthen_criminal_detention().equals("30"))) {
				String [] params= {processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(processDTO.getLeader().getPolice_phone_number());
				tel.add(processDTO.getCap().getPolice_phone_number());
				MsgSend.doSendSimple(params, tel, MsgSend.CRIMINAL_CASE_FILE_BACK);
				tel.remove(processDTO.getLeader().getPolice_phone_number());//不给局长发语音
				for(String num:tel) {
					MsgSend.doSendVoiceSimple(params, num, MsgSend.CRIMINAL_CASE_FILE_BACK_VOICE);
				}
			}
		}
	}
	/*
	 * 逮捕
	 */
	private void arrest() throws InterruptedException {
		waitTime(50*24);
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process=processDTO.getProcess();
		if(true) {
			List<ajdbxt_police> policeList=processDTO.getPolice();
			String [] params= {processDTO.getInfo().getInfo_name()};
			for(ajdbxt_police police:policeList) {
				String num=police.getPolice_phone_number();
				List<String> tel=new ArrayList<>();
				tel.add(num);
				MsgSend.doSendSimple(params, tel, MsgSend.CRIMINAL_ARREST_DUE);
				MsgSend.doSendVoiceSimple(params, num, MsgSend.CRIMINAL_ARREST_DUE_VOICE);
			}
		}
	}
	
	/*
	 * 监视居住
	 */
	private void monitoringLive() throws InterruptedException {
		waitTime(170*24);
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process=processDTO.getProcess();
		List<ajdbxt_police> policeList=processDTO.getPolice();
		String [] params= {processDTO.getInfo().getInfo_name()};
		for(ajdbxt_police police:policeList) {
			String num=police.getPolice_phone_number();
			List<String> tel=new ArrayList<>();
			tel.add(num);
			MsgSend.doSendSimple(params, tel, MsgSend.CRIMINAL_MONITORING_LIVE_DUE);
			MsgSend.doSendVoiceSimple(params, num, MsgSend.CRIMINAL_MONITORING_LIVE_DUE_VOICE);
		}
	}
	/*
	 *取保候审 
	 */
	private void bail() throws InterruptedException {
//		for(int index=0;index<3;index++) {
//			ProcessDTO processDTO=getProcessDTO();
//			List<ajdbxt_police> policeList=processDTO.getPolice();
//			String [] params= {processDTO.getInfo().getInfo_name()};
//			for(ajdbxt_police police:policeList) {
//				String num=police.getPolice_phone_number();
//				List<String> tel=new ArrayList<>();
//				tel.add(num);
//				MsgSend.doSendSimple(params, tel, MsgSend.CRIMINAL_BAIL);
//				MsgSend.doSendVoiceSimple(params, num, MsgSend.CRIMINAL_BAIL_VOICE);
//			}
//			wait(85*24);
//		}
		waitTime(355*24);
		ProcessDTO processDTO=getProcessDTO();
		List<ajdbxt_police> policeList=processDTO.getPolice();
		String [] params= {processDTO.getInfo().getInfo_name()};
		for(ajdbxt_police police:policeList) {
			String num=police.getPolice_phone_number();
			List<String> tel=new ArrayList<>();
			tel.add(num);
			MsgSend.doSendSimple(params, tel, MsgSend.CRIMINAL_BAIL_DUE);
			MsgSend.doSendVoiceSimple(params, num, MsgSend.CRIMINAL_BAIL_DUE_VOICE);
		}
	}
	/*
	 * 拘留超时
	 */
	private void detentionTimeOut() throws InterruptedException {
		for(int index=0;index<8;index++) {
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process=processDTO.getProcess();
			if(process.getProcess_lengthen_criminal_detention()==null) {
				List<ajdbxt_police> policeList=processDTO.getPolice();
				String [] params= {processDTO.getInfo().getInfo_name()};
				for(ajdbxt_police police:policeList) {
					String num=police.getPolice_phone_number();
					List<String> tel=new ArrayList<>();
					tel.add(num);
					MsgSend.doSendSimple(params, tel, MsgSend.CRIMINAL_LENGTH_DETENTION);
					MsgSend.doSendVoiceSimple(params, num, MsgSend.CRIMINAL_LENGTH_DETENTION_VOICE);
				}
				waitTime(2);
			}else {
				break;
			}
		}
	}
	
	/*
	 * 补查结果
	 */
	private void result() throws InterruptedException {
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process=processDTO.getProcess();
		if(process.getProcess_search_result_two()!=null&&process.getProcess_search_result_two().equals("是")) {
			searchResult();
		}else if(process.getProcess_search_result_one()!=null&&process.getProcess_search_result_two().equals("是")) {
			searchResult();		
		}
	}
	private void searchResult() throws InterruptedException {
		waitTime(20*24);
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process=processDTO.getProcess();
		if(process.getProcess_search_result_two()==null) {
			List<ajdbxt_police> policeList=processDTO.getPolice();
			String [] params= {processDTO.getInfo().getInfo_name()};
			for(ajdbxt_police police:policeList) {
				String num=police.getPolice_phone_number();
				List<String> tel=new ArrayList<>();
				tel.add(num);
				MsgSend.doSendSimple(params, tel, MsgSend.CRIMINAL_SEARCH_BACK);//补查
				MsgSend.doSendVoiceSimple(params, num, MsgSend.CRIMINAL_SEARCH_BACK_VOICE);
			}
		}
		
	}
	private ProcessDTO getProcessDTO() {
		ProcessService processService=applicationCotext.getBean(ProcessService.class);
		ProcessDTO processDTO=processService.getSingleProcessByCaseId(CASE_ID);
		return processDTO;
	}
	private void isWorking() throws InterruptedException {
		int nowHour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//得到当前的小时
		int hour=0;
		if(nowHour>20) {
			hour=nowHour-20;
		}else if(nowHour<8) {
			hour=8-nowHour;
		}
		waitTime(hour);
	}
	private void waitTime(int hour) throws InterruptedException {
		synchronized (this) {
			this.wait(hour*1000);
		}
	}

}
