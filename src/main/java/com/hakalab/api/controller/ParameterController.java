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

import com.hakalab.api.entity.Parameter;
import com.hakalab.api.entity.Step;
import com.hakalab.api.service.ParameterService;

@RestController
@RequestMapping(value = "/hakalab/features/scenarios/steps/")
public class ParameterController {

	private ParameterService parameterService;

	@GetMapping(value = "parameters", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findAll() {
		List<Parameter> parameter = parameterService.getAll();
		if (parameter.isEmpty()) {
			return new ResponseEntity<String>("Parameter not found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(parameter.toString());
	}

	@GetMapping(value = "parameterById", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getStepById(@RequestBody Parameter parameter) {
		Parameter parameter2 = parameterService.getById(parameter.getIdParameter());
		if (parameter2 == null) {
			return new ResponseEntity<String>("Parameter not found with id: " + parameter.getIdParameter(), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(parameter2.toString());
	}

	@GetMapping(value = "parametersByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getStepByName(@RequestBody Parameter parameter) {
		List<Parameter> parameter2 = parameterService.getByName(parameter.getNameParameter());
		if (parameter2.isEmpty()) {
			return new ResponseEntity<String>("Parameter not found with name: " + parameter.getNameParameter(), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(parameter2.toString());
	}

	@PostMapping(value = "parameters", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addStep(@RequestBody Step step) {
		String status = parameterService.save(step);
		switch (status) {
		case "idStep":
			return new ResponseEntity<String>("The idStep is null or 0", HttpStatus.BAD_REQUEST);
		case "idStepNoExist":
			return new ResponseEntity<String>("The step no exist", HttpStatus.NOT_FOUND);
		case "nameParameter":
			return new ResponseEntity<String>("The nameparameter is null or has nothing written", HttpStatus.BAD_REQUEST);
		case "valueParameter":
			return new ResponseEntity<String>("The valueParameter is null or has nothing written", HttpStatus.BAD_REQUEST);
		case "parameterExist":
			return new ResponseEntity<String>("The parameter already exists", HttpStatus.FOUND);
		default:
			return ResponseEntity.status(HttpStatus.CREATED).body(step.toString());
		}
	}

	@PutMapping(value = "parameter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateStep(@RequestBody Parameter parameter) {
		Integer status = 0;
		status = parameterService.update(parameter);
		if (status == 0) {
			return new ResponseEntity<String>("Parameter not found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(parameter.toString());
	}

	@DeleteMapping(value = "parameter", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deteteAllStep(@RequestBody Parameter parameter) {
		Parameter parameter2 = parameterService.deleteAll(parameter);
		if (parameter2 != null) {
			return ResponseEntity.status(HttpStatus.OK).body("Eliminated parameter with id: " + parameter2.getIdParameter());
		}
		return new ResponseEntity<String>("parameter not found with id: " + parameter.getIdParameter(), HttpStatus.NOT_FOUND);
	}

	@Autowired
	public void setStepService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}
}
