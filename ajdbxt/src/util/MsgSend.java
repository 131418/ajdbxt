package util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;

/**
 * 基于腾讯云-短信 服务的短信发送工具类
 * SDK APPID 1400082910 固定帐号信息 
 * App Key 60d642699deae3beaeda20ae44f96f80 固定帐号信息
 * @author ppMonkey工作室
 *
 */
public class MsgSend {
	// URL，其中sdkappid为SDKAPPID
	private final static String URL_BASE = "https://yun.tim.qq.com/v5/tlssmssvr/sendmultisms2?sdkappid=1400082910&random=";
	//腾讯云语音通知URL
	private final static String URL_BASE_VOICE="https://yun.tim.qq.com/v5/tlsvoicesvr/sendvoiceprompt?sdkappid=1400082910&random=";
	// App Key 要高度保密
	private final static String APPKEY = "02c9cf3cef358f484514dad73e5b0159";
	//签名
	public static final String POlICE_OFFICE="安源公安分局";
	
	//語音模板
	public static final String SUBPOENA_A_SUSPECT_DIED_LINE_VOICE="_，传唤_案件嫌疑人时间不足_小时，请尽快传唤嫌疑人。";
	public static final String SUBPOENA_A_SUSPECT_TIME_OUT_VOICE="_，你未在8小时内传唤_案件嫌疑人，请办理延长传唤手续.";
	public static final String SUBPOENA_A_SUSPECT_DIED_LINE_CAPTAIN_VOICE="_所队长，_案件嫌疑人还未传唤，请督促办案民警处理。";
	public static final String COMMUNITY_ABANDON_DRUG_VOICE="_，请24小时内通知_案件嫌疑人家属、戒毒人员户籍所在社区或现住地社区";
	public static final String PUNISH_FINE_VOICE="_，请通知_案件嫌疑人15日内到银行缴纳罚款";
	public static final String CASE_PAGE_HAND_IN_VOICE="_，请在7天内把_案件案卷上交法制大队";
	public static final String SUBPOENA_A_SUSPECT_VOICE="_，请在8小时内传唤_案件嫌疑人。";
	public static final String MANDATORY_ABANDON_DRUG_VOICE="_，请24小时内通知_案件嫌疑人家属";
	public static final String PENALTY_AND_DETENTION_VOICE="_，请24小时内通知_案件嫌疑人家属，并通知嫌疑人15日内到银行缴纳罚款";
	public static final String PUNISH_DETENTION_VOICE="_，_案件嫌疑人被行政拘留，请24小时内通知其家属。";
	public static final String SUE_RESULT_CATCH_POLICE_VOICE="_，检察院对_案件嫌疑人作出逮捕决定，请24小时内执行逮捕，并在逮捕后24小时内通知家属";
	public static final String MONITORING_LIVE_VOICE="_案件嫌疑人被强制监视居住6个月";
	public static final String GET_KEEP_WAIT_EXAMINE_VOICE="_，对_案取保候审人进行侦查";
	//短信模板
	public static final int SUBPOENA_A_SUSPECT_DIED_LINE=108845;
	public static final int SUBPOENA_A_SUSPECT_TIME_OUT=108077;
	public static final int SUBPOENA_A_SUSPECT_DIED_LINE_CAPTAIN=108846;
	public static final int COMMUNITY_ABANDON_DRUG=108851;
	public static final int PUNISH_FINE=108847;
	public static final int CASE_PAGE_HAND_IN=108852;
	public static final int SUBPOENA_A_SUSPECT=108844;
	public static final int MANDATORY_ABANDON_DRUG=108850;
	public static final int PENALTY_AND_DETENTION=108849;
	public static final int PUNISH_DETENTION=108848;
	public static final int SUE_RESULT_CATCH_POLICE=108853;
	public static final int MONITORING_LIVE=108854;
	public static final int GET_KEEP_WAIT_EXAMINE=108855;
	
	/**
	 * 测试方法
	 */
	@Test
	public void sendTest() {
		try {
			List<Tel> tel = new ArrayList<Tel>();
			tel.add(new Tel("15270634643", "86"));
			String[] params = { "8" };
			String result_msg = doSend("", "", params, "萍乡市安源区公安分局", tel, 98357);
			System.out.println(result_msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String [] params= {"李木木","王五抢劫案"};
		String s=doSendVoiceSimple(params, "15270634643",PUNISH_DETENTION_VOICE);
		System.out.println(s);
	}
	
	/**
	 * 简单版发短信
	 * @param params 参数列表
	 * @param tel 电话号码链表
	 * @param tpl_id 发送的模板
	 * @return 腾讯云回馈的信息
	 */
	public static String doSendSimple(String[] params,  List<String> tel, Integer tpl_id) {
		List<Tel> telList=new ArrayList<>();
		for(String telNum:tel) {
			telList.add(new Tel(telNum,"86"));
		}
		String result_msg;
		try {
			result_msg=doSend("", "", params, POlICE_OFFICE, telList, tpl_id);
		} catch (Exception e) {
			result_msg=e.getMessage();
		}
		return result_msg;
	}
	
	public static String doSendVoiceSimple(String [] params,String telNum,String voiceMadel) {
		Tel tel=new Tel(telNum,"86");
		String msg="";
		try {
			msg=doSendVoice("",2,params,tel,voiceMadel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	public static String doSendVoice(String ext,int playTimes,String [] params,Tel tel,String voiceMadel) throws Exception {
		// 生成随机数
		int random = (int) Math.floor(Math.random() * 100);
		String url = URL_BASE_VOICE + random;
		URL restURL = new URL(url);
		// 此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类的子类HttpURLConnection
		HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
		// 设置请求的内容类型
		conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
		// 请求方式
		conn.setRequestMethod("POST");
		// 发送POST请求必须设置如下两行
		// 设置接受数据
		conn.setDoInput(true);
		// 设置发送数据
		conn.setDoOutput(true);
		// 对象中的mobile转为字符串
		String strMobile =tel.getMobile();
		// 获取当前时间戳，并设置于10秒后发送
		long time = new Date().getTime() / 1000;
		// SHA256算法生成sig
		String sig = getSHA256StrJava(
				"appkey=" + APPKEY + "&random=" + random + "&time=" + time + "&mobile=" + strMobile);
		String promptfile=voiceMadel;
		for(String str:params) {
			promptfile=promptfile.replaceFirst("_", str);
		}
		VoiceMsgPojo voice=new VoiceMsgPojo("",playTimes,promptfile,2,sig,tel,time);
		String JsonData=JsonUtils.toJson(voice);
		System.out.println("神地方三年级"+JsonData);
		voice = null;//s虽然我不知道这句有什么用
		// 发送数据,使用输出流
		OutputStream outputStream = conn.getOutputStream();
		// 发送数据
		outputStream.write(JsonData.getBytes());
		// 接收数据
		InputStream inputStream = conn.getInputStream();
		// 定义字节数组
		byte[] b = new byte[1024];
		// 定义一个输出流存储接收到的数据
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		// 开始接收数据
		int len = 0;
		while (true) {
			len = inputStream.read(b);
			if (len == -1) {
				// 数据读完
				break;
			}
			byteArrayOutputStream.write(b, 0, len);
		}
		// 从输出流中获取读取到数据(服务端返回的)
		String response = byteArrayOutputStream.toString();
		return response;
	}
	/**
	 **** 所需传参数
	 * 
	 * @param ext
	 *            String 用户的 session 内容，腾讯 server 回包中会原样返回，可选字段，不需要就填空（可空）
	 * @param extend
	 *            string 短信码号扩展号，格式为纯数字串，其他格式无效。默认没有开通，开通请联系 腾讯云短信技术支持（可空）
	 * @param params
	 *            array 模板参数，若模板没有参数，请提供为空数组（非空）
	 *            如：您的{1}是{2}，请于{3}分钟内填写。如非本人操作，请忽略本短信。则params数组中参数对应模版中的位置
	 * @param sign
	 *            string 短信签名，如果使用默认签名，该字段可缺省（非空）
	 * @param tel
	 *            object 国际电话号码，格式依据 e.164 标准为: +[国家码][手机号] ，示例如：+8613711112222，
	 *            其中前面有一个符号 ，86 为国家码，13711112222 为手机号（非空）
	 * @param tpl_id
	 *            number(Integer) 模板 ID，在控制台审核通过的模板 ID（非空） 自动生成参数
	 * @param sig(自动生成)
	 *            string App凭证，具体计算方式见下注（非空）
	 *            (字段根据公式sha256（appkey=$appkey&random=$random&time=$time&mobile=$mobile）生成)
	 * @param time(自动生成)
	 *            number(long) 请求发起时间，unix 时间戳（单位：秒），如果和系统时间相差超过 10 分钟则会返回失败
	 *            注：系统里设定10秒后发送
	 * @return 服务端返回的数据
	 * @throws Exception
	 */
	public static String doSend(String ext, String extend, String[] params, String sign, List<Tel> tel, Integer tpl_id)
			throws Exception {
		// 生成随机数
		int random = (int) Math.floor(Math.random() * 100);
		String url = URL_BASE + random;
		URL restURL = new URL(url);
		// 此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类的子类HttpURLConnection
		HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
		// 设置请求的内容类型
		conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
		// 请求方式
		conn.setRequestMethod("POST");
		// 发送POST请求必须设置如下两行
		// 设置接受数据
		conn.setDoInput(true);
		// 设置发送数据
		conn.setDoOutput(true);
		// 对象中的mobile转为字符串
		String strMobile = listToString(tel);
		// 获取当前时间戳，并设置于10秒后发送
		long time = new Date().getTime() / 1000;
		// SHA256算法生成sig
		String sig = getSHA256StrJava(
				"appkey=" + APPKEY + "&random=" + random + "&time=" + time + "&mobile=" + strMobile);
		// 放入对象中用来生成对应的json数据
		MsgPojo mp = new MsgPojo(ext, extend, params, sig, sign, tel, time, tpl_id);
		// 生成的json数据
		String sendData = new Gson().toJson(mp);
		mp = null;
		// 发送数据,使用输出流
		OutputStream outputStream = conn.getOutputStream();
		// 发送数据
		outputStream.write(sendData.getBytes());
		// 接收数据
		InputStream inputStream = conn.getInputStream();
		// 定义字节数组
		byte[] b = new byte[1024];
		// 定义一个输出流存储接收到的数据
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		// 开始接收数据
		int len = 0;
		while (true) {
			len = inputStream.read(b);
			if (len == -1) {
				// 数据读完
				break;
			}
			byteArrayOutputStream.write(b, 0, len);
		}
		// 从输出流中获取读取到数据(服务端返回的)
		String response = byteArrayOutputStream.toString();
		return response;
	}

	/**
	 * 将list中对象的mobile属性值转为逗号隔开的字符串
	 * 
	 * @param tel
	 *            对象（手机号，国家码）
	 * @return 以逗号隔开的结果字符串
	 */
	private  static String listToString(List<Tel> tel) {
		StringBuilder SB = new StringBuilder();
		for (Tel t : tel) {
			SB.append("," + t.getMobile());
		}
		return SB.toString().replaceFirst(",", "");
	}

	/**
	 * 利用java原生的摘要实现SHA256加密
	 * 
	 * @param str
	 *            初始报文
	 * @return 加密后的报文
	 */
	private static String getSHA256StrJava(String str) {
		MessageDigest messageDigest;
		String encodestr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(str.getBytes("UTF-8"));
			encodestr = byte2Hex(messageDigest.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodestr;
	}

	/**
	 * 将byte转为16进制
	 * 
	 * @param bytes
	 * @return 16进制字符串
	 */
	private static String byte2Hex(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		String temp = null;
		for (int i = 0; i < bytes.length; i++) {
			temp = Integer.toHexString(bytes[i] & 0xFF);
			if (temp.length() == 1) {
				// 1得到一位的进行补0操作
				stringBuffer.append("0");
			}
			stringBuffer.append(temp);
		}
		return stringBuffer.toString();
	}

}
