package com.haka.api.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import static java.util.Collections.emptyList;

public class JwtUtil {
	
	// Metodo para crear el JWT y enviarlo al cliente en el header de la respuesta
	static void addAuthentication(HttpServletResponse res, String username) {
		
		String token = Jwts.builder().setSubject(username)
		//Hash con el que se firmara
		.signWith(SignatureAlgorithm.HS512, "S3B4S7I4N#").compact();
		
		//Se agrega el header del token
		res.addHeader("Authorization", "Bearer "+ token);
	}
	
	// Metodo para validar el token enviado por el cliente
	static Authentication getAuthentication(HttpServletRequest request) {
		
		//Se obtiene el token que viene en el encabezado de la peticion
		
		String token = request.getHeader("Authorization");
		
		//Si hay token, se valida
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("S3B4S7I4N")
					.parseClaimsJws(token.replace("Bearer", "")) //eso lo valida
					.getBody().getSubject();
		
		return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
		}
		
		return null;
	}
}
