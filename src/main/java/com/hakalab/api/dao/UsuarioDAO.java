package com.hakalab.api.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Usuario;

@Repository
public class UsuarioDAO extends BaseDAO {

	public Usuario getUserByName(String name) {
		Usuario usuario = null;
		try {
			Query<Usuario> query = getSession().createQuery("select a from Usuario a where a.nameUser=:name",Usuario.class);
			query.setParameter("name", name);
			usuario = query.getSingleResult();
		} catch (Exception e) {
		}
		
		return usuario;
	}
}
