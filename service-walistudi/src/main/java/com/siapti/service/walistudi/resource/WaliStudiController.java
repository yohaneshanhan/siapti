package com.siapti.service.walistudi.resource;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.siapti.service.walistudi.model.PageOutputSchema;
import com.siapti.service.walistudi.model.View;
import com.fasterxml.jackson.annotation.JsonView;
import com.siapti.service.walistudi.model.OptionalOutputSchema;
import com.siapti.service.walistudi.service.WaliStudiService;

@RestController
public class WaliStudiController {
	
	private static final Logger logger = LogManager.getLogger("CONTROLLER-WALISTUDI");
	
	@Autowired
	WaliStudiService walistudiService;

	@GetMapping(path = "/walistudi-connect")
	public HttpStatus connect(@RequestHeader(value="client-nm") String clientNM) {
		HttpStatus status = HttpStatus.OK;
		return status;
	}
	
	/*	=== GET ALL WITH PAGE === */
	@JsonView(View.Show.class)
	@GetMapping(path = "/walistudi")
	public PageOutputSchema allWaliStudi(@RequestHeader(value="client-nm") String clientNM,Pageable pageable) {
		logger.trace("FIND ALL Header Client : "+clientNM + " Param Pageable : " +pageable);
		return walistudiService.allWalistudi("siapti",pageable);
	}
	
	/*	=== GET SEARCH WITH PAGE === */
	@GetMapping(path = "/walistudi/{id}/{nama}")
	public PageOutputSchema searchWaliStudi(@RequestHeader(value="client-nm") String clientNM,@PathVariable("id") String id,@PathVariable("nama") String nama,Pageable pageable) {
		logger.trace("SEARCH Header Client : " +clientNM+ " Param Nip : " +id+ " Param nama : " +nama);
		return walistudiService.searchWalistudi(clientNM,id,nama,pageable);
	}
	
	/*	=== GET DETAIL === */
	@GetMapping("/walistudi/{id}")
	public OptionalOutputSchema detailWaliStudi(@RequestHeader(value="client-nm") String clientNM, @PathVariable(value = "id") String id) {
		logger.trace("DETAIL Header Client : "+clientNM +" Param Nip : "+id);
		
		return walistudiService.detailWalistudi(id, clientNM);
	}
	
	//	=== POST ADD === 
	@JsonView(View.Show.class)
	@PostMapping(value = "/walistudi/{nip_dosen}")
	public OptionalOutputSchema addWaliStudi(@RequestHeader(value="client-nm") String clientNM, @PathVariable(value = "nip_dosen") String nip_dosen) {
		logger.trace("ADD Body ID : ");
		return walistudiService.addWalistudi(clientNM,nip_dosen);
	}
/*	//	=== POST ADD === 
	@JsonView(View.Show.class)
	@PostMapping(value = "/walistudi")
	public OptionalOutputSchema addWaliStudi(@RequestHeader(value="client-nm") String clientNM,@Valid @RequestBody WaliStudi walistudi) {
		logger.trace("ADD Body ID : ");
		return walistudiService.addWalistudi(clientNM,walistudi);
	}
*/
	/*	=== DELETE === */
	@DeleteMapping("/walistudi/{id}")
	public OptionalOutputSchema deleteStaff(@RequestHeader(value="client-nm") String clientNM,@PathVariable("id") String id) {
		logger.trace("CONTROLLER DELETE Param Nip : " +id);
		return walistudiService.deleteWalistudi(id);
	}
	
}
