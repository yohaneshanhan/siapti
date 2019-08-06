package com.siapti.service.logindetail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siapti.service.logindetail.model.Staff;

@Repository
public interface StaffInterface extends JpaRepository<Staff,String> {
	Staff findByNip(String nip);
}