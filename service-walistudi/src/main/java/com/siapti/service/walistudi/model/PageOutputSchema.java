package com.siapti.service.walistudi.model;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class PageOutputSchema {
	@JsonView(View.Show.class)
	@JsonProperty("error-schema")
	ErrorSchema error_schema;
	
	@JsonView(View.Show.class)
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("output-schema")
	Page<WaliStudi> output_schema;

	public ErrorSchema getError_schema() {
		return error_schema;
	}

	public void setError_schema(ErrorSchema error_schema) {
		this.error_schema = error_schema;
	}

	public Page<WaliStudi> getOutput_schema() {
		return output_schema;
	}

	public void setOutput_schema(Page<WaliStudi> output_schema) {
		this.output_schema = output_schema;
		
	}
}
