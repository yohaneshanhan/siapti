package com.siapti.service.idgenerator.repository;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class IdRepository {
	@PersistenceContext
    private EntityManager em;
	
	public String next_staff() {
		Query query = em.createNativeQuery("select next_val from staff_seq");
		
		Object result = query.getSingleResult(); 
		System.out.println("Result : "+result);
		
		String next_val = ((BigInteger) result).toString();
		System.out.println("Next Val : "+next_val);
		
		return next_val;
	}

	public String next_dosen(String progdi) {
		Query query = em.createNativeQuery("select concat(id_progdi,next_val) from dosen_seq where id_progdi = ?");
		query.setParameter(1, progdi);
		
		Object result = query.getSingleResult(); 
		System.out.println("Result : "+result);
		
		String next_val = (String) result;
		System.out.println("Next Val : "+next_val);
		
		return next_val;
	}
	
	public String next_mahasiswa(String progdi) {
		Query query = em.createNativeQuery("select concat(id_progdi,tahun,next_val) from mahasiswa_seq where id_progdi = ?");
		query.setParameter(1, progdi);
		
		Object result = query.getSingleResult(); 
		System.out.println("Result : "+result);
		
		String next_val = (String) result;
		System.out.println("Next Val : "+next_val);
		
		return next_val;
	}
	
	public String next_matakuliah(String progdi) {
		Query query = em.createNativeQuery("select concat(alias_matkul,next_val) from progdi_matakuliah_seq where id_progdi = ?");
		query.setParameter(1, progdi);
		
		Object result = query.getSingleResult(); 
		System.out.println("Result : "+result);
		
		String next_val = (String) result;
		System.out.println("Next Val : "+next_val);
		
		return next_val;
	}

	public String next_jadwal_kuliah(String kode_matkul) {
		Query query = em.createNativeQuery("select concat(kode_matkul,kode_kelas) from jadwal_kuliah_seq where kode_matkul = ?");
		query.setParameter(1, kode_matkul);
		
		Object result = query.getSingleResult(); 
		System.out.println("Result : "+result);
		
		String next_val = (String) result;
		System.out.println("Next Val : "+next_val);
		
		return next_val;
	}
	
}
