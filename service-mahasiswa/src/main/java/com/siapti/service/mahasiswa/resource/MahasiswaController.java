package com.siapti.service.mahasiswa.resource;


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
import com.siapti.service.mahasiswa.model.Mahasiswa;
import com.siapti.service.mahasiswa.service.MahasiswaService;
import com.siapti.service.mahasiswa.model.OptionalOutputSchema;
import com.siapti.service.mahasiswa.model.PageOutputSchema;
import com.siapti.service.mahasiswa.model.View;

@RestController
public class MahasiswaController {

	private static final Logger logger = LogManager.getLogger("CONTROLLER-MAHASISWA");
	
	@Autowired
	MahasiswaService mhsService;
	
	@GetMapping(path = "/mahasiswa-connect")
	public HttpStatus connect(@RequestHeader(value="client-nm") String clientNM) {
		HttpStatus status = HttpStatus.OK;
		return status;
	}
	
	/*	=== GET ALL WITH PAGE === */
	@JsonView(View.Show.class)
	@GetMapping(path = "/mahasiswa")
	public PageOutputSchema allMahasiswa(@RequestHeader(value="client-nm") String clientNM,Pageable pageable) {
		logger.trace("FIND ALL Header Client : "+clientNM + " Param Pageable : " +pageable);
		return mhsService.allMahasiswa("siapti",pageable);
	}

	/*	=== GET SEARCH WITH PAGE === */
	@JsonView(View.Show.class)
	@GetMapping(path = "/mahasiswa/{nim}/{nama}")
	public PageOutputSchema searchMahasiswa(@RequestHeader(value="client-nm") String clientNM,@PathVariable("nim") String nim,@PathVariable("nama") String nama,
			Pageable pageable) {
		logger.trace("SEARCH Header Client : " +clientNM+ " Param Nim : " +nim+ " Param nama : " +nama);
		return mhsService.searchMahasiswa(clientNM,nim,nama,pageable);
	}
	
	/*	=== GET DETAIL === */
	@GetMapping("/mahasiswa/{nim}")
	public OptionalOutputSchema detailMahasiswa(@RequestHeader(value="client-nm") String clientNM, @PathVariable(value = "nim") String nim) {
		logger.trace("DETAIL Header Client : "+clientNM +" Param Nim : "+nim);
		
		return mhsService.detailMahasiswa(nim, clientNM);
	}
	
	/*	=== POST ADD === */
	@PostMapping(value = "/mahasiswa")
	public OptionalOutputSchema addMahasiswa(@RequestHeader(value="client-nm") String clientNM,@Valid @RequestBody Mahasiswa mahasiswa) {
		logger.trace("ADD");
		return mhsService.addMahasiswa(clientNM,mahasiswa);
	}
	
	/*@PutMapping("/mahasiswa/{nim}")
	public OptionalMahasiswaOutput updateStaff(@PathVariable("nim") Integer nim, @RequestBody Mahasiswa mahasiswaModel) {
		return mhsService.updateMahasiswa(nim, mahasiswaModel);
	}*/

	/*	=== DELETE === */
	@DeleteMapping("/mahasiswa/{nim}")
	public OptionalOutputSchema deleteMahasiswa(@RequestHeader(value="client-nm") String clientNM,@PathVariable("nim") String nim) {
		logger.trace("CONTROLLER DELETE Param Nim : " +nim);
		return mhsService.deleteMahasiswa(nim);
	}
	
	/*	=== GET MHS By Wali === */
	@JsonView(View.FindBy.class)
	@GetMapping("/mahasiswa-by-wali/{id}")
	public PageOutputSchema findByWali(@PathVariable(value = "id") String id,Pageable pageable) {
		logger.trace("FindByWali Param Nim : "+id);
		
		return mhsService.findMhsByWali(id,pageable);
	}
}
