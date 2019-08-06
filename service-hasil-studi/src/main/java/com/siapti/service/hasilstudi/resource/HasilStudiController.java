package com.siapti.service.hasilstudi.resource;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.siapti.service.hasilstudi.model.HasilStudi;
import com.siapti.service.hasilstudi.model.ListOutputSchema;
import com.siapti.service.hasilstudi.model.OptionalOutputSchema;
import com.siapti.service.hasilstudi.model.View;
import com.siapti.service.hasilstudi.service.HasilStudiService;

@RestController
public class HasilStudiController {

	private static final Logger logger = LogManager.getLogger("CONTROLLER-HASIL-STUDI");
	
	@Autowired
	HasilStudiService hasilStudiService;
	
	@GetMapping(path = "/hasil-studi-connect")
	public HttpStatus connect(@RequestHeader(value="client-nm") String clientNM) {
		HttpStatus status = HttpStatus.OK;
		return status;
	}
	
	/*	=== GET ALL BY DOSEN === */
	@JsonView(View.ByDosen.class)
	@GetMapping(path = "/input-nilai/{nip}/{kode}")
	public ListOutputSchema<HasilStudi> inputNilaibyDosenAndKodeKelas(@RequestHeader(value="client-nm") String clientNM,@PathVariable("nip") String nip,@PathVariable("kode") String kode) {
		logger.trace("GET DATA FOR DOSEN");
		return hasilStudiService.allMhsbyDosenAndKodeKelas(clientNM,nip,kode);
	}

	/*	=== GET ALL BY MHS === */
	@JsonView(View.ByDosen.class)
	@GetMapping(path = "/hasil-studi/{nim}")
	public ListOutputSchema<HasilStudi> hasilstudiByMhs(@RequestHeader(value="client-nm") String clientNM,@PathVariable("nim") String nim) {
		logger.trace("GET DATA FOR MAHASISWA");
		return hasilStudiService.allByMhs(clientNM,nim);
	}

	/*	=== INPUT NILAI MHS BY DOSEN AND KODE KELAS === */
	@PutMapping(path = "/input-nilai/")
	public OptionalOutputSchema<HasilStudi> inputNilaiMhsByDosen(@Valid @RequestBody HasilStudi hasilStudi) {
		logger.trace("INPUT NILAI MAHASISWA");
		return hasilStudiService.inputNilaiMhsByDosen(hasilStudi);
	}

	/*	=== RELEASE HASIL STUDI === */
	@PutMapping(path = "/hasil-studi-release")
	public OptionalOutputSchema<HasilStudi> releaseHasilStudi() {
		logger.trace("RELEASE HASIL STUDI");
		return hasilStudiService.release();
	}

	/*	=== DROP HASIL STUDI === */
	@DeleteMapping(path = "/hasil-studi-drop")
	public OptionalOutputSchema<HasilStudi> dropHasilStudi() {
		logger.trace("DROP HASIL STUDI");
		return hasilStudiService.drop();
	}
}
