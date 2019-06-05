/**
 * com.ctc.ctcoss.http.Deliver
 * 2012-11-27
 */
package com.dahantc.mms;



/**
 * 获取彩信上行
 * 
 * @author 8512
 * 
 */
public class Deliver {
	/**
	 * 获取上行彩信
	 * 
	 * @param url
	 *            接口地址
	 * @param account
	 *            用户名
	 * @param password
	 *            MD5密码
	 * @return
	 */
	public String deliver(String url, String account, String password) {
		StringBuffer param=new StringBuffer();
		String message = "";
		try {
			param.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			param.append("<root>");
			param.append("<head>");
			param.append("<cmdId>003</cmdId>");
			param.append("<account>"+account+"</account>");
			param.append("<password>" + password + "</password>");
			param.append("</head>");
			param.append("</root>");
			//System.out.println("请求验证连接"+param.toString());
			message = new Bind().doRequest(param.toString(),url);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
}
