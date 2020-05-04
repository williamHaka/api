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
import com.hakalab.api.entity.Scenario;
import com.hakalab.api.entity.ScenarioStep;
import com.hakalab.api.entity.Step;
import com.hakalab.api.entity.StepParameter;

@Service
public class FeatureService{
	
	private FeatureDAO featureDAO;
	private ScenarioDAO scenarioDAO;
	private ScenarioStepDAO scenarioStepDAO;
	private StepParameterDAO stepParameterDAO;
	private StepDAO stepDAO;
	private ParameterDAO parameterDAO;
	
	private ScenarioStepService scenarioStepService;
	private StepParameterService stepParameterService;
	private ParameterService parameterService;
	private StepService stepService;
	private ScenarioService scenarioService;

	public List<Feature> getAll() {
		List<Feature> features = featureDAO.getAll();
		return features;
	}

	public Feature getByName(String name) {
		Feature feature = featureDAO.getByName(name) ;
		return feature;
	}


	public Integer save(Feature feature) {
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
	
//	public Integer update(Feature feature) {
//		Integer status = 0;
//		try {
//			featureDAO.update(feature);
//			for (Scenario scenario : feature.getScenarios()) {
//				scenario.setFeature(feature);
//				scenarioDAO.update(scenario);
//				for (Step step : scenario.getSteps()) {
//					step.setScenarios(scenario);
//					stepDAO.update(step);
//					for (Parameter parameter : step.getParameters()) {
//						parameter.setStep(step);
//						parameterDAO.update(parameter);
//					}
//				}
//			}
//			status = 1;
//		} catch (Exception e) {
//		}
//		return status;
//	}
	
	
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
	
	@Autowired
	public void setFeatureDAO(FeatureDAO featureDAO) {
		this.featureDAO = featureDAO;
	}
	@Autowired
	public void setScenarioDAO(ScenarioDAO scenarioDAO) {
		this.scenarioDAO = scenarioDAO;
	}
	@Autowired
	public void setStepDAO(StepDAO stepDAO) {
		this.stepDAO = stepDAO;
	}
	@Autowired
	public void setParameterDAO(ParameterDAO parameterDAO) {
		this.parameterDAO = parameterDAO;
	}
	@Autowired
	public void setScenarioStepDAO(ScenarioStepDAO scenarioStepDAO) {
		this.scenarioStepDAO = scenarioStepDAO;
	}
	@Autowired
	public void setStepParameterDAO(StepParameterDAO stepParameterDAO) {
		this.stepParameterDAO = stepParameterDAO;
	}
	@Autowired
	public void setScenarioStepService(ScenarioStepService scenarioStepService) {
		this.scenarioStepService = scenarioStepService;
	}
	@Autowired
	public void setStepParameterService(StepParameterService stepParameterService) {
		this.stepParameterService = stepParameterService;
	}
	@Autowired
	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}
	@Autowired
	public void setStepService(StepService stepService) {
		this.stepService = stepService;
	}
	@Autowired
	public void setScenarioService(ScenarioService scenarioService) {
		this.scenarioService = scenarioService;
	}
	
}
