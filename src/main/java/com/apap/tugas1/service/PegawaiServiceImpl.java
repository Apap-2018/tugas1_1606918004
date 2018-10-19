package com.apap.tugas1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDb;
@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	@Autowired
	private PegawaiDb pegawaiDb;

	@Override
	public PegawaiModel getDataPegawaiByNIP(String nip) {
		// TODO Auto-generated method stub
		return pegawaiDb.findBynip(nip);
	}

	@Override
	public void addPegawai(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePegawai(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PegawaiModel getPegawaiTertuaDiInstansi(Long idInstansi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PegawaiModel getPegawaiTermudaDiInstansi(Long idInstansi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calculateGaji(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PegawaiModel> getAllPegawai() {
		// TODO Auto-generated method stub
		
		return null;
	}

}
