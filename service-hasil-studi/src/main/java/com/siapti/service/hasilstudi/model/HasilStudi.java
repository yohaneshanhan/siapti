package com.siapti.service.hasilstudi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="hasil_studi")
public class HasilStudi {
	@Id
	@Column(name= "id")
	private int id;
	
	@JsonProperty("data-mahasiswa")
	@JsonView(View.Show.class)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nim_mhs", referencedColumnName = "nim_mhs")
	private Mahasiswa mahasiswa;
	
	@JsonProperty("data-jadwal")
	@JsonView(View.Show.class)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "kode_kelas", referencedColumnName = "kode_kelas")
	private JadwalKuliah jadwalkuliah;
	
	@JsonView(View.Show.class)
	@JsonProperty("aksara")
	private String aksara;
	
	@JsonProperty("release")
	private String release;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JadwalKuliah getJadwalkuliah() {
		return jadwalkuliah;
	}

	public void setJadwalkuliah(JadwalKuliah jadwalkuliah) {
		this.jadwalkuliah = jadwalkuliah;
	}

	public Mahasiswa getMahasiswa() {
		return mahasiswa;
	}

	public void setMahasiswa(Mahasiswa mahasiswa) {
		this.mahasiswa = mahasiswa;
	}

	public String getAksara() {
		return aksara;
	}

	public void setAksara(String aksara) {
		this.aksara = aksara;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}
	
}
