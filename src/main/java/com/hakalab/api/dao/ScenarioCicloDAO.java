package com.hakalab.api.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Scenario;
import com.hakalab.api.entity.ScenarioCiclo;

@Repository
public class ScenarioCicloDAO extends BaseDAO {

	public List<ScenarioCiclo> getIdScenario(Scenario scenario) {
		Query<ScenarioCiclo> query = getSession().createQuery("select a from ScenarioCiclo where a.idScenario=:idScenario", ScenarioCiclo.class);
		query.setParameter("idScenario", scenario.getIdScenario());
		return query.getResultList();
	}
	
	public void save(ScenarioCiclo scenarioCiclo) {
		getSession().save(scenarioCiclo);
	}
	
}
