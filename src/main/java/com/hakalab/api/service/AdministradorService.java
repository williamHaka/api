package com.hakalab.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.AdministradorDAO;
import com.hakalab.api.dao.OrganizacionDAO;
import com.hakalab.api.entity.Administrador;
import com.hakalab.api.entity.Organizacion;

@Service
public class AdministradorService {
	@Autowired
	private AdministradorDAO administradorDAO;
	@Autowired
	private OrganizacionDAO organizacionDAO;
	
	public Administrador save(Organizacion organizacion) {
		Organizacion organizacionExist = organizacionDAO.getById(organizacion.getIdOrganizacion());
		if (organizacionExist != null) {
			for (Administrador administrador : organizacion.getAdministradores()) {
				Administrador administradorExist = administradorDAO.getByCorreo(administrador.getCorreoAdministrador());
				if (administradorExist == null) {
					administrador.setOrganizacion(organizacion);
					administradorDAO.save(administrador);
					Administrador guardado = administradorDAO.getById(administrador.getIdAdministrador());
					return guardado;
				}
			}
		}
		return null;
	}
	
	public List<Administrador> getAll(){
		List<Administrador> administradores = administradorDAO.getAll();
		return administradores;
	}
	
	public Administrador getById(Integer id) {
		Administrador administrador = null;
			administrador = administradorDAO.getById(id);
		return administrador;
	}
	
	public Administrador update(Administrador administrador) {
		Administrador administradorExist = administradorDAO.getById(administrador.getIdAdministrador());
		if (administradorExist != null) {
			administradorExist.setNombreAdministrador(administrador.getNombreAdministrador());
			administradorExist.setApellidoAdministrador(administrador.getApellidoAdministrador());
			administradorExist.setCorreoAdministrador(administrador.getCorreoAdministrador());
			administradorExist.setPasswordAdministrador(administrador.getPasswordAdministrador());
			administradorDAO.update(administradorExist);
			return administradorExist;
		}
		return null;
	}
	
	public Integer delete(Integer id) {
		Administrador administradorExist = administradorDAO.getById(id);
		if (administradorExist != null) {
			administradorDAO.delete(administradorExist);
			return 1;
		}
		return 0;
	}
	
}
