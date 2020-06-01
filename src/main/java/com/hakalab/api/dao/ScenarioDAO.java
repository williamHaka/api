package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Scenario;

@Repository
public class ScenarioDAO extends BaseDAO{
	
	public List<Scenario> getAll() {
		Query<Scenario> selectScenario = getSession().createQuery("select a from Scenario a", Scenario.class);
		return selectScenario.getResultList();
	}
	
	public Scenario getByName(String name) {
		Scenario scenario = null;
		try {
			Query<Scenario> query = getSession().createQuery("select a from Scenario a where a.nameScenario=:nombre", Scenario.class);
			query.setParameter("nombre", name);
			scenario = query.getSingleResult();
		} catch (Exception e) {
		}
		return scenario;
	}
	
	public Scenario getById(Integer idScenario) {
		Scenario scenario = null;
		try {
			Query<Scenario> query = getSession().createQuery("select a from Scenario a where a.idScenario=:idScenario", Scenario.class);
			query.setParameter("idScenario", idScenario);
			scenario = query.getSingleResult();
		} catch (Exception e) {
		}
		return scenario;	
	}
	
	public List<Scenario> getByIdFeature(Integer idFeature) {
		Query<Scenario> query = getSession().createQuery("select a from Scenario a where a.feature.id=:idFeature", Scenario.class);
		query.setParameter("idFeature", idFeature);
		return query.getResultList();
	}
	
	public void save(Scenario scenario) {
		getSession().save(scenario);
	}

	@Transactional
	@Modifying
	public void update(Scenario scenario) {
		getSession().update(scenario);
	}
	
	@Transactional
	@Modifying
	public void delete(Scenario scenario) {
		getSession().delete(scenario);
	}
}
