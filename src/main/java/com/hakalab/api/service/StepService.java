package com.hakalab.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.StepDAO;
import com.hakalab.api.entity.Feature;
import com.hakalab.api.entity.Scenario;
import com.hakalab.api.entity.Step;

@Service
public class StepService {

	@Autowired
	private StepDAO stepDAO;
	
	public void delete(Feature feature) {
		for (Scenario scenario : feature.getScenarios()) {
			for (Step step : scenario.getSteps()) {
				stepDAO.delete(step);
			}
		}
	}
	
	public void deleteFromScenario(Scenario scenario) {
		for (Step step : scenario.getSteps()) {
			stepDAO.delete(step);
			}
		}
	
}
