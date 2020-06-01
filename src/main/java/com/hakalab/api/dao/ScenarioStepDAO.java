package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.ScenarioStep;
import com.hakalab.api.entity.Step;

@Repository
public class ScenarioStepDAO extends BaseDAO{

	public List<ScenarioStep> getByIdScenarioAndIdStep(ScenarioStep scenarioStep) {
		Query<ScenarioStep> query = getSession().createQuery("select a from ScenarioStep a where a.idScenario=:idScenario and a.idStep=:idStep",ScenarioStep.class);
		query.setParameter("idScenario", scenarioStep.getIdScenario());
		query.setParameter("idStep", scenarioStep.getIdStep());
		return query.getResultList();
	}
	
	public List<ScenarioStep> getByIdStep(Step step) {
		Query<ScenarioStep> query = getSession().createQuery("select a from ScenarioStep a where a.idStep=:idStep",ScenarioStep.class);
		query.setParameter("idStep", step.getIdStep());
		return query.getResultList();
	}
	
	public List<ScenarioStep> getByIdScenario(Integer idScenario) {
		Query<ScenarioStep> query = getSession().createQuery("select a from ScenarioStep a where a.idScenario=:idScenario",ScenarioStep.class);
		query.setParameter("idStep", idScenario);
		return query.getResultList();
	}
	
	public ScenarioStep getByIdStepAndIdScenario(Integer idStep, Integer idScenario) {
		ScenarioStep scenarioStep = null;
		Query<ScenarioStep> query = getSession().createQuery("select a from ScenarioStep a where a.idScenario=:idScenario and a.idStep=:idStep",ScenarioStep.class);
		query.setParameter("idScenario", idScenario);
		query.setParameter("idStep", idStep);
		scenarioStep = query.getSingleResult();
		return scenarioStep;
	}
	
	public void save(ScenarioStep scenarioStep) {
		getSession().save(scenarioStep);
	}
	
	@Transactional
	@Modifying
	public void delete(ScenarioStep scenarioStep) {
		getSession().delete(scenarioStep);
	}
}
