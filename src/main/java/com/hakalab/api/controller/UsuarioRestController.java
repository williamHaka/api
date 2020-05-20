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

import com.hakalab.api.entity.Usuario;
import com.hakalab.api.service.UsuarioService;

@RestController
@RequestMapping(value = "/hakalab")
public class UsuarioRestController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findAll() {
		List<Usuario> usuarios = usuarioService.getAll();
		if (usuarios.isEmpty())
			return new ResponseEntity<String>("Usuarios not found", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(usuarios.toString());
	}

	@GetMapping(value = "/usuariosByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getUsuario(@PathVariable String name) {
		Usuario usuario = usuarioService.getByName(name);
		if (usuario == null)
			return new ResponseEntity<String>("Usuarios not found with name: "+name, HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(usuario.toString());
	}
	
	@GetMapping(value = "/usuariosById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getUsuariosById(@PathVariable Integer id){
		Usuario usuario = usuarioService.getById(id);		
		if(usuario == null)
			 return new ResponseEntity<String>("Usuarios not found with id: "+ id, HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(usuario.toString());
	}
	
	@PostMapping(value = "/usuarios", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addUsuario(@RequestBody Usuario usuario) {
		Integer status = usuarioService.saveUsuario(usuario);
		if(status==0) 
			return new ResponseEntity<String>("Usuario exist with name: "+usuario.getNameUser(), HttpStatus.FOUND);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario.toString());
	}
	
	@PutMapping(value = "/updateusuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUsuario(@RequestBody Usuario usuario) {
		Integer status = 0;
		status = usuarioService.update(usuario);
		if(status==0)
			 return new ResponseEntity<String>("Usuarios not found with name: " + usuario.getNameUser(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(usuario.toString());
	}
	
	@DeleteMapping("usuariosByName/{name}")
	public ResponseEntity<String> deleteUsuario(@PathVariable Usuario usuario) {
		Usuario usuario2 = usuarioService.deleteUsuario(usuario.getIdUser());
		if(usuario2!=null)
			return ResponseEntity.status(HttpStatus.OK).body("Eliminated user with name: "+usuario.getIdUser());
		return new ResponseEntity<String>("Usuarios not found with name: "+usuario.getIdUser(), HttpStatus.NOT_FOUND);
	}
	
}
