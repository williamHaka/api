package com.hakalab.api.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Feature;
import com.hakalab.api.entity.Parameter;
import com.hakalab.api.entity.Scenario;
import com.hakalab.api.entity.Step;

@Repository
public class FeatureDAO{

	@Autowired
	private EntityManager entityManager;
	
	public List<Feature> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Feature> selectFeture = currentSession.createQuery("select a from Feature a",Feature.class);
		List<Feature> features = selectFeture.getResultList();
		return features;
	}
//
	public Feature finByName(String name) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Feature> selectFeture = currentSession.createQuery("select a from Feature a where a.nameFeature=:nombre",Feature.class);
		selectFeture.setParameter("nombre", name);
		Feature feature = null;
		try {
			feature = selectFeture.getSingleResult();
		} catch (Exception e) {
		}
		return feature;
	}

		public void createOrUpdate(Feature feature) {
		Session currentSession = entityManager.unwrap(Session.class);
		Feature featureAux = this.finByName(feature.getNameFeature());		
		if(featureAux!=null) 
		 feature = (Feature) currentSession.merge(feature);
		 currentSession.saveOrUpdate(feature);
		}

	public void save(Feature feature) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(feature);
	}
	public void save(Scenario scenario) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(scenario);
	}
	public void save(Step step) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(step);
	}
	public void save(Parameter parameter) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(parameter);
	}
	
	@Modifying
	public void update(Feature featureBody) {
		Session currentSession = entityManager.unwrap(Session.class);
		Feature feature = this.finByName(featureBody.getNameFeature());		
		if(feature!=null) { 
			feature = (Feature) currentSession.merge(featureBody);
			feature.setDescriptionFeature(featureBody.getDescriptionFeature());
			Query<Feature> update = currentSession.createQuery("update Feature",Feature.class);
			update.executeUpdate();
			currentSession.update(feature);
		}
	}
	
	
	@Transactional
	@Modifying
	public void delete(Feature feature) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(feature);
	}
	@Transactional
	@Modifying
	public void delete(Scenario scenario) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(scenario);
	}
	@Transactional
	@Modifying
	public void delete(Step step) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(step);
	}
	@Transactional
	@Modifying
	public void delete(Parameter parameter) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(parameter);
	}

	public List<Scenario> getScenarioByIdFeature(Integer idFeature) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Scenario> selectFeture = currentSession.createQuery("select a from Scenario a where a.feature.id=:idFeature",Scenario.class);
		selectFeture.setParameter("idFeature", idFeature);
		List<Scenario> scenarios= null;
		try {
			scenarios = selectFeture.getResultList();
		} catch (Exception e) {
		}
		return scenarios;
	}
	
	public List<Step> getStepByIdScenario(Integer idScenario) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Step> selectFeture = currentSession.createQuery("select a from Step a where a.scenario.id=:idScenario",Step.class);
		selectFeture.setParameter("idScenario", idScenario);
		List<Step> steps= null;
		try {
			steps = selectFeture.getResultList();
		} catch (Exception e) {
		}
		return steps;
	}
	public List<Parameter> getParameterByIdStep(Integer idStep) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Parameter> selectFeture = currentSession.createQuery("select a from Parameter a where a.step.id=:idStep",Parameter.class);
		selectFeture.setParameter("idStep", idStep);
		List<Parameter> parameters= null;
		try {
			parameters = selectFeture.getResultList();
		} catch (Exception e) {
		}
		return parameters;
	}
}
