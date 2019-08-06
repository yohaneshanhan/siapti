package com.siapti.service.hasilstudi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListHasilStudi {
	
	@JsonProperty("nim")
	private String[] nim;
	
	@JsonProperty("kode")
	private String[] kode;
	
	@JsonProperty("aksara")
	private String[] aksara;

	@JsonProperty("size")
	private int size;
	
	public String[] getNim() {
		return nim;
	}

	public void setNim(String[] nim) {
		this.nim = nim;
	}

	public String[] getKode() {
		return kode;
	}

	public void setKode(String[] kode) {
		this.kode = kode;
	}

	public String[] getAksara() {
		return aksara;
	}

	public void setAksara(String[] aksara) {
		this.aksara = aksara;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
