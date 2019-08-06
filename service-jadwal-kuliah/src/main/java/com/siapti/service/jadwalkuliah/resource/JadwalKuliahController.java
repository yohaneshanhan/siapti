package com.siapti.service.jadwalkuliah.resource;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.siapti.service.jadwalkuliah.model.JadwalKuliah;
import com.siapti.service.jadwalkuliah.model.ListOutputSchema;
import com.siapti.service.jadwalkuliah.model.OptionalOutputSchema;
import com.siapti.service.jadwalkuliah.model.PageOutputSchema;
import com.siapti.service.jadwalkuliah.model.RegistrasiMatakuliah;
import com.siapti.service.jadwalkuliah.model.View;
import com.siapti.service.jadwalkuliah.service.JadwalKuliahService;

@FeignClient(name="service-jadwal-kuliah" )
@RibbonClient(name="service-jadwal-kuliah")
@RestController
public class JadwalKuliahController {
	
	private static final Logger logger = LogManager.getLogger("CONTROLLER-JADWAL-KULIAH");
	
	@Autowired
	JadwalKuliahService jadwalkuliahService;
	
	@GetMapping(path = "/jadwal-kuliah-connect")
	public HttpStatus connect(@RequestHeader(value="client-nm") String clientNM) {
		HttpStatus status = HttpStatus.OK;
		return status;
	}
	
	/*	=== GET ALL BY DOSEN === */
	@JsonView(View.By.class)
	@GetMapping(path = "/jadwal-kuliah-by-dosen/{nip}")
	public ListOutputSchema<JadwalKuliah> jadwalKuliahbyDosen(@RequestHeader(value="client-nm") String clientNM,@PathVariable("nip") String nip) {
		return jadwalkuliahService.allJadwalKuliahbyDosen(clientNM,nip);
	}
	
	/*	=== GET ALL BY PROGDI MHS === */
	@JsonView(View.By.class)
	@GetMapping(path = "/jadwal-kuliah-by-progdi-mhs/{id}")
	public ListOutputSchema<JadwalKuliah> jadwalKuliahbyProgdiMhs(@RequestHeader(value="client-nm") String clientNM,@PathVariable("id") String id) {
		return jadwalkuliahService.allJadwalKuliahbyProgdiMhs(clientNM,id);
	}
	
	/*	=== GET ALL BY NIM MHS === */
	@JsonView(View.By.class)
	@GetMapping(path = "/jadwal-kuliah-by-mhs/{nim}")
	public ListOutputSchema<RegistrasiMatakuliah> jadwalKuliahByMhs(@RequestHeader(value="client-nm") String clientNM,@PathVariable("nim") String nim) {
		return jadwalkuliahService.allJadwalKuliahByMhs(clientNM,nim);
	}

	/*	=== GET ALL WITH PAGE === */
	@JsonView(View.Show.class)
	@GetMapping(path = "/jadwal-kuliah")
	public PageOutputSchema allJadwalKuliah(@RequestHeader(value="client-nm") String clientNM,Pageable pageable) {
		return jadwalkuliahService.allJadwalKuliah(clientNM,pageable);
	}
	
	/*	=== GET SEARCH WITH PAGE === */
	@JsonView(View.Show.class)
	@GetMapping(path = "/jadwal-kuliah/{kode}/{nama}")
	public PageOutputSchema searchJadwalKuliah (@RequestHeader(value="client-nm") String clientNM,@PathVariable("kode") String kode,@PathVariable("nama") String nama,Pageable pageable) {
		logger.trace("SEARCH Header Client : " +clientNM+ " Param Nip : " +kode+ " Param nama : " +nama);
		
		return jadwalkuliahService.searchJadwalKuliah(clientNM,kode,nama,pageable);
	}
	
	/*	=== GET DETAIL === */
	@JsonView(View.By.class)
	@GetMapping("/jadwal-kuliah/{kode}")
	public OptionalOutputSchema detailJadwalKuliah(@RequestHeader(value="client-nm") String clientNM, @PathVariable(value = "kode") String kode) {
		logger.trace("DETAIL Header Client : "+clientNM +" Param Nip : "+kode);
		
		return jadwalkuliahService.detailJadwalKuliah(kode, clientNM);
	}

	/*	=== POST ADD === */
	@PostMapping(value = "/jadwal-kuliah")
	public OptionalOutputSchema addJadwalKuliah(@Valid @RequestBody JadwalKuliah jadwalkuliah) {
		logger.trace("ADD");
		return jadwalkuliahService.addJadwalKuliah(jadwalkuliah);
	}

	/*@PutMapping("/jadwal-kuliah/{nip}")
	public OptionalOutputSchema updateJadwalKuliah(@PathVariable("nip") String nip, @RequestBody JadwalKuliah dosenModel) {
		return dosenService.updateJadwalKuliah(nip, dosenModel);
	}*/
	
	/*	=== DELETE === */
	@DeleteMapping("/jadwal-kuliah/{kode}")
	public OptionalOutputSchema deleteJadwalKuliah(@RequestHeader(value="client-nm") String clientNM,@PathVariable("kode") String kode) {
		logger.trace("CONTROLLER DELETE Param Kode : " +kode);
		return jadwalkuliahService.deleteJadwalKuliah(kode);
	}
	
	/*	=== DROP HASIL STUDI === */
	@DeleteMapping(path = "/jadwal-kuliah-drop")
	public OptionalOutputSchema dropJadwalKuliah() {
		logger.trace("DROP HASIL STUDI");
		return jadwalkuliahService.drop();
	}
}
