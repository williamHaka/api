package com.hakalab.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.ScenarioDAO;
import com.hakalab.api.entity.Scenario;

@Service
public class ScenarioService {
	
	@Autowired	
	private ScenarioDAO scenarioDAO;
	
	public void save(Scenario scenario) {
		scenarioDAO.save(scenario);
	}
}
