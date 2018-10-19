package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.JabatanService;

@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private JabatanService jabatanService;
	
    @RequestMapping("/")
    private String home(Model model) {	
    	List<JabatanModel> allJabatan = jabatanService.findAll();
    	model.addAttribute("allJabatan", allJabatan);
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

}
