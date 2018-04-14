package util;

import java.util.List;

public class SMSThread extends Thread {
	private String ext;
	private String extend;
	private String[] params;
	private String sign;
	private List<Tel> tel;
	private Integer tpl_id;
	private int time;
	public SMSThread(String ext, String extend, String[] params, String sign, List<Tel> tel, Integer tpl_id, int time) {
		this.ext = ext;
		this.extend = extend;
		this.params = params;
		this.sign = sign;
		this.tel = tel;
		this.tpl_id = tpl_id;
		this.time = time;
	}

	@Override
	public void run() {
			try {
				this.wait(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	/**
	 * 刚指派通知民警传唤嫌疑人
	 */
	private void sendPoliceCallTheMan() {
		
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
}
