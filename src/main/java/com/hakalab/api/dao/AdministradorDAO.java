package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Administrador;
import com.hakalab.api.entity.Usuario;

@Repository
public class AdministradorDAO extends BaseDAO{
	public List<Administrador> getAll(){
		Query<Administrador> selectAdministrador = getSession().createQuery("select a from Administrador a", Administrador.class);
		return selectAdministrador.getResultList();
	}
	
	public Administrador getById(Integer id) {
		Administrador administrador = null;
			Query<Administrador> query = getSession().createQuery("select a from Administrador a where a.id=:id", Administrador.class);
			query.setParameter("id", id);
			administrador = query.getSingleResult();
		return administrador;
	}
	
	public Administrador getByCorreo(String correo) {
		Administrador administrador = null;
			Query<Administrador> query = getSession().createQuery("select a from Administrador a where a.correo=:correo", Administrador.class);
			query.setParameter("correo", correo);
			administrador = query.getSingleResult();
		return administrador;
	}
	
	public void save(Administrador administrador) {
		getSession().save(administrador);
	}
	
	@Transactional
	@Modifying
	public void update(Usuario usuario) {
		getSession().update(usuario);
	}
	
	@Transactional
	@Modifying
	public void delete(Usuario usuario) {
		getSession().delete(usuario);
	}
}
