package com.apap.tugas1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDb;

import com.apap.tugas1.repository.InstansiDb;
@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	@Autowired
	private PegawaiDb pegawaiDb;
	
	@Autowired
	private InstansiDb instansiDb;

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
	public double calculateGaji(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		double tempGaji = 0;
		double hasilGaji = 0;
		for(JabatanModel jabatan : pegawai.getListJabatan()) {
			double gajiPokok = jabatan.getGaji_pokok();
			if(gajiPokok > tempGaji) {
				tempGaji = gajiPokok;
			}
			
		}
		hasilGaji = tempGaji + ((pegawai.getInstansi().getProvinsi().getPresentase_tunjangan()/100)*tempGaji);
		return hasilGaji;
	}

	@Override
	public List<PegawaiModel> getAllPegawai() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public PegawaiModel getPegawaiTertuaDiInstansi(Long idInstansi) {
		// TODO Auto-generated method stub
		InstansiModel instansi = instansiDb.getOne(idInstansi);
		List<PegawaiModel> listPegawai = instansi.getListPegawai();
		PegawaiModel tempPegawai = listPegawai.get(0) ;
		for(PegawaiModel pegawai : instansi.getListPegawai()) {
			if(tempPegawai.getTanggal_lahir().compareTo(pegawai.getTanggal_lahir())>0) {
				tempPegawai = pegawai;
			}
		}
		return tempPegawai;
	}

	@Override
	public PegawaiModel getPegawaiTermudaDiInstansi(Long idInstansi) {
		// TODO Auto-generated method stub
		InstansiModel instansi = instansiDb.getOne(idInstansi);
		List<PegawaiModel> listPegawai = instansi.getListPegawai();
		PegawaiModel tempPegawai = listPegawai.get(0) ;
		for(PegawaiModel pegawai : instansi.getListPegawai()) {
			if(tempPegawai.getTanggal_lahir().compareTo(pegawai.getTanggal_lahir())<0) {
				tempPegawai = pegawai;
			}
		}
		return tempPegawai;
	}

}
