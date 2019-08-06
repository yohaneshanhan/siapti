package com.siapti.service.walistudi.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.siapti.service.walistudi.model.PageOutputSchema;
import com.siapti.service.walistudi.model.ErrorSchema;
import com.siapti.service.walistudi.model.OptionalOutputSchema;
import com.siapti.service.walistudi.model.WaliStudi;
import com.siapti.service.walistudi.repository.WaliStudiInterface;
import com.siapti.service.walistudi.repository.WaliStudiRepository;

@Service
public class WaliStudiService {

	private static final Logger logger = LogManager.getLogger("SERVICE-WALISTUDI");
	
	@Autowired
	WaliStudiInterface walistudiInterface;

	@Autowired
	WaliStudiRepository walistudiRepository;
	
	ErrorSchema errorSchema = null;
	PageOutputSchema pageOutputSchema = null;
	OptionalOutputSchema optionalOutputSchema = null;
	
	WaliStudi walistudi = null;
	List<WaliStudi> listWalistudi = null;
	Page<WaliStudi> pageWalistudi = null;
	Optional<WaliStudi> optWalistudi = null;
	Optional<WaliStudi> optWalistudi2 = null;

	// ====================== Select All ===================
	public PageOutputSchema allWalistudi(String clientNM,Pageable pageable) {
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
				
				pageWalistudi = walistudiInterface.findAll(pageable);
				
				errorSchema = errorSchema("SIA-01-200");
				pageOutputSchema.setError_schema(errorSchema);
				pageOutputSchema.setOutput_schema(pageWalistudi);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			pageOutputSchema.setError_schema(errorSchema);
		}
		return pageOutputSchema;
	}	

	// ====================== Search All by Id or Name ===================
	public PageOutputSchema searchWalistudi(String clientNM,String nip,String nama, Pageable pageable) {
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
				
				pageWalistudi = walistudiInterface.findByIdContainingOrDosenNamaContaining(pageable,nip,nama);
				
				errorSchema = errorSchema("SIA-01-200");
				pageOutputSchema.setError_schema(errorSchema);
				pageOutputSchema.setOutput_schema(pageWalistudi);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			pageOutputSchema.setError_schema(errorSchema);
		}
		return pageOutputSchema;
	}	

	// ====================== Select Detail ================
	public OptionalOutputSchema detailWalistudi(String nip, String clientNM) {
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
				optWalistudi = walistudiInterface.findById(nip);
				
				if (optWalistudi.isPresent()) {
					logger.info("DETAIL SUKSES");
					errorSchema = errorSchema("SIA-01-200");
					optionalOutputSchema.setError_schema(errorSchema);
					optionalOutputSchema.setOutput_schema(optWalistudi);
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
	public OptionalOutputSchema addWalistudi(String clientNM, String nip_dosen) {
		logger.trace("Service ADD");
		try {
			optionalOutputSchema = new OptionalOutputSchema();
			
			if (clientNM.equals("")) {
				errorSchema = errorSchema("SIA-11-444");
				optionalOutputSchema.setError_schema(errorSchema);
			} else if (!clientNM.equals("siapti")) {
				errorSchema = errorSchema("SIA-22-404");
				optionalOutputSchema.setError_schema(errorSchema);
			} else {
				//String nip = walistudi.getDosen().getNip();
				
				logger.trace("Service ADD NIP : " +nip_dosen);
				
				optWalistudi2 = walistudiInterface.findByDosenNip(nip_dosen);
				logger.trace("Service ADD find : " +optWalistudi);
				logger.trace("Service ADD find 2 : " +optWalistudi2);
				
				if (optWalistudi2.isPresent()) {
					errorSchema = errorSchema("SIA-01-301");
					optionalOutputSchema.setError_schema(errorSchema);
				} else {
					logger.trace("Service ADD ID not exist");
					walistudiRepository.insertData(nip_dosen);
					logger.trace("Success ADD");
					//optWalistudi = walistudiInterface.findById(id);
					logger.trace("Success After Add");
					errorSchema = errorSchema("SIA-01-200");
					optionalOutputSchema.setError_schema(errorSchema);
					//optionalOutputSchema.setOutput_schema(optWalistudi);
				}
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			optionalOutputSchema.setError_schema(errorSchema);
		}
		return optionalOutputSchema;
	}
	
	// ====================== Delete =======================
	public OptionalOutputSchema deleteWalistudi(String id) {
		try {
			optionalOutputSchema = new OptionalOutputSchema();
			optWalistudi = walistudiInterface.findById(id);
			if (optWalistudi.isPresent()) {
				String errCD = walistudiRepository.storedProcedure("del_walistudi",id);
				
				errorSchema = errorSchema(errCD);
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

		String errorMsg = walistudiRepository.storedProcedure("call_errorCD",errorCd);
		
		errorSchema.setErrorCd(errorCd);
		errorSchema.setErrorMsg(errorMsg);
		
		return errorSchema;
	}	

}
