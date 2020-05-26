package com.hakalab.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.FeatureDAO;
import com.hakalab.api.dao.ParameterDAO;
import com.hakalab.api.dao.ScenarioDAO;
import com.hakalab.api.dao.ScenarioStepDAO;
import com.hakalab.api.dao.StepDAO;
import com.hakalab.api.dao.StepParameterDAO;
import com.hakalab.api.entity.Feature;
import com.hakalab.api.entity.Parameter;
import com.hakalab.api.entity.Project;
import com.hakalab.api.entity.Scenario;
import com.hakalab.api.entity.ScenarioStep;
import com.hakalab.api.entity.Step;
import com.hakalab.api.entity.StepParameter;

@Service
public class ScenarioService {
	
	@Autowired
	private FeatureDAO featureDAO;
	@Autowired
	private StepDAO stepDAO;
	@Autowired
	private ScenarioDAO scenarioDAO;
	@Autowired
	private ScenarioStepDAO scenarioStepDAO;
	@Autowired
	private ParameterDAO parameterDAO;
	@Autowired
	private StepParameterDAO stepParameterDAO;

	@Autowired
	private ScenarioStepService scenarioStepService;
	@Autowired
	private StepParameterService stepParameterService;
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private StepService stepService;
	
	public List<Scenario> getAll() {
		List<Scenario> scenarios = scenarioDAO.getAll();
		return scenarios;
	}
	
	public Scenario getByName(String name) {
		Scenario scenario = scenarioDAO.getByName(name) ;
		return scenario;
	}
	
	public Scenario getById(Integer id) {
		Scenario scenario = scenarioDAO.getById(id) ;
		return scenario;
	}
	
	public Integer saveScenario(Feature feature) {
		Integer status = 0;
		try {
			Feature featureExist = featureDAO.getById(feature.getIdFeature());
			if(featureExist != null) {
				for (Scenario scenario : feature.getScenarios()) {
					Scenario scenarioExist = scenarioDAO.getByName(scenario.getNameScenario());
					if (scenarioExist == null) {
						scenario.setFeature(feature);
						scenarioDAO.save(scenario);
						for (Step step : scenario.getSteps()) {
							ScenarioStep scenarioStep = new ScenarioStep();
							step.addScenario(scenario);
							stepDAO.save(step);
							scenarioStep.setIdScenario(scenario.getIdScenario());
							scenarioStep.setIdStep(step.getIdStep());
							scenarioStepDAO.save(scenarioStep);
							for (Parameter parameter : step.getParameters()) {
								List<Step> stepAux = new ArrayList<Step>();
								for (Step steps : scenario.getSteps()) {
									stepAux.add(steps);
								}
								parameter.setSteps(stepAux);
								parameterDAO.save(parameter);
								StepParameter stepParameter = new StepParameter();
								stepParameter.setIdStep(step.getIdStep());
								stepParameter.setIdParameter(parameter.getIdParameter());
								stepParameterDAO.save(stepParameter);
							}
						}
					}else {
						return 0;
					}
				}
			}
		}catch (Exception e) {
		}
		return status;
	}
	
	public Integer update(Scenario scenario) {
		Integer status = 0;
		try {
			Scenario scenarioExist = scenarioDAO.getById(scenario.getIdScenario());
			if (scenarioExist != null) {
				scenarioExist.setTagScenario(scenario.getTagScenario());
				scenarioExist.setNameScenario(scenario.getNameScenario());
				scenarioExist.setTypeScenario(scenario.getTypeScenario());
				scenarioExist.setStatusScenario(scenario.getStatusScenario());
				scenarioDAO.update(scenarioExist);
				status = 1;
			}
		} catch (Exception e) {
		}return status;
	}
	
	public void deleteFromProject(Project project) {
		for (Feature feature: project.getFeatures()) {
			for (Scenario scenario : feature.getScenarios()) {
				scenarioDAO.delete(scenario);
			}
		}
	}
	
	public void delete(Feature feature) {
		for (Scenario scenario : feature.getScenarios()) {
			scenarioDAO.delete(scenario);
		}
	}

	public Scenario deleteScenario(String name) {
		Scenario scenario = scenarioDAO.getByName(name);
		if(scenario!=null) {
			scenarioStepService.deleteFromScenario(scenario);
			stepParameterService.deleteFromScenario(scenario);
			parameterService.deleteFromScenario(scenario);
			stepService.deleteFromScenario(scenario);
			scenarioDAO.delete(scenario);
			}
		return scenario;
		}
	
}
