package com.hakalab.api.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Feature;

@Repository
public class FeatureDAO{

//	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//	Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
	@Autowired
	private EntityManager entityManager;
	
	public List<Feature> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Feature> selectFeture = currentSession.createQuery("select f from Feature f",Feature.class);
		List<Feature> features = selectFeture.getResultList();
		return features;
	}

	public Feature finByName(String name) {
		Session currentSession = entityManager.unwrap(Session.class);
		Feature feature = currentSession.get(Feature.class,name);
		return feature;
	}

	public void save(Feature feature) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(feature);
		
	}

	public void deleteByName(String name) {
		Session currentSession = entityManager.unwrap(Session.class);

		@SuppressWarnings("unchecked")
		Query<Feature> delete = currentSession.createQuery("delete from feature where nombre=:nombre");
		delete.setParameter("nombre",name);
		delete.executeUpdate();
		
	}

}
