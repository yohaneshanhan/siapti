package com.siapti.service.matakuliah.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.siapti.service.matakuliah.model.Matakuliah;
import com.siapti.service.matakuliah.model.ErrorSchema;
import com.siapti.service.matakuliah.model.OptionalOutputSchema;
import com.siapti.service.matakuliah.model.PageOutputSchema;
import com.siapti.service.matakuliah.repository.MatakuliahInterface;
import com.siapti.service.matakuliah.repository.MatakuliahRepository;

@Service
public class MatakuliahService {
	
	private static final Logger logger = LogManager.getLogger("SERVICE-MATAKULIAH");
	
	@Autowired
	MatakuliahInterface mkInterface;

	@Autowired
	MatakuliahRepository mkRepository;

	ErrorSchema errorSchema = null;
	PageOutputSchema pageOutputSchema = null;
	OptionalOutputSchema optionalOutputSchema = null;
	
	Matakuliah matakuliah = null;
	List<Matakuliah> listMatakuliah = null;
	Page<Matakuliah> pageMatakuliah = null;
	Optional<Matakuliah> optMatakuliah = null;
	
	// ====================== Select All ===================
		public PageOutputSchema allMatakuliah(String clientNM,Pageable pageable) {
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
					pageMatakuliah = mkInterface.findAll(pageable);
					
					errorSchema = errorSchema("SIA-01-200");
					pageOutputSchema.setError_schema(errorSchema);
					pageOutputSchema.setOutput_schema(pageMatakuliah);
				}
			} catch (Exception e) {
				errorSchema = errorSchema("SIA-51-500");
				pageOutputSchema.setError_schema(errorSchema);
			}
			return pageOutputSchema;
		}
	
	// ====================== Search All by Kode or Name ===================
	public PageOutputSchema searchMatakuliah(String clientNM,String kode,String nama, Pageable pageable) {
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
				
				pageMatakuliah = mkInterface.findByKodeContainingOrNamaContaining(pageable,kode,nama);
				
				errorSchema = errorSchema("SIA-01-200");
				pageOutputSchema.setError_schema(errorSchema);
				pageOutputSchema.setOutput_schema(pageMatakuliah);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			pageOutputSchema.setError_schema(errorSchema);
		}
		return pageOutputSchema;
	}		
	
	// ====================== Select Detail ================
	public OptionalOutputSchema detailMatakuliah(String kode, String clientNM) {
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
				optMatakuliah = mkInterface.findByKode(kode);
				
				if (optMatakuliah.isPresent()) {
					logger.info("DETAIL SUKSES");
					errorSchema = errorSchema("SIA-01-200");
					optionalOutputSchema.setError_schema(errorSchema);
					optionalOutputSchema.setOutput_schema(optMatakuliah);
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
	public OptionalOutputSchema addMatakuliah(Matakuliah matakuliah) {
		try {
			optionalOutputSchema = new OptionalOutputSchema();
			String kode = matakuliah.getKode();
			logger.trace("Service ADD KODE : " +kode);
			logger.trace("Service ADD PROGDI: " +matakuliah.getProgdi().getId());
			
			optMatakuliah = mkInterface.findByKode(kode);
			logger.trace("Service ADD find : " +optMatakuliah);
			if (optMatakuliah.isPresent()) {
				logger.trace("Service ADD KODE exist");
				errorSchema = errorSchema("SIA-01-300");
				optionalOutputSchema.setError_schema(errorSchema);
			} else {
				logger.trace("Service ADD KODE not exist");
				mkRepository.insertData(matakuliah);
				logger.trace("Success ADD");
				optMatakuliah = mkInterface.findByKode(kode);
				logger.trace("Success After Add");
				errorSchema = errorSchema("SIA-01-200");
				optionalOutputSchema.setError_schema(errorSchema);
				optionalOutputSchema.setOutput_schema(optMatakuliah);
			}

		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			optionalOutputSchema.setError_schema(errorSchema);
		}
		return optionalOutputSchema;
	}
	
	// ====================== Delete =======================
		public OptionalOutputSchema deleteMatakuliah(String kode) {
			logger.trace("Service DELETE");
			try {
				optionalOutputSchema = new OptionalOutputSchema();
				logger.trace("Service DELETE KODE : "+kode);
				optMatakuliah= mkInterface.findByKode(kode);
				if (optMatakuliah.isPresent()) {
					logger.trace("Service DELETE Kode Exist");
					mkRepository.deleteData(kode);
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

			String errorMsg = mkRepository.storedProcedure("call_errorCD",errorCd);

			errorSchema.setErrorCd(errorCd);
			errorSchema.setErrorMsg(errorMsg);
				
			return errorSchema;
		}
}
