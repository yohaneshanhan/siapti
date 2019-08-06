package com.siapti.service.mahasiswa.model;

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
@Table(name="wali_studi")
public class Walistudi {
	@Id
	@Column(name= "id_wali_studi")@JsonProperty("id-wali-studi")
	private String id;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("data-dosen")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nip_dosen", referencedColumnName = "nip_dosen")
	private Dosen dosen;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Dosen getDosen() {
		return dosen;
	}

	public void setDosen(Dosen dosen) {
		this.dosen = dosen;
	}

}