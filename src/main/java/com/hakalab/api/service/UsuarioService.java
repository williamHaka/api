package com.hakalab.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.UsuarioDAO;
import com.hakalab.api.dao.UsuarioProjectDAO;
import com.hakalab.api.entity.Usuario;
import com.hakalab.api.entity.UsuarioProject;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private UsuarioProjectDAO usuarioprojectDAO;
	
	@Autowired
	private UsuarioProjectService usuarioProjectService;
	
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
		return status;
	}

	public Integer updateUsuario(Usuario usuario) {
		Integer status = 0;
		try {
			Usuario usuarioExist = usuarioDAO.getById(usuario.getIdUsuario());
			if (!usuarioExist.equals(null)) {
				usuarioExist.setUsernameUsuario(usuario.getUsernameUsuario());
				usuarioExist.setPassUsuario(usuario.getPassUsuario());
				usuarioExist.setPhoneUsuario(usuario.getPhoneUsuario());
				usuarioExist.setEmailUsuario(usuario.getEmailUsuario());
				usuarioExist.setAddressUsuario(usuario.getAddressUsuario());
				usuarioExist.setNameUsuario(usuario.getNameUsuario());
				usuarioExist.setLastNameUsuario(usuario.getLastNameUsuario());
				usuarioExist.setPostalCodeUsuario(usuario.getPostalCodeUsuario());
				usuarioExist.setNameEmpresaUsuario(usuario.getNameEmpresaUsuario());
			}
		} catch (Exception e) {
		}
		return status;
	}
	
	public Usuario deleteUsuario(Integer idUsuario) {
		Usuario usuario = usuarioDAO.getById(idUsuario);
		if (!usuario.equals(null)) {
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
