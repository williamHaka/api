package com.hakalab.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.UsuarioDAO;
import com.hakalab.api.dao.UsuarioProjectDAO;
import com.hakalab.api.entity.Usuario;
import com.hakalab.api.entity.UsuarioProject;

@Service
public class UsuarioProjectService {

	@Autowired
	private UsuarioProjectDAO usuarioProjectDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public void delete(Integer idUsuario) {
		Usuario usuario = usuarioDAO.getById(idUsuario);
		if (usuario != null) {
			List<UsuarioProject> usuarioProjects = usuarioProjectDAO.getByIdUsuario(usuario);
			if (usuarioProjects != null) {
				for (UsuarioProject usuarioProject : usuarioProjects) {
					usuarioProjectDAO.delete(usuarioProject);
				}
			}
			usuarioDAO.delete(usuario);
		}
	}
	
}
