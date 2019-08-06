package com.siapti.service.resetpass.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siapti.service.resetpass.service.ResetPassService;

@RestController
public class ResetPassController {

	@Autowired
	ResetPassService service;
	
	@GetMapping(path = "/reset-password-connect")
	public HttpStatus connect(@RequestHeader(value="client-nm") String clientNM) {
		HttpStatus status = HttpStatus.OK;
		return status;
	}
	
	/*	=== RESET PASSWORD STAFF === */
	@PutMapping("/reset-pass-staff")
	public String resetStaff(@RequestParam String id) {
		
		return service.staff(id);
	}
	
	/*	=== RESET PASSWORD DOSEN === */
	@PutMapping("/reset-pass-dosen")
	public String resetDosen(@RequestParam String id) {
		
		return service.dosen(id);
	}
	
	/*	=== RESET PASSWORD MAHASISWA === */
	@PutMapping("/reset-pass-mahasiswa")
	public String resetMhs(@RequestParam String id) {
		
		return service.mhs(id);
	}
}
