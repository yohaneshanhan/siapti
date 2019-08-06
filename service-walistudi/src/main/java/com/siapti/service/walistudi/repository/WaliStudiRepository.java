package com.siapti.service.walistudi.repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public class WaliStudiRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Transactional
	public void insertData(String nip_dosen) {
	    entityManager.createNativeQuery("INSERT INTO wali_studi (nip_dosen) VALUES (?)")
	      .setParameter(1,nip_dosen)
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
