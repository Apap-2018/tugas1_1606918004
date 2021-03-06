package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.InstansiDb;
import com.apap.tugas1.repository.ProvinsiDb;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.InstansiService;

@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	@Autowired
	private ProvinsiDb provinsiDb;
	
	@Autowired
	private JabatanService jabatanService;
	@Autowired
	private InstansiService instansiService;
	@Autowired
	private ProvinsiDb provinsiService;
	
    @RequestMapping("/")
    private String home(Model model) {	
    	List<JabatanModel> allJabatan = jabatanService.findAll();
    	List<InstansiModel> allInstansi = instansiService.findAll();
    	List<ProvinsiModel> allProvinsi = provinsiService.findAll();
    	model.addAttribute("allJabatan", allJabatan);
    	model.addAttribute("allInstansi", allInstansi);
    	model.addAttribute("allProvinsi", allProvinsi);
        return "home";
    }
	
	@RequestMapping(value="/pegawai", method = RequestMethod.GET)
	private String viewPegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getDataPegawaiByNIP(nip);
		String instansi = pegawai.getInstansi().getNama();
		double gaji = pegawaiService.calculateGaji(pegawai);
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("gaji", (int) gaji);
		System.out.println("test" + pegawai.getNama());
		model.addAttribute("instansi", instansi);
		return "view-pegawai";
	}
	
	@RequestMapping(value="/pegawai/termuda-tertua", method = RequestMethod.GET)
	private String getTertuaTermuda(@RequestParam("id_instansi") Long id_instansi, Model model) {
		InstansiModel instansi = instansiService.getInstansiById(id_instansi);
		PegawaiModel pegawaiMuda = pegawaiService.getPegawaiTermudaDiInstansi(id_instansi);
		PegawaiModel pegawaiTua = pegawaiService.getPegawaiTertuaDiInstansi(id_instansi);
		model.addAttribute("pegawaiMuda", pegawaiMuda);
		model.addAttribute("pegawaiTua", pegawaiTua);
		
		model.addAttribute("instansi", instansi);
		return "view-tertuatermuda";
	}
	
	@RequestMapping(value="/pegawai/tambah",method = RequestMethod.GET)
	private String addPegawai(Model model) {
		List<JabatanModel> allJabatan = jabatanService.findAll();
    	List<InstansiModel> allInstansi = instansiService.findAll();
    	List<ProvinsiModel> allProvinsi = provinsiDb.findAll();
    	model.addAttribute("allProvinsi", allProvinsi);
    	model.addAttribute("allJabatan", allJabatan);
    	model.addAttribute("allInstansi", allInstansi);
		model.addAttribute("pegawai",new PegawaiModel());
		
		return "add-pegawai";
	}
	@RequestMapping(value="/pegawai/tambah", method =RequestMethod.POST)
	private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		pegawaiService.addPegawai(pegawai);
		model.addAttribute("pegawai", pegawai);
		return "add-pegawai-success";
	}
}
