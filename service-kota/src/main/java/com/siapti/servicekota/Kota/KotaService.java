package com.siapti.servicekota.Kota;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class KotaService {
	@Autowired
	KotaRepository otherRepository;
	
	@Autowired
	KotaRepo kotaRepo;
	
	MongoOperations mongoOperations; 
	
	// ====================== ALL KOTA =======================
	public List<Kota> dataKota(String kota){
		System.out.println("MASUK SERVICE DAFTAR KOTA");
		List<Kota> data = new ArrayList<Kota>();
		
		data = otherRepository.findByNamaContaining(kota);
		for (int i = 0 ; i < data.size(); i++) {
			String getKota = data.get(i).getNama();
			String formatKota;
			if (getKota.contains("KOTA")) {
				formatKota = getKota.replaceAll("KOTA ", "");
				formatKota = formatKota.toLowerCase();
				formatKota = format_kapital(formatKota);
				data.get(i).setNama(formatKota);
			}else if (getKota.contains("KABUPATEN")) {
				formatKota = getKota.replaceAll("KABUPATEN ", "KAB. ");
				formatKota = formatKota.toLowerCase();
				formatKota = format_kapital(formatKota);
				data.get(i).setNama(formatKota);
			}
		}
		return data;
	}				
			
	// ====================== FORMAT KAPITAL KOTA =======================
	public String format_kapital(String str) { 
        StringBuffer s = new StringBuffer(); 
        /*System.out.println("Format Kapital : "+str);*/
        // Declare a character of space 
        // To identify that the next character is the starting 
        // of a new word 
        char ch = ' '; 
        for (int i = 0; i < str.length(); i++) { 
              
            // If previous character is space and current 
            // character is not space then it shows that 
            // current letter is the starting of the word 
            if (ch == ' ' && str.charAt(i) != ' ') 
                s.append(Character.toUpperCase(str.charAt(i))); 
            else
                s.append(str.charAt(i)); 
            ch = str.charAt(i); 
        } 
  
        // Return the string with trimming 
        System.out.println("Format Kapital : "+s.toString().trim());
        return s.toString().trim(); 
    }						
}
