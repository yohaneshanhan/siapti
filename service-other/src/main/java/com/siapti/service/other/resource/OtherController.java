package com.siapti.service.other.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.siapti.service.other.model.Dosen;
import com.siapti.service.other.model.Matakuliah;
import com.siapti.service.other.model.Progdi;
import com.siapti.service.other.model.Walistudi;
import com.siapti.service.other.service.OtherService;

@RestController
public class OtherController {
	//private static final Logger logger = LogManager.getLogger("CONTROLLER-OTHER");
	
	@Autowired
	OtherService service;
	
	/*	=== GET ALL PROGDI === */
	@GetMapping("/all-progdi")
	public @ResponseBody List<Progdi> listProgdi(){
		
		return service.dataProgdi();
		
	}
	
	/*	=== GET ALL DOSEN BY PROGDI === */
	@GetMapping("/all-dosen-by-progdi")
	public @ResponseBody List<Dosen> listDosen(@RequestParam String id_progdi){
		
		return service.dataDosen(id_progdi);
		
	}
	
	/*	=== GET ALL MATKUL BY PROGDI === */
	@GetMapping("/all-matakuliah-by-progdi")
	public @ResponseBody List<Matakuliah> listMatakuliah(@RequestParam String id_progdi){
		
		return service.dataMatakuliah(id_progdi);
		
	}
	
	/*	=== GET ALL WALISTUDI BY PROGDI === */
	@GetMapping("/all-walistudi-by-progdi")
	public @ResponseBody List<Walistudi> listWalistudi(@RequestParam String id_progdi){
		
		return service.dataWali(id_progdi);
		
	}
}
