package com.apap.tugas1.service;
import java.util.List;

import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiService {
	PegawaiModel getDataPegawaiByNIP(String nip);
	void addPegawai(PegawaiModel pegawai);
	void updatePegawai(PegawaiModel pegawai);
	double calculateGaji(PegawaiModel pegawai);
	List<PegawaiModel> getAllPegawai();
	PegawaiModel getPegawaiTertuaDiInstansi(Long idInstansi);
	PegawaiModel getPegawaiTermudaDiInstansi(Long idInstansi);
	String generateNIP(PegawaiModel pegawai);
}
