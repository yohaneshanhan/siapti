package com.siapti.service.walistudi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siapti.service.walistudi.model.WaliStudi;

@Repository
public interface WaliStudiInterface extends JpaRepository<WaliStudi,String> {
	List<WaliStudi> findAll();
	Optional<WaliStudi> findById(String id);
	Optional<WaliStudi> findByDosenNip(String nip);
	
	Page<WaliStudi> findAll(Pageable pageable);
	//WaliStudi findAll();
	Page<WaliStudi> findByIdContainingOrDosenNamaContaining(Pageable pageable,String id,String nama);
	
}
