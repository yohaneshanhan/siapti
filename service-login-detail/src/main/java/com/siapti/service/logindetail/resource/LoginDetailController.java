package com.siapti.service.logindetail.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.siapti.service.logindetail.model.Dosen;
import com.siapti.service.logindetail.model.Mahasiswa;
import com.siapti.service.logindetail.model.Staff;
import com.siapti.service.logindetail.service.LoginDetailService;

@RestController
public class LoginDetailController {
	
	private static final Logger logger = LogManager.getLogger("CONTROLLER-LOGIN-DETAIL");
	
	@Autowired
	LoginDetailService service;
	
	/*	=== GET DETAIL === */
	@GetMapping("/login-staff/{nip}")
	public Staff detailStaff(@PathVariable(value = "nip") String nip) {
		logger.trace("DETAIL Param Nip : "+nip);
		
		return service.detailStaff(nip);
	}
	
	/*	=== GET DETAIL === */
	@GetMapping("/login-dosen/{nip}")
	public Dosen detailDosen(@PathVariable(value = "nip") String nip) {
		logger.trace("DETAIL Param Nip : "+nip);
		
		return service.detailDosen(nip);
	}
	
	/*	=== GET DETAIL === */
	@GetMapping("/login-mahasiswa/{nim}")
	public Mahasiswa detailMahasiswa(@PathVariable(value = "nim") String nim) {
		logger.trace("DETAIL Param Nim : "+nim);
		
		return service.detailMahasiswa(nim);
	}
}
