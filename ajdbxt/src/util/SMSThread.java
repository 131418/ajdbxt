package util;

import java.util.ArrayList;
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
			switch (tpl_id) {
			case MsgSend.SUBPOENA_A_SUSPECT_VOICE://传唤嫌疑人
				subpoenaASuspect();
				break;
			case MsgSend.PUNISH_FINE_VOICE://罚款
				punishFine();
				break;
			case MsgSend.CASE_PAGE_HAND_IN_VOICE://案卷上交
				casePageHandIn();
				break;
			case MsgSend.COMMUNITY_ABANDON_DRUG_VOICE://社区戒毒
				communityAbandonDrug();
				break;
			case MsgSend.GET_KEEP_WAIT_EXAMINE_VOICE://取保候审
				getKeepWaitExamine();
				break;
			case MsgSend.MANDATORY_ABANDON_DRUG_VOICE://强制戒毒
				mandatoryAbandonDrug();
				break;
			case MsgSend.MONITORING_LIVE_VOICE://监视居住
				monitoringLive();
				break;
			case MsgSend.PENALTY_AND_DETENTION_VOICE://拘留并罚款
				penaltyAndDetention();
				break;
			case MsgSend.PUNISH_DETENTION_VOICE://拘留
				punishDetention();
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
		if(process.getProcess_lengthen_subpoena()==null||process.getProcess_lengthen_subpoena().isEmpty()) {
			List<ajdbxt_police> policeList=processDTO.getPolice();
			for(ajdbxt_police police:policeList) {
				String name=police.getPolice_name();
				String num=police.getPolice_phone_number();
				String [] params= {name,processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(num);
				MsgSend.doSendSimple(params, tel, MsgSend.SUBPOENA_A_SUSPECT);
				MsgSend.doSendVoiceSimple(params, num, MsgSend.SUBPOENA_A_SUSPECT_VOICE);
			}
		}
		synchronized (this) {//为当前对象加锁
			if(caseFiled) {
				this.wait(8*60*1000);
			}else {
				this.wait(12*60*1000);
			}
		}
		subpoenaASuspectTimeOut();
	}
	/*
	 * 罚款
	 */
	private void punishFine() {
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process =processDTO.getProcess();
		if(process.getProcess_lengthen_subpoena()==null||process.getProcess_punish_inform().isEmpty()) {
			List<ajdbxt_police> policeList=processDTO.getPolice();
			for(ajdbxt_police police:policeList) {
				String name=police.getPolice_name();
				String num=police.getPolice_phone_number();
				String [] params= {name,processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(num);
				MsgSend.doSendSimple(params, tel, MsgSend.PUNISH_FINE);
				MsgSend.doSendVoiceSimple(params, num, MsgSend.PUNISH_FINE_VOICE);
			}
		}
	}
	/*
	 * 案卷上交
	 */
	private void casePageHandIn() {
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process =processDTO.getProcess();
		if(process.getProcess_lengthen_subpoena()==null||process.getProcess_file_hand().isEmpty()) {
			List<ajdbxt_police> policeList=processDTO.getPolice();
			for(ajdbxt_police police:policeList) {
				String name=police.getPolice_name();
				String num=police.getPolice_phone_number();
				String [] params= {name,processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(num);
				MsgSend.doSendSimple(params, tel, MsgSend.CASE_PAGE_HAND_IN);
				MsgSend.doSendVoiceSimple(params, num, MsgSend.CASE_PAGE_HAND_IN_VOICE);
			}
		}
	}
	/*
	 *社区戒毒
	 */
	private void communityAbandonDrug() {
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process =processDTO.getProcess();
		if(process.getProcess_lengthen_subpoena()==null||process.getProcess_treatment_category().isEmpty()) {
			List<ajdbxt_police> policeList=processDTO.getPolice();
			for(ajdbxt_police police:policeList) {
				String name=police.getPolice_name();
				String num=police.getPolice_phone_number();
				String [] params= {name,processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(num);
				MsgSend.doSendSimple(params, tel, MsgSend.COMMUNITY_ABANDON_DRUG);
				MsgSend.doSendVoiceSimple(params, num, MsgSend.COMMUNITY_ABANDON_DRUG_VOICE);
			}
		}
	}
	/*
	 * 取保候审
	 */
	private void getKeepWaitExamine() {
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process =processDTO.getProcess();
		if(process.getProcess_lengthen_subpoena()==null||process.getProcess_get_keep_wait_interrogate().isEmpty()) {
			List<ajdbxt_police> policeList=processDTO.getPolice();
			for(ajdbxt_police police:policeList) {
				String name=police.getPolice_name();
				String num=police.getPolice_phone_number();
				String [] params= {name,processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(num);
				MsgSend.doSendSimple(params, tel, MsgSend.GET_KEEP_WAIT_EXAMINE);
				MsgSend.doSendVoiceSimple(params, num, MsgSend.GET_KEEP_WAIT_EXAMINE_VOICE);
			}
		}
	}
	/*
	 * 强制戒毒
	 */
	private void mandatoryAbandonDrug() {
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process =processDTO.getProcess();
		if(process.getProcess_lengthen_subpoena()==null||process.getProcess_treatment_category().isEmpty()) {
			List<ajdbxt_police> policeList=processDTO.getPolice();
			for(ajdbxt_police police:policeList) {
				String name=police.getPolice_name();
				String num=police.getPolice_phone_number();
				String [] params= {name,processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(num);
				MsgSend.doSendSimple(params, tel, MsgSend.MANDATORY_ABANDON_DRUG);
				MsgSend.doSendVoiceSimple(params, num, MsgSend.MANDATORY_ABANDON_DRUG_VOICE);
			}
		}
	}
	/*
	 * 监视居住
	 */
	private void monitoringLive() {
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process =processDTO.getProcess();
		if(process.getProcess_lengthen_subpoena()==null||process.getProcess_live_at_home_unde_surveillance().isEmpty()) {
			List<ajdbxt_police> policeList=processDTO.getPolice();
			for(ajdbxt_police police:policeList) {
				String name=police.getPolice_name();
				String num=police.getPolice_phone_number();
				String [] params= {processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(num);
				MsgSend.doSendSimple(params, tel, MsgSend.MONITORING_LIVE);
				MsgSend.doSendVoiceSimple(params, num, MsgSend.MONITORING_LIVE_VOICE);
			}
		}
	}
	/*
	 * 罚款并拘留
	 */
	private void penaltyAndDetention() {
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process =processDTO.getProcess();
		if(process.getProcess_lengthen_subpoena()==null||process.getProcess_punish_inform().isEmpty()) {
			List<ajdbxt_police> policeList=processDTO.getPolice();
			for(ajdbxt_police police:policeList) {
				String name=police.getPolice_name();
				String num=police.getPolice_phone_number();
				String [] params= {name,processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(num);
				MsgSend.doSendSimple(params, tel, MsgSend.PENALTY_AND_DETENTION);
				MsgSend.doSendVoiceSimple(params, num, MsgSend.PENALTY_AND_DETENTION_VOICE);
			}
		}
	}
	/*
	 * 拘留
	 */
	private void punishDetention() {
		ProcessDTO processDTO=getProcessDTO();
		ajdbxt_process process =processDTO.getProcess();
		if(process.getProcess_lengthen_subpoena()==null||process.getProcess_punish_inform().isEmpty()) {
			List<ajdbxt_police> policeList=processDTO.getPolice();
			for(ajdbxt_police police:policeList) {
				String name=police.getPolice_name();
				String num=police.getPolice_phone_number();
				String [] params= {name,processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList<>();
				tel.add(num);
				MsgSend.doSendSimple(params, tel, MsgSend.PUNISH_DETENTION);
				MsgSend.doSendVoiceSimple(params, num, MsgSend.PUNISH_DETENTION_VOICE);
			}
		}
	}
	/*
	 * 延长传唤
	 */
	private void subpoenaASuspectTimeOut() throws InterruptedException {
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
		}
		synchronized (this) {
			if(caseFiled) {
				this.wait(12*60*1000);
			}else {
				this.wait(8*60*1000);
			}
		}		
		subpoenaASuspectDiedLine();
	}
	/*
	 * 接近24小时未传唤
	 */
	private void subpoenaASuspectDiedLine() throws InterruptedException {
		for(int index=0;index<3;index++) {
			int died_line=4;
			ProcessDTO processDTO=getProcessDTO();
			ajdbxt_process process =processDTO.getProcess();
			if(process.getProcess_lengthen_subpoena()==null||process.getProcess_lengthen_subpoena().isEmpty()) {
				List<ajdbxt_police> policeList=processDTO.getPolice();
				for(ajdbxt_police police:policeList) {
					String name=police.getPolice_name();
					String num=police.getPolice_phone_number();
					String [] params= {name,processDTO.getInfo().getInfo_name(),died_line+""};
					died_line--;
					List<String> tel=new ArrayList<>();
					tel.add(num);
					MsgSend.doSendSimple(params, tel, MsgSend.SUBPOENA_A_SUSPECT_DIED_LINE);
					MsgSend.doSendVoiceSimple(params, num, MsgSend.SUBPOENA_A_SUSPECT_DIED_LINE_VOICE);
				}
				ajdbxt_police cap=processDTO.getCap();
				String [] params= {cap.getPolice_name(),processDTO.getInfo().getInfo_name()};
				List<String> tel=new ArrayList();
				tel.add(cap.getPolice_phone_number());
				MsgSend.doSendSimple(params, tel,  MsgSend.SUBPOENA_A_SUSPECT_DIED_LINE_CAPTAIN);
				MsgSend.doSendVoiceSimple(params, cap.getPolice_phone_number(),MsgSend.SUBPOENA_A_SUSPECT_DIED_LINE_CAPTAIN_VOICE);
				synchronized (this) {
					this.wait(1*60*1000);
				}
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

}
