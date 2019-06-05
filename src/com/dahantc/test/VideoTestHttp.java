package com.dahantc.test;

import java.io.IOException;
import java.util.UUID;

import com.chinatricom.util.Encrypt;
import com.oss.mms.MmsVideoSend;

/**
 * 类描述：===========================超级彩信接口/http/video/mms======================
 * ======
 * 
 * @version: 1.0
 * @author: 8588
 * @date: 2019年5月4日 下午6:13:13
 */
public class VideoTestHttp {

	static String url = "http://mms.3tong.net/http/video/mms";// 接口地址
	static String account = "dh****";
	static String password = Encrypt.MD5Encode("****");
	static String phones = "136****"; 
	static String title = "【大汉三通】";// 彩信标题
	static String path = "D:\\WorkSpace\\demo-smsCloud-mms\\resource\\supermms\\mmsVideo";// 彩信包路径（路径下存放图片，txt文件，视频等）
	static String templateNo = "2c90819f69c8ee750169e249aeb1****";// 模板Id

	static String msgid = UUID.randomUUID().toString().replace("-", "");

	public static void main(String[] args) {

		MmsVideoSend send = new MmsVideoSend();
		try {
			// 1. 上传模板
//			send.upload(url, account, password, path, title);
			// 2. 获取审核状态
			 send.queryVideoCheckStatus(url, account, password, templateNo);
			// 3. 下发彩信
			 send.submit(url, account, password, templateNo, phones);
			// 4. 获取状态报告
			 send.report(url, account, password);
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}
