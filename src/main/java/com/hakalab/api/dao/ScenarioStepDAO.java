package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.ScenarioStep;

@Repository
public class ScenarioStepDAO extends BaseDAO{

	public List<ScenarioStep> getByIdScenarioAndIdStep(ScenarioStep scenarioStep) {
		Query<ScenarioStep> query = getSession().createQuery("select a from ScenarioStep a where a.idScenario=:idScenario and a.idStep=:idStep",ScenarioStep.class);
		query.setParameter("idScenario", scenarioStep.getIdScenario());
		query.setParameter("idStep", scenarioStep.getIdStep());
		return query.getResultList();
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
