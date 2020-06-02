package com.hakalab.api.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.hakalab.api.entity.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service("usuarioService")
public class UsuarioService{
	
	public String getTokenByUsername(Usuario usuario) throws Exception {
		String jwt = Jwts.builder()
				.setSubject("users/TzMUocMF4p").setExpiration(new Date(1300819380))
				.claim("name", usuario.getNameUser())
				.claim("scope", "self gropus/admins") //setear roles
				.signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8"))
				.compact();
		return jwt;
	}
	
	
}
