package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Administrador;

@Repository
public class AdministradorDAO extends BaseDAO {
	public List<Administrador> getAll() {
		Query<Administrador> selectAdministrador = getSession().createQuery("select a from Administrador a",Administrador.class);
		return selectAdministrador.getResultList();
	}

	public Administrador getById(Integer id) {
		try {
			Query<Administrador> query = getSession().createQuery("select a from Administrador a where a.id=:id",
					Administrador.class);
			query.setParameter("id", id);
			Administrador administrador = query.getSingleResult();
			return administrador;
		} catch (Exception e) {
		}
		return null;
	}

	public Administrador getByCorreo(String correo) {
		try {
			Query<Administrador> query = getSession().createQuery("select a from Administrador a where a.correo=:correo",Administrador.class);
			query.setParameter("correo", correo);
			Administrador administrador = query.getSingleResult();
			return administrador;
		} catch (Exception e) {
		}
		return null;
	}

	public void save(Administrador administrador) {
		getSession().save(administrador);
	}

	@Transactional
	@Modifying
	public void update(Administrador administrador) {
		getSession().update(administrador);
	}

	@Transactional
	@Modifying
	public void delete(Administrador administrador) {
		getSession().delete(administrador);
	}
}
