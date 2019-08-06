package com.siapti.service.transkrip.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siapti.service.transkrip.model.Transkrip;


public interface TranskripInterface extends JpaRepository<Transkrip,Integer> {
	Optional<Transkrip> findByKodeMatkul(String kode);
	
	List<Transkrip> findByNim(String nim);

}
