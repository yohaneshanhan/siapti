package com.siapti.service.dosen.resource;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.siapti.service.dosen.model.Dosen;
import com.siapti.service.dosen.model.OptionalOutputSchema;
import com.siapti.service.dosen.model.PageOutputSchema;
import com.siapti.service.dosen.service.DosenService;
import com.siapti.service.dosen.model.View;

@RestController
public class DosenController {

	private static final Logger logger = LogManager.getLogger("CONTROLLER-DOSEN");
	
	@Autowired
	DosenService dosenService;
	
	@GetMapping(path = "/dosen-connect")
	public HttpStatus connect(@RequestHeader(value="client-nm") String clientNM) {
		HttpStatus status = HttpStatus.OK;
		return status;
	}
	
	/*	=== GET ALL WITH PAGE === */
	@JsonView(View.Show.class)
	@GetMapping(path = "/dosen")
	public PageOutputSchema allDosen(@RequestHeader(value="client-nm") String clientNM,Pageable pageable) {
		return dosenService.allDosen(clientNM,pageable);
	}
	
	/*	=== GET SEARCH WITH PAGE === */
	@JsonView(View.Show.class)
	@GetMapping(path = "/dosen/{nip}/{nama}")
	public PageOutputSchema searchDosen (@RequestHeader(value="client-nm") String clientNM,@PathVariable("nip") String nip,@PathVariable("nama") String nama,Pageable pageable) {
		logger.trace("SEARCH Header Client : " +clientNM+ " Param Nip : " +nip+ " Param nama : " +nama);
		
		return dosenService.searchDosen(clientNM,nip,nama,pageable);
	}
	
	/*	=== GET DETAIL === */
	@GetMapping("/dosen/{nip}")
	public OptionalOutputSchema detailDosen(@RequestHeader(value="client-nm") String clientNM, @PathVariable(value = "nip") String nip) {
		logger.trace("DETAIL Header Client : "+clientNM +" Param Nip : "+nip);
		
		return dosenService.detailDosen(nip, clientNM);
	}

	/*	=== POST ADD === */
	@PostMapping(value = "/dosen")
	public OptionalOutputSchema addDosen(@Valid @RequestBody Dosen dosen) {
		logger.trace("ADD");
		return dosenService.addDosen(dosen);
	}

	/*@PutMapping("/dosen/{nip}")
	public OptionalOutputSchema updateDosen(@PathVariable("nip") String nip, @RequestBody Dosen dosenModel) {
		return dosenService.updateDosen(nip, dosenModel);
	}*/
	
	/*	=== DELETE === */
	@DeleteMapping("/dosen/{nip}")
	public OptionalOutputSchema deleteDosen(@RequestHeader(value="client-nm") String clientNM,@PathVariable("nip") String nip) {
		logger.trace("CONTROLLER DELETE Param Nip : " +nip);
		return dosenService.deleteDosen(nip);
	}
	
	/*	=== GET DOSEN WS === */
	@GetMapping("/dosen-wali")
	public String dosenWali(@RequestParam String nip) {
		logger.trace("Controller Dosen Wali Param Nip : "+nip);
		
		return dosenService.checkIsWs(nip);
	}
}
