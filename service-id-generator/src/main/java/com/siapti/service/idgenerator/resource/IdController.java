package com.siapti.service.idgenerator.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.siapti.service.idgenerator.service.IdService;

@RestController
public class IdController {
	
	@Autowired
	IdService idService;
	
	/*	=== GET NEXT STAFF === */
	@GetMapping("/next-staff")
	public @ResponseBody String nextStaff() {
		
		return idService.staff();
	}
	
	/*	=== GET NEXT DOSEN === */
	@GetMapping("/next-dosen/{id}")
	public @ResponseBody String nextDosen(@PathVariable(value = "id") String id) {
		
		return idService.dosen(id);
	}
	
	/*	=== GET NEXT MAHASISWA === */
	@GetMapping("/next-mahasiswa/{id}")
	public @ResponseBody String nextMahasiswa(@PathVariable(value = "id") String id) {
		
		return idService.mahasiswa(id);
	}

	/*	=== GET NEXT MATAKULIAH === */
	@GetMapping("/next-matakuliah/{id}")
	public @ResponseBody String nextMatakuliah(@PathVariable(value = "id") String id) {
		
		return idService.matakuliah(id);
	}

	/*	=== GET NEXT JADWAL KULIAH === */
	@GetMapping("/next-jadwalkuliah/{id}")
	public @ResponseBody String nextJadwalkuliah(@PathVariable(value = "id") String id) {
		
		return idService.jadwal_kuliah(id);
	}
}
