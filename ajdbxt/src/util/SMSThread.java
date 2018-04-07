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
			System.out.println(e.getMessage());
		}finally {
			try {
				MsgSend.doSend(ext, extend, params, sign, tel, tpl_id);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
}
