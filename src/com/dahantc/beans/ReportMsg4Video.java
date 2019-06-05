package com.dahantc.beans;

public class ReportMsg4Video {
	/** 手机号码 */
	private String mobile;// 手机号码

	/** 彩信标识 */
	private String msgId;// 彩信标识

	/** 彩信发送状态, 0：发送成功, 其它：错误码 */
	private String status;// 彩信发送状态, 0：发送成功, 其它：错误码

	/** 发送状态描述 */
	private String statusDesp;// 发送状态描述

	public String getMsgId() {
		return msgId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	/** 彩信发送状态, 0：发送成功, 其它：错误码 */
	public String getStatus() {
		return status;
	}

	/** 彩信发送状态, 0：发送成功, 其它：错误码 */
	public void setStatus(String status) {
		this.status = status;
	}

	/** 发送状态描述 */
	public String getStatusDesp() {
		return statusDesp;
	}

	/** 发送状态描述 */
	public void setStatusDesp(String statusDesp) {
		this.statusDesp = statusDesp;
	}

}
