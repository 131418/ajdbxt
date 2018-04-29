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
				waitTime(hour);//定时一小时
			}else {
				waitTime(hour);//定时一小时
			}
			subpoenaASuspectTimeOut();
		}
	}
	
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
