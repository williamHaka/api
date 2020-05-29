package com.hakalab.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.FeatureDAO;
import com.hakalab.api.dao.ParameterDAO;
import com.hakalab.api.dao.ProjectDAO;
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
public class FeatureService{
	
	@Autowired
	private ProjectDAO projectDAO;
	@Autowired
	private FeatureDAO featureDAO;
	@Autowired
	private ScenarioDAO scenarioDAO;
	@Autowired
	private ScenarioStepDAO scenarioStepDAO;
	@Autowired
	private StepParameterDAO stepParameterDAO;
	@Autowired
	private StepDAO stepDAO;
	@Autowired
	private ParameterDAO parameterDAO;
	
	@Autowired
	private ScenarioStepService scenarioStepService;
	@Autowired
	private StepParameterService stepParameterService;
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private StepService stepService;
	@Autowired
	private ScenarioService scenarioService;

	public List<Feature> getAll() {
		List<Feature> features = featureDAO.getAll();
		return features;
	}

	public Feature getByName(String name) {
		Feature feature = featureDAO.getByName(name);
		return feature;
	}
	
	public Integer saveFeature(Feature feature) {
		Integer status = 0;
		try {
			Feature featureExist = featureDAO.getByName(feature.getNameFeature());
			if(featureExist == null) {
				featureDAO.save(feature);
				for (Scenario scenario : feature.getScenarios()) {
					scenario.setFeature(feature);
					scenarioDAO.save(scenario);
					for (Step step : scenario.getSteps()) {
						ScenarioStep scenarioStep = new ScenarioStep();
						step.setScenarios(feature.getScenarios());
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
				}
				status = 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
		}
		return status;
	} 
	
	public Integer update(Feature feature) {
		Integer status = 0;
		try {
			Feature featureExist = featureDAO.getById(feature.getIdFeature());
			if (featureExist != null) {
				featureExist.setNameFeature(feature.getNameFeature());
				featureExist.setDescriptionFeature(feature.getDescriptionFeature());
				featureDAO.update(featureExist);
				status = 1;
			}
		} catch (Exception e) {
		}return status;
	}
	
	public Feature deleteFeature(String name) {
		Feature feature = featureDAO.getByName(name);
		if(feature!=null) {
			scenarioStepService.delete(feature);
			stepParameterService.delete(feature);
			parameterService.delete(feature);
			stepService.delete(feature);
			scenarioService.delete(feature);
			featureDAO.delete(feature);
		}
		
		return feature;
	}

	public void deleteFromProject(Project project) {
		for (Feature feature: project.getFeatures()) {
			featureDAO.delete(feature);
		}
	}
	
}
