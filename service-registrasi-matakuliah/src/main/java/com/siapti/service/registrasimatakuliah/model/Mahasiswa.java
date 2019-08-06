package com.siapti.service.registrasimatakuliah.model;

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
@Table(name="mahasiswa")
public class Mahasiswa {
	@Id
	@Column(name= "nim_mhs")@JsonProperty("nim")
	private String nim;
	
	@Column(name = "nama_mhs")@JsonProperty("nama-mahasiswa")
	private String nama;
	
	@Column(name= "tempat_lahir")@JsonProperty("tempat-lahir")
	private String tempat_lahir;
	
	@Column(name= "tgl_lahir")@JsonProperty("tanggal-lahir")
	private String tanggal;
	
	@Column(name= "no_telp")@JsonProperty("no-telp")
	private String noTelp;
	
	@Column(name= "email")@JsonProperty("email")@JsonView(View.Show.class)
	private String email;

	@Column(name= "angkatan")@JsonProperty("angkatan")
	private String angkatan;

	@Column(name= "password")@JsonProperty("password")
	private String password;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("data-wali-studi")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_wali_studi", referencedColumnName = "id_wali_studi")
	private Walistudi walistudi;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("data-progdi")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_progdi", referencedColumnName = "id_progdi")
	private Progdi progdi;

	public String getNim() {
		return nim;
	}

	public void setNim(String nim) {
		this.nim = nim;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getTempat_lahir() {
		return tempat_lahir;
	}

	public void setTempat_lahir(String tempat_lahir) {
		this.tempat_lahir = tempat_lahir;
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

	public String getAngkatan() {
		return angkatan;
	}

	public void setAngkatan(String angkatan) {
		this.angkatan = angkatan;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Walistudi getWalistudi() {
		return walistudi;
	}

	public void setWalistudi(Walistudi walistudi) {
		this.walistudi = walistudi;
	}

	public Progdi getProgdi() {
		return progdi;
	}

	public void setProgdi(Progdi progdi) {
		this.progdi = progdi;
	}

}
