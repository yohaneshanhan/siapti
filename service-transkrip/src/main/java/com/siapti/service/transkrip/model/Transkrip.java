package com.siapti.service.transkrip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="transkrip")
public class Transkrip {
	@Id
	@Column(name = "id")@JsonProperty("id")
	private int id;

	@Column(name = "nim_mhs")@JsonProperty("nim-mhs")
	private String nim;
	
	@Column(name = "kode_matakuliah")@JsonProperty("kode-matakuliah")
	private String kodeMatkul;
	
	@Column(name = "nama_matakuliah")@JsonProperty("nama-matakuliah")
	private String namaMatkul;
	
	@Column(name = "sks")@JsonProperty("sks")
	private int sks;
	
	@Column(name = "aksara")@JsonProperty("aksara")
	private String aksara;
	
	@Column(name = "semester")@JsonProperty("semester")
	private String semester;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNim() {
		return nim;
	}

	public void setNim(String nim) {
		this.nim = nim;
	}

	public String getKodeMatkul() {
		return kodeMatkul;
	}

	public void setKodeMatkul(String kodeMatkul) {
		this.kodeMatkul = kodeMatkul;
	}

	public String getNamaMatkul() {
		return namaMatkul;
	}

	public void setNamaMatkul(String namaMatkul) {
		this.namaMatkul = namaMatkul;
	}

	public int getSks() {
		return sks;
	}

	public void setSks(int sks) {
		this.sks = sks;
	}

	public String getAksara() {
		return aksara;
	}

	public void setAksara(String aksara) {
		this.aksara = aksara;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
	
}
