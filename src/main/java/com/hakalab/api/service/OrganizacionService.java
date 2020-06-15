package com.hakalab.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.AdministradorDAO;
import com.hakalab.api.dao.OrganizacionDAO;
import com.hakalab.api.entity.Administrador;
import com.hakalab.api.entity.Organizacion;

@Service
public class OrganizacionService {
	@Autowired
	private OrganizacionDAO organizacionDAO;
	@Autowired
	private AdministradorDAO administradorDAO;
	
	public Organizacion save(Organizacion organizacion) {
		Organizacion organizacionExist = organizacionDAO.getByRut(organizacion.getRutOrganizacion());
		if (organizacionExist == null) {
			Administrador administradorExist = administradorDAO.getByCorreo(organizacion.getAdministradores().get(0).getCorreoAdministrador());
			if (administradorExist == null) {
				organizacionDAO.save(organizacion);
				Organizacion guardado = organizacionDAO.getByRut(organizacion.getRutOrganizacion());
				organizacion.getAdministradores().get(0).setOrganizacion(organizacion);
				administradorDAO.save(organizacion.getAdministradores().get(0));
				return guardado;
			}
		}
		return null ;
	}
	
	public List<Organizacion> getAll(){
		List<Organizacion> organizaciones = organizacionDAO.getAll();
		return organizaciones;
	}
	
	public Organizacion getById(Integer id) {
		Organizacion organizacion = organizacionDAO.getById(id);
		return organizacion;
	}
	
	public Organizacion update(Organizacion organizacion) {
		Organizacion organizacionExist = organizacionDAO.getById(organizacion.getIdOrganizacion());
		if (organizacionExist != null) {
			organizacionExist.setNombreOrganizacion(organizacion.getNombreOrganizacion());
			organizacionExist.setDireccionOrganizacion(organizacion.getRutOrganizacion());
			organizacionExist.setRutOrganizacion(organizacion.getRutOrganizacion());
			organizacionDAO.update(organizacionExist);
			return organizacionExist;
		}
		return null;
	}
	
	public Integer delete(Integer id) {
		Organizacion organizacionExist = organizacionDAO.getById(id);
		if (organizacionExist != null) {
			for (Administrador administrador : organizacionExist.getAdministradores()) {
				administradorDAO.delete(administrador);
			}
			organizacionDAO.delete(organizacionExist);
			return 1;
		}
		return 0;
	}
}
