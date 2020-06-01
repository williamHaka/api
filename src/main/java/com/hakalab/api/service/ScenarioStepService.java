package com.hakalab.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.ScenarioStepDAO;
import com.hakalab.api.entity.Feature;
import com.hakalab.api.entity.Project;
import com.hakalab.api.entity.Scenario;
import com.hakalab.api.entity.ScenarioStep;
import com.hakalab.api.entity.Step;

@Service
public class ScenarioStepService {
	
	@Autowired	
	private ScenarioStepDAO scenarioStepDAO;
	
	public void save(ScenarioStep scenarioStep) {
		scenarioStepDAO.save(scenarioStep);
	}
	
	public void deleteFromProject(Project project) {
		List<ScenarioStep> scenarioSteps = new ArrayList<>();
		for (Feature feature: project.getFeatures()) {
			for (Scenario scenario : feature.getScenarios()) {
				for (Step step: scenario.getSteps()) {
					ScenarioStep scenarioStep = new ScenarioStep();
					scenarioStep.setIdScenario(scenario.getIdScenario());
					scenarioStep.setIdStep(step.getIdStep());
					scenarioSteps.addAll(scenarioStepDAO.getByIdScenarioAndIdStep(scenarioStep));
					}
				}
			for (ScenarioStep scenarioStep : scenarioSteps) {
				scenarioStepDAO.delete(scenarioStep);
				}
			}
	}
	
	public void delete(Feature feature) {
		List<ScenarioStep> scenarioSteps = new ArrayList<>();
		for (Scenario scenario : feature.getScenarios()) {
			for (Step step: scenario.getSteps()) {
				ScenarioStep scenarioStep = new ScenarioStep();
				scenarioStep.setIdScenario(scenario.getIdScenario());
				scenarioStep.setIdStep(step.getIdStep());
				scenarioSteps.addAll(scenarioStepDAO.getByIdScenarioAndIdStep(scenarioStep));
			}
		}
		for (ScenarioStep scenarioStep : scenarioSteps) {
			scenarioStepDAO.delete(scenarioStep);
		}
	}
	
	public void deleteFromScenario(Scenario scenario) {
		List<ScenarioStep> scenarioSteps = new ArrayList<>();
			for (Step step: scenario.getSteps()) {
				ScenarioStep scenarioStep = new ScenarioStep();
				scenarioStep.setIdScenario(scenario.getIdScenario());
				scenarioStep.setIdStep(step.getIdStep());
				scenarioSteps.addAll(scenarioStepDAO.getByIdScenarioAndIdStep(scenarioStep));
			}
		for (ScenarioStep scenarioStep : scenarioSteps) {
			scenarioStepDAO.delete(scenarioStep);
		}
	}
	
}
