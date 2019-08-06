package com.siapti.service.registrasimatakuliah.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siapti.service.registrasimatakuliah.model.ErrorSchema;
import com.siapti.service.registrasimatakuliah.model.ListOutputSchema;
import com.siapti.service.registrasimatakuliah.model.OptionalOutputSchema;
import com.siapti.service.registrasimatakuliah.model.RegistrasiMatakuliah;
import com.siapti.service.registrasimatakuliah.repository.RegistrasiMatakuliahInterface;
import com.siapti.service.registrasimatakuliah.repository.RegistrasiMatakuliahRepository;

@Service
public class RegistrasiMatakuliahService {
	private static final Logger logger = LogManager.getLogger("SERVICE-REGISTRASI-MATAKULIAH");
	
	@Autowired
	RegistrasiMatakuliahRepository registrasiMatkulRepository;

	@Autowired
	RegistrasiMatakuliahInterface registrasiMatkulInterface;
	
	ErrorSchema errorSchema = null;
	OptionalOutputSchema optionalOutputSchema = null;
	ListOutputSchema<RegistrasiMatakuliah> listOutputSchema = null;
	
	RegistrasiMatakuliah registrasiMatkul = null;
	List<RegistrasiMatakuliah> listRegisMatkul = null;
	Optional<RegistrasiMatakuliah> optRegisMatkul = null;
	Optional<RegistrasiMatakuliah> optRegisMatkul2 = null;
	
	// ====================== Add Registrasi Matakuliah Mahasiswa ================
	public OptionalOutputSchema addRegisMhs(String clientNM, RegistrasiMatakuliah regisMatkul) {
		logger.trace("Service ADD");
		try {
			optionalOutputSchema = new OptionalOutputSchema();
			String kode = regisMatkul.getJadwalkuliah().getKode();
			String kode2 = regisMatkul.getJadwalkuliah().getMatakuliah().getKode();
			String nim = regisMatkul.getMahasiswa().getNim();
			
			logger.trace("Service ADD Regis Kode Kelas : "+ kode+ " Kode Matkul : " +kode2+" Nim : "+nim);
			
			optRegisMatkul = registrasiMatkulInterface.findByJadwalkuliahKodeAndMahasiswaNim(kode, nim);
			logger.trace("Service ADD find Regis: " +optRegisMatkul);
			optRegisMatkul2 = registrasiMatkulInterface.findByJadwalkuliahMatakuliahKodeAndMahasiswaNim(kode2,nim);
			logger.trace("Service ADD find Regis 2: " +optRegisMatkul2);
			
			if (optRegisMatkul.isPresent()) {
				logger.trace("Service ADD Regis Kode and Nim exist");
				errorSchema = errorSchema("SIA-01-300");
				optionalOutputSchema.setError_schema(errorSchema);
			}else if(optRegisMatkul2.isPresent()) {
				logger.trace("Service ADD Kode Matakuliah and Nim exist");
				errorSchema = errorSchema("SIA-06-300");
				optionalOutputSchema.setError_schema(errorSchema);
			} else {
				logger.trace("Service ADD Regis Kode and Nim not exist");
				registrasiMatkulRepository.insertData(regisMatkul);
				logger.trace("Success ADD");
				//optRegisMatkul = registrasiMatkulInterface.findByJadwalkuliahKodeAndMahasiswaNim(kode, nim);
				//logger.trace("Success After Add");
				errorSchema = errorSchema("SIA-01-200");
				optionalOutputSchema.setError_schema(errorSchema);
				//optionalOutputSchema.setOutput_schema(optRegisMatkul);
			}

		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			optionalOutputSchema.setError_schema(errorSchema);
		}
		return optionalOutputSchema;
	}	
	
	// ====================== Delete Registrasi Matakuliah Mahasiswa ================
	public OptionalOutputSchema deleteRegisMhs(String kode, String nim) {
		logger.trace("Service DELETE");
		try {
			optionalOutputSchema = new OptionalOutputSchema();
			logger.trace("Service DELETE Regis Kode : " +kode+ " Nim : "+nim);
			optRegisMatkul = registrasiMatkulInterface.findByJadwalkuliahKodeAndMahasiswaNim(kode,nim);
			if (optRegisMatkul.isPresent()) {
				logger.trace("Service DELETE Nip Exist");
				registrasiMatkulRepository.deleteData(kode,nim);
				logger.trace("Service DELETE Berhasil");
				errorSchema = errorSchema("SIA-01-200");
				optionalOutputSchema.setError_schema(errorSchema);
			} else {
				errorSchema = errorSchema("SIA-01-404");
				optionalOutputSchema.setError_schema(errorSchema);
			}
			
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			optionalOutputSchema.setError_schema(errorSchema);
		}
		return optionalOutputSchema;
	}	
		
	// ====================== Error Schema ================
	public ErrorSchema errorSchema(String errorCd) {
		logger.trace("Masuk Error Code: "+errorCd);
		errorSchema = new ErrorSchema();

		String errorMsg = registrasiMatkulRepository.storedProcedure("call_errorCD",errorCd);

		errorSchema.setErrorCd(errorCd);
		errorSchema.setErrorMsg(errorMsg);
			
		return errorSchema;
	}

}
