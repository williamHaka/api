package com.hakalab.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.SuscripcionDAO;
import com.hakalab.api.entity.Suscripcion;

@Service
public class SuscripcionService {
	@Autowired
	private SuscripcionDAO suscripcionDAO;
	
	public Suscripcion save(Suscripcion suscripcion) {
		Suscripcion suscripcionExist = suscripcionDAO.getByName(suscripcion.getNombreSuscripcion());
		if (suscripcionExist == null) {
			suscripcionDAO.save(suscripcion);
			return suscripcion;
		}
		return null;
	}
	
	public List<Suscripcion> getAll(){
		List<Suscripcion> suscripciones = suscripcionDAO.getAll();
		return suscripciones;
	}
	
	public Suscripcion update(Suscripcion suscripcion) {
		Suscripcion suscripcionExist = suscripcionDAO.getByName(suscripcion.getNombreSuscripcion());
		if (suscripcionExist != null) {
			suscripcionExist.setNombreSuscripcion(suscripcion.getNombreSuscripcion());
			suscripcionExist.setCostoSuscripcion(suscripcion.getCostoSuscripcion());
			suscripcionDAO.update(suscripcionExist);
			return suscripcionExist;
		}
		return null;
	}
	
	public Integer delete(Integer id) {
		Suscripcion suscripcionExist = suscripcionDAO.getById(id);
		if (suscripcionExist != null) {
			suscripcionDAO.delete(suscripcionExist);
			return 1;
		}
		return 0;
	}
}
