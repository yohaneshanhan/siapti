package com.siapti.service.other.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Progdi {
	@JsonProperty("id-progdi")
	public String id;
	
	@JsonProperty("nama-progdi")
	public String nama;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public Progdi(String id, String nama) {
		this.id = id;
		this.nama = nama;
	}
	
}
