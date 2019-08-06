package com.siapti.service.matakuliah.repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.siapti.service.matakuliah.model.Matakuliah;

@Repository
public class MatakuliahRepository {
	@PersistenceContext
    private EntityManager entityManager;
	
	private static final Logger logger = LogManager.getLogger("SERVICE-MATAKULIAH");
	
	@Transactional
	public void insertData(Matakuliah matakuliah) {
		logger.trace("Repo ADD Matakuliah Body " + matakuliah.getKode());
		logger.trace("Repo ADD Matakuliah Body " + matakuliah.getProgdi().getId());
	    entityManager.createNativeQuery("INSERT INTO matakuliah (kode_matkul,nama_matkul,id_progdi,sks) VALUES (?,?,?,?)")
	      .setParameter(1, matakuliah.getKode())
	      .setParameter(2, matakuliah.getNama())
	      .setParameter(3, matakuliah.getProgdi().getId())
	      .setParameter(4, matakuliah.getSks())
	      .executeUpdate();
	}
	
	@Transactional
	public void deleteData(String kode) {
		logger.trace("Repo Delete Matakuliah " +kode);
		entityManager.createNativeQuery("DELETE FROM matakuliah WHERE kode_matkul= ?")
		.setParameter(1, kode)
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
