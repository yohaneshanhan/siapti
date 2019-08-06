package com.siapti.service.transkrip.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="dosen")
public class Dosen{
	@Id
	@Column(name= "nip_dosen")@JsonProperty("nip-dosen")@JsonView(View.Show.class)
	private String nip;
	
	@Column(name = "nama_dosen")@JsonProperty("nama-dosen")@JsonView(View.Show.class)
	private String nama;
	
	@Column(name= "tempat_lahir")@JsonProperty("tempat-lahir")
	private String tempat;
	
	@Column(name= "tgl_lahir")@JsonProperty("tanggal-lahir")
	private String tanggal;
	
	@Column(name= "no_telp")@JsonProperty("no-telp")
	private String noTelp;
	
	@Column(name= "email")@JsonProperty("email")@JsonView(View.Show.class)
	private String email;

	@Column(name= "password")@JsonProperty("password")
	private String password;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("data-progdi")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_progdi", referencedColumnName = "id_progdi")
	private Progdi progdi;
	
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

	public String getTempat() {
		return tempat;
	}

	public void setTempat(String tempat) {
		this.tempat = tempat;
	}

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}

	public String getNoTelp() {
		return noTelp;
	}

	public void setNoTelp(String noTelp) {
		this.noTelp = noTelp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Progdi getProgdi() {
		return progdi;
	}

	public void setProgdi(Progdi progdi) {
		this.progdi = progdi;
	}
	
}
