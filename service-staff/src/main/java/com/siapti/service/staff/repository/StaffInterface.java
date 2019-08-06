package com.siapti.service.staff.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siapti.service.staff.model.Staff;

@Repository
public interface StaffInterface extends JpaRepository<Staff,String> {
	Page<Staff> findAll(Pageable pageable);
	Page<Staff> findByNipContainingOrNamaContaining(Pageable pageable,String nip,String nama);
	
	Optional<Staff> findByNip(String nip);
	
	@Transactional
	void deleteByNip (String nip);
	
}