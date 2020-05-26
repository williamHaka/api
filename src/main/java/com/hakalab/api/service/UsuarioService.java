//package com.hakalab.api.service;
//
//import java.sql.Date;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.hakalab.api.dao.UsuarioDAO;
//import com.hakalab.api.dao.UsuarioProjectDAO;
//import com.hakalab.api.entity.Usuario;
//import com.hakalab.api.entity.UsuarioProject;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Service
//public class UsuarioService{
//	
//	@Autowired
//	private UsuarioDAO usuarioDAO;
//	@Autowired
//	private UsuarioProjectDAO usuarioprojectDAO;
//
//	public String getTokenByUsername(Usuario usuario) throws Exception {
//		String jwt = Jwts.builder()
//				.setSubject("users/TzMUocMF4p")
//				.setExpiration(Date.from(Instant.now().plus(1,ChronoUnit.DAYS)))
//				.claim("name", usuario.getNameUser())
//				.claim("scope", "self gropus/admins") //setear roles
//				.signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8"))
//				.compact();
//		return "Bearer "+jwt;
//	}
//	
//	public List<Usuario> getAll() {
//		List<Usuario> usuarios = usuarioDAO.getAll();
//		return usuarios;
//	}
//	
//	public Usuario getByName(String name) {
//		Usuario usuario = usuarioDAO.getByName(name);
//		return usuario;
//	}
//	
//	public Usuario getById(Integer id) {
//		Usuario usuario = usuarioDAO.getById(id) ;
//		return usuario;
//	}
//	
//	public Usuario getUserByName(String username) {
//		return usuarioDAO.getUserByName(username);
//	}
//	
//	public Integer saveUsuario(Usuario usuario) {
//		Integer status = 0;
//		try {
//			Usuario usuarioExist = usuarioDAO.getByName(usuario.getNameUser());
//			if (usuarioExist == null) {
//				usuarioDAO.save(usuario);
//			}
//		} catch (Exception e) {
//		}
//		return status;
//	}
//	
//	public Integer update(Usuario usuario) {
//		Integer status = 0;
//		try {
//			Usuario usuarioExist = usuarioDAO.getById(usuario.getIdUser());
//			if (usuarioExist != null) {
//				usuarioExist.setNameUser(usuario.getNameUser());
//				usuarioExist.setPassUser(usuario.getPassUser());
//				usuarioDAO.update(usuarioExist);
//				status = 1;
//			}
//		} catch (Exception e) {
//		}return status;
//	}
//	
//	public Usuario deleteUsuario(Integer idUsuario) {
//		Usuario usuario = usuarioDAO.getById(idUsuario);
//		if(usuario!=null) {
//			List<UsuarioProject> usuariosProject = usuarioprojectDAO.getByIdUsuario(usuario);
//			if (usuariosProject != null) {
//				for (UsuarioProject usuarioProject : usuariosProject) {
//					usuarioprojectDAO.delete(usuarioProject);
//					usuarioDAO.delete(usuario);
//					}
//				}
//			}
//		return usuario;
//		}
//	
//}
