package com.hakalab.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.AdministradorDAO;
import com.hakalab.api.entity.Administrador;
import com.hakalab.api.entity.Project;
import com.hakalab.api.entity.Usuario;
import com.hakalab.api.entity.UsuarioProject;

@Service
public class AdministradorService {
	@Autowired
	private AdministradorDAO administradorDAO;
	
	public List<Administrador> getAll(){
		List<Administrador> administradores = administradorDAO.getAll();
		return administradores;
	}
	
	public Administrador getById(Integer id) {
		Administrador administrador = null;
			administrador = administradorDAO.getById(id);
		return administrador;
	}
	
//	public Integer saveUsuario(Organizacion organizacion) {
//		
//		Administrador adminExist = administradorDAO.getByCorreo(administrador.getCorreo());
//		if (adminExist == null) {	
//			administradorDAO.save(administrador);
//			return 1;
//		}
//		return 0;
//	}
//
//	public Integer assignUsuario(Usuario usuario) {
//		Integer status = 0;
//		try {
//			Usuario usuarioExist = usuarioDAO.getById(usuario.getIdUsuario());
//			if (usuarioExist != null) {
//				for (Project project: usuario.getProjects()) {
//					Project projectExist = projectDAO.getById(project.getIdProject());
//					if (projectExist != null) {
//						UsuarioProject usuarioProject = new UsuarioProject();
//						usuarioProject.setIdUsuario(usuario.getIdUsuario());
//						usuarioProject.setIdProject(project.getIdProject());
//						usuarioprojectDAO.save(usuarioProject);
//						status = 200;
//					} else {
//						status = 0;
//					}
//				}
//			} else {
//				status = 0;
//			}
//		} catch (Exception e) {
//		}
//		return status;
//	}
//
//	public Integer updateUsuario(Usuario usuario) {
//		Integer status = 0;
//		try {
//			Usuario usuarioExist = usuarioDAO.getById(usuario.getIdUsuario());
//			if (usuarioExist != null) {
//				usuarioExist.setUserNameUsuario(usuario.getUserNameUsuario());
//				usuarioExist.setPassUsuario(usuario.getPassUsuario());
//				usuarioExist.setPhoneUsuario(usuario.getPhoneUsuario());
//				usuarioExist.setEmailUsuario(usuario.getEmailUsuario());
//				usuarioExist.setAddressUsuario(usuario.getAddressUsuario());
//				usuarioExist.setNameUsuario(usuario.getNameUsuario());
//				usuarioExist.setLastNameUsuario(usuario.getLastNameUsuario());
//				usuarioExist.setPostalCodeUsuario(usuario.getPostalCodeUsuario());
//				usuarioExist.setNameEmpresaUsuario(usuario.getNameEmpresaUsuario());
//				usuarioDAO.update(usuarioExist);
//				status = 1;
//			}
//		} catch (Exception e) {
//		}
//		return status;
//	}
//
//	public Usuario deleteUsuario(Integer idUsuario) {
//		Usuario usuario = usuarioDAO.getById(idUsuario);
//		if (usuario != null) {
//			List<UsuarioProject> usuarioProjects = usuarioprojectDAO.getByIdUsuario(usuario);
//			if (usuarioProjects != null) {
//				for (UsuarioProject usuarioProject : usuarioProjects) {
//					usuarioprojectDAO.delete(usuarioProject);
//				}
//			}
//			usuarioDAO.delete(usuario);
//		}
//		return usuario;
//	}
}
