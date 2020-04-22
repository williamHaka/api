package com.hakalab.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.FeatureDAO;
import com.hakalab.api.entity.Feature;
import com.hakalab.api.entity.Parameter;
import com.hakalab.api.entity.Scenario;
import com.hakalab.api.entity.Step;

@Service
public class FeatureService{
	
	@Autowired	
	private FeatureDAO featureDAO;
//	private StepDAO stepDAO;
//	private ParameterDAO parameterDAO;

	public List<Feature> findAll() {
		List<Feature> features = featureDAO.findAll();
		return features;
	}

	public Feature finByName(String name) {
		Feature feature = featureDAO.finByName(name) ;
		return feature;
	}

//	public void createOrUpdate(Feature feature) {
//		featureDAO.createOrUpdate(feature);
//		
//	}
//	
//	public void save(Feature feature) {
//		featureDAO.save(feature);
//		
//	}
//	
//	public void update(Feature feature) {
//		featureDAO.update(feature);
//		
//	}
//	
//	public void deleteByName(String name) {
//		featureDAO.deleteByName(name);
//	}
	

	public Feature saveOrUpdateFeature(Feature feature) {
		Feature featureExist = featureDAO.finByName(feature.getNameFeature());
		if(featureExist==null) {
			saveFeature(feature);
		}
		return feature;
	} 
	
	public void saveFeature(Feature feature) {
		featureDAO.save(feature);
		for (Scenario scenario : feature.getScenarios()) {
			scenario.setFeature(feature);
			featureDAO.save(scenario);
			for (Step step : scenario.getSteps()) {
				step.setScenario(scenario);
				featureDAO.save(step);
				for (Parameter parameter : step.getParameters()) {
					parameter.setStep(step);
					featureDAO.save(parameter);
				}
			}
		}
	}
	
//	public void updateFeature(Feature featureExist, Feature feature) {
//		feature.setId(featureExist.getId());
//		featureDAO.save(feature);
//		for (Scenario scenario : feature.getScenarios()) {
//			scena
//			scenario.setFeature(feature);
//			featureDAO.save(scenario);
//			for (Step step : scenario.getSteps()) {
//				step.setScenario(scenario);
//				featureDAO.save(step);
//				for (Parameter parameter : step.getParameters()) {
//					parameter.setStep(step);
//					featureDAO.save(parameter);
//				}
//			}
//		}
//	}
	
	public Feature deleteFeature(String name) {
		Feature feature = featureDAO.finByName(name);
		if(feature!=null) {
			List<Scenario> scenarios = featureDAO.getScenarioByIdFeature(feature.getId());
			List<Step> steps = new ArrayList<Step>();
			List<Parameter> parameters = new ArrayList<Parameter>();
			
			for (Scenario scenario : scenarios) {
				steps.addAll(featureDAO.getStepByIdScenario(scenario.getId()));
			}
			
			for (Step step: steps) {
				parameters.addAll(featureDAO.getParameterByIdStep(step.getId()));
			}
			
			for (Parameter parameter : parameters) {
				featureDAO.delete(parameter);
			}
			
			for (Step step : steps) {
				featureDAO.delete(step);
			}
			
			for (Scenario scenario : scenarios) {
				featureDAO.delete(scenario);
			}
			
			featureDAO.delete(feature);
		}
		
		return feature;
	}
	
	public void existFeature(Feature feature) {
		Feature featureExist = featureDAO.finByName(feature.getNameFeature());
		feature.setId(featureExist.getId());
		for (Scenario scenario : feature.getScenarios()) {
			scenario.setFeature(feature);
			featureDAO.save(scenario);
			for (Step step : scenario.getSteps()) {
				step.setScenario(scenario);
				featureDAO.save(step);
				for (Parameter parameter : step.getParameters()) {
					parameter.setStep(step);
					featureDAO.save(parameter);
				}
			}
		}
	}

}
