package com.onboard.model;

public class OnboardResponse {
	
	private int responseCode;
	
	private String respondMsg;
	
	private Object jData;
	
	private String Data;

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getRespondMsg() {
		return respondMsg;
	}

	public void setRespondMsg(String respondMsg) {
		this.respondMsg = respondMsg;
	}

	public Object getjData() {
		return jData;
	}

	public void setjData(Object jData) {
		this.jData = jData;
	}

	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}
	
	

}
