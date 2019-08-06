package com.siapti.service.jadwalkuliah.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.siapti.service.jadwalkuliah.model.JadwalKuliah;

@Repository
public class JadwalKuliahRepository {
	@PersistenceContext
    private EntityManager entityManager;
	
	private static final Logger logger = LogManager.getLogger("SERVICE-JADWAL-KULIAH");
	
	@Transactional
	public void insertData(JadwalKuliah jadwalkuliah) {
		logger.trace("Repo ADD JadwalKuliah Body " + jadwalkuliah.getKode());
		logger.trace("Repo ADD JadwalKuliah Body " + jadwalkuliah.getDosen().getNip());
	    entityManager.createNativeQuery("INSERT INTO jadwal_kuliah (kode_kelas,kode_matkul,nip_dosen,hari) "
	    		+ "VALUES (?,?,?,?)")
	      .setParameter(1, jadwalkuliah.getKode())
	      .setParameter(2, jadwalkuliah.getMatakuliah().getKode())
	      .setParameter(3, jadwalkuliah.getDosen().getNip())
	      .setParameter(4, jadwalkuliah.getHari())
	      .executeUpdate();
	}
	
	@Transactional
	public void deleteData(String kode) {
		logger.trace("Repo Delete Jadwal Kuliah " +kode);
		entityManager.createNativeQuery("DELETE FROM jadwal_kuliah WHERE kode_kelas = ?")
		.setParameter(1, kode)
		.executeUpdate();
	}
	
	@SuppressWarnings("rawtypes")
	public List jadwalByProgdiMhs(String id) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sel_jadwal_by_progdi");
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		query.setParameter(1, id);
		query.execute();
		
		return query.getResultList();
	}
	
	@Transactional
	public void dropData() {
		entityManager.createNativeQuery("DELETE from jadwal_kuliah")
		.executeUpdate();
		
		String kode= "A";
		entityManager.createNativeQuery("UPDATE jadwal_kuliah_seq set kode_kelas = ?")
		.setParameter(1, kode)
		.executeUpdate();
	}
	
	/*@SuppressWarnings("rawtypes")
	public List jadwalByMhs(String nim) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sel_jadwal_by_mhs");
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		query.setParameter(1, nim);
		query.execute();
		
		return query.getResultList();
	}*/
	
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
