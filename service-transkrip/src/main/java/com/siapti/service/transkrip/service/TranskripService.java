package com.siapti.service.transkrip.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siapti.service.transkrip.model.ErrorSchema;
import com.siapti.service.transkrip.model.ListOutputSchema;
import com.siapti.service.transkrip.model.OptionalOutputSchema;
import com.siapti.service.transkrip.model.Transkrip;
import com.siapti.service.transkrip.repository.TranskripInterface;
import com.siapti.service.transkrip.repository.TranskripRepository;

@Service
public class TranskripService {
	private static final Logger logger = LogManager.getLogger("SERVICE-TRANSKRIP");
	
	@Autowired
	TranskripInterface transkripInterface;
	
	@Autowired
	TranskripRepository transkripRepository;
	
	ErrorSchema errorSchema = null;
	OptionalOutputSchema<Transkrip> optionalOutputSchema = null;
	ListOutputSchema<Transkrip> listOutputSchema = null;
	
	Transkrip transkrip = null;
	List<Transkrip> listTranskrip = null;
	Optional<Transkrip> optTranskrip = null;
	
	// ====================== Select All by Mahasiswa ===================
	public ListOutputSchema<Transkrip> allByMhs(String clientNM,String nim) {
		try {
			listOutputSchema = new ListOutputSchema<Transkrip>();
			listTranskrip =  new ArrayList<Transkrip>();
			errorSchema = new ErrorSchema();
			listTranskrip = transkripInterface.findByNim(nim);
			
			if (clientNM.equals("")) {
				errorSchema = errorSchema("SIA-11-444");
				listOutputSchema.setError_schema(errorSchema);
			} else if (!clientNM.equals("siapti")) {
				errorSchema = errorSchema("SIA-22-404");
				listOutputSchema.setError_schema(errorSchema);
			} else if(listTranskrip.isEmpty()){
				errorSchema = errorSchema("SIA-01-404");
				listOutputSchema.setError_schema(errorSchema);
				listOutputSchema.setOutput_schema(listTranskrip);
			} else {
				errorSchema = errorSchema("SIA-01-200");
				listOutputSchema.setError_schema(errorSchema);
				listOutputSchema.setOutput_schema(listTranskrip);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			listOutputSchema.setError_schema(errorSchema);
		}
		return listOutputSchema;
	}
	
	// ====================== Release Transkrip Nilai ===================
	public OptionalOutputSchema<Transkrip> release() {
		logger.trace("Service Release Input Nilai");
		try {
			optionalOutputSchema = new OptionalOutputSchema<Transkrip>();
			errorSchema = new ErrorSchema();
			
			transkripRepository.releaseData();
			logger.trace("Service Release Transkrip Nilai Success");
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

		String errorMsg = transkripRepository.storedProcedure("call_errorCD",errorCd);

		errorSchema.setErrorCd(errorCd);
		errorSchema.setErrorMsg(errorMsg);
						
		return errorSchema;
	}			
}
