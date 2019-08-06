package com.siapti.service.hasilstudi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siapti.service.hasilstudi.model.HasilStudi;


public interface HasilStudiInterface extends JpaRepository<HasilStudi,Integer> {
	Optional<HasilStudi> findByJadwalkuliahKodeAndMahasiswaNim(String kode, String nim);
	
	List<HasilStudi> findByMahasiswaNimAndRelease(String nim,String release);

	List<HasilStudi> findByJadwalkuliahKodeAndJadwalkuliahDosenNip(String kode,String nip);
	
}
