package com.siapti.service.jadwalkuliah.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.siapti.service.jadwalkuliah.model.ErrorSchema;
import com.siapti.service.jadwalkuliah.model.JadwalKuliah;
import com.siapti.service.jadwalkuliah.model.ListOutputSchema;
import com.siapti.service.jadwalkuliah.model.OptionalOutputSchema;
import com.siapti.service.jadwalkuliah.model.PageOutputSchema;
import com.siapti.service.jadwalkuliah.model.RegistrasiMatakuliah;
import com.siapti.service.jadwalkuliah.repository.JadwalKuliahInterface;
import com.siapti.service.jadwalkuliah.repository.JadwalKuliahRepository;
import com.siapti.service.jadwalkuliah.repository.RegistrasiMatakuliahInterface;

@Service
public class JadwalKuliahService {
	private static final Logger logger = LogManager.getLogger("SERVICE-JADWAL-KULIAH");
	
	@Autowired
	JadwalKuliahInterface jadwalkuliahInterface;

	@Autowired
	RegistrasiMatakuliahInterface registrasimatakuliahInterface;
	
	@Autowired
	JadwalKuliahRepository jadwalkuliahRepository;

	ErrorSchema errorSchema = null;
	PageOutputSchema pageOutputSchema = null;
	OptionalOutputSchema optionalOutputSchema = null;
	ListOutputSchema<JadwalKuliah> listOutputSchema = null;
	ListOutputSchema<RegistrasiMatakuliah> listRegisOutputSchema = null;
	
	JadwalKuliah jadwalkuliah = null;
	List<JadwalKuliah> listJadwalKuliah = null;
	Page<JadwalKuliah> pageJadwalKuliah = null;
	Optional<JadwalKuliah> optJadwalKuliah = null;
	Optional<JadwalKuliah> optJadwalKuliahDosen = null;
	
	List<RegistrasiMatakuliah> listRegisMatkul = null;
	
	// ====================== Select All by Dosen ===================
	public ListOutputSchema<JadwalKuliah> allJadwalKuliahbyDosen(String clientNM,String nip) {
		try {
			listOutputSchema = new ListOutputSchema<JadwalKuliah>();
			listJadwalKuliah = new ArrayList<JadwalKuliah>();
			errorSchema = new ErrorSchema();
				
			if (clientNM.equals("")) {
				errorSchema = errorSchema("SIA-11-444");
				listOutputSchema.setError_schema(errorSchema);
			} else if (!clientNM.equals("siapti")) {
				errorSchema = errorSchema("SIA-22-404");
				listOutputSchema.setError_schema(errorSchema);
			} else {
				listJadwalKuliah = jadwalkuliahInterface.findAllByDosenNip(nip);
					
				errorSchema = errorSchema("SIA-01-200");
				listOutputSchema.setError_schema(errorSchema);
				listOutputSchema.setOutput_schema(listJadwalKuliah);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			pageOutputSchema.setError_schema(errorSchema);
		}
		return listOutputSchema;
	}
	
	// ====================== Select All by Progdi Mhs ===================
	public ListOutputSchema<JadwalKuliah> allJadwalKuliahbyProgdiMhs(String clientNM,String id) {
		try {
			listOutputSchema = new ListOutputSchema<JadwalKuliah>();
			listJadwalKuliah = new ArrayList<JadwalKuliah>();
			errorSchema = new ErrorSchema();
			
			if (clientNM.equals("")) {
				errorSchema = errorSchema("SIA-11-444");
				listOutputSchema.setError_schema(errorSchema);
			} else if (!clientNM.equals("siapti")) {
				errorSchema = errorSchema("SIA-22-404");
				listOutputSchema.setError_schema(errorSchema);
			} else {
				listJadwalKuliah = jadwalkuliahInterface.findAllByMatakuliahProgdiId(id);
				
				errorSchema = errorSchema("SIA-01-200");
				listOutputSchema.setError_schema(errorSchema);
				listOutputSchema.setOutput_schema(listJadwalKuliah);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			listOutputSchema.setError_schema(errorSchema);
		}
		return listOutputSchema;
	}
	
	// ====================== Select All by Mhs ===================
	public ListOutputSchema<RegistrasiMatakuliah> allJadwalKuliahByMhs(String clientNM,String nim) {
		try {
			listRegisOutputSchema = new ListOutputSchema<RegistrasiMatakuliah>();
			listRegisMatkul = new ArrayList<RegistrasiMatakuliah>();
			errorSchema = new ErrorSchema();
			listRegisMatkul = registrasimatakuliahInterface.findByMahasiswaNimOrderByJadwalkuliahKode(nim);
			
			if (clientNM.equals("")) {
				errorSchema = errorSchema("SIA-11-444");
				listRegisOutputSchema.setError_schema(errorSchema);
			} else if (!clientNM.equals("siapti")) {
				errorSchema = errorSchema("SIA-22-404");
				listRegisOutputSchema.setError_schema(errorSchema);
			} else if (listRegisMatkul.isEmpty()) {
				errorSchema = errorSchema("SIA-01-405");
				listRegisOutputSchema.setError_schema(errorSchema);
			}else {
				errorSchema = errorSchema("SIA-01-200");
				listRegisOutputSchema.setError_schema(errorSchema);
				listRegisOutputSchema.setOutput_schema(listRegisMatkul);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			pageOutputSchema.setError_schema(errorSchema);
		}
		return listRegisOutputSchema;
	}
	
	// ====================== Select All With Page ===================
	public PageOutputSchema allJadwalKuliah(String clientNM,Pageable pageable) {
		try {
			pageOutputSchema = new PageOutputSchema();
			errorSchema = new ErrorSchema();
				
			if (clientNM.equals("")) {
				errorSchema = errorSchema("SIA-11-444");
				pageOutputSchema.setError_schema(errorSchema);
			} else if (!clientNM.equals("siapti")) {
				errorSchema = errorSchema("SIA-22-404");
				pageOutputSchema.setError_schema(errorSchema);
			} else {
				pageJadwalKuliah = jadwalkuliahInterface.findAll(pageable);
					
				errorSchema = errorSchema("SIA-01-200");
				pageOutputSchema.setError_schema(errorSchema);
				pageOutputSchema.setOutput_schema(pageJadwalKuliah);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			pageOutputSchema.setError_schema(errorSchema);
		}
		return pageOutputSchema;
	}
		
		// ====================== Search All by Kode or Name ===================
		public PageOutputSchema searchJadwalKuliah(String clientNM,String kode,String nama, Pageable pageable) {
			try { 
				pageOutputSchema = new PageOutputSchema();
				errorSchema = new ErrorSchema();
				
				if (clientNM.equals("")) {
					errorSchema = errorSchema("SIA-11-444");
					pageOutputSchema.setError_schema(errorSchema);
				} else if (!clientNM.equals("siapti")) {
					errorSchema = errorSchema("SIA-22-404");
					pageOutputSchema.setError_schema(errorSchema);
				} else {
					
					pageJadwalKuliah = jadwalkuliahInterface.findByKodeContainingOrMatakuliahNamaContaining(pageable,kode,nama);
					
					errorSchema = errorSchema("SIA-01-200");
					pageOutputSchema.setError_schema(errorSchema);
					pageOutputSchema.setOutput_schema(pageJadwalKuliah);
				}
			} catch (Exception e) {
				errorSchema = errorSchema("SIA-51-500");
				pageOutputSchema.setError_schema(errorSchema);
			}
			return pageOutputSchema;
		}		
		
		// ====================== Select Detail ================
		public OptionalOutputSchema detailJadwalKuliah(String kode, String clientNM) {
			try {
				logger.trace("DETAIL");
				optionalOutputSchema = new OptionalOutputSchema();
				errorSchema = new ErrorSchema();
				
				if (clientNM.equals("")) {
					errorSchema = errorSchema("SIA-11-444");
					optionalOutputSchema.setError_schema(errorSchema);
				} else if (!clientNM.equals("siapti")) {
					errorSchema = errorSchema("SIA-22-404");
					optionalOutputSchema.setError_schema(errorSchema);
				} else {
					logger.info("DETAIL FIND BY KODE");
					optJadwalKuliah = jadwalkuliahInterface.findByKode(kode);
					logger.info("DETAIL FIND BY KODE : "+optJadwalKuliah);
					if (optJadwalKuliah.isPresent()) {
						logger.info("DETAIL SUKSES");
						errorSchema = errorSchema("SIA-01-200");
						optionalOutputSchema.setError_schema(errorSchema);
						optionalOutputSchema.setOutput_schema(optJadwalKuliah);
					} else {
						logger.error("DETAIL ERROR : SIA-01-404");
						errorSchema = errorSchema("SIA-01-404");
						optionalOutputSchema.setError_schema(errorSchema);
					}
				}
			} catch (Exception e) {
				errorSchema = errorSchema("SIA-51-500");
				optionalOutputSchema.setError_schema(errorSchema);
			}

			return optionalOutputSchema;
		}
		
		// ====================== Add ==========================
		public OptionalOutputSchema addJadwalKuliah(JadwalKuliah jadwalkuliah) {
			logger.trace("Service ADD");
			try {
				optionalOutputSchema = new OptionalOutputSchema();
				String kode = jadwalkuliah.getKode();
				String kodematkul = jadwalkuliah.getMatakuliah().getKode();
				String nip = jadwalkuliah.getDosen().getNip();
				String hari = jadwalkuliah.getHari();
				
				logger.trace("Service ADD NIP : " +kode);
				
				optJadwalKuliah = jadwalkuliahInterface.findByKode(kode);
				optJadwalKuliahDosen = jadwalkuliahInterface.findByMatakuliahKodeAndDosenNipAndHari(kodematkul, nip, hari);
				
				logger.trace("Service ADD find : " +optJadwalKuliah);
				logger.trace("Service ADD find Dosen : " +optJadwalKuliahDosen);
				if (optJadwalKuliah.isPresent()) {
					logger.trace("Service ADD Kode exist");
					errorSchema = errorSchema("SIA-01-300");
					optionalOutputSchema.setError_schema(errorSchema);
				}else if(optJadwalKuliahDosen.isPresent()) {
					logger.trace("Service ADD Dosen Time exist");
					errorSchema = errorSchema("SIA-05-301");
					optionalOutputSchema.setError_schema(errorSchema);
				} else {
					logger.trace("Service ADD Kode not exist");
					jadwalkuliahRepository.insertData(jadwalkuliah);
					logger.trace("Success ADD");
					optJadwalKuliah = jadwalkuliahInterface.findByKode(kode);
					logger.trace("Success After Add");
					errorSchema = errorSchema("SIA-01-200");
					optionalOutputSchema.setError_schema(errorSchema);
					optionalOutputSchema.setOutput_schema(optJadwalKuliah);
				}

			} catch (Exception e) {
				errorSchema = errorSchema("SIA-51-500");
				optionalOutputSchema.setError_schema(errorSchema);
			}
			return optionalOutputSchema;
		}		

		// ====================== Delete =======================
		public OptionalOutputSchema deleteJadwalKuliah(String kode) {
			logger.trace("Service DELETE");
			try {
				optionalOutputSchema = new OptionalOutputSchema();
				logger.trace("Service DELETE NIP : "+kode);
				optJadwalKuliah= jadwalkuliahInterface.findByKode(kode);
				if (optJadwalKuliah.isPresent()) {
					logger.trace("Service DELETE Nip Exist");
					jadwalkuliahRepository.deleteData(kode);
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
		
		// ====================== Drop Jadwal Kuliah ===================
		public OptionalOutputSchema drop() {
			logger.trace("Service Drop Hasil Studi");
			try {
				optionalOutputSchema = new OptionalOutputSchema();
				errorSchema = new ErrorSchema();
				
				jadwalkuliahRepository.dropData();
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

			String errorMsg = jadwalkuliahRepository.storedProcedure("call_errorCD",errorCd);

			errorSchema.setErrorCd(errorCd);
			errorSchema.setErrorMsg(errorMsg);
				
			return errorSchema;
		}
}
