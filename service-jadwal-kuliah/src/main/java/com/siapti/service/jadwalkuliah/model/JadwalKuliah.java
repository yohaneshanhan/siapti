package com.siapti.service.jadwalkuliah.model;

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
@Table(name="jadwal_kuliah")
public class JadwalKuliah {
	@Id
	@Column(name= "kode_kelas")@JsonProperty("kode-kelas")@JsonView(View.Show.class)
	private String kode;
	
	@JsonProperty("data-matakuliah")
	@JsonView(View.Show.class)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "kode_matkul", referencedColumnName = "kode_matkul")
	private Matakuliah matakuliah;
	
	@JsonProperty("data-dosen")
	@JsonView(View.Show.class)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nip_dosen", referencedColumnName = "nip_dosen")
	private Dosen dosen;
	
	@Column(name= "hari")@JsonProperty("hari")@JsonView(View.By.class)
	private String hari;

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public Matakuliah getMatakuliah() {
		return matakuliah;
	}

	public void setMatakuliah(Matakuliah matakuliah) {
		this.matakuliah = matakuliah;
	}

	public Dosen getDosen() {
		return dosen;
	}

	public void setDosen(Dosen dosen) {
		this.dosen = dosen;
	}

	public String getHari() {
		return hari;
	}

	public void setHari(String hari) {
		this.hari = hari;
	}

	/*public JadwalKuliah(String kode, Matakuliah matakuliah, Dosen dosen, String hari) {
		this.kode = kode;
		this.matakuliah = matakuliah;
		this.dosen = dosen;
		this.hari = hari;
	}*/
	
}
