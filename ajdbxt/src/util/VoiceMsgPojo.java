package util;

import java.util.List;

public class VoiceMsgPojo {
/*
{
    "ext": "",
    "playtimes": 2,
    "promptfile": "语音内容文本",
    "prompttype": 2,
    "sig": "ecab4881ee80ad3d76bb1da68387428ca752eb885e52621a3129dcf4d9bc4fd4",
    "tel": {
        "mobile": "13788888888",
        "nationcode": "86"
    },
    "time": 1457336869
}
 */
	private String ext;
	private int playtimes;
	private String promptfile;
	private int prompttype;
	private String sig;
	private Tel tel;
	private long time;
	public VoiceMsgPojo() {
		
	}
	
	public VoiceMsgPojo(String ext, int playtimes, String promptfile, int prompttype, String sig, Tel tel, long time) {
		super();
		this.ext = ext;
		this.playtimes = playtimes;
		this.promptfile = promptfile;
		this.prompttype = prompttype;
		this.sig = sig;
		this.tel = tel;
		this.time = time;
	}

	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public int getPlaytimes() {
		return playtimes;
	}
	public void setPlaytimes(int playtimes) {
		this.playtimes = playtimes;
	}
	public String getPromptfile() {
		return promptfile;
	}
	public void setPromptfile(String promptfile) {
		this.promptfile = promptfile;
	}
	public int getPrompttype() {
		return prompttype;
	}
	public void setPrompttype(int prompttype) {
		this.prompttype = prompttype;
	}
	public String getSig() {
		return sig;
	}
	public void setSig(String sig) {
		this.sig = sig;
	}
	
	public Tel getTel() {
		return tel;
	}

	public void setTel(Tel tel) {
		this.tel = tel;
	}

	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}	
}
