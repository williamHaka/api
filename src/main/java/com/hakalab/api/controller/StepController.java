package com.hakalab.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakalab.api.entity.Scenario;
import com.hakalab.api.entity.Step;
import com.hakalab.api.service.StepService;

@RestController
@RequestMapping(value = "/hakalab/features/scenarios")
public class StepController {

	@Autowired
	private StepService stepService;
	
	@GetMapping(value = "/steps", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findAll(){
		List<Step> step = stepService.getAll();
		if(step.isEmpty()) {
			 return new ResponseEntity<String>("Step not found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(step.toString());
	}
	
	@GetMapping(value = "/stepById", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getStepById(@RequestBody Step step){
		Step stepExist = stepService.getById(step.getIdStep());		
		if(stepExist==null) 
			 return new ResponseEntity<String>("Step not found with id: "+step.getIdStep(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(stepExist.toString());
	}
	
	@GetMapping(value = "/stepsByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getStepByName(@RequestBody Step step){
		List<Step> stepsExist = stepService.getByName(step.getNameStep());		
		if(stepsExist.isEmpty()) 
			 return new ResponseEntity<String>("Step not found with name: "+step.getNameStep(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(stepsExist.toString());
	}
	
	@PostMapping(value = "/step",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addStep(@RequestBody Scenario scenario) {
		Integer status = stepService.save(scenario);
		if(status==0) 
			return new ResponseEntity<String>("Step exist with desciption: "+scenario.getSteps().get(0).getDescriptionStep(), HttpStatus.FOUND);
		return ResponseEntity.status(HttpStatus.CREATED).body(scenario.getSteps().toString());
	}
	
	@PutMapping(value = "/step",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateStep(@RequestBody Step step) {
		Integer status = 0;
		status = stepService.update(step);
		if(status==0)
			return new ResponseEntity<String>("Step not found", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(step.toString());
	}
	
	@DeleteMapping(value = "/step", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deteteAllStep(@RequestBody Step step) {
		Step step2 = stepService.deleteAll(step.getIdStep());
		if(step2!=null)
			return ResponseEntity.status(HttpStatus.OK).body("Eliminated step with id: "+step.getIdStep());
		return new ResponseEntity<String>("Step not found with id: "+step.getIdStep(), HttpStatus.NOT_FOUND);
	}
 
}
