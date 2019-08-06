package com.siapti.service.registrasimatakuliah.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siapti.service.registrasimatakuliah.model.RegistrasiMatakuliah;

public interface RegistrasiMatakuliahInterface extends JpaRepository<RegistrasiMatakuliah,Integer> {
	Optional<RegistrasiMatakuliah> findByJadwalkuliahKodeAndMahasiswaNim(String kode, String nim);
	
	Optional<RegistrasiMatakuliah> findByJadwalkuliahMatakuliahKodeAndMahasiswaNim(String kode2,String nim);

	List<RegistrasiMatakuliah> findByMahasiswaNim(String nim);
	
}
