package com.siapti.service.registrasimatakuliah.resource;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.netflix.ribbon.apache.HttpClientStatusCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.siapti.service.registrasimatakuliah.model.OptionalOutputSchema;
import com.siapti.service.registrasimatakuliah.model.RegistrasiMatakuliah;
import com.siapti.service.registrasimatakuliah.service.RegistrasiMatakuliahService;

@RestController
public class RegistrasiMatakuliahController {
	private static final Logger logger = LogManager.getLogger("CONTROLLER-REGISTRASI-MATAKULIAH");
	
	@Autowired
	RegistrasiMatakuliahService regismatkulService;
	
	@GetMapping(path = "/registrasi-matakuliah-connect")
	public HttpStatus connect(@RequestHeader(value="client-nm") String clientNM) {
		HttpStatus status = HttpStatus.OK;
		return status;
	}
	
	/*	=== POST ADD === */
	@PostMapping(value = "/registrasi-matakuliah")
	public OptionalOutputSchema addRegisMatkulMhs(@RequestHeader(value="client-nm") String clientNM,@Valid @RequestBody RegistrasiMatakuliah regisMatkul) {
		logger.trace("ADD");
		return regismatkulService.addRegisMhs(clientNM,regisMatkul);
	}
	
	/*	=== DELETE === */
	@DeleteMapping("/registrasi-matakuliah/{kode}/{nim}")
	public OptionalOutputSchema deleteRegisMatkulMhs(@RequestHeader(value="client-nm") String clientNM,@PathVariable("kode") String kode,@PathVariable("nim") String nim) {
		logger.trace("CONTROLLER DELETE Param Kode : " +kode);
		return regismatkulService.deleteRegisMhs(kode,nim);
	}
}
