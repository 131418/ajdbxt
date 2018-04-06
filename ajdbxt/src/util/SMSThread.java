package util;

public class SMSThread extends Thread {
	private MsgSend mag;
	private int time;
	public SMSThread(MsgSend mag,int time) {
		this.mag=mag;
		this.time=time;
	}
	@Override 
	public void run() {
		
	}
	
}
