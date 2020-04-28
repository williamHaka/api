package com.hakalab.api.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDAO {
	
	@Autowired
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	protected Session getSession() {
		Session session =  getEntityManager().unwrap(Session.class);
		return session;
	}
}
