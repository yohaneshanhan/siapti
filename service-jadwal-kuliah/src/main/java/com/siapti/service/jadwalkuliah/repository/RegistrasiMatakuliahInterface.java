package com.siapti.service.jadwalkuliah.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siapti.service.jadwalkuliah.model.RegistrasiMatakuliah;

public interface RegistrasiMatakuliahInterface extends JpaRepository<RegistrasiMatakuliah,Integer> {
	//Optional<RegistrasiMatakuliah> findByJadwalkuliahKodeAndMahasiswaNim(String kode, String nim);
	
	List<RegistrasiMatakuliah> findByMahasiswaNimOrderByJadwalkuliahKode(String nim);
	
	
}
