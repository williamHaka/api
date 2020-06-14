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

import com.hakalab.api.entity.Administrador;
import com.hakalab.api.entity.Organizacion;
import com.hakalab.api.service.AdministradorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hakalab/organizacion")
public class AdministradorRestController {
	@Autowired
	private AdministradorService administradorService;

	@PostMapping(value = "/admninistrador", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addAdministrador(@RequestBody Organizacion organizacion){
		Administrador administrador = administradorService.save(organizacion);
		if (administrador == null) {
			return new ResponseEntity<String>("No se ha podido guardar el administrador", HttpStatus.CONFLICT);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(administrador.toString());
	}
	
	@GetMapping(value = "/administradores", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findAll(){
		List<Administrador> administradores = administradorService.getAll();
		if (administradores.isEmpty()) {
			return new ResponseEntity<String>("No hay administradores en el sistema", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(administradores.toString());
	}
	
	@GetMapping(value = "/admninistrador", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAdmninistrador(@RequestBody Administrador administrador) {
		Administrador administradorExist = administradorService.getById(administrador.getIdAdministrador());
		if (administradorExist == null) {
			return new ResponseEntity<String>("No se ha encontrado un administrador con id: " + administrador.getIdAdministrador(), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(administradorExist.toString());
	}
	
	@PutMapping(value = "/admninistrador", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateAdministrador(@RequestBody Administrador administrador) {
		Administrador administradorUpdate = administradorService.update(administrador);
		if(administradorUpdate == null) {
			return new ResponseEntity<String>("No se encuentra el administrador en el sistema", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(administradorUpdate.toString());
	}
	
	@DeleteMapping(value = "/admninistrador", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteAdministrador(@RequestBody Administrador administrador) {
		Integer status = administradorService.delete(administrador.getIdAdministrador());
		if(status == 0) {
			return new ResponseEntity<String>("No se ha encontrado el administrador: "+administrador.getNombreAdministrador(), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body("Administrador eliminado");
	}
}
