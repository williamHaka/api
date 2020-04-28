package com.hakalab.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Step;

@Repository
public class StepDAO extends BaseDAO {

	public List<Step> getByIdScenario(Integer idScenario) {
		Query<Step> query = getSession().createQuery("select a from Step a where a.scenario.id=:idScenario",Step.class);
		query.setParameter("idScenario", idScenario);
		return query.getResultList();
	}
	
	public void save(Step step) {
		getSession().save(step);
	}
	
	public void update(Step step) {
		getSession().update(step);
	}
	
	@Transactional
	@Modifying
	public void delete(Step step) {
		getSession().delete(step);
	}
}
