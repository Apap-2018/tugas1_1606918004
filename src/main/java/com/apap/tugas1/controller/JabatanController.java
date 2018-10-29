package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.service.JabatanService;


@Controller
public class JabatanController {
	@Autowired
	private JabatanService jabatanService;
	
	private long tempId;
	
	@RequestMapping(value="/jabatan/tambah",method = RequestMethod.GET)
	private String addJabatan(Model model) {
		model.addAttribute("jabatan",new JabatanModel());
		
		return "add-jabatan";
	}
	@RequestMapping(value="/jabatan/tambah", method =RequestMethod.POST)
	private String addJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
		jabatanService.addJabatan(jabatan);
		model.addAttribute("jabatan", jabatan);
		return "add";
	}
	@RequestMapping(value = "/jabatan/view")
	private String viewPegawai(@RequestParam("id_jabatan") Long id_jabatan, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanById(id_jabatan);
		tempId = id_jabatan;		
		model.addAttribute("jabatan", jabatan);
		System.out.println("jabatan" + jabatan);
		return "view-jabatan"; 
	}
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
	private String editJabatan(@RequestParam("id_jabatan") Long id_jabatan, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanById(id_jabatan);
		model.addAttribute("jabatan", jabatan);
		return "ubah-jabatan";
	}
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
	private String updateJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
		jabatan.setId(tempId);
		jabatanService.updateJabatan(jabatan);
		model.addAttribute("jabatan", jabatan);
		return "ubah-jabatan-success";
	}
	
	@RequestMapping(value = "jabatan/hapus", method = RequestMethod.POST) 
	private String deleteJabatan(){
		jabatanService.deleteJabatanById(tempId);
		 
		return "delete-success";
	}
	@RequestMapping("/jabatan/viewall")
	public String viewall(Model model) {
		List<JabatanModel> archive = jabatanService.findAll();
		
		model.addAttribute("listJabatan", archive);
		return "viewall-jabatan";
	}

}
