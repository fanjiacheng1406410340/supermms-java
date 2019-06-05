package com.dahantc.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpUtil {

	private static final int CON_TIMEOUT = 60000;
	private static final int READ_TIMEOUT = 60000;

	public static String postMethod(String url, byte[] bt) throws HttpException, IOException {
		return postMethod(url, bt, CON_TIMEOUT);
	}

	public static String postMethod(String url, byte[] bt, int conTimeout) throws HttpException, IOException {
		return postMethod(url, bt, conTimeout, READ_TIMEOUT);
	}

	public static String postMethod(String url, byte[] bt, int conTimeout, int readTimeout) throws HttpException, IOException {
		String res = null;
		PostMethod postMethod = null;
		try {
			postMethod = new PostMethod(url);
			postMethod.addRequestHeader("Content-Type", "application/xml;charset=UTF-8");
			postMethod.setRequestHeader("Connection", "close");
			ByteArrayInputStream inputStream = new ByteArrayInputStream(bt);
			RequestEntity requestEntity = new InputStreamRequestEntity(inputStream);
			postMethod.setRequestEntity(requestEntity);
			HttpClient httpClient = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
			HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
			managerParams.setConnectionTimeout(conTimeout);
			managerParams.setSoTimeout(readTimeout);
			httpClient.executeMethod(postMethod);
			int code = postMethod.getStatusCode();
			if (code == HttpStatus.SC_OK) {
				res = new String(postMethod.getResponseBody(), "utf-8");
			}

		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}

		return res;
	}

	public static String postMethod(String url, Map<String, String> params) {

		String response = null;
		HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
		PostMethod postMethod = new PostMethod(url);
		postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		postMethod.setRequestHeader("Connection", "close");
		try {
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			// 设置Post数据
			if (!params.isEmpty()) {
				int i = 0;
				NameValuePair[] data = new NameValuePair[params.size()];
				for (Entry<String, String> entry : params.entrySet()) {
					data[i] = new NameValuePair(entry.getKey(), entry.getValue());
					i++;
				}
				postMethod.setRequestBody(data);

			}
			HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams();
			managerParams.setConnectionTimeout(CON_TIMEOUT);
			managerParams.setSoTimeout(READ_TIMEOUT);
			client.executeMethod(postMethod);
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				response = new String(postMethod.getResponseBody(), "utf-8");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			postMethod.releaseConnection();
			params.clear();
			params = null;
		}
		return response;
	}

}
