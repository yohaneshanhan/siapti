package com.siapti.service.matakuliah.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorSchema {
	@JsonProperty("error-code")
	private String errorCd;
	
	@JsonProperty("error-message")
	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorCd() {
		return errorCd;
	}

	public void setErrorCd(String errorCd) {
		this.errorCd = errorCd;
	}
}
