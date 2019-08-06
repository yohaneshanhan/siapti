package com.siapti.service.other.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dosen {
	@JsonProperty("nip")
	private String nip;
	
	@JsonProperty("nama")
	private String nama;
	
	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Dosen(String nip, String nama) {
		this.nip = nip;
		this.nama = nama;
	}
	
}
