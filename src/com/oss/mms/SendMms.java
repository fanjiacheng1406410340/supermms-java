package com.oss.mms;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.stream.FileImageInputStream;
import sun.misc.BASE64Encoder;


/**
 * 类描述：彩信下发、彩信上传
 * @version: 1.0
 * @author: 8588
 * @date: 2017年6月28日 下午1:28:53 
 */
public class SendMms {

	
	/**
	  * 方法描述：下发彩信
	  * @param url
	  * @param account
	  * @param password
	  * @param phones
	  * @param mmsid
	  * @param subcode
	  * @param params
	  * @return
	  * @author: 8588
	  * @date: 2017年6月28日 下午1:28:37
	  */
	public String submit(String url,String account,String password,String phones,String mmsid,String subcode,String params){
		
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
			param.append("<mmsid>"+mmsid+"</mmsid>");
			param.append("<content>"+params+"</content>");
			param.append("<subCode>"+subcode+"</subCode>");
			param.append("</submitMsg>");
			
//			param.append("<submitMsg>");
//			param.append("<phone>"+phones+"</phone>");
//			param.append("<mmsid>"+mmsid+"</mmsid>");
//			param.append("<content>{param3}</content>");
//			param.append("<subCode>"+subcode+"</subCode>");
//			param.append("</submitMsg>");
			
			
			param.append("</body>");
			param.append("</root>");
			message = new Bind().doRequest(param.toString(),url);
			System.out.println("-------------------------");
			System.out.println(message);
			System.out.println("-------------------------");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return message;
	}
	
	/**
	  * 方法描述：上传彩信
	  * @param url
	  * @param account
	  * @param password
	  * @param img
	  * @param type
	  * @param title
	  * @param dur
	  * @param text
	  * @return
	  * @author: 8588
	  * @date: 2017年6月28日 下午1:28:09
	  */
	public String upload(String url,String account,String password,String dur){
		
		StringBuffer param=new StringBuffer();
		String message = "";
		String img1 = "E:/dahantc/OSS/demo/RCmm7/快乐.png";
		String img2 = "E:/dahantc/OSS/demo/RCmm7/心心相印.jpg";
		String img3 = "E:/dahantc/OSS/demo/RCmm7/亚切.bmp";
		String img4 = "E:/dahantc/OSS/demo/RCmm7/untitled.gif";
		
		
		String type1 ="image/png";
		String type2 ="image/jpg";
		String type3 ="image/bmp";
		String type4 ="image/gif";
		
		
		try {
			param.append("<smil>");
			param.append("<head>");
			param.append("<cmdId>005</cmdId>");
			param.append("<account>"+account+"</account>");
			param.append("<password>" + password + "</password>");
			param.append("</head>");
			param.append("<body>");
			param.append("<title>#####</title>");
			
			param.append("<par dur=\""+dur+"ms\">");
			param.append("<img type=\""+type1+"\">");
			param.append("<content>"+getImgBase64(img1)+"</content>");
			param.append("</img>");
			param.append("<text>#####</text>");
			param.append("</par>");
			
			param.append("<par dur=\""+dur+"ms\">");
			param.append("<img type=\""+type2+"\">");
			param.append("<content>"+getImgBase64(img2)+"</content>");
			param.append("</img>");
			param.append("<text>#####</text>");
			param.append("</par>");
			
			param.append("<par dur=\""+dur+"ms\">");
			param.append("<img type=\""+type3+"\">");
			param.append("<content>"+getImgBase64(img3)+"</content>");
			param.append("</img>");
			param.append("<text>#####</text>");
			param.append("</par>");
			
			param.append("<par dur=\""+dur+"ms\">");
			param.append("<img type=\""+type4+"\">");
			param.append("<content>"+getImgBase64(img4)+"</content>");
			param.append("</img>");
			param.append("<text>#####</text>");
			param.append("</par>");
			
			
			param.append("</body>");
			param.append("</smil>");
			message = new Bind().doRequest(param.toString(),url);
			System.out.println("-------------------------");
			System.out.println(message);
			System.out.println("-------------------------");
		} catch (Throwable e) {
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
	
	/**
	  * 方法描述：图片转化base64编码
	  * @param path
	  * @return
	  * @throws IOException
	  * @author: 8588
	  * @date: 2017年6月28日 下午1:25:37
	  */
	public static String getImgBase64(String path) throws IOException {

		byte[] data = null;
		FileImageInputStream input = null;
		input = new FileImageInputStream(new File(path));
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];

		int numBytesRead = 0;
		while ((numBytesRead = input.read(buf)) != -1) {
			output.write(buf, 0, numBytesRead);
		}
		data = output.toByteArray();
		output.close();
		input.close();

		String encode = Base64Operator.encode(data);
		return encode;
	}
}
