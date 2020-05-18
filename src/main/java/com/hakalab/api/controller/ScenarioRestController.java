package com.hakalab.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakalab.api.entity.Feature;
import com.hakalab.api.entity.Scenario;
import com.hakalab.api.service.ScenarioService;

@RestController
@RequestMapping(value = "/hakalab/features") //esta sera la raiz de la url, es decir http://127.0.0.1:8082/hakalab/
public class ScenarioRestController {

	@Autowired
	private ScenarioService scenarioService;

	@GetMapping(value = "/scenarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findAll(){
		List<Scenario> scenarios = scenarioService.getAll();
		if(scenarios.isEmpty()) 
			 return new ResponseEntity<String>("Scenarios not found", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(scenarios.toString());
	}

	@GetMapping(value = "/scenariosByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getScenarioByName(@PathVariable String name){
		Scenario scenario = scenarioService.getByName(name);		
		if(scenario == null) 
			 return new ResponseEntity<String>("Scenarios not found with name: "+name, HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(scenario.toString());
	}
	
	@GetMapping(value = "/scenariosById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getScenarioById(@PathVariable Integer id){
		Scenario scenario = scenarioService.getById(id);		
		if(scenario == null)
			 return new ResponseEntity<String>("Scenarios not found with id: "+ id, HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(scenario.toString());
	}
	
	@PostMapping(value = "/scenarios", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addScenario(@RequestBody Feature feature) {
		Integer status = scenarioService.saveScenario(feature);
		if(status==0) 
			return new ResponseEntity<String>("Scenario exist with name: " + feature.getNameFeature(), HttpStatus.FOUND);
		return new ResponseEntity<String>("Scenario created with name: " + feature.getNameFeature(), HttpStatus.FOUND);
	}
	
	@PutMapping(value = "/updatescenario", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateScenario(@RequestBody Scenario scenario) {
		Integer status = 0;
		status = scenarioService.update(scenario);
		if(status==0)
			 return new ResponseEntity<String>("Scenario not found with name: " + scenario.getNameScenario(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(scenario.toString());
	}
	
	@DeleteMapping("scenariosByName/{name}")
	public ResponseEntity<String> deleteScenario(@PathVariable String name) {
		Scenario scenario = scenarioService.deleteScenario(name);
		if(scenario!=null)
			return ResponseEntity.status(HttpStatus.OK).body("Eliminated scenario with name: "+name);
		return new ResponseEntity<String>("Scenarios not found with name: "+name, HttpStatus.NOT_FOUND);
	}
	
}
