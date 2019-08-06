package com.siapti.servicekota.Kota;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonView;

@Document(collection = "kota")
public class Kota implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    private ObjectId _id;

	@JsonView(View.Show.class)
    private String nama;

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Kota(String nama) {
		super();
		this.nama = nama;
	}

}