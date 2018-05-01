package util;

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

public class SMSThread extends Thread{
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
				checkCaseEnd();
				break;	
			case MsgSend.CASE_ROLLBACK_VOICE://回退
				rollBack();
				break;
			case MsgSend.QUESTION_UP_VOICE:
				questionChange();
				break;
			case MsgSend.CASE_GOODS_LIB_VOICE:
				punishMan();
				break;
			case MsgSend.CASE_FILE_UP_VOICE:
				caseEnd();
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
				wait(1);
			}else {
				break;
			}
		}
	}
	/*
	 * 回退
	 */
	private void rollBack() throws InterruptedException{
		for(int index=0;index<16;index++) {
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_is_rollback()==null||process.getProcess_is_rollback().isEmpty()) {
				ajdbxt_police police=processDTO.getLegal();
				String num=police.getPolice_phone_number();
				String [] params= {processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(num);
				MsgSend.doSendSimple(params, tel, MsgSend.CASE_ROLLBACK);
				MsgSend.doSendVoiceSimple(params, num, MsgSend.CASE_ROLLBACK_VOICE);
				wait(1);
			}else if(process.getProcess_is_rollback().equals("是")){
				rollBackUpdate();
				break;
			}else {
				break;
			}
		}
	}
	/*
	 * 回退提醒修改
	 */
	private void rollBackUpdate() throws InterruptedException {
		for(int index=0;index<16;index++) {
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
				wait(1);
			}else {
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
			if(process.getProcess_question()==null) {
				ajdbxt_police police=processDTO.getLegal();
				String num=police.getPolice_phone_number();
				String [] params= {processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(num);
				MsgSend.doSendSimple(params, tel, MsgSend.CASE_ROLLBACK_OVER);
				MsgSend.doSendVoiceSimple(params, num, MsgSend.CASE_ROLLBACK_OVER_VOICE);
				wait(1);
			}else {
				break;
			}
		}
	}
	/*
	 * 问题整改
	 */
	private void questionChange() throws InterruptedException {
		for(int index=0;index<16;index++) {
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_question()!=null) {//如果整改数量为空
				List<String> tel=new ArrayList<>();
				String [] params= {processDTO.getInfo().getInfo_name(),process.getProcess_question_list().toString(),process.getProcess_question().toString()};
				tel.add(processDTO.getLeader().getPolice_phone_number());
				MsgSend.doSendSimple(params, tel, MsgSend.QUESTION_UP_LEADER);
				MsgSend.doSendVoiceSimple(params, processDTO.getLeader().getPolice_phone_number(), MsgSend.QUESTION_UP_LEADER_VOICE);
				tel=new ArrayList<>();
				tel.add(processDTO.getCap().getPolice_phone_number());
				MsgSend.doSendSimple(params, tel, MsgSend.QUESTION_UP_CHECK);
				MsgSend.doSendVoiceSimple(params, processDTO.getLeader().getPolice_phone_number(), MsgSend.QUESTION_UP_CHECK_VOICE);
				break;
			}else {
				waitTime(4);
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
			if(process.getProcess_case_goods().equals("是")&&
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
				wait(4);
			}else {
				break;
			}
		}
		if(index>=6) {//超时通知局队长
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_case_goods().equals("是")&&
					(process.getProcess_goods_in_lib()==null||process.getProcess_goods_in_lib().equals("否"))) {
				String [] params= {processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(processDTO.getLeader().getPolice_phone_number());
				tel.add(processDTO.getCap().getPolice_phone_number());
				MsgSend.doSendSimple(params, tel, MsgSend.CASE_GOODS_LIB_OUT);
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
				wait(24);
			}else {
				break;
			}
		}
		if(index>=7) {//超时通知局队长
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_case_goods().equals("是")&&
					(process.getProcess_goods_in_lib()==null||process.getProcess_goods_in_lib().equals("否"))) {
				String [] params= {processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(processDTO.getLeader().getPolice_phone_number());
				tel.add(processDTO.getCap().getPolice_phone_number());
				MsgSend.doSendSimple(params, tel, MsgSend.CASE_FILE_UP_OUT);
				for(String num:tel) {
					MsgSend.doSendVoiceSimple(params, num, MsgSend.CASE_FILE_UP_OUT_VOICE);
				}
			}
		}
	}
	private void checkCaseEnd() throws InterruptedException {
		new Thread(new Runnable() {
			public void run() {
				ProcessDTO processDTO=getProcessDTO();
				ajdbxt_process process =processDTO.getProcess();
				int hour=caseFiled?18:999999 ;
				try {
					Date date= DateFormat.getDateInstance().parse(processDTO.getInfo().getInfo_catch_time());
					hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY)-date.getHours()>0?
							caseFiled? 18-(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)-date.getHours()):
								999999-(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)-date.getHours()):0;
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
			this.wait(hour*60*60*1000);
		}
	}

}
