
package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Organizacion;
 
@Repository
public class OrganizacionDAO extends BaseDAO{
	public List<Organizacion> getAll(){
		Query<Organizacion> query = getSession().createQuery("select a from Organizacion a", Organizacion.class);
		return query.getResultList();
	}
	
	public Organizacion getById(Integer id) {
		try {
			Query<Organizacion> query = getSession().createQuery("select a from Organizacion a where a.id=:id", Organizacion.class);
			query.setParameter("id", id);
			Organizacion organizacion = query.getSingleResult();
			return organizacion;
		} catch (Exception e) {
		}
		return null;
	}
	
	public Organizacion getByRut(String rut) {
		try {
			Query<Organizacion> query = getSession().createQuery("select a from Organizacion a where a.rutOrganizacion=:rutOrganizacion", Organizacion.class);
			query.setParameter("rutOrganizacion", rut);
			Organizacion organizacion = query.getSingleResult();
			return organizacion;
		} catch (Exception e) {
		}
		return null;
	}
	
	public void save(Organizacion organizacion) {
		getSession().save(organizacion);
	}

	@Transactional
	@Modifying
	public void update(Organizacion organizacion) {
		getSession().update(organizacion);
	}
	
	@Transactional
	@Modifying
	public void delete(Organizacion organizacion) {
		getSession().delete(organizacion);
	}
}
