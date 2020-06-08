package com.hakalab.api.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.hakalab.api.dao.UsuarioDAO;
import com.hakalab.api.dao.UsuarioProjectDAO;
import com.hakalab.api.entity.Usuario;
import com.hakalab.api.entity.UsuarioProject;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private UsuarioProjectDAO usuarioprojectDAO;
	@Autowired
	private UsuarioProjectService usuarioProjectService;

	public String getToken(Usuario usuario) throws Exception {
		Usuario user = usuarioDAO.getByEmailUser(usuario.getEmailUsuario());
		if (user.getEmailUsuario().equals(usuario.getEmailUsuario()) && user.getPassUsuario().equals(usuario.getPassUsuario())) {
			String secretKey = "H4kAl4B";
			List grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN");
			long tiempo = System.currentTimeMillis();
			String token = Jwts.builder()
					.setSubject(usuario.getEmailUsuario())
					.setIssuedAt(new Date(tiempo))
					.setExpiration(new Date(tiempo + 120000))
					.claim("nombre", user.getNameUsuario())
					.claim("apellido", user.getLastNameUsuario())
					.claim("authorities", grantedAuthorities)
					.signWith(SignatureAlgorithm.HS512,	secretKey.getBytes()).compact();
			return "Bearer " + token;
		}
		return null;
	}

	public List<Usuario> getAll() {
		List<Usuario> usuarios = usuarioDAO.getAll();
		return usuarios;
	}

	public Usuario getById(Integer id) {
		Usuario usuario = null;
		try {
			usuario = usuarioDAO.getById(id);
		} catch (Exception e) {
		}
		return usuario;
	}

	public List<Usuario> getByName(String name) {
		List<Usuario> usuarios = null;
		try {
			usuarios = usuarioDAO.getByName(name);
		} catch (Exception e) {
		}
		return usuarios;
	}

	public Integer saveUsuario(Usuario usuario) {
		Integer status = 0;
		try {
			Usuario usuarioExist = usuarioDAO.getNameUser(usuario.getNameUsuario());
			if (usuarioExist == null) {
				usuarioDAO.save(usuario);
			}
		} catch (Exception e) {
		}
		return status;
	}

	public Integer updateUsuario(Usuario usuario) {
		Integer status = 0;
		try {
			Usuario usuarioExist = usuarioDAO.getById(usuario.getIdUsuario());
			if (usuarioExist != null) {
				usuarioExist.setUserNameUsuario(usuario.getUserNameUsuario());
				usuarioExist.setPassUsuario(usuario.getPassUsuario());
				usuarioExist.setPhoneUsuario(usuario.getPhoneUsuario());
				usuarioExist.setEmailUsuario(usuario.getEmailUsuario());
				usuarioExist.setAddressUsuario(usuario.getAddressUsuario());
				usuarioExist.setNameUsuario(usuario.getNameUsuario());
				usuarioExist.setLastNameUsuario(usuario.getLastNameUsuario());
				usuarioExist.setPostalCodeUsuario(usuario.getPostalCodeUsuario());
				usuarioExist.setNameEmpresaUsuario(usuario.getNameEmpresaUsuario());
				usuarioDAO.update(usuarioExist);
				status = 1;
			}
		} catch (Exception e) {
		}
		return status;
	}

	public Usuario deleteUsuario(Integer idUsuario) {
		Usuario usuario = usuarioDAO.getById(idUsuario);
		if (usuario != null) {
			List<UsuarioProject> usuarioProjects = usuarioprojectDAO.getByIdUsuario(usuario);
			if (usuarioProjects != null) {
				for (UsuarioProject usuarioProject : usuarioProjects) {
					usuarioprojectDAO.delete(usuarioProject);
				}
			}
			usuarioDAO.delete(usuario);
		}
		return usuario;
	}
}