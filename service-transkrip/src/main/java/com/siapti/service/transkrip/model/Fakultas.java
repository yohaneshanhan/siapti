package com.siapti.service.transkrip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="fakultas")
public class Fakultas {
	@Id
	@Column(name= "id_fakultas")
	@JsonProperty("id-fakultas")
	private Integer id;
	
	@Column(name = "nama_fakultas")@JsonProperty("nama-fakultas")
	private String nama;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
}
