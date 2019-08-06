package com.siapti.service.staff.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.siapti.service.staff.model.PageOutputSchema;
import com.siapti.service.staff.model.ErrorSchema;
import com.siapti.service.staff.model.OptionalOutputSchema;
import com.siapti.service.staff.model.Staff;
import com.siapti.service.staff.repository.StaffInterface;

@Service
public class StaffService {

	private static final Logger logger = LogManager.getLogger("SERVICE-STAFF");
	
	@Autowired
	StaffInterface staffInterface;

	@PersistenceContext
	private EntityManager entityManager;
	
	ErrorSchema errorSchema = null;
	PageOutputSchema pageOutputSchema = null;
	OptionalOutputSchema optionalOutputSchema = null;
	
	Staff staff = null;
	List<Staff> listStaff = null;
	Page<Staff> pageStaff = null;
	Optional<Staff> optStaff = null;
	
	// ====================== Select All ===================
	public PageOutputSchema allStaff(String clientNM,Pageable pageable) {
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
				
				pageStaff = staffInterface.findAll(pageable);
				
				errorSchema = errorSchema("SIA-01-200");
				pageOutputSchema.setError_schema(errorSchema);
				pageOutputSchema.setOutput_schema(pageStaff);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			pageOutputSchema.setError_schema(errorSchema);
		}
		return pageOutputSchema;
	}
	
	// ====================== Search All by Nip or Name ===================
	public PageOutputSchema searchStaff(String clientNM,String nip,String nama, Pageable pageable) {
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
				
				pageStaff = staffInterface.findByNipContainingOrNamaContaining(pageable,nip,nama);
				
				errorSchema = errorSchema("SIA-01-200");
				pageOutputSchema.setError_schema(errorSchema);
				pageOutputSchema.setOutput_schema(pageStaff);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			pageOutputSchema.setError_schema(errorSchema);
		}
		return pageOutputSchema;
	}

	// ====================== Select Detail ================
	public OptionalOutputSchema detailStaff(String nip, String clientNM) {
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
				optStaff = staffInterface.findByNip(nip);
				
				if (optStaff.isPresent()) {
					logger.info("DETAIL SUKSES");
					errorSchema = errorSchema("SIA-01-200");
					optionalOutputSchema.setError_schema(errorSchema);
					optionalOutputSchema.setOutput_schema(optStaff);
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
	public OptionalOutputSchema addStaff(Staff staffModel) {
		logger.trace("Service ADD");
		logger.trace("Service ADD : " +staffModel.getPassword());		
		logger.trace("Service ADD : " +staffModel.getTanggal());		
		try {
			optionalOutputSchema = new OptionalOutputSchema();
			String nip = staffModel.getNip();
			logger.trace("Service ADD NIP : " +nip);
			
			optStaff = staffInterface.findByNip(nip);
			logger.trace("Service ADD find : " +optStaff);
			if (optStaff.isPresent()) {
				logger.trace("Service ADD NIP exist");
				errorSchema = errorSchema("SIA-01-300");
				optionalOutputSchema.setError_schema(errorSchema);
			} else {
				logger.trace("Service ADD NIP not exist");
				staffInterface.save(staffModel);
				logger.trace("Success ADD");
				optStaff = staffInterface.findByNip(nip);
				logger.trace("Success AFter Add");
				errorSchema = errorSchema("SIA-01-200");
				optionalOutputSchema.setError_schema(errorSchema);
				optionalOutputSchema.setOutput_schema(optStaff);
			}

		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			optionalOutputSchema.setError_schema(errorSchema);
		}
		return optionalOutputSchema;
	}

	// ====================== Update =======================
	/*public OptionalStaffOutput updateStaff(String nip, StaffModel staffModel) {

		try {
			optionalstaffOutput = new OptionalStaffOutput();
			optstaffModel = staffInterface.findByNip(nip);

			if (optstaffModel.isPresent()) {
				StaffModel _staffModel = optstaffModel.get();
				_staffModel.setNama(staffModel.getNama());
				_staffModel.setNoTelp(staffModel.getNoTelp());
				_staffModel.setEmail(staffModel.getEmail());
				staffInterface.save(_staffModel);

				messageOutput = errorSchema("SIA-01-200");
				optionalstaffOutput.setError_schema(messageOutput);
				optionalstaffOutput.setOutput_schema(optstaffModel);

			} else {
				messageOutput = errorSchema("SIA-01-404");
				optionalstaffOutput.setError_schema(messageOutput);
			}
		} catch (Exception e) {
			messageOutput = errorSchema("SIA-51-500");
			optionalstaffOutput.setError_schema(messageOutput);
		}

		return optionalstaffOutput;

	}*/

	// ====================== Delete =======================
	public OptionalOutputSchema deleteStaff(String nip) {
		logger.trace("Service DELETE");
		try {
			optionalOutputSchema = new OptionalOutputSchema();
			logger.trace("Service DELETE NIP : "+nip);
			optStaff = staffInterface.findByNip(nip);
			logger.trace("Service DELETE FIND : "+optStaff);
			if (optStaff.isPresent()) {
				logger.trace("Service DELETE Berhasil : "+nip);
				staffInterface.deleteByNip(nip);
				logger.trace("Service DELETE Berhasil 2");
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
		System.out.println("masuk error");
		errorSchema = new ErrorSchema();

		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("call_errorCD");

		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
		query.setParameter(1, errorCd);
		query.execute();

		String errorMsg = (String) query.getOutputParameterValue(2);

		errorSchema.setErrorCd(errorCd);
		errorSchema.setErrorMsg(errorMsg);
		
		return errorSchema;
	}
	
}

