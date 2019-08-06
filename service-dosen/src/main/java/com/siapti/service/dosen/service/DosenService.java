package com.siapti.service.dosen.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.siapti.service.dosen.model.Dosen;
import com.siapti.service.dosen.model.ErrorSchema;
import com.siapti.service.dosen.model.OptionalOutputSchema;
import com.siapti.service.dosen.repository.DosenInterface;
import com.siapti.service.dosen.repository.DosenRepository;
import com.siapti.service.dosen.model.PageOutputSchema;

@Service
public class DosenService {

	private static final Logger logger = LogManager.getLogger("SERVICE-DOSEN");
	
	@Autowired
	DosenInterface dosenInterface;
	
	@Autowired
	DosenRepository dosenRepository;

	ErrorSchema errorSchema = null;
	PageOutputSchema pageOutputSchema = null;
	OptionalOutputSchema optionalOutputSchema = null;
	
	Dosen dosen = null;
	List<Dosen> listDosen = null;
	Page<Dosen> pageDosen = null;
	Optional<Dosen> optDosen = null;
	
	// ====================== Select All ===================
	public PageOutputSchema allDosen(String clientNM,Pageable pageable) {
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
				pageDosen = dosenInterface.findAll(pageable);
				
				errorSchema = errorSchema("SIA-01-200");
				pageOutputSchema.setError_schema(errorSchema);
				pageOutputSchema.setOutput_schema(pageDosen);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			pageOutputSchema.setError_schema(errorSchema);
		}
		return pageOutputSchema;
	}
	
	// ====================== Search All by Nip or Name ===================
	public PageOutputSchema searchDosen(String clientNM,String nip,String nama, Pageable pageable) {
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
				
				pageDosen = dosenInterface.findByNipContainingOrNamaContaining(pageable,nip,nama);
				
				errorSchema = errorSchema("SIA-01-200");
				pageOutputSchema.setError_schema(errorSchema);
				pageOutputSchema.setOutput_schema(pageDosen);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			pageOutputSchema.setError_schema(errorSchema);
		}
		return pageOutputSchema;
	}		
	
	// ====================== Select Detail ================
	public OptionalOutputSchema detailDosen(String nip, String clientNM) {
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
				optDosen = dosenInterface.findByNip(nip);
				
				if (optDosen.isPresent()) {
					logger.info("DETAIL SUKSES");
					errorSchema = errorSchema("SIA-01-200");
					optionalOutputSchema.setError_schema(errorSchema);
					optionalOutputSchema.setOutput_schema(optDosen);
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
	public OptionalOutputSchema addDosen(Dosen dosen) {
		logger.trace("Service ADD");
		try {
			optionalOutputSchema = new OptionalOutputSchema();
			String nip = dosen.getNip();
			logger.trace("Service ADD NIP : " +nip);
			
			optDosen = dosenInterface.findByNip(nip);
			logger.trace("Service ADD find : " +optDosen);
			if (optDosen.isPresent()) {
				logger.trace("Service ADD NIP exist");
				errorSchema = errorSchema("SIA-01-300");
				optionalOutputSchema.setError_schema(errorSchema);
			} else {
				logger.trace("Service ADD NIP not exist");
				dosenRepository.insertData(dosen);
				logger.trace("Success ADD");
				optDosen = dosenInterface.findByNip(nip);
				logger.trace("Success After Add");
				errorSchema = errorSchema("SIA-01-200");
				optionalOutputSchema.setError_schema(errorSchema);
				optionalOutputSchema.setOutput_schema(optDosen);
			}

		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			optionalOutputSchema.setError_schema(errorSchema);
		}
		return optionalOutputSchema;
	}		

	// ====================== Update =======================
	/*public OptionalOutputSchema updateDosen(Integer nip, Dosen dosenModel) {

		try {
			optionalOutputSchema = new OptionalOutputSchema();
			optDosen = dosenInterface.findByNip(nip);

			if (optDosen.isPresent()) {
				Dosen _DosenModel = optDosen.get();
				_DosenModel.setNama(dosenModel.getNama());
				_DosenModel.setNoTelp(dosenModel.getNoTelp());
				_DosenModel.setEmail(dosenModel.getEmail());
				dosenInterface.save(_DosenModel);

				errorSchema = errorSchema("SIA-01-200");
				optionalOutputSchema.setError_schema(errorSchema);
				optionalOutputSchema.setOutput_schema(optDosen);

			} else {
				errorSchema = errorSchema("SIA-01-404");
				optionalOutputSchema.setError_schema(errorSchema);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			optionalOutputSchema.setError_schema(errorSchema);
		}

		return optionalOutputSchema;

	}*/

	// ====================== Delete =======================
	public OptionalOutputSchema deleteDosen(String nip) {
		logger.trace("Service DELETE");
		try {
			optionalOutputSchema = new OptionalOutputSchema();
			logger.trace("Service DELETE NIP : "+nip);
			optDosen= dosenInterface.findByNip(nip);
			if (optDosen.isPresent()) {
				logger.trace("Service DELETE Nip Exist");
				dosenRepository.deleteData(nip);
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

		String errorMsg = dosenRepository.storedProcedure("call_errorCD",errorCd);

		errorSchema.setErrorCd(errorCd);
		errorSchema.setErrorMsg(errorMsg);
			
		return errorSchema;
	}
	
	// ====================== Check Dosen WS ================
	public String checkIsWs(String nip) {
		logger.trace("Masuk Check Dosen WS : "+nip);
		String result = dosenRepository.storedProcedure("check_dosen_ws",nip);
		
		if(result == null) {
			result="false";
		}
		
		
		return result;
	}
}
