package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Scenario;

@Repository
public class ScenarioDAO extends BaseDAO{
	
	public Scenario getById(Integer id) {
		Query<Scenario> query = getSession().createQuery("select a from scenario a where a.id=:id",Scenario.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	public List<Scenario> getByIdFeature(Integer idFeature) {
		Query<Scenario> query = getSession().createQuery("select a from Scenario a where a.feature.id=:idFeature",Scenario.class);
		query.setParameter("idFeature", idFeature);
		return query.getResultList();
	}
	
	public void save(Scenario scenario) {
		getSession().save(scenario);
	}

	public void update(Scenario scenario) {
		getSession().update(scenario);
	}
	
	@Transactional
	@Modifying
	public void delete(Scenario scenario) {
		getSession().delete(scenario);
	}
}
