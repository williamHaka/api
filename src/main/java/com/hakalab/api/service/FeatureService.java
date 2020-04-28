package com.hakalab.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.FeatureDAO;
import com.hakalab.api.dao.ParameterDAO;
import com.hakalab.api.dao.ScenarioDAO;
import com.hakalab.api.dao.StepDAO;
import com.hakalab.api.entity.Feature;
import com.hakalab.api.entity.Parameter;
import com.hakalab.api.entity.Scenario;
import com.hakalab.api.entity.Step;

@Service
public class FeatureService{
	
	private FeatureDAO featureDAO;
	private ScenarioDAO scenarioDAO;
	private StepDAO stepDAO;
	private ParameterDAO parameterDAO;

	public List<Feature> getAll() {
		List<Feature> features = featureDAO.getAll();
		return features;
	}

	public Feature getByName(String name) {
		Feature feature = featureDAO.getByName(name) ;
		return feature;
	}


//	public Integer save(Feature feature) {
//		Integer status = 0;
//		try {
//			featureDAO.save(feature);
//			for (Scenario scenario : feature.getScenarios()) {
//				scenario.setFeature(feature);
//				scenarioDAO.save(scenario);
//				for (Step step : scenario.getSteps()) {
//					step.set
//					stepDAO.save(step);
//					for (Parameter parameter : step.getParameters()) {
//						parameter.setStep(step);
//						parameterDAO.save(parameter);
//					}
//				}
//			}
//			status = 1;
//		} catch (Exception e) {
//		}
//		return status;
//	} 
	
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
			List<Scenario> scenarios = scenarioDAO.getByIdFeature(feature.getId());
			List<Step> steps = new ArrayList<Step>();
			List<Parameter> parameters = new ArrayList<Parameter>();
			
			for (Scenario scenario : scenarios) {
				steps.addAll(stepDAO.getByIdScenario(scenario.getIdScenario()));
			}
			
			for (Step step: steps) {
				parameters.addAll(parameterDAO.getByIdStep(step.getIdStep()));
			}
			
			for (Parameter parameter : parameters) {
				parameterDAO.delete(parameter);
			}
			
			for (Step step : steps) {
				stepDAO.delete(step);
			}
			
			for (Scenario scenario : scenarios) {
				scenarioDAO.delete(scenario);
			}
			
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
	
	
	
}
