package com.siapti.service.hasilstudi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siapti.service.hasilstudi.model.ErrorSchema;
import com.siapti.service.hasilstudi.model.HasilStudi;
import com.siapti.service.hasilstudi.model.ListOutputSchema;
import com.siapti.service.hasilstudi.model.OptionalOutputSchema;
import com.siapti.service.hasilstudi.repository.HasilStudiInterface;
import com.siapti.service.hasilstudi.repository.HasilStudiRepository;

@Service
public class HasilStudiService {
	private static final Logger logger = LogManager.getLogger("SERVICE-HASIL-STUDI");
	
	@Autowired
	HasilStudiInterface hasilstudiInterface;
	
	@Autowired
	HasilStudiRepository hasilstudiRepository;

	ErrorSchema errorSchema = null;
	OptionalOutputSchema<HasilStudi> optionalOutputSchema = null;
	ListOutputSchema<HasilStudi> listOutputSchema = null;
	
	HasilStudi hasilstudi = null;
	List<HasilStudi> listHasilStudi = null;
	Optional<HasilStudi> optHasilstudi = null;
	
	// ====================== Select All by Dosen ===================
	public ListOutputSchema<HasilStudi> allMhsbyDosenAndKodeKelas(String clientNM,String nip, String kode) {
		try {
			listOutputSchema = new ListOutputSchema<HasilStudi>();
			listHasilStudi =  new ArrayList<HasilStudi>();
			errorSchema = new ErrorSchema();
			listHasilStudi = hasilstudiInterface.findByJadwalkuliahKodeAndJadwalkuliahDosenNip(kode, nip);
			if (clientNM.equals("")) {
				errorSchema = errorSchema("SIA-11-444");
				listOutputSchema.setError_schema(errorSchema);
			} else if (!clientNM.equals("siapti")) {
				errorSchema = errorSchema("SIA-22-404");
				listOutputSchema.setError_schema(errorSchema);
			}else if(listHasilStudi.isEmpty()){
				errorSchema = errorSchema("SIA-01-404");
				listOutputSchema.setError_schema(errorSchema);
			}else {
				errorSchema = errorSchema("SIA-01-200");
				listOutputSchema.setError_schema(errorSchema);
				listOutputSchema.setOutput_schema(listHasilStudi);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			listOutputSchema.setError_schema(errorSchema);
		}
		return listOutputSchema;
	}

	// ====================== Select All by Mahasiswa ===================
	public ListOutputSchema<HasilStudi> allByMhs(String clientNM,String nim) {
		try {
			listOutputSchema = new ListOutputSchema<HasilStudi>();
			listHasilStudi =  new ArrayList<HasilStudi>();
			errorSchema = new ErrorSchema();
			listHasilStudi = hasilstudiInterface.findByMahasiswaNimAndRelease(nim, "1");
			
			if (clientNM.equals("")) {
				errorSchema = errorSchema("SIA-11-444");
				listOutputSchema.setError_schema(errorSchema);
			} else if (!clientNM.equals("siapti")) {
				errorSchema = errorSchema("SIA-22-404");
				listOutputSchema.setError_schema(errorSchema);
			} else if(listHasilStudi.isEmpty()){
				errorSchema = errorSchema("SIA-01-404");
				listOutputSchema.setError_schema(errorSchema);
				listOutputSchema.setOutput_schema(listHasilStudi);
			} else {
				errorSchema = errorSchema("SIA-01-200");
				listOutputSchema.setError_schema(errorSchema);
				listOutputSchema.setOutput_schema(listHasilStudi);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			listOutputSchema.setError_schema(errorSchema);
		}
		return listOutputSchema;
	}

	// ====================== Input Nilai Mahasiswa by Dosen ===================
	public OptionalOutputSchema<HasilStudi> inputNilaiMhsByDosen(HasilStudi hasilStudi) {
		logger.trace("Service Input Nilai");
		try {
			optionalOutputSchema = new OptionalOutputSchema<HasilStudi>();
			errorSchema = new ErrorSchema();
			
			String nim = hasilStudi.getMahasiswa().getNim();
			String kode = hasilStudi.getJadwalkuliah().getKode();
			
			optHasilstudi = hasilstudiInterface.findByJadwalkuliahKodeAndMahasiswaNim(kode, nim);
			if (!optHasilstudi.isPresent()) {
				logger.trace("Service Input Nilai Data not Found");
				errorSchema = errorSchema("SIA-01-404");
				optionalOutputSchema.setError_schema(errorSchema);
			} else {
				hasilstudiRepository.updateData(hasilStudi);
				logger.trace("Service Input Nilai Success");
				errorSchema = errorSchema("SIA-01-200");
				optionalOutputSchema.setError_schema(errorSchema);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			optionalOutputSchema.setError_schema(errorSchema);
		}
		return optionalOutputSchema;
	}
	
	// ====================== Input Release Input Nilai ===================
	public OptionalOutputSchema<HasilStudi> release() {
		logger.trace("Service Release Input Nilai");
		try {
			optionalOutputSchema = new OptionalOutputSchema<HasilStudi>();
			errorSchema = new ErrorSchema();
			
			hasilstudiRepository.releaseData();
			logger.trace("Service Release Input Nilai Success");
			errorSchema = errorSchema("SIA-01-200");
			optionalOutputSchema.setError_schema(errorSchema);
			
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			optionalOutputSchema.setError_schema(errorSchema);
		}
		return optionalOutputSchema;
	}

	// ====================== Drop Hasil Studi ===================
	public OptionalOutputSchema<HasilStudi> drop() {
		logger.trace("Service Drop Hasil Studi");
		try {
			optionalOutputSchema = new OptionalOutputSchema<HasilStudi>();
			errorSchema = new ErrorSchema();
			
			hasilstudiRepository.dropData();
			logger.trace("Service Drop Hasil Studi Success");
			errorSchema = errorSchema("SIA-01-200");
			optionalOutputSchema.setError_schema(errorSchema);
			
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

		String errorMsg = hasilstudiRepository.storedProcedure("call_errorCD",errorCd);

		errorSchema.setErrorCd(errorCd);
		errorSchema.setErrorMsg(errorMsg);
						
		return errorSchema;
	}
}
