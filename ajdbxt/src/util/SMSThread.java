package util;

import java.util.Calendar;
import java.util.List;
import com.ajdbxt.dao.Process.ProcessDao;
import com.ajdbxt.dao.impl.Process.ProcessDaoImpl;
import com.ajdbxt.domain.DO.ajdbxt_process;

public class SMSThread extends Thread {
	private String[] params;
	private List<String> tel;
	private Integer tpl_id;
	private String CASE_ID;
	private boolean caseFild;
	private final ProcessDao processDao=new ProcessDaoImpl();//用来得到案件流程信息
	private boolean caseFiled;
	/**
	 * @param params 参数列表
	 * @param tel 电话号码链表
	 * @param tpl_id 模板id
	 * @param CASE_ID 案件信息id
	 * @param caseFiled 案件实现（true为行政案件，false为刑事案件）
	 */
	public SMSThread(String[] params,  List<String> tel, Integer tpl_id,String CASE_ID,boolean caseFiled) {
		this.params = params;
		this.tel = tel;
		this.tpl_id = tpl_id;
		this.CASE_ID=CASE_ID;
	}

	public void run() {
		switch (tpl_id) {
		case MsgSend.SUBPOENA_A_SUSPECT://传唤嫌疑人民警
			
			break;
		case MsgSend.SUBPOENA_A_SUSPECT_CAPTAIN://传唤嫌疑人所队长
			
			break;
		case MsgSend.SUBPOENA_A_SUSPECT_LEGAL_PERSONNEL://传唤嫌疑人法制员
			
			break;
		case MsgSend.SUBPOENA_A_SUSPECT_DIRECTOR://传唤嫌疑人局长
			
			break;
		case MsgSend.CANCEL_DISPATCH://取消指派
			
			break;
		case MsgSend.PUNISH_FINE://罚款
					
			break;
		case MsgSend.PUNISH_DETENTION://拘留
			
			break;
		case MsgSend.PENALTY_AND_DETENTION://罚款并拘留
			
			break;
		case MsgSend.MANDATORY_ABANDON_DRUG://强制戒毒
			
			break;
		case MsgSend.COMMUNITY_ABANDON_DRUG://社区戒毒
			
			break;
		case MsgSend.MONITORING_LIVE://监视居住
			
			break;
		case MsgSend.GET_KEEP_WAIT_EXAMINE://取保候审民警，刑事
			
			break;
		case MsgSend.GET_KEEP_WAIT_EXAMINE_CAPTAIN://取保候审所队长，刑事
			
			break;
		case MsgSend.SUE_RESULT_CATCH_POLICE://逮捕民警,刑事
			break;
		case MsgSend.SUE_RESULT_CATCH_CAPTAIN://逮捕，所队长,刑事
		    break;
		case MsgSend.WITHDRAW_CASE://撤案
			break;
		case MsgSend.PROCURATORATE_WITHDRAW_CASE://检察院撤案
			break;
		case MsgSend.CASE_PAGE_HAND_IN://案卷上交
			
			break;
		}		
	}
	/**
	 * 刚指派通知民警传唤嫌疑人
	 * @throws Exception 
	 */
	private void sendPoliceCallTheMan(){
		if(caseFiled) {
			int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			if(hour>=8&&hour<=20) {
				ajdbxt_process process=getTheProcess();
				MsgSend.doSendSimple(params, tel, tpl_id);
				
			}
			
			
		}else {
			
		}
			
	}
	/**
	 * 刚指派通知所队长谁做这件事
	 */
	private void sendCaptainCallTheMan() {
		
	}
	/**
	 * 刚指派时通知法制员跟踪案情
	 */
	private void sendLegalCallTheMan() {
		
	}
	/**
	 * 刚指派时通知局长有这案子
	 */
	private void sendChiefCallTheMan() {
		
	} 
	/**
	 * 结案评分后提醒主协办民警上交案卷
	 */
	private void sendPoliceFileUp() {
		
	}
	/**
	 * 处罚结果为罚款时提醒主协办民警催促罚款
	 */
	private void sendPolicePunishMoney() {
		
	}
	/**
	 * 处罚为拘留时通知主协办民警
	 */
	private void sendPolicePunishDetention() {
		
	}
	/**
	 * 处罚结果为处罚并罚款时通知主协办民警
	 */
	private void sendPolicePunishDetentionAndMoney() {
		
	}
	/**
	 * 处罚结果为强制戒毒时通知主协办民警
	 */
	private void sendPoliceCompulsoryAbandonmentOfDrugHabits() {
		
	}
	/**
	 * 处罚结果为社区戒毒时通知主协办民警
	 */
	private void sendPoliceCommunityDetoxification() {
		
	}
	/**
	 * 传唤超时通知民警办理延长传唤手续
	 */
	private void sendPoliceCallLong() {
		
	}
	/**
	 * 取消指派民警
	 */
	private void sendPoliceUnassignPolice(){
		
	}
	/**
	 * 取保候审通知民警
	 */
	private void sendPoliceGetKeepWaitInterrogate() {
		
	}
	/**
	 * 取保候审通知所队长
	 */
	private void sendCaptainGetKeepWaitInterrogate(){
		
	}
	/**
	 * 监视居住通知民警和所队长
	 */
	private void sendCaptainAndPoliceMonitoringLive() {
		
	}
	/**
	 * 通知民警逮捕
	 */
	private void sendPoliceArrest() {
		
	}
	/**
	 * 起诉结果为逮捕时通知所队长
	 */
	private void sendCaptainArrest() {
		
	}
	/**
	 * 通知民警和所队长检察院撤案
	 */
	private void sendPoliceAndCaptainProcuratorateWithdrawtTheCase() {
		
	}
	/**
	 * 通知民警和所队长某案撤案
	 */
	private void sendPoliceAndCaptainWithdrawtTheCase() {
		
	}
	private ajdbxt_process getTheProcess() {
		ajdbxt_process process=processDao.findProcessByCaseId(CASE_ID).get(0);
		return process;
	}  
}
