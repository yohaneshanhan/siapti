package com.siapti.service.walistudi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.siapti.service.walistudi.model.View;

@Entity
@Table(name="wali_studi")
public class WaliStudi {
	@JsonView(View.Show.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id_wali_studi")
	@JsonProperty("id-wali-studi")
	private String id;
	
	@JsonView(View.Show.class)
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("data-dosen")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nip_dosen", referencedColumnName = "nip_dosen", updatable = false)
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
