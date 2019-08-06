package com.siapti.service.matakuliah.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siapti.service.matakuliah.model.Matakuliah;

@Repository
public interface MatakuliahInterface extends JpaRepository<Matakuliah,String> {
	Page<Matakuliah> findAll(Pageable pageable);
	Page<Matakuliah> findByKodeContainingOrNamaContaining(Pageable pageable,String kode,String nama);
	
	Optional<Matakuliah> findByKode(String kode);
	//Optional<Dosen> saveAndFlush(Dosen entity);
	
	@Transactional
	void deleteByKode (String kode);
}
