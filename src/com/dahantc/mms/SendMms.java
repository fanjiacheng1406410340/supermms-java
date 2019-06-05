/**
 * com.ctc.ctcoss.http.SendMms
 * 2012-11-27
 */
package com.dahantc.mms;

import java.io.File;
import java.io.FileInputStream;

import sun.misc.BASE64Encoder;


/**
 * 发送彩信
 * @author 8512
 *
 */
public class SendMms {

	/**
	 * 发送彩信
	 * @return
	 */
	public String submit(String url,String account,String password,String msgid,String phones,String title,
			String path,String subCode,String connId, String serviceID ,String sendTime
	){
		StringBuffer param=new StringBuffer();
		String message = "";
		
		try {
			param.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			param.append("<root>");
			param.append("<head>");
			param.append("<cmdId>002</cmdId>");
			param.append("<account>"+account+"</account>");
			param.append("<password>" + password + "</password>");
			param.append("</head>");
			param.append("<body>");
			param.append("<submitMsg>");
			param.append("<phone>"+phones+"</phone>");
			param.append("<title>"+title+"</title>");
			param.append("<content>"+getMmsContent(path)+"</content>");
			param.append("<msgid>"+msgid+"</msgid>");
//			param.append("<subCode>"+subCode+"</subCode>");
			//param.append("<connId>"+connId+"</connId>");
			//param.append("<serviceID>"+serviceID+"</serviceID>");
			//param.append("<sendtime>"+sendTime+"</sendtime>");
			param.append("</submitMsg>");
			param.append("</body>");
			param.append("</root>");
			//System.out.println("请求验证连接"+param.toString());
			message = new Bind().doRequest(param.toString(),url);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	public String getMmsContent(String path) {
		String content="";
		try {
			FileInputStream fis = new FileInputStream(new File(path));
			byte[] b = new byte[fis.available()];
			fis.read(b);
			fis.close();
			BASE64Encoder baseEncoder = new BASE64Encoder();
			content = baseEncoder.encodeBuffer(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}
