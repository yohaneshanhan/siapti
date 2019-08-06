package com.siapti.service.logindetail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siapti.service.logindetail.model.Dosen;

@Repository
public interface DosenInterface extends JpaRepository<Dosen,String> {
	Dosen findByNip(String nip);
}
