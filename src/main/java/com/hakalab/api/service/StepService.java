package com.hakalab.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class StepService {

	private StepDAO stepDAO;
	private ScenarioStepDAO scenariostepDAO;
	private StepParameterDAO stepParameterDAO;
	private ParameterDAO parameterDAO;
	private ScenarioDAO scenarioDAO;
	
	public List<Step> getAll() {
		List<Step> step = stepDAO.getAll();
		return step;
	}

	public Step getById(Integer id) {
		Step step = null;
		try {
			step = stepDAO.getById(id);
		} catch (Exception e) {
		}
		return step;
	}
	
	public List<Step> getByName(String name) {
		List<Step> steps = null;
		try {
			steps = stepDAO.getByName(name);
		} catch (Exception e) {
		}
		return steps;
	}
	
	public Integer save(Scenario scenario) {
		Integer status = 0;
		try {
			Scenario scenarioExist = scenarioDAO.getById(scenario.getIdScenario());
			if (scenarioExist != null) {
				for (Step step : scenario.getSteps()) {
					Step stepExist = stepDAO.getByNameAndDescription(step.getNameStep(),step.getDescriptionStep());
					if(stepExist == null) {
						stepDAO.save(step);
						Step step2 = stepDAO.getByNameAndDescription(step.getNameStep(), step.getDescriptionStep());
						ScenarioStep scenarioStep = new ScenarioStep();
						scenarioStep.setIdScenario(scenario.getIdScenario());
						scenarioStep.setIdStep(step2.getIdStep());
						scenariostepDAO.save(scenarioStep);
						status = 1;
					}else {
						return 0;
					}
				}
			}
		} catch (Exception e) {
		}
		return status;
	}
	
	public Step deleteAll(Integer idStep) {
		Step step = stepDAO.getById(idStep);
		if (step != null) {
			List<ScenarioStep> scenariosSteps = scenariostepDAO.getByIdStep(step);
			if (scenariosSteps != null) {
				for (ScenarioStep scenarioStep : scenariosSteps) {
					scenariostepDAO.delete(scenarioStep);
				}
			}
			List<StepParameter> stepParameters = stepParameterDAO.getByIdStep(step);
			if (stepParameters != null) {
				for (StepParameter stepParameter : stepParameters) {
					Integer idParameter = stepParameter.getIdParameter();
					stepParameterDAO.delete(stepParameter);
					Parameter parameter = parameterDAO.getById(idParameter);
					parameterDAO.delete(parameter);
				}
			}
			stepDAO.delete(step);
		}
		return step;
	}
	
	public void delete(Feature feature) {
		for (Scenario scenario : feature.getScenarios()) {
			for (Step step : scenario.getSteps()) {
				stepDAO.delete(step);
			}
		}
	}
	
	public Integer update(Step step) {
		Integer status = 0;
		try {
			Step stepExist = stepDAO.getById(step.getIdStep());
			if (stepExist != null) {
				stepExist.setNameStep(step.getNameStep());
				stepExist.setDescriptionStep(step.getDescriptionStep());
				stepDAO.update(stepExist);
				status = 1;
			}
		} catch (Exception e) {
		}
		return status;
	}

	@Autowired
	public final void setStepDAO(StepDAO stepDAO) {
		this.stepDAO = stepDAO;
	}

	@Autowired
	public final void setScenarioStepDAO(ScenarioStepDAO scenariostepDAO) {
		this.scenariostepDAO = scenariostepDAO;
	}

	@Autowired
	public final void setStepParameterDAO(StepParameterDAO stepParameterDAO) {
		this.stepParameterDAO = stepParameterDAO;
	}

	@Autowired
	public final void setParameterDAO(ParameterDAO parameterDAO) {
		this.parameterDAO = parameterDAO;
	}

	@Autowired
	public final void setScenarioDAO(ScenarioDAO scenarioDAO) {
		this.scenarioDAO = scenarioDAO;
	}
}
