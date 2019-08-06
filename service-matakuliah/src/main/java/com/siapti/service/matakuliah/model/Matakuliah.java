package com.siapti.service.matakuliah.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="matakuliah")
public class Matakuliah {
	@Id
	@Column(name= "kode_matkul")@JsonProperty("kode-matakuliah")
	private String kode;
	
	@Column(name = "nama_matkul")@JsonProperty("nama-matakuliah")
	private String nama;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("data-progdi")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_progdi", referencedColumnName = "id_progdi")
	private Progdi progdi;
	
	@Column(name = "sks")@JsonProperty("sks")
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

	public Progdi getProgdi() {
		return progdi;
	}

	public void setProgdi(Progdi progdi) {
		this.progdi = progdi;
	}

	public String getSks() {
		return sks;
	}

	public void setSks(String sks) {
		this.sks = sks;
	}
	
}
