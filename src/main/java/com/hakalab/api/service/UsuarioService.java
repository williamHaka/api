package com.hakalab.api.service;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.UsuarioDAO;
import com.hakalab.api.entity.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UsuarioService{
	@Autowired
	private UsuarioDAO usuarioDAO;

	public String getTokenByUsername(Usuario usuario) throws Exception {
		String jwt = Jwts.builder()
				.setSubject("users/TzMUocMF4p")
				.setExpiration(Date.from(Instant.now().plus(1,ChronoUnit.DAYS)))
				.claim("name", usuario.getNameUser())
				.claim("scope", "self gropus/admins") //setear roles
				.signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8"))
				.compact();
		return "Bearer "+jwt;
	}
	
	public Usuario getUserByName(String username) {
		return usuarioDAO.getUserByName(username);
	}
}
