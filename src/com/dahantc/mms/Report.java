/**
 * com.ctc.ctcoss.http.Report
 * 2012-11-27
 */
package com.dahantc.mms;


/**
 * 获取彩信下行状态报告
 * 
 * @author 8512
 * 
 */
public class Report {

	/**
	 * 获取才行下行状态报告
	 * 
	 * @return 请求结果
	 */
	public String report(String url,String account, String password) {
		StringBuffer param=new StringBuffer();
		String message = "";
		try {
			param.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			param.append("<root>");
			param.append("<head>");
			param.append("<cmdId>004</cmdId>");
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
