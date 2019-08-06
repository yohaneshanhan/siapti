package com.siapti.service.logindetail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siapti.service.logindetail.model.Dosen;
import com.siapti.service.logindetail.model.Mahasiswa;
import com.siapti.service.logindetail.model.Staff;
import com.siapti.service.logindetail.repository.DosenInterface;
import com.siapti.service.logindetail.repository.MahasiswaInterface;
import com.siapti.service.logindetail.repository.StaffInterface;

@Service
public class LoginDetailService {
	
	@Autowired
	DosenInterface dosenInterface;
	
	@Autowired
	MahasiswaInterface mhsInterface;
	
	@Autowired
	StaffInterface staffInterface;
	
	Dosen dosen = null;
	Mahasiswa mhs = null;
	Staff staff = null;
	
	// ====================== Select Detail ================
	public Staff detailStaff(String nip) {
		try {
			staff = staffInterface.findByNip(nip);
			
		} catch (Exception e) {
		}
		
		return staff;
	}	
	// ====================== Select Detail ================
	public Dosen detailDosen(String nip) {
		try {
			dosen = dosenInterface.findByNip(nip);
			
		} catch (Exception e) {
		}

		return dosen;
	}	
	// ====================== Select Detail ================
	public Mahasiswa detailMahasiswa(String nim) {
		try {
			mhs = mhsInterface.findByNim(nim);
			
		} catch (Exception e) {
		}
		
		return mhs;
	}	
}
