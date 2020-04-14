package com.hakalab.api.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Feature;

@Repository
public class FeatureDAOImpl implements FeatureDAO{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Feature> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Feature> selectFeture = currentSession.createQuery("from feature",Feature.class);
		List<Feature> features = selectFeture.getResultList();
		return features;
	}

	@Override
	public Feature finByName(String name) {
		Session currentSession = entityManager.unwrap(Session.class);
		Feature feature = currentSession.get(Feature.class,name);
		return feature;
	}

	@Override
	public void save(Feature feature) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(feature);
		
	}

	@Override
	public void deleteByName(String name) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<Feature> delete = currentSession.createQuery("delete from feature where nombre=:nombre");
		delete.setParameter("nombre",name);
		delete.executeUpdate();
		
	}

}
