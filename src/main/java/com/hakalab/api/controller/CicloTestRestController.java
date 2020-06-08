package com.hakalab.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakalab.api.entity.CicloTest;
import com.hakalab.api.service.CicloTestService;

@RestController
@RequestMapping(value = "/hakalab/projects/usuarios")
public class CicloTestRestController {

	@Autowired
	private CicloTestService cicloTestService;
	
	@GetMapping(value = "/ciclos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findAll() {
		List<CicloTest> ciclos = cicloTestService.getAll();
		if (ciclos.isEmpty())
			return new ResponseEntity<String>("No test cycles have been performed yet", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(ciclos.toString());	
	}
}
