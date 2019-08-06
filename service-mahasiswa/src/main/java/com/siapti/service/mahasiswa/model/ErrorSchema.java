package com.siapti.service.mahasiswa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

public class ErrorSchema {
	@JsonView(View.FindBy.class)
	@JsonProperty("error-code")
	private String errorCd;
	
	@JsonView(View.FindBy.class)
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
