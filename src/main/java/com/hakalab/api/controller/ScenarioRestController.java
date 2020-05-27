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

	@GetMapping(value = "/scenariosByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getScenarioByName(@RequestBody Scenario scenario){
		Scenario scenario1 = scenarioService.getByName(scenario.getNameScenario());		
		if(scenario1 == null) 
			 return new ResponseEntity<String>("Scenarios not found with name: "+scenario.getNameScenario(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(scenario1.toString());
	}
	
	@GetMapping(value = "/scenariosById", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getScenarioById(@RequestBody Scenario scenario){
		Scenario scenario1 = scenarioService.getById(scenario.getIdScenario());		
		if(scenario1 == null)
			 return new ResponseEntity<String>("Scenarios not found with id: "+ scenario.getIdScenario(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(scenario1.toString());
	}
	
	@PostMapping(value = "/scenarios", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addScenario(@RequestBody Feature feature) {
		Integer status = scenarioService.saveScenario(feature);
		if(status==0) 
			return new ResponseEntity<String>("Scenario created with name: " + feature.getNameFeature(), HttpStatus.FOUND);
		return new ResponseEntity<String>("Scenario exist with name: " + feature.getNameFeature(), HttpStatus.FOUND);
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
	public ResponseEntity<String> deleteScenario(@RequestBody Scenario scenario) {
		Scenario scenario1 = scenarioService.deleteScenario(scenario.getNameScenario());
		if(scenario1!=null)
			return ResponseEntity.status(HttpStatus.OK).body("Eliminated scenario with name: "+scenario.getNameScenario());
		return new ResponseEntity<String>("Scenarios not found with name: "+scenario.getNameScenario(), HttpStatus.NOT_FOUND);
	}
	
}
