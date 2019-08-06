package com.siapti.service.mahasiswa.repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.siapti.service.mahasiswa.model.Mahasiswa;

@Repository
public class MahasiswaRepository {
	@PersistenceContext
    private EntityManager entityManager;
	
	private static final Logger logger = LogManager.getLogger("SERVICE-MAHASISWA");
	
	@Transactional
	public void insertData(Mahasiswa mahasiswa) {
		logger.trace("Repo ADD MHS Body " + mahasiswa.getWalistudi().getId());
		logger.trace("Repo ADD MHS Body " + mahasiswa.getProgdi().getId());
		entityManager.createNativeQuery("INSERT INTO mahasiswa (nim_mhs,nama_mhs, tempat_lahir,tgl_lahir, no_telp,"
	    		+ "email,angkatan,password, id_wali_studi,id_progdi) VALUES (?,?,?,?,?,?,?,?,?,?)")
		  .setParameter(1, mahasiswa.getNim())
	      .setParameter(2, mahasiswa.getNama())
	      .setParameter(3, mahasiswa.getTempat_lahir())
	      .setParameter(4, mahasiswa.getTanggal())
	      .setParameter(5, mahasiswa.getNoTelp())
	      .setParameter(6, mahasiswa.getEmail())
	      .setParameter(7, mahasiswa.getAngkatan())
	      .setParameter(8, mahasiswa.getPassword())
	      .setParameter(9, mahasiswa.getWalistudi().getId())
	      .setParameter(10, mahasiswa.getProgdi().getId())
	      .executeUpdate();
	}
	
	@Transactional
	public void deleteData(String nim) {
		logger.trace("Repo Delete Mahasiswa " +nim);
		entityManager.createNativeQuery("DELETE FROM mahasiswa WHERE nim_mhs = ?")
		.setParameter(1, nim)
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
