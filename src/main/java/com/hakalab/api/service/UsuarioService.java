package com.hakalab.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.ProjectDAO;
import com.hakalab.api.dao.UsuarioDAO;
import com.hakalab.api.entity.Project;
import com.hakalab.api.entity.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
//	@Autowired
//	private UsuarioProjectDAO usuarioprojectDAO;
	@Autowired
	private ProjectDAO projectDAO;

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
			Usuario usuarioExist = usuarioDAO.getByEmailUser(usuario.getEmailUsuario());
//			Usuario usuarioExist = usuarioDAO.getNameUser(usuario.getNameUsuario());
			if (usuarioExist == null) {
				usuarioDAO.save(usuario);
				status = 1;
			}
		} catch (Exception e) {
		}
		return status;
	}

	public Integer assignUsuario(Project project) {
		Integer status = 0;
		try {
			Project projectExist = projectDAO.getById(project.getIdProject());
			if (projectExist != null) {
				for (Usuario usuario : project.getUsuarios()) {
					Usuario usuarioExist = usuarioDAO.getById(usuario.getIdUsuario());
					if (usuarioExist != null) {
						usuarioExist.setProject(projectExist);
						usuarioDAO.update(usuarioExist);
					}
					else {
						return 0;
					}
				}
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

	public Integer deleteUsuario(Usuario usuario) {
		Integer status = 0;
		try {
			Usuario usuarioExist = usuarioDAO.getById(usuario.getIdUsuario());
			if (usuarioExist != null) {
				usuarioDAO.delete(usuarioExist);
				status = 1;
			}
		} catch (Exception e) {
		}
		return status;
	}
	
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
