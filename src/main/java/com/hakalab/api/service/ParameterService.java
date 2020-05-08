package com.hakalab.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.ParameterDAO;
import com.hakalab.api.entity.Feature;
import com.hakalab.api.entity.Parameter;
import com.hakalab.api.entity.Scenario;
import com.hakalab.api.entity.Step;

@Service
public class ParameterService {
	
	@Autowired
	private ParameterDAO parameterDAO;
	
	public void delete(Feature feature) {
		for (Scenario scenario : feature.getScenarios()) {
			for (Step step : scenario.getSteps()) {
				for (Parameter parameter : step.getParameters()) {
					parameterDAO.delete(parameter);
				}
			}
		}
	}
	
	public void deleteFromScenario(Scenario scenario) {
		for (Step step : scenario.getSteps()) {
			for (Parameter parameter : step.getParameters()) {
				parameterDAO.delete(parameter);
				}
			}
		}
	
}
