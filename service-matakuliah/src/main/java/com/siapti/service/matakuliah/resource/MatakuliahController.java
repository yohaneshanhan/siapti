package com.siapti.service.matakuliah.resource;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.siapti.service.matakuliah.model.Matakuliah;
import com.siapti.service.matakuliah.model.OptionalOutputSchema;
import com.siapti.service.matakuliah.model.PageOutputSchema;
import com.siapti.service.matakuliah.service.MatakuliahService;

@RestController
public class MatakuliahController {
	
	private static final Logger logger = LogManager.getLogger("CONTROLLER-MATAKULIAH");
	
	@Autowired
	MatakuliahService mkService;
	
	@GetMapping(path = "/matakuliah-connect")
	public HttpStatus connect(@RequestHeader(value="client-nm") String clientNM) {
		HttpStatus status = HttpStatus.OK;
		return status;
	}
	
	/*	=== GET ALL WITH PAGE === */
	@GetMapping(path = "/matakuliah")
	public PageOutputSchema allDosen(@RequestHeader(value="client-nm") String clientNM,Pageable pageable) {
		return mkService.allMatakuliah(clientNM,pageable);
	}
	
	/*	=== GET SEARCH WITH PAGE === */
	@GetMapping(path = "/matakuliah/{kode}/{nama}")
	public PageOutputSchema searchMatakuliah(@RequestHeader(value="client-nm") String clientNM,@PathVariable("kode") String kode,@PathVariable("nama") String nama,Pageable pageable) {
		logger.trace("SEARCH Header Client : " +clientNM+ " Param Kode : " +kode+ " Param nama : " +nama);
		
		return mkService.searchMatakuliah(clientNM,kode,nama,pageable);
	}
	
	/*	=== GET DETAIL === */
	@GetMapping("/matakuliah/{kode}")
	public OptionalOutputSchema detailMatakuliah(@RequestHeader(value="client-nm") String clientNM, @PathVariable(value = "kode") String kode) {
		logger.trace("DETAIL Header Client : "+clientNM +" Param Kode : "+kode);
		
		return mkService.detailMatakuliah(kode, clientNM);
	}
	
	/*	=== POST ADD === */
	@PostMapping(value = "/matakuliah")
	public OptionalOutputSchema addMatakuliah(@Valid @RequestBody Matakuliah matakuliah) {
		logger.trace("ADD Matakuliah");
		
		return mkService.addMatakuliah(matakuliah);
	}
	
	/*	=== DELETE === */
	@DeleteMapping("/matakuliah/{kode}")
	public OptionalOutputSchema deleteMatakuliah(@RequestHeader(value="client-nm") String clientNM,@PathVariable("kode") String kode) {
		logger.trace("CONTROLLER DELETE Param Kode : " +kode);
		return mkService.deleteMatakuliah(kode);
	}
	
}
