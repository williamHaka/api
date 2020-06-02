package com.hakalab.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakalab.api.entity.Usuario;
import com.hakalab.api.service.FeatureService;
import com.hakalab.api.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/hakalab/") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/hakalab/
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping(value = "/authenticate")
	public ResponseEntity<String> getToken(@RequestBody Usuario usuario) throws Exception{
		String token = usuarioService.getTokenByUsername(usuario);
		if(token != null)
			return ResponseEntity.status(HttpStatus.OK).body(token);	
		return new ResponseEntity<String>("Token null: ", HttpStatus.NOT_FOUND);
		
	}
}
