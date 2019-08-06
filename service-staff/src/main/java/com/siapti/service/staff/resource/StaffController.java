package com.siapti.service.staff.resource;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.siapti.service.staff.model.PageOutputSchema;
import com.siapti.service.staff.model.OptionalOutputSchema;
import com.siapti.service.staff.model.Staff;
import com.siapti.service.staff.model.View;
import com.siapti.service.staff.service.StaffService;

@RestController
public class StaffController {
	
	private static final Logger logger = LogManager.getLogger("CONTROLLER-STAFF");
	
	@Autowired
	StaffService staffService;
	
	@GetMapping(path = "/staff-connect")
	public HttpStatus connect(@RequestHeader(value="client-nm") String clientNM) {
		HttpStatus status = HttpStatus.OK;
		return status;
	}
	
	/*	=== GET ALL WITH PAGE === */
	@JsonView(View.Show.class)
	@GetMapping(path = "/staff")
	public PageOutputSchema allStaff(@RequestHeader(value="client-nm") String clientNM,Pageable pageable) {
		logger.trace("FIND ALL Header Client : "+clientNM + " Param Pageable : " +pageable);
		return staffService.allStaff("siapti",pageable);
	}
	
	/*	=== GET SEARCH WITH PAGE === */
	@JsonView(View.Show.class)
	@GetMapping(path = "/staff/{nip}/{nama}")
	public PageOutputSchema searchStaff(@RequestHeader(value="client-nm") String clientNM,@PathVariable("nip") String nip,@PathVariable("nama") String nama,Pageable pageable) {
		logger.trace("SEARCH Header Client : " +clientNM+ " Param Nip : " +nip+ " Param nama : " +nama);
		return staffService.searchStaff(clientNM,nip,nama,pageable);
	}
	
	/*	=== GET DETAIL === */
	@GetMapping("/staff/{nip}")
	public OptionalOutputSchema detailStaff(@RequestHeader(value="client-nm") String clientNM, @PathVariable(value = "nip") String nip) {
		logger.trace("DETAIL Header Client : "+clientNM +" Param Nip : "+nip);
		
		return staffService.detailStaff(nip, clientNM);
	}

	/*	=== POST ADD === */
	@PostMapping(value = "/staff")
	public OptionalOutputSchema addStaff(@Valid @RequestBody Staff staff) {
		logger.trace("ADD");
		return staffService.addStaff(staff);
	}
	
	/*@PutMapping("/staff/{nip}")
	public OptionalStaffOutput updateStaff(@PathVariable("nip") String nip, @RequestBody Staff staff) {
		return staffService.updateStaff(nip, staff);
	}*/
	
	/*	=== DELETE === */
	@DeleteMapping("/staff/{nip}")
	public OptionalOutputSchema deleteStaff(@RequestHeader(value="client-nm") String clientNM,@PathVariable("nip") String nip) {
		logger.trace("CONTROLLER DELETE Param Nip : " +nip);
		return staffService.deleteStaff(nip);
	}
	
}