package com.siapti.service.logindetail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siapti.service.logindetail.model.Mahasiswa;
@Repository
public interface MahasiswaInterface extends JpaRepository<Mahasiswa,String> {
	Mahasiswa findByNim(String nim);
}
