package com.siapti.service.dosen.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siapti.service.dosen.model.Dosen;

@Repository
public interface DosenInterface extends JpaRepository<Dosen,String> {
	Page<Dosen> findAll(Pageable pageable);
	Page<Dosen> findByNipContainingOrNamaContaining(Pageable pageable,String nip,String nama);
	
	Optional<Dosen> findByNip(String nip);
	//Optional<Dosen> saveAndFlush(Dosen entity);
	
	@Transactional
	void deleteByNip (String nip);
}
