package com.siapti.service.registrasimatakuliah.repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.siapti.service.registrasimatakuliah.model.RegistrasiMatakuliah;

@Repository
public class RegistrasiMatakuliahRepository {
	@PersistenceContext
    private EntityManager entityManager;
	
	private static final Logger logger = LogManager.getLogger("SERVICE-REGISTRASI-MATAKULIAH");
	
	@Transactional
	public void insertData(RegistrasiMatakuliah registrasiMatkul) {
		logger.trace("Repo ADD Registrasi Matakuliah Mhs : " + registrasiMatkul.getJadwalkuliah().getKode());
		logger.trace("Repo ADD Registrasi Matakuliah Mhs : " + registrasiMatkul.getMahasiswa().getNim());
	    entityManager.createNativeQuery("INSERT INTO registrasi_matakuliah (kode_kelas,nim_mhs)"
	    		+ "VALUES (?,?)")
	      .setParameter(1, registrasiMatkul.getJadwalkuliah().getKode())
	      .setParameter(2, registrasiMatkul.getMahasiswa().getNim())
	      .executeUpdate();
	}

	@Transactional
	public void deleteData(String kode,String nim) {
		logger.trace("Repo Delete JadwalKuliah " +kode);
		entityManager.createNativeQuery("DELETE FROM registrasi_matakuliah WHERE kode_kelas= ? and nim_mhs = ? ")
		.setParameter(1, kode)
		.setParameter(2, nim)
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
