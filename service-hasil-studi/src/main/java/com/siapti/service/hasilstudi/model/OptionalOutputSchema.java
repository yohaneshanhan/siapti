package com.siapti.service.hasilstudi.model;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreType
public class OptionalOutputSchema<T> {
	
	@JsonProperty("error-schema")
	ErrorSchema error_schema;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("output-schema")
	Optional<T> output_schema;

	public ErrorSchema getError_schema() {
		return error_schema;
	}

	public void setError_schema(ErrorSchema error_schema) {
		this.error_schema = error_schema;
	}

	public Optional<T> getOutput_schema() {
		return output_schema;
	}

	public void setOutput_schema(Optional<T> output_schema) {
		this.output_schema = output_schema;
	}

}
