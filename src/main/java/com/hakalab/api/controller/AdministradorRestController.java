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
import com.hakalab.api.entity.Usuario;
import com.hakalab.api.service.AdministradorService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hakalab/organizacion")
public class AdministradorRestController {
	@Autowired
	private AdministradorService administradorService;
	
	@GetMapping(value = "/administradores", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findAll(){
		List<Administrador> administradores = administradorService.getAll();
		if (administradores.isEmpty()) {
			return new ResponseEntity<String>("Admins not found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(administradores.toString());
	}
	
	@GetMapping(value = "/administrador", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAdministradorById(@RequestBody Administrador administrador) {
		Administrador administradorExist = administradorService.getById(administrador.getId());
		if (administradorExist == null) {
			return new ResponseEntity<String>("Admin not found with id: "+ administrador.getId(), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.OK).body(administradorExist.toString());
	}
	
//	@PostMapping(value = "/administrador", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> addUsuario(@RequestBody Organizacion organizacion) {
//		Integer status = administradorService.saveAdministrador(organizacion);
//		if (status == 0) {
//			return new ResponseEntity<String>("Admin exist with name: " +organizacion.getAdministradores().toString(), HttpStatus.CONFLICT);
//		}
//		return ResponseEntity.status(HttpStatus.CREATED).body(organizacion.getAdministradores().toString());
//	}
//	
//	@PutMapping(value = "/administrador", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> updateUsuario(@RequestBody Administrador administrador) {
//		Integer status = 0;
//		status = administradorService.updateAdministrador(administrador);
//		if (status == 0) {
//			return new ResponseEntity<String>("admin not found with name: " +administrador.toString(), HttpStatus.NOT_FOUND);
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(administrador.toString());
//	}
//	
//	@DeleteMapping(value = "/usuarioById")
//	public ResponseEntity<String> deleteUsuario(@RequestBody Administrador administrador) {
//		Usuario usuarioExist = administradorService.deleteAdministrador(administrador.getId());
//		if (usuarioExist == null) {
//			return new ResponseEntity<String>("Usuario not found with name: " +administrador.getId(), HttpStatus.NOT_FOUND);
//		}
//		return ResponseEntity.status(HttpStatus.OK).body("Eliminated admin with id: " +administrador.getId());
//	}
}
