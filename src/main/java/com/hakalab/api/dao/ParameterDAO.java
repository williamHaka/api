package com.hakalab.api.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Scenario;

@Repository
public class ParameterDAO {

	@Autowired
	private EntityManager entityManager;
	
	public void saveAll(List<Scenario> scenarios) {
		Session currentSession = entityManager.unwrap(Session.class);
		for (Scenario scenario : scenarios) {
			currentSession.save(scenario);
		}
	}
}
