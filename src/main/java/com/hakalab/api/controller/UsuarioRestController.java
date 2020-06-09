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

import com.hakalab.api.entity.Usuario;
import com.hakalab.api.service.UsuarioService;

@RestController
@RequestMapping(value = "/hakalab")
public class UsuarioRestController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(value = "/authenticate")
	public ResponseEntity<String> getToken(@RequestBody Usuario usuario) throws Exception{
		String token = usuarioService.getToken(usuario);
		if(token != null)
			return ResponseEntity.status(HttpStatus.CREATED).body(token);	
		return new ResponseEntity<String>("Token null: ", HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findAll() {
		List<Usuario> usuarios = usuarioService.getAll();
	if (usuarios.isEmpty())
		return new ResponseEntity<String>("Usuarios not found", HttpStatus.NOT_FOUND);
	return ResponseEntity.status(HttpStatus.OK).body(usuarios.toString());
	}

	@GetMapping(value = "/usuarioById", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getUsuarioById(@RequestBody Usuario usuario) {
		Usuario usuarioExist = usuarioService.getById(usuario.getIdUsuario());
		if(usuarioExist == null)
			return new ResponseEntity<String>("Usuario not found with id: " +usuario.getIdUsuario(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioExist.toString());
	}
	
	@GetMapping(value = "/usuariosByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getUsuarioByName(@RequestBody Usuario usuario) {
		List<Usuario> usuarioExist = usuarioService.getByName(usuario.getNameUsuario());
		if (usuarioExist.isEmpty())
			return new ResponseEntity<String>("Usuario not found with name: " +usuario.getNameUsuario(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioExist.toString());
	}
	
	@PostMapping(value = "/usuarios", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addUsuario(@RequestBody Usuario usuario) {
		Integer status = usuarioService.saveUsuario(usuario);
		if (status == null)
			return new ResponseEntity<String>("Usuario exist with name: " +usuario.getNameUsuario(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario.toString());
	}
	
	@PostMapping(value = "/assignusuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> asignUsuario(@RequestBody Usuario usuario) {
		Integer status = usuarioService.assignUsuario(usuario);
		if (status == null)
			return new ResponseEntity<String>("Usuario: " +usuario.getNameUsuario() + "assigned to project: " +usuario.getNameUsuario(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario.toString());
	}
	
	@PutMapping(value = "/updateusuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUsuario(@RequestBody Usuario usuario) {
		Integer status = 0;
		status = usuarioService.updateUsuario(usuario);
		if (status == 0)
			return new ResponseEntity<String>("Usuario not found with name: " +usuario.getNameUsuario(), HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(usuario.toString());
	}
	
	@DeleteMapping(value = "/usuarioById")
	public ResponseEntity<String> deleteUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioExist = usuarioService.deleteUsuario(usuario.getIdUsuario());
		if (usuarioExist == null)
			return ResponseEntity.status(HttpStatus.OK).body("Eliminated usuario with id: " +usuario.getIdUsuario());
		return new ResponseEntity<String>("Usuario not found with id: " +usuario.getIdUsuario(), HttpStatus.NOT_FOUND);
	}
	
}