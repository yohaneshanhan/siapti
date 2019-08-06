package com.siapti.service.mahasiswa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siapti.service.mahasiswa.model.Walistudi;
@Repository
public interface WalistudiInterface extends JpaRepository<Walistudi,String> {
	
	Optional<Walistudi> findById(String id);
	
}
