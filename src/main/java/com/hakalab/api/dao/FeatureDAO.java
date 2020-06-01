package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Feature;

@Repository
public class FeatureDAO extends BaseDAO{

	public List<Feature> getAll() {
		Query<Feature> selectFeture = getSession().createQuery("select a from Feature a",Feature.class);
		return selectFeture.getResultList();
	}
	
	public Feature getByName(String name) {
		Feature feature = null;
		try {
			Query<Feature> query = getSession().createQuery("select a from Feature a where a.nameFeature=:nombre",Feature.class);
			query.setParameter("nombre", name);
			feature = query.getSingleResult();
		} catch (Exception e) {
		}
		
		return feature;
	}
	
	public Feature getById(Integer id) {
		Feature feature = null;
		try {
			Query<Feature> query = getSession().createQuery("select a from Feature a where a.idFeature=:id",Feature.class);
			query.setParameter("id", id);
			feature = query.getSingleResult();
		}catch (Exception e) {
		}
		return feature;
	}

	public void save(Feature feature) {
		getSession().save(feature);
	}
	
	@Transactional
	@Modifying
	public void update(Feature feature) {
		getSession().update(feature);
	}
	
	@Transactional
	@Modifying
	public void delete(Feature feature) {
		getSession().delete(feature);
	}
}
