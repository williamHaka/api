package com.hakalab.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakalab.api.entity.Organizacion;
import com.hakalab.api.service.OrganizacionService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/hakalab")
public class OrganizacionRestController {
	@Autowired
	private OrganizacionService organizacionService;
	
	@PostMapping(value = "/organizacion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addOrganizacion(@RequestBody Organizacion organizacion) {
		Organizacion organizacionSave = organizacionService.save(organizacion);
		if(organizacionSave == null) {
			return new ResponseEntity<String>("El rut ingresado ya se encuentra en el sistema: " + organizacion.getRutOrganizacion(), HttpStatus.CONFLICT);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(organizacionSave.toString());
	}
	
	@GetMapping(value = "/organizaciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findAll(){
		List<Organizacion> organizaciones = organizacionService.getAll();
		if (organizaciones.isEmpty()) {
			return new ResponseEntity<String>("No hay organizaciones en el sistema", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(organizaciones.toString());
	}
	
	@GetMapping(value = "/organizacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getOrganizacion(@RequestBody Organizacion organizacion) {
		Organizacion organizacionExist = organizacionService.getById(organizacion.getIdOrganizacion());
		if (organizacionExist == null) {
			return new ResponseEntity<String>("No se ha encontrado una organizacion con id: " + organizacion.getIdOrganizacion(), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(organizacionExist.toString());
	}
	
	@PutMapping(value = "/organizacion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateOrganizacion(@RequestBody Organizacion organizacion) {
		Organizacion organizacionUpdate = organizacionService.update(organizacion);
		if(organizacionUpdate == null) {
			return new ResponseEntity<String>("No se encuentra organizacion: " + organizacion.getNombreOrganizacion() + " en el sistema", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(organizacionUpdate.toString());
	}
	
	@DeleteMapping(value = "/organizacion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteOrganizacion(@RequestBody Organizacion organizacion) {
		Integer status = organizacionService.delete(organizacion.getIdOrganizacion());
		if(status == 0) {
			return new ResponseEntity<String>("No se ha encontrado la organizacion: "+organizacion.getNombreOrganizacion(), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body("Organizacion eliminada");
	}
}
