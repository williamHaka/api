package com.hakalab.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.StepParameterDAO;
import com.hakalab.api.entity.Feature;
import com.hakalab.api.entity.Parameter;
import com.hakalab.api.entity.Scenario;
import com.hakalab.api.entity.Step;
import com.hakalab.api.entity.StepParameter;

@Service
public class StepParameterService {
	
	@Autowired	
	private StepParameterDAO stepParameterDAO;
	
	public void save(StepParameter stepParameter) {
		stepParameterDAO.save(stepParameter);
	}
	
	public void delete(Feature feature) {
		List<StepParameter> stepParameters = new ArrayList<>();
		for (Scenario scenario : feature.getScenarios()) {
			for (Step step: scenario.getSteps()) {
				for (Parameter parameter : step.getParameters()) {
					StepParameter stepParameter = new StepParameter();
					stepParameter.setIdStep(step.getIdStep());
					stepParameter.setIdParameter(parameter.getIdParameter());
					stepParameters.addAll(stepParameterDAO.getByIdStepAndIdParameter(stepParameter));
				}
			}
		}
		
		for (StepParameter stepParameter : stepParameters) {
			stepParameterDAO.delete(stepParameter);
		}
	}
	
	
}
