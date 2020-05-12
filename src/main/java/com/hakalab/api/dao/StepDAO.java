package com.hakalab.api.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.ScenarioStep;
import com.hakalab.api.entity.Step;

@Repository
public class StepDAO extends BaseDAO {
	
	public List<Step> getAll() {
		Query<Step> selectStep = getSession().createQuery("select a from Step a",Step.class);
		return selectStep.getResultList();
	}
	
	public Step getById(Integer idStep) {
		Step step = null;
		try {
			Query<Step> query = getSession().createQuery("select a from Step a where a.idStep=:idStep",Step.class);
			query.setParameter("idStep", idStep);
			step = query.getSingleResult();
		} catch (Exception e) {
		}
		return step;
	}
	
	public List<Step> getByName(String nameStep) {
		Query<Step> query = getSession().createQuery("select a from Step a where a.nameStep=:nameStep",Step.class);
		query.setParameter("nameStep", nameStep);
		return query.getResultList();
	}
	
	public Step getByNameAndDescription(String name, String description) {
		Step step = null;
		try {
			Query<Step> query = getSession().createQuery("select a from Step a where a.nameStep=:nombre and a.descriptionStep=:description",Step.class);
			query.setParameter("nombre", name);
			query.setParameter("description", description);
			step = query.getSingleResult();
		} catch (Exception e) {
		}
		return step;
	}
	
	@Transactional
	@Modifying
	public void delete(Step step) {
		getSession().delete(step);
	}

	public List<Step> getByIdScenario(Integer idScenario) {
		List<ScenarioStep> scenarioSteps = new ArrayList<ScenarioStep>();
		List<Step> steps = new ArrayList<Step>();
		Query<ScenarioStep> query = getSession().createQuery("select a from ScenarioStep a where a.idScenario=:idScenario",ScenarioStep.class);
		query.setParameter("idScenario", idScenario);
		scenarioSteps = query.getResultList();
		for (ScenarioStep scenarioStep : scenarioSteps) {
			Query<Step> queryStep = getSession().createQuery("select a from Step a where a.idStep=:idStep",Step.class);
			queryStep.setParameter("idStep", scenarioStep.getIdStep());
			steps.addAll(queryStep.getResultList());
		}
		return steps;
	}
	
	public void save(Step step) {
		getSession().save(step);
	}

	@Transactional
	@Modifying
	public void update(Step step) {
		getSession().update(step);
	}
}
