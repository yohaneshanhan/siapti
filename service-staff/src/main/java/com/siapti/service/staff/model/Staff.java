package com.siapti.service.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="staff")
public class Staff {
	@Id//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "nip_staff")@JsonProperty("nip-staff")@JsonView(View.Show.class)
	private String nip;
	
	@Column(name = "nama_staff")@JsonProperty("nama-staff")@JsonView(View.Show.class)
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
	
}
