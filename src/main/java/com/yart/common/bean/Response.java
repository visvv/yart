package com.yart.common.bean;


public class Response<T> {
	private RESPONSE_STATUS status;
	private String message;
	private T payload;
	private String type;

	public Response() {

	}

	public RESPONSE_STATUS getStatus() {
		return status;
	}

	public void setStatus(RESPONSE_STATUS status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
		if(payload != null)
		this.type = payload.getClass().getSimpleName();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public enum RESPONSE_STATUS {
		STATUS_OK,
		SUCCESS,
		FAIL
	}
}
