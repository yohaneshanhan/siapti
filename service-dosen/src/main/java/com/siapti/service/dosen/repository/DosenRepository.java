package com.siapti.service.dosen.repository;


import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.siapti.service.dosen.model.Dosen;

@Repository
public class DosenRepository {
	@PersistenceContext
    private EntityManager entityManager;
	
	private static final Logger logger = LogManager.getLogger("SERVICE-DOSEN");
	
	@Transactional
	public void insertData(Dosen dosen) {
		logger.trace("Repo ADD Dosen Body " + dosen.getNip());
		logger.trace("Repo ADD Dosen Body " + dosen.getProgdi().getId());
	    entityManager.createNativeQuery("INSERT INTO dosen (nip_dosen,nama_dosen, tempat_lahir,tgl_lahir, no_telp,"
	    		+ "email,password,id_progdi) VALUES (?,?,?,?,?,?,?,?)")
	      .setParameter(1, dosen.getNip())
	      .setParameter(2, dosen.getNama())
	      .setParameter(3, dosen.getTempat())
	      .setParameter(4, dosen.getTanggal())
	      .setParameter(5, dosen.getNoTelp())
	      .setParameter(6, dosen.getEmail())
	      .setParameter(7, dosen.getPassword())
	      .setParameter(8, dosen.getProgdi().getId())
	      .executeUpdate();
	}
	
	@Transactional
	public void deleteData(String nip) {
		logger.trace("Repo Delete Dosen " +nip);
		entityManager.createNativeQuery("DELETE FROM dosen WHERE nip_dosen= ?")
		.setParameter(1, nip)
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
