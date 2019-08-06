package com.siapti.service.transkrip.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.siapti.service.transkrip.service.TranskripService;
import com.siapti.service.transkrip.model.ListOutputSchema;
import com.siapti.service.transkrip.model.OptionalOutputSchema;
import com.siapti.service.transkrip.model.Transkrip;

@RestController
public class TranskripController {
	private static final Logger logger = LogManager.getLogger("CONTROLLER-TRANSKRIP");
	
	@Autowired
	TranskripService transkripService;
	
	@GetMapping(path = "/transkrip-connect")
	public HttpStatus connect(@RequestHeader(value="client-nm") String clientNM) {
		HttpStatus status = HttpStatus.OK;
		return status;
	}
	
	/*	=== GET ALL BY MHS === */
	@GetMapping(path = "/transkrip/{nim}")
	public ListOutputSchema<Transkrip> hasilstudiByMhs(@RequestHeader(value="client-nm") String clientNM,@PathVariable("nim") String nim) {
		logger.trace("GET DATA TRANSKRIP NILAI FOR MAHASISWA");
		return transkripService.allByMhs(clientNM,nim);
	}
	
	/*	=== RELEASE HASIL STUDI === */
	@PutMapping(path = "/transkrip-release")
	public OptionalOutputSchema<Transkrip> releaseHasilStudi() {
		logger.trace("RELEASE TRANSKRIP NILAI");
		return transkripService.release();
	}
}
