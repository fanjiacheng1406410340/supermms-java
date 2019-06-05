package com.oss.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.stream.FileImageInputStream;
import com.chinatricom.util.Encrypt;
import com.oss.mms.Base64Operator;
import com.oss.mms.Bind;
import com.oss.mms.Deliver;
import com.oss.mms.Report;
import com.oss.mms.SendMms;


/**
 * 类描述：===========================彩信新接口彩信HTTP接口/http/template/mms============================
 * @version: 1.0
 * @author: 8588
 * @date: 2017年7月3日 下午6:13:13 
 */
public class TestHttp {

	static String url = "http://172.18.1.109:8089/http/template/mms";
	static String account = "lei858";
	static String password = Encrypt.MD5Encode("456.com");
	
	
	static String phones = "18084059356";
	static String mmsid = "2c9481ed5cf77ec1015cf7b438c9001c";
	static String subcode = "2011";
//	static String params = "这是第一帧的文字！！！,这是第二帧的文字！！！";
//	static String params = "这是每一帧的文字！！！,这是每二帧的文字！！！,这是每三帧的文字！！！";
	static String params = "大汉三通数据通信有限公司(特殊)#####" //标题变量
			             + "㎡→￥ひ?ΦΩ♂♀℃1234567890qwerTyuioplkjhgfdsazxcvbnm·～@￥$%……*QWERTYUIOPLKJHGFDSAZXCVBNM（）()=——_+-｛｝：:“\"｜|《》!--？?/【】[]'\\‘，,。.、㎡/試3發#####"       //第一帧文字变量
			             + "㎡→￥ひ?ΦΩ♂♀℃1234567890qwerTyuioplkjhgfdsazxcvbnm·～@￥$%……*QWERTYUIOPLKJHGFDSAZXCVBNM（）()=——_+-｛｝：:“\"｜|《》!--？?/【】[]'\\‘，,。.、㎡/ms囧測試下發#####"       //第二帧文字变量
			             + "㎡→￥ひ?ΦΩ♂♀℃1234567890qwerTyuioplkjhgfdsazxcvbnm·～@￥$%……*QWERTYUIOPLKJHGFDSAZXCVBNM（）()=——_+-｛｝：:“\"｜|《》!--？?/【】[]'\\‘，,。.、㎡/ms囧測試下發#####"       //第三帧文字变量
			             + "㎡→￥ひ?ΦΩ♂♀℃1234567890qwerTyuioplkjhgfdsazxcvbnm·～@￥$%……*QWERTYUIOPLKJHGFDSAZXCVBNM（）()=——_+-｛｝：:“\"｜|《》!--？?/【】[]'\\‘，,。.、㎡/ms囧測試下發";           //第四帧文字变量

// 数据通信彩信产品id  2c9481ed5cf77ec1015cf7b438c9001c
// 无线数据彩信产品id  2c9481ed5cf77ec1015cf7b0b14a0018
	
	static String dur = "5000";  //每一帧图片显示间隔      单位毫秒
	
	
	public static void main(String[] args) throws IOException {

		SendMms send = new SendMms();
		Report report = new Report();
		Deliver deliver = new Deliver();

		//上传彩信
		send.upload(url, account, password, dur);

		
		// 下发彩信
		send.submit(url, account, password, phones, mmsid, subcode, params);
        
		
		// 获取状态报告
//		 report.report(url, account, password);

		// 获取上行
//		 deliver.deliver(url, account, password);


//		 Bind bind = new Bind();
//		 String result = bind.bind(account, password, url);
//		 System.out.println(result);

	}
}
