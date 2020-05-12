package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Scenario;
import com.hakalab.api.entity.Step;

@Repository
public class ScenarioDAO extends BaseDAO{
	
	public Scenario getById(Integer idScenario) {
		Query<Scenario> query = getSession().createQuery("select a from Scenario a where a.idScenario=:idScenario",Scenario.class);
		query.setParameter("idScenario", idScenario);
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
