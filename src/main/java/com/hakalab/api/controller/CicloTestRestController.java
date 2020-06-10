package com.hakalab.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.HttpAccessor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakalab.api.entity.CicloTest;
import com.hakalab.api.entity.Scenario;
import com.hakalab.api.service.CicloTestService;

@RestController
@RequestMapping(value = "/hakalab/projects/features/scenarios")
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
	
	@GetMapping(value = "/cicloById", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getCicloById(@RequestBody CicloTest cicloTest) {
		CicloTest cicloExist = cicloTestService.getById(cicloTest.getIdCiclo());
		if (cicloExist == null)
			return new ResponseEntity<String>("Ciclo not found with id: " +cicloTest.getIdCiclo(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(cicloExist.toString());
	}
	
	@PostMapping(value = "/ciclos", consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addCiclo(@RequestBody Scenario scenario) {
		Integer status = cicloTestService.save(scenario);
		if (status == 0)
			return new ResponseEntity<String>("Ciclo exist: " +scenario.getCiclos().get(0).getIdCiclo(), HttpStatus.FOUND);
		return ResponseEntity.status(HttpStatus.CREATED).body(scenario.getSteps().toString());
	}
	
	@PutMapping(value = "/ciclos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateCiclo(@RequestBody CicloTest ciclo) {
		Integer status = 0;
		status = cicloTestService.updateCiclo(ciclo);
		if (status == 0)
			return new ResponseEntity<String>("Ciclo not found", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(ciclo.toString());
	}
//	
//	@DeleteMapping(value = "/cicloByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> deleteCiclo(@PathVariable String name) {
//		CicloTest cicloExist = cicloTestService.deleteCiclo(name);
//		if (cicloExist != null)
//			return ResponseEntity.status(HttpStatus.OK).body("Eliminated ciclo with name: " +name);
//		return new ResponseEntity<String>("usuario not found with name: " +name, HttpStatus.NOT_FOUND);
//	}
	
}
