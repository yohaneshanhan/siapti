package com.siapti.service.jadwalkuliah.model;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class PageOutputSchema {
	@JsonView(View.Show.class)
	@JsonProperty("error-schema")
	ErrorSchema error_schema;
	
	@JsonInclude(Include.NON_NULL)
	@JsonView(View.Show.class)
	@JsonProperty("output-schema")
	Page<JadwalKuliah> output_schema;

	public ErrorSchema getError_schema() {
		return error_schema;
	}

	public void setError_schema(ErrorSchema error_schema) {
		this.error_schema = error_schema;
	}

	public Page<JadwalKuliah> getOutput_schema() {
		return output_schema;
	}

	public void setOutput_schema(Page<JadwalKuliah> output_schema) {
		this.output_schema = output_schema;
		
	}
}
