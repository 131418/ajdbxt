package util;

import java.util.List;

public class MsgPojo {
	private String ext;
	private String extend;
	private String[] params;
	private String sig;
	private String sign;
	private List<Tel> tel;
	private long time;
	private int tpl_id;

	public MsgPojo() {

	}

	public MsgPojo(String ext, String extend, String[] params, String sig, String sign, List<Tel> tel, long time,
			int tpl_id) {
		this.ext = ext;
		this.extend = extend;
		this.params = params;
		this.sig = sig;
		this.sign = sign;
		this.tel = tel;
		this.time = time;
		this.tpl_id = tpl_id;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public List<Tel> getTel() {
		return tel;
	}

	public void setTel(List<Tel> tel) {
		this.tel = tel;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getTpl_id() {
		return tpl_id;
	}

	public void setTpl_id(int tpl_id) {
		this.tpl_id = tpl_id;
	}

}
