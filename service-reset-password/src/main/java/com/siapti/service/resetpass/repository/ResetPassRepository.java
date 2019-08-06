package com.siapti.service.resetpass.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public class ResetPassRepository {

	@PersistenceContext
    private EntityManager em;
	
	public String get_default_staff(String id) {
		Object result = em.createNativeQuery("SELECT DATE_FORMAT(tgl_lahir, '%d%m%Y') FROM staff where nip_staff = ?")
				.setParameter(1, id)
				.getSingleResult();
		System.out.println("Result : "+result);
		
		String new_pass = (String) result;
		System.out.println("Default Pass : "+new_pass);
		
		return new_pass;
	}
	
	public String get_default_dosen(String id) {
		Object result = em.createNativeQuery("SELECT DATE_FORMAT(tgl_lahir, '%d%m%Y') FROM dosen where nip_dosen = ?")
				.setParameter(1, id)
				.getSingleResult();
		System.out.println("Result : "+result);
		
		String new_pass = (String) result;
		System.out.println("Default Pass : "+new_pass);
		
		return new_pass;
	}
	
	public String get_default_mhs(String id) {
		Object result = em.createNativeQuery("SELECT DATE_FORMAT(tgl_lahir, '%d%m%Y') FROM mahasiswa where nim_mhs = ?")
				.setParameter(1, id)
				.getSingleResult();
		System.out.println("Result : "+result);
		
		String new_pass = (String) result;
		System.out.println("Default Pass : "+new_pass);
		
		return new_pass;
	}
	
	@Transactional
	public int update_pass_staff(String new_pass,String id) {
		int countUpdated = em.createNativeQuery("UPDATE staff SET password = ? where nip_staff = ?")
				.setParameter(1, new_pass)
				.setParameter(2, id)
				.executeUpdate();
		
		return countUpdated;
	}
	
	@Transactional
	public int update_pass_dosen(String new_pass,String id) {
		int countUpdated = em.createNativeQuery("UPDATE dosen SET password = ? where nip_dosen = ?")
				.setParameter(1, new_pass)
				.setParameter(2, id)
				.executeUpdate();
		
		return countUpdated;
	}
	
	@Transactional
	public int update_pass_mhs(String new_pass,String id) {
		int countUpdated = em.createNativeQuery("UPDATE mahasiswa SET password = ? where nim_mhs = ?")
				.setParameter(1, new_pass)
				.setParameter(2, id)
				.executeUpdate();
		
		return countUpdated;
	}
	
}
