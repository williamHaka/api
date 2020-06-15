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

import com.hakalab.api.entity.Suscripcion;
import com.hakalab.api.service.SuscripcionService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/hakalab/organizacion")
public class SuscripcionRestController {
	@Autowired
	private SuscripcionService suscripcionService;
	
	@PostMapping(value = "/suscripcion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addSuscripcion(@RequestBody Suscripcion suscripcion) {
		Suscripcion suscripcionSave = suscripcionService.save(suscripcion);                                                                                    
		if(suscripcionSave == null) {
			return new ResponseEntity<String>("Ya hay una suscripcion con el nombre: " + suscripcion.getNombreSuscripcion(), HttpStatus.CONFLICT);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(suscripcionSave.toString());
	}
	
	@GetMapping(value = "/suscripciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findAll(){
		List<Suscripcion> suscripciones = suscripcionService.getAll();
		if (suscripciones.isEmpty()) {
			return new ResponseEntity<String>("No hay suscripciones en el sistema", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(suscripciones.toString());
	}
	
	@PutMapping(value = "/suscripcion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateOrganizacion(@RequestBody Suscripcion suscripcion) {
		Suscripcion suscripcionUpdate = suscripcionService.update(suscripcion);
		if(suscripcionUpdate == null) {
			return new ResponseEntity<String>("No se encuentra la suscripcion id: " + suscripcion.getIdSuscripcion() + " en el sistema", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(suscripcionUpdate.toString());
	}
	
	@DeleteMapping(value = "/suscripcion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteOrganizacion(@RequestBody Suscripcion suscripcion) {
		Integer status = suscripcionService.delete(suscripcion.getIdSuscripcion());
		if(status == 0) {
			return new ResponseEntity<String>("No se encuentra la suscripcion id: "+suscripcion.getIdSuscripcion(), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body("Suscripcion eliminada");
	}
}
