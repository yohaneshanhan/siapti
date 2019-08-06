package com.siapti.service.other.service;

import java.util.ArrayList;
import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siapti.service.other.model.Dosen;
import com.siapti.service.other.model.Matakuliah;
import com.siapti.service.other.model.Progdi;
import com.siapti.service.other.model.Walistudi;
import com.siapti.service.other.repository.OtherRepository;

@Service
public class OtherService {
	//private static final Logger logger = LogManager.getLogger("SERVICE-OTHER");
	
	@Autowired
	OtherRepository otherRepository;
	
	// ====================== ALL PROGDI =====================
	public List<Progdi> dataProgdi(){
		System.out.println("MASUK SERVER SERVICE DAFTAR PROGDI");
		List<Progdi> data = new ArrayList<Progdi>();
		@SuppressWarnings("unchecked")
		List<Object[]> obj = otherRepository.listProgdi();
		 
		for (Object[] a : obj) {
		    System.out.println("Progdi "
		            + a[0].toString()
		            + " "
		            + a[1].toString());
		    data.add(new Progdi(a[0].toString(),a[1].toString()));
		}
		return data;
	}

	// ====================== ALL DOSEN =====================
	public List<Dosen> dataDosen(String id_progdi){
		System.out.println("MASUK SERVER SERVICE DAFTAR DOSEN BY PROGDI");
		List<Dosen> data = new ArrayList<Dosen>();
		@SuppressWarnings("unchecked")
		List<Object[]> obj = otherRepository.listByProgdi(id_progdi,"sel_dosen_by_progdi");
		
		for (Object[] a : obj) {
			System.out.println("Dosen "
					+ a[0].toString()
					+ " "
					+ a[1].toString());
			data.add(new Dosen(a[0].toString(),a[1].toString()));
		}
		return data;
	}
	
	// ====================== ALL MATAKULIAH =====================
	public List<Matakuliah> dataMatakuliah(String id_progdi){
		System.out.println("MASUK SERVER SERVICE DAFTAR MATAKULIAH BY PROGDI");
		List<Matakuliah> data = new ArrayList<Matakuliah>();
		@SuppressWarnings("unchecked")
		List<Object[]> obj = otherRepository.listByProgdi(id_progdi,"sel_matkul_by_progdi");
		
		for (Object[] a : obj) {
			System.out.println("Matakuliah "
					+ a[0].toString()
					+ " "
					+ a[1].toString());
			data.add(new Matakuliah(a[0].toString(),a[1].toString(),a[2].toString()));
		}
		return data;
	}
	
	// ====================== ALL WALISTUDI BY PROGDI =====================
	public List<Walistudi> dataWali(String id_progdi){
		System.out.println("MASUK SERVER SERVICE DAFTAR WALISTUDI BY WALI");
		List<Walistudi> data = new ArrayList<Walistudi>();
		@SuppressWarnings("unchecked")
		List<Object[]> obj = otherRepository.listByProgdi(id_progdi,"sel_ws_by_progdi");
		
		for (Object[] a : obj) {
			System.out.println("Wali Studi "
					+ a[0].toString()
					+ " "
					+ a[1].toString());
			data.add(new Walistudi(a[0].toString(),a[1].toString()));
		}
		return data;
	}
}
