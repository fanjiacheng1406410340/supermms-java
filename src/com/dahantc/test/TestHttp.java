package com.dahantc.test;

import java.util.UUID;
import com.chinatricom.util.Encrypt;
import com.dahantc.mms.Deliver;
import com.dahantc.mms.Report;
import com.dahantc.mms.SendMms;

/**
 * 类描述：===========================彩信老接口彩信HTTP接口/http/mms========================
 * ====
 * 
 * @version: 1.0
 * @author: 8588
 * @date: 2017年7月3日 下午6:12:27
 */
public class TestHttp {
	static String url = "http://172.18.1.104:8089/http/mms";
	static String account = "wanglg";
	static String password = Encrypt.MD5Encode("****");

	static String phones = "180840****"; 
	static String title = "测试彩信";
	static String path = "D:\\WorkSpace\\demo-smsCloud-mms\\resource\\mms\\mms.zip";
	static String msgid = UUID.randomUUID().toString().replace("-", "");

	// String sendTime="";
	// String subCode="002";

	public static void main(String[] args) {

		SendMms send = new SendMms();
		Report report = new Report();
		Deliver deliver = new Deliver();

		// 下发彩信
		send.submit(url, account, password, msgid, phones, title, path, null, null, null, null);

		// 获取状态报告
		// report.report(url, account, password);

		// 获取上行
		// deliver.deliver(url, account, password);

	}
}
