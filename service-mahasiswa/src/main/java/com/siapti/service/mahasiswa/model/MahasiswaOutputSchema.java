package com.siapti.service.mahasiswa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MahasiswaOutputSchema {

	@JsonProperty("error-schema")
	ErrorSchema error_schema;
	
	@JsonProperty("output-schema")
	Mahasiswa output_schema;
	
	public ErrorSchema getError_schema() {
		return error_schema;
	}

	public void setError_schema(ErrorSchema error_schema) {
		this.error_schema = error_schema;
	}

	public Mahasiswa getOutput_schema() {
		return output_schema;
	}

	public void setOutput_schema(Mahasiswa output_schema) {
		this.output_schema = output_schema;
	}
	
	
}

