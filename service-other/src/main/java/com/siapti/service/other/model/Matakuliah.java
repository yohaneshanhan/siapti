package com.siapti.service.other.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Matakuliah {
	@JsonProperty("kode")
	private String kode;
	
	@JsonProperty("nama")
	private String nama;
	
	@JsonProperty("sks")
	private String sks;
	
	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	
	public String getSks() {
		return sks;
	}

	public void setSks(String sks) {
		this.sks = sks;
	}

	public Matakuliah(String kode, String nama, String sks) {
		this.kode = kode;
		this.nama = nama;
		this.sks = sks;
	}

}
