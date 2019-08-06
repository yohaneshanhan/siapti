package com.siapti.service.jadwalkuliah.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siapti.service.jadwalkuliah.model.JadwalKuliah;

@Repository
public interface JadwalKuliahInterface extends JpaRepository<JadwalKuliah,String> {
	Page<JadwalKuliah> findAll(Pageable pageable);
	Page<JadwalKuliah> findByKodeContainingOrMatakuliahNamaContaining(Pageable pageable,String kode,String nama);
	
	List<JadwalKuliah> findAllByDosenNip(String nip);

	List<JadwalKuliah> findAllByMatakuliahProgdiId(String nip);
	
	Optional<JadwalKuliah> findByKode(String kode);
	Optional<JadwalKuliah> findByMatakuliahKodeAndDosenNipAndHari(String kode,String nip,String hari);
	
	@Transactional
	void deleteByKode (String kode);
}
