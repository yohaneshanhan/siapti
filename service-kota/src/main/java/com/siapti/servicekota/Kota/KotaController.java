package com.siapti.servicekota.Kota;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class KotaController {

	@Autowired
	KotaService service;
	
	/*	=== AUTOCOMPLETE KOTA === */
	@GetMapping("/autocomplete")
	@JsonView(View.Show.class)
	public @ResponseBody List<Kota> autocomplete_kota(@RequestParam String input_kota) {
		System.out.println("Input Kota : "+input_kota);	
		input_kota = input_kota.toUpperCase();
		
		return service.dataKota(input_kota);
	}
	
}
