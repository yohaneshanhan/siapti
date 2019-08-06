package com.siapti.service.hasilstudi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="progdi")
public class Progdi {
	@Id
	@Column(name= "id_progdi")
	@JsonProperty("id-progdi")
	private String id;
	
	@Column(name = "nama_progdi")@JsonProperty("nama-progdi")
	private String nama;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("data-fakultas")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_fakultas", referencedColumnName = "id_fakultas")
	private Fakultas fakultas;

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

	public Fakultas getFakultas() {
		return fakultas;
	}

	public void setFakultas(Fakultas fakultas) {
		this.fakultas = fakultas;
	}

}
