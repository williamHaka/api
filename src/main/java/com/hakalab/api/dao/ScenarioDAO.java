package com.hakalab.api.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Scenario;

@Repository
public class ScenarioDAO {

	@Autowired
	private EntityManager entityManager;
	
	public void save(Scenario scenario) {
		Session currentSession = entityManager.unwrap(Session.class);
			currentSession.save(scenario);
	}
}
