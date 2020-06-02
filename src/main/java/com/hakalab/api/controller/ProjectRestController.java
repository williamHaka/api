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

import com.hakalab.api.entity.Project;
import com.hakalab.api.service.ProjectService;

@RestController
@RequestMapping(value = "/hakalab")
public class ProjectRestController {

	@Autowired
	private ProjectService projectService;
	
	@GetMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findAll(){
		List<Project> projects = projectService.getAll();
		if (projects.isEmpty())
			return new ResponseEntity<String>("Projects not found", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(projects.toString());
	}
	
	@GetMapping(value = "/projectByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getProjectByName(@RequestBody Project project) {
		Project projectExist = projectService.getByName(project.getNameProject());
		if (projectExist.equals(null))
			return new ResponseEntity<String>("Project not found with name: " + project.getNameProject(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(projectExist.toString());
	}
	
	@GetMapping(value = "/projectById", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getProjectById(@RequestBody Project project) {
		Project projectExist = projectService.getById(project.getIdProject());
		if (projectExist.equals(null))
			return new ResponseEntity<String>("Project not found with Id: " + project.getIdProject(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(projectExist.toString());
	}
	
	@PostMapping(value = "/projects", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProject(@RequestBody Project project) {
		Integer status = projectService.saveProject(project);
		if(status==0)
			return new ResponseEntity<String>("Project Created with name: " + project.getNameProject(), HttpStatus.FOUND);
		return ResponseEntity.status(HttpStatus.CREATED).body(project.toString());
	}
	
	@PutMapping(value = "/updateproject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateProject(@RequestBody Project project) {
		Integer status = 0;
		status = projectService.update(project);
		if(status==0)
			 return new ResponseEntity<String>("Project not found with name: " + project.getNameProject(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(project.toString());
	}
	
	@DeleteMapping("projectsByName/{name}")
	public ResponseEntity<String> deleteProject(@PathVariable String name) {
		Project project = projectService.deleteProject(name);
		if(project!=null)
			return ResponseEntity.status(HttpStatus.OK).body("Eliminated project with name: "+name);
		return new ResponseEntity<String>("Projects not found with name: "+name, HttpStatus.NOT_FOUND);
	}
}
