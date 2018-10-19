package com.apap.tugas1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.repository.InstansiDb;

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService{
	@Autowired
	private InstansiDb instansiDb;
	@Override
	public List<InstansiModel> findAll() {
		// TODO Auto-generated method stub
		return instansiDb.findAll();
	}
	@Override
	public InstansiModel getInstansiById(Long id) {
		// TODO Auto-generated method stub
		return instansiDb.getOne(id);
	}
	

}
