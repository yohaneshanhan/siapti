package com.siapti.service.walistudi.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WaliStudiOutputSchema {

	@JsonProperty("error-schema")
	ErrorSchema error_schema;
	
	@JsonProperty("output-schema")
	WaliStudi output_schema;
	
	public ErrorSchema getError_schema() {
		return error_schema;
	}

	public void setError_schema(ErrorSchema error_schema) {
		this.error_schema = error_schema;
	}

	public WaliStudi getOutput_schema() {
		return output_schema;
	}

	public void setOutput_schema(WaliStudi output_schema) {
		this.output_schema = output_schema;
	}
	
	
}

