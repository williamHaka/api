package com.hakalab.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hakalab.api.entity.Usuario;
import com.hakalab.api.repository.GestorUsuario;

@Service("usuarioService")
public class UsuarioService implements UserDetailsService{

	@Autowired
	@Qualifier("GestorUsuario")
	private GestorUsuario repo;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		BCryptPasswordEncoder encoder = passwordEncoder();
		Usuario user = repo.findByNameUser(username);
		return new User(user.getNameUser(), encoder.encode(user.getPassUser()), buildgrante(user.getNameUser()));
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	public List<GrantedAuthority> buildgrante(String rolUser){
		String[] rol = {"hakalab"};
		List<GrantedAuthority> auths = new ArrayList<>();
		
		for (int i = 0; i < rol.length; i++) {
			auths.add(new SimpleGrantedAuthority("hakalab"));	
		}
		
		return auths;
	}
	
	
}
