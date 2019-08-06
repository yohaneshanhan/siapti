package com.siapti.service.registrasimatakuliah.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;


public class ListOutputSchema<T> {

	@JsonProperty("error-schema")@JsonView(View.ByDosen.class)
	ErrorSchema error_schema;
	
	@JsonProperty("output-schema")@JsonView(View.ByDosen.class)
	List<T> output_schema;
	
	public ErrorSchema getError_schema() {
		return error_schema;
	}

	public void setError_schema(ErrorSchema error_schema) {
		this.error_schema = error_schema;
	}

	public List<T> getOutput_schema() {
		return output_schema;
	}

	public void setOutput_schema(List<T> output_schema) {
		this.output_schema = output_schema;
	}

}

