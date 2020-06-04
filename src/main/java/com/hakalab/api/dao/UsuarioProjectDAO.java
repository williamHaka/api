package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Usuario;
import com.hakalab.api.entity.UsuarioProject;

@Repository
public class UsuarioProjectDAO extends BaseDAO{
	
	public List<UsuarioProject> getByIdUsuario(Usuario usuario) {
		Query<UsuarioProject> query = getSession().createQuery("select a from UsuarioProject a where a.idUsuario=:idUsuario", UsuarioProject.class);
		query.setParameter("idUsuario", usuario.getIdUsuario());
		return query.getResultList();
	}
	
	public void save(UsuarioProject usuarioProject) {
		getSession().save(usuarioProject);
	}
	
	@Transactional
	@Modifying
	public void delete(UsuarioProject usuarioProject) {
		getSession().delete(usuarioProject);
	}
	
}
