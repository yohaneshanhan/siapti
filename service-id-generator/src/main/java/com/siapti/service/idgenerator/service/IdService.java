package com.siapti.service.idgenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siapti.service.idgenerator.repository.IdRepository;

@Service
public class IdService {

	@Autowired
	IdRepository idRepository;
	
	public String staff(){
		
		String next_val = idRepository.next_staff();
		
		return next_val;
	}
	
	public String dosen(String progdi){
		
		String next_val = idRepository.next_dosen(progdi);
		
		return next_val;
	}
	
	public String mahasiswa(String progdi){
		
		String next_val = idRepository.next_mahasiswa(progdi);
		
		return next_val;
	}

	public String matakuliah(String progdi){
		
		String next_val = idRepository.next_matakuliah(progdi);
		
		return next_val;
	}

	public String jadwal_kuliah(String kode_matkul){
		
		String next_val = idRepository.next_jadwal_kuliah(kode_matkul);
		
		return next_val;
	}
	
}
