package com.hakalab.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.OrganizacionDAO;
import com.hakalab.api.entity.Organizacion;

@Service
public class OrganizacionService {
	@Autowired
	private OrganizacionDAO organizacionDAO;
	
	public Organizacion save(Organizacion organizacion) {
		Organizacion organizacionExist = organizacionDAO.getByRut(organizacion.getRutOrganizacion());
		if (organizacionExist == null) {
			organizacionDAO.save(organizacion);
			Organizacion guardado = organizacionDAO.getByRut(organizacion.getRutOrganizacion());
			return guardado;
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
	
	public Integer detele(Integer id) {
		Organizacion organizacionExist = organizacionDAO.getById(id);
		if (organizacionExist != null) {
			organizacionDAO.delete(organizacionExist);
			return 1;
		}
		return 0;
	}
}
