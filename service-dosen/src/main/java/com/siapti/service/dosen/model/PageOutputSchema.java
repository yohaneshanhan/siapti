package com.siapti.service.dosen.model;

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
	Page<Dosen> output_schema;

	public ErrorSchema getError_schema() {
		return error_schema;
	}

	public void setError_schema(ErrorSchema error_schema) {
		this.error_schema = error_schema;
	}

	/*public RestResponsePage<StaffModel> getOutput_schema() {
		return output_schema;
	}

	public void setOutput_schema(RestResponsePage<StaffModel> output_schema) {
		this.output_schema = output_schema;
	}*/
	
	

	/*public PageImpl<StaffModel> getOutput_schema() {
		return output_schema;
	}

	public void setOutput_schema(PageImpl<StaffModel> output_schema) {
		this.output_schema = output_schema;
	}*/
	
	

	public Page<Dosen> getOutput_schema() {
		return output_schema;
	}

	public void setOutput_schema(Page<Dosen> output_schema) {
		this.output_schema = output_schema;
		
	}
}
