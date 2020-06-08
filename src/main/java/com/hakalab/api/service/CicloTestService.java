package com.hakalab.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.CicloTestDAO;
import com.hakalab.api.entity.CicloTest;

@Service
public class CicloTestService {

	@Autowired
	private CicloTestDAO cicloTestDAO;
	
	public List<CicloTest> getAll() {
		List<CicloTest> ciclos = cicloTestDAO.getAll();
		return ciclos;
	}
}
