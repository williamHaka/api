package com.hakalab.api.dao;

import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.ScenarioStep;

@Repository
public class ScenarioStepDAO extends BaseDAO{

	public void save(ScenarioStep senarioStep) {
		getSession().save(senarioStep);
	}
}
