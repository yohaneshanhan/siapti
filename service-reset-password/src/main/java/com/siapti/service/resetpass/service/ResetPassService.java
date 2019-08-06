package com.siapti.service.resetpass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.siapti.service.resetpass.repository.ResetPassRepository;

@Service
public class ResetPassService {

	@Autowired
	ResetPassRepository repository;
	
	public String staff(String id){
		
		String pass = repository.get_default_staff(id);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(pass);
		
		int res = repository.update_pass_staff(hashedPassword, id);
		String code = Integer.toString(res);
		
		return code;
	}
	
	public String dosen(String id){
		
		String pass = repository.get_default_dosen(id);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(pass);
		
		int res = repository.update_pass_dosen(hashedPassword, id);
		String code = Integer.toString(res);
		
		return code;
	}
	
	public String mhs(String id){
		
		String pass = repository.get_default_mhs(id);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(pass);
		
		int res = repository.update_pass_mhs(hashedPassword, id);
		String code = Integer.toString(res);
		
		return code;
	}
}
