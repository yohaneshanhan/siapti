package com.siapti.service.mahasiswa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siapti.service.mahasiswa.model.Progdi;
@Repository
public interface ProgdiInterface extends JpaRepository<Progdi,String> {
	
	Optional<Progdi> findById(String id);
	
}
