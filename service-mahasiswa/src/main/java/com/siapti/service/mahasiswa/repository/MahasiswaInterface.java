package com.siapti.service.mahasiswa.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siapti.service.mahasiswa.model.Mahasiswa;
@Repository
public interface MahasiswaInterface extends JpaRepository<Mahasiswa,String> {
	Page<Mahasiswa> findAll(Pageable pageable);
	Page<Mahasiswa> findByNimContainingOrNamaContaining(Pageable pageable,String nip,String nama);
	
	Optional<Mahasiswa> findByNim(String nim);
	Optional<Mahasiswa> findByProgdiId(String id);
	Page<Mahasiswa> findByWalistudiId(Pageable pageable, String id);
	
	@Transactional
	void deleteByNim (String nim);
}
