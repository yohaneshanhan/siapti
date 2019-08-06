package com.siapti.service.mahasiswa.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.siapti.service.mahasiswa.model.Mahasiswa;
import com.siapti.service.mahasiswa.repository.MahasiswaInterface;
import com.siapti.service.mahasiswa.repository.MahasiswaRepository;
import com.siapti.service.mahasiswa.repository.ProgdiInterface;
import com.siapti.service.mahasiswa.repository.WalistudiInterface;
import com.siapti.service.mahasiswa.model.ErrorSchema;
import com.siapti.service.mahasiswa.model.OptionalOutputSchema;
import com.siapti.service.mahasiswa.model.PageOutputSchema;
import com.siapti.service.mahasiswa.model.Progdi;
import com.siapti.service.mahasiswa.model.Walistudi;

@Service
public class MahasiswaService {
	
	private static final Logger logger = LogManager.getLogger("SERVICE-MAHASISWA");
	
	@Autowired
	MahasiswaInterface mhsInterface;
	
	@Autowired
	WalistudiInterface wsInterface;
	
	@Autowired
	ProgdiInterface progdiInterface;

	@Autowired
	MahasiswaRepository mhsRepository;
	
	ErrorSchema errorSchema = null;
	PageOutputSchema pageOutputSchema = null;
	OptionalOutputSchema optionalOutputSchema = null;
	
	Mahasiswa mahasiswa = null;
	List<Mahasiswa> listMahasiswa = null;
	Page<Mahasiswa> pageMahasiswa = null;
	Optional<Mahasiswa> optMahasiswa = null;
	Optional<Walistudi> optWalistudi = null;
	Optional<Progdi> optProgdi = null;
	
	// ====================== Select All ===================
	public PageOutputSchema allMahasiswa(String clientNM,Pageable pageable) {
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
				
				pageMahasiswa= mhsInterface.findAll(pageable);
				
				errorSchema = errorSchema("SIA-01-200");
				pageOutputSchema.setError_schema(errorSchema);
				pageOutputSchema.setOutput_schema(pageMahasiswa);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			pageOutputSchema.setError_schema(errorSchema);
		}
		return pageOutputSchema;
	}		
	
	// ====================== Search All by Nip or Name ===================
	public PageOutputSchema searchMahasiswa(String clientNM,String nim,String nama, Pageable pageable) {
		logger.trace("Service Show : ");
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
				pageMahasiswa = mhsInterface.findByNimContainingOrNamaContaining(pageable,nim,nama);
				
				errorSchema = errorSchema("SIA-01-200");
				pageOutputSchema.setError_schema(errorSchema);
				pageOutputSchema.setOutput_schema(pageMahasiswa);
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			pageOutputSchema.setError_schema(errorSchema);
		}
		return pageOutputSchema;
	}

	// ====================== Select Detail ================
	public OptionalOutputSchema detailMahasiswa(String nim, String clientNM) {
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
				optMahasiswa = mhsInterface.findByNim(nim);
				
				if (optMahasiswa.isPresent()) {
					logger.info("DETAIL SUKSES");
					errorSchema = errorSchema("SIA-01-200");
					optionalOutputSchema.setError_schema(errorSchema);
					optionalOutputSchema.setOutput_schema(optMahasiswa);
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
	public OptionalOutputSchema addMahasiswa(String clientNM,Mahasiswa mahasiswa) {
		logger.trace("Service ADD");
		try {
			optionalOutputSchema = new OptionalOutputSchema();
			String nim = mahasiswa.getNim();
			logger.trace("Service ADD NIP : " +nim);
			if (clientNM.equals("")) {
				errorSchema = errorSchema("SIA-11-444");
				optionalOutputSchema.setError_schema(errorSchema);
			} else if (!clientNM.equals("siapti")) {
				errorSchema = errorSchema("SIA-22-404");
				optionalOutputSchema.setError_schema(errorSchema);
			} else {
				optWalistudi = wsInterface.findById( mahasiswa.getWalistudi().getId());
				optProgdi = progdiInterface.findById(mahasiswa.getProgdi().getId());
				
				if(!optWalistudi.isPresent()){
					
					logger.trace("Service ADD ID Wali not exist");
					errorSchema = errorSchema("SIA-03-404");
					optionalOutputSchema.setError_schema(errorSchema);
					
				}else if(!optProgdi.isPresent()){
					
					logger.trace("Service ADD ID Progdi not exist");
					errorSchema = errorSchema("SIA-04-404");
					optionalOutputSchema.setError_schema(errorSchema);
					
				}else {
					logger.trace("Service ADD NIM not exist");
					
					mhsRepository.insertData(mahasiswa);
					logger.trace("Success ADD Mahasiswa");
					optMahasiswa = mhsInterface.findByNim(mahasiswa.getNim());
					logger.trace("Success After Add");
					errorSchema = errorSchema("SIA-01-200");
					optionalOutputSchema.setError_schema(errorSchema);
					optionalOutputSchema.setOutput_schema(optMahasiswa);
				}
			}
		} catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			optionalOutputSchema.setError_schema(errorSchema);
		}
		return optionalOutputSchema;
	}
		
	/*// ====================== Update =======================
		public OptionalMahasiswaOutput updateMahasiswa(Integer nim, Mahasiswa mahasiswaModel) {

			try {
				optionalMahasiswaOutput = new OptionalMahasiswaOutput();
				optMahasiswaModel = mhsInterface.findByNim(nim);

				if (optMahasiswaModel.isPresent()) {
					Mahasiswa _mahasiswaModel = optMahasiswaModel.get();
					_mahasiswaModel.setNama(mahasiswaModel.getNama());
					_mahasiswaModel.setNoTelp(mahasiswaModel.getNoTelp());
					_mahasiswaModel.setEmail(mahasiswaModel.getEmail());
					_mahasiswaModel.setPassword(mahasiswaModel.getPassword());
					_mahasiswaModel.setWaliStudiModel(mahasiswaModel.getWaliStudiModel());
					_mahasiswaModel.setProgdiModel(mahasiswaModel.getProgdiModel());
					
					mhsInterface.save(_mahasiswaModel);

					messageOutput = errorSchema("SIA-01-200");
					optionalMahasiswaOutput.setError_schema(messageOutput);
					optionalMahasiswaOutput.setOutput_schema(optMahasiswaModel);

				} else {
					messageOutput = errorSchema("SIA-01-404");
					optionalMahasiswaOutput.setError_schema(messageOutput);
				}
			} catch (Exception e) {
				messageOutput = errorSchema("SIA-51-500");
				optionalMahasiswaOutput.setError_schema(messageOutput);
			}

			return optionalMahasiswaOutput;

		}*/

	// ====================== Delete =======================
	public OptionalOutputSchema deleteMahasiswa (String nim) {
		logger.trace("Service DELETE");
		try {
			optionalOutputSchema = new OptionalOutputSchema();
			logger.trace("Service DELETE NIM : "+nim);
			optMahasiswa = mhsInterface.findByNim(nim);
			if (optMahasiswa.isPresent()) {
				logger.trace("Service DELETE NIM Exist");
				mhsRepository.deleteData(nim);
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

		String errorMsg = mhsRepository.storedProcedure("call_errorCD",errorCd);

		errorSchema.setErrorCd(errorCd);
		errorSchema.setErrorMsg(errorMsg);
		
		return errorSchema;
	}

	public PageOutputSchema findMhsByWali(String id,Pageable pageable) {
		logger.trace("MASUK SERVICE DAFTAR MHS BY WALI "+id);
		pageOutputSchema = new PageOutputSchema();
		errorSchema = new ErrorSchema();
		try{
			pageMahasiswa = mhsInterface.findByWalistudiId(pageable,id);
			errorSchema = errorSchema("SIA-01-200");
			pageOutputSchema.setError_schema(errorSchema);
			pageOutputSchema.setOutput_schema(pageMahasiswa);
		}catch (Exception e) {
			errorSchema = errorSchema("SIA-51-500");
			pageOutputSchema.setError_schema(errorSchema);
		}
		return pageOutputSchema ;
	}
	
}
