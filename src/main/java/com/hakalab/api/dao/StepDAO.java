package com.hakalab.api.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Scenario;
import com.hakalab.api.entity.Step;

@Repository
public class StepDAO {

	@Autowired
	private EntityManager entityManager;
	
	public void saveAll(List<Step> steps) {
		Session currentSession = entityManager.unwrap(Session.class);
		for (Step step : steps) {
			currentSession.save(step);
		}
	}
}
