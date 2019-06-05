package com.dahantc.beans;

import java.util.List;

public class Resut4Video {

	private int result;

	private String msg;

	private Integer status;

	private String templateNo;

	private String reportMsg;

	private List<Response4Video> response;

	public String getReportMsg() {
		return reportMsg;
	}

	public void setReportMsg(String reportMsg) {
		this.reportMsg = reportMsg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Response4Video> getResponse() {
		return response;
	}

	public void setResponse(List<Response4Video> response) {
		this.response = response;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTemplateNo() {
		return templateNo;
	}

	public void setTemplateNo(String templateNo) {
		this.templateNo = templateNo;
	}

}
