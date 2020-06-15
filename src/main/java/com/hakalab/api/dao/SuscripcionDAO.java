package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Suscripcion;

@Repository
public class SuscripcionDAO extends BaseDAO{
	public List<Suscripcion> getAll(){
		Query<Suscripcion> query = getSession().createQuery("select a from Suscripcion a", Suscripcion.class);
		return query.getResultList();
	}
	
	public Suscripcion getById(Integer id) {
		try {
			Query<Suscripcion> query = getSession().createQuery("select a from Suscripcion a where a.id=:id", Suscripcion.class);
			query.setParameter("id", id);
			Suscripcion suscripcion = query.getSingleResult();
			return suscripcion;
		} catch (Exception e) {
		}
		return null;
	}
	
	public Suscripcion getByName(String nombreSuscripcion) {
		try {
			Query<Suscripcion> query = getSession().createQuery("select a from Suscripcion a where a.nombre=:nombre", Suscripcion.class);
			query.setParameter("nombre", nombreSuscripcion);
			Suscripcion suscripcion = query.getSingleResult();
			return suscripcion;
		} catch (Exception e) {
		}
		return null;
	}
	
	public void save(Suscripcion suscripcion) {
		getSession().save(suscripcion);
	}

	@Transactional
	@Modifying
	public void update(Suscripcion suscripcion) {
		getSession().update(suscripcion);
	}
	
	@Transactional
	@Modifying
	public void delete(Suscripcion suscripcion) {
		getSession().delete(suscripcion);
	}
}
