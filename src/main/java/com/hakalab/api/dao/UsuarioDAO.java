package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Feature;
import com.hakalab.api.entity.Step;
import com.hakalab.api.entity.Usuario;

@Repository
public class UsuarioDAO extends BaseDAO{

	public List<Usuario> getAll() {
 		Query<Usuario> selectUsuario = getSession().createQuery("select a from Usuario a", Usuario.class);
		return selectUsuario.getResultList();
	}

	public List<Usuario> getByName(String name) {
			Query<Usuario> query = getSession().createQuery("select a from Usuario a where a.nameUsuario=:nombre", Usuario.class);
			query.setParameter("nombre", name);
			return query.getResultList();
	}

	public Usuario getNameUser(String name) {
		Usuario usuario = null;
		try {
			Query<Usuario> query = getSession().createQuery("select a from Usuario a where a.nameUsuario=:nombre", Usuario.class);
			query.setParameter("nombre", name);
			usuario = query.getSingleResult();
		} catch (Exception e) {
		}
		return usuario;
	}
	
	public Usuario getById(Integer idUsuario) {
		Usuario usuario = null;
		try {
			Query<Usuario> query = getSession().createQuery("select a from Usuario a where a.idUsuario=:idUsuario", Usuario.class);
			query.setParameter("idUsuario", idUsuario);
			usuario = query.getSingleResult();
		} catch (Exception e) {
		}
		return usuario;
	}
	
	public void save(Usuario usuario) {
		getSession().save(usuario);
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
