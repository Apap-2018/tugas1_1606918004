package com.apap.tugas1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDb;
import com.apap.tugas1.repository.ProvinsiDb;
import com.apap.tugas1.repository.InstansiDb;

import com.apap.tugas1.model.ProvinsiModel;
@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	@Autowired
	private PegawaiDb pegawaiDb;
	
	@Autowired
	private InstansiDb instansiDb;
	@Autowired
	private ProvinsiDb provinsiDb;

	@Override
	public PegawaiModel getDataPegawaiByNIP(String nip) {
		// TODO Auto-generated method stub
		return pegawaiDb.findBynip(nip);
	}

	@Override
	public void addPegawai(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		String NIP = generateNIP(pegawai);
		pegawaiDb.save(pegawai);
		
		
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

	@Override
	public String generateNIP(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		
		ProvinsiModel provinsi = pegawai.getInstansi().getProvinsi();
		int urutan=0;
		for(InstansiModel instansi : provinsi.getListInstansi()) {
			urutan++;
			if(instansi.equals(pegawai.getInstansi())) {//buat dapetin urutan instansi
				break;
			}//belum jadi
		}
		int urutanPeg = 1;
		for(PegawaiModel pegawaiTemp : pegawai.getInstansi().getListPegawai()) {
			if(pegawaiTemp.getTanggal_lahir().equals(pegawai.getTanggal_lahir())) {
				if(pegawaiTemp.getTahun_masuk().equals(pegawai.getTahun_masuk())) {
					urutanPeg++;
				}
			}
		}

		String urutan1 = Integer.toString(urutan);
		String urutanPeg1 = Integer.toString(urutanPeg);
		String tglLahir = Integer.toString(pegawai.getTanggal_lahir().getDate());
		String blnLahir = Integer.toString(pegawai.getTanggal_lahir().getMonth());
		String thnLahir = Integer.toString(pegawai.getTanggal_lahir().getYear());
		String thnMulai = pegawai.getTahun_masuk();
		
		String kodeProv = Long.toString(provinsi.getId());
		if (kodeProv.length() == 1) kodeProv="0" +kodeProv;
		if (urutan1.length()<2) urutan1 = "0" + urutan1;
		if (tglLahir.length()<2) tglLahir = "0" + tglLahir;
		if (blnLahir.length()<2) blnLahir = "0" + blnLahir;
		if (thnLahir.length()<2) thnLahir = "0" + thnLahir;
		if (urutanPeg1.length()<2) urutanPeg1 = "0" + urutanPeg1;
		return kodeProv + urutan1 +tglLahir + blnLahir + thnLahir + thnMulai + urutanPeg1;
	}

}
