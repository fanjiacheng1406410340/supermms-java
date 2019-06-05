package com.oss.mms;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class Bind {
	
	public String bind(String name,String password,String url)  {
		StringBuffer param=new StringBuffer();
		String message = "";
		
		try {
			param.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			param.append("<root>");
			param.append("<head>");
			param.append("<cmdId>001</cmdId>");
			param.append("<account>"+name+"</account>");
			param.append("<password>" + password + "</password>");
			param.append("</head>");
			param.append("</root>");
			System.out.println("请求验证连接"+param.toString());
			message = doRequest(param.toString(),url);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	public String doRequest(String  message,String url) throws Throwable {
		PostMethod postMethod = null;
		String responseRoot = null;
		try {
			postMethod = new PostMethod(url);
			postMethod.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			HttpClient httpClient = new HttpClient();
			HttpConnectionManagerParams managerParams = httpClient
					.getHttpConnectionManager().getParams();
			managerParams.setConnectionTimeout(60000);
			managerParams.setSoTimeout(120000);
			message = message.replaceAll("&#xd;", "");
			RequestEntity entity = new StringRequestEntity(message, "text/xml",
					"utf-8");
			postMethod.setRequestEntity(entity);
			System.out.println("发起httpmms请求:" + message);
			httpClient.executeMethod(postMethod);
			int code = postMethod.getStatusCode();
			if (code == HttpStatus.SC_OK) {
				responseRoot = postMethod.getResponseBodyAsString();
				System.out.println("httpmms请求返回:" + responseRoot);
			}
		} catch (Exception e) {
			
		} finally {
			if (postMethod != null)
				postMethod.releaseConnection();
		}
		return responseRoot;
	}
}
