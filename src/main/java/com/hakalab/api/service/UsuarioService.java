package com.hakalab.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hakalab.api.entity.Usuario;
import com.hakalab.api.repository.GestorUsuario;

@Service("usuarioService")
public class UsuarioService implements UserDetailsService{

	@Autowired
	@Qualifier("GestorUsuario")
	private GestorUsuario repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Usuario user = repo.findByNameUser(username);
		return new User(user.getNameUser(), user.getPassUser(), buildgrante(user.getRolUser()));
	}
	
	public List<GrantedAuthority> buildgrante(byte rolUser){
		String[] rol = {"LECTOR", "USUARIO", "ADMIN"};
		List<GrantedAuthority> auths = new ArrayList<>();
		
		for (int i = 0; i < rolUser; i++) {
			auths.add(new SimpleGrantedAuthority(rol[i]));	
		}
		
		return auths;
	}
}
