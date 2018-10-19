package com.apap.tugas1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;
@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{
	@Autowired
	private JabatanDb jabatanDb;
	
	@Override
	public void addJabatan(JabatanModel jabatan) {
		// TODO Auto-generated method stub
		jabatanDb.save(jabatan);
	}

	@Override
	public void updateJabatan(JabatanModel jabatan) {
		// TODO Auto-generated method stub
		JabatanModel jabatanToUpdate = jabatanDb.getOne(jabatan.getId());
		jabatanToUpdate.setNama(jabatan.getNama());
		jabatanToUpdate.setDeskripsi(jabatan.getDeskripsi());
		jabatanToUpdate.setGaji_pokok(jabatan.getGaji_pokok());
		
	}

	@Override
	public void deleteJabatanById(Long id) {
		// TODO Auto-generated method stub
		jabatanDb.delete(jabatanDb.getOne(id));
	}

	@Override
	public JabatanModel getJabatanById(Long id) {
		// TODO Auto-generated method stub
		return jabatanDb.getOne(id);
	}

	@Override
	public List<JabatanModel> findAll() {
		// TODO Auto-generated method stub
		return jabatanDb.findAll();
		
	}

}
