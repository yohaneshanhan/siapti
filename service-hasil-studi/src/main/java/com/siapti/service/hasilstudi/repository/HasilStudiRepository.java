package com.siapti.service.hasilstudi.repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.siapti.service.hasilstudi.model.HasilStudi;

@Repository
public class HasilStudiRepository {

	@PersistenceContext
    private EntityManager entityManager;
	
	private static final Logger logger = LogManager.getLogger("SERVICE-JADWAL-KULIAH");
	
	@Transactional
	public void updateData(HasilStudi hasilStudi) {
		logger.trace("Repo Input Hasil Studi : " + hasilStudi.getAksara());
		logger.trace("Repo Input Hasil Studi : " + hasilStudi.getMahasiswa().getNim());
		logger.trace("Repo Input Hasil Studi : " + hasilStudi.getJadwalkuliah().getKode());
	    entityManager.createNativeQuery("UPDATE hasil_studi set aksara = ? where nim_mhs = ? and kode_kelas = ?")
	    	.setParameter(1, hasilStudi.getAksara())
	    	.setParameter(2, hasilStudi.getMahasiswa().getNim())
	    	.setParameter(3, hasilStudi.getJadwalkuliah().getKode())
	    	.executeUpdate();
	}

	@Transactional
	public void releaseData() {
		entityManager.createNativeQuery("UPDATE hasil_studi set hasil_studi.release = ?")
			.setParameter(1, "1")
			.executeUpdate();
	}

	@Transactional
	public void dropData() {
		entityManager.createNativeQuery("DELETE from hasil_studi")
		.executeUpdate();

		entityManager.createNativeQuery("DELETE from registrasi_matakuliah")
		.executeUpdate();
	}
	
	public String storedProcedure(String procedure, String param1) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(procedure);
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
		query.setParameter(1, param1);
		query.execute();

		String output = (String) query.getOutputParameterValue(2);
		
		return output;
	}
	
}
