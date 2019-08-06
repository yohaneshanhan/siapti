package com.siapti.service.staff.model;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreType
public class OptionalOutputSchema {

	@JsonProperty("error-schema")
	ErrorSchema error_schema;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("output-schema")
	Optional<Staff> output_schema;

	public ErrorSchema getError_schema() {
		return error_schema;
	}

	public void setError_schema(ErrorSchema error_schema) {
		this.error_schema = error_schema;
	}

	public Optional<Staff> getOutput_schema() {
		return output_schema;
	}

	public void setOutput_schema(Optional<Staff> output_schema) {
		this.output_schema = output_schema;
	}
	
	
}
