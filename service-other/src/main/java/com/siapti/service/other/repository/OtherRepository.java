package com.siapti.service.other.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

@Repository
public class OtherRepository {
	@PersistenceContext
    private EntityManager entityManager;
	
	public String storedProcedure(String procedure, String param1) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(procedure);
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
		query.setParameter(1, param1);
		query.execute();

		String output = (String) query.getOutputParameterValue(2);
		
		return output;
	}
	
	@SuppressWarnings("rawtypes")
	public List listProgdi() {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("call_progdi");
		query.execute();
		
		return query.getResultList();
	}
	
	@SuppressWarnings("rawtypes")
	public List listByProgdi(String id_progdi,String sp) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(sp);
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		query.setParameter(1, id_progdi);
		query.execute();
		
		return query.getResultList();
	}
}
