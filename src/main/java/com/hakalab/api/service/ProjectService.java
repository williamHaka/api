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
public class ProjectService {

	@Autowired
	private ProjectDAO projectDAO;
	@Autowired
	private FeatureDAO featureDAO;
	@Autowired
	private ScenarioDAO scenarioDAO;
	@Autowired
	private StepDAO stepDAO;
	@Autowired
	private ScenarioStepDAO scenarioStepDAO;
	@Autowired
	private ParameterDAO parameterDAO;
	@Autowired
	private StepParameterDAO stepParameterDAO;
	
	@Autowired
	private StepParameterService stepParameterService;
	@Autowired
	private ScenarioStepService scenarioStepService;
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private StepService stepService;
	@Autowired
	private ScenarioService scenarioService;
	@Autowired
	private FeatureService featureService;
	
	public List<Project> getAll() {
		List<Project> projects = projectDAO.getAll();
		return projects;
	}
	
	public Project getByName(String name) {
		Project project = projectDAO.getByName(name);
		return project;
	}
	
	public Project getById(Integer Id) {
		Project project = projectDAO.getById(Id);
		return project;
	}
	
	public Integer saveProject(Project project) {
		Integer status = 0;
		try {
			Project projectExist = projectDAO.getById(project.getIdProject());
			if (projectExist != null) {
				projectDAO.save(project);
				for (Feature feature : project.getFeatures()) {
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
					}
				}
			}
		} catch (Exception e) {
		}
		return status;
	}
	
	public Integer update(Project project) {
		Integer status = 0;
		try {
			Project projectExist = projectDAO.getById(project.getIdProject());
			if (projectExist != null) {
				projectExist.setNameProject(project.getNameProject());
				projectExist.setDescriptionProject(project.getDescriptionProject());
				projectDAO.update(projectExist);
				status = 1;
			}
		} catch (Exception e) {
		}return status;
	}
	
	public Project deleteProject(String name) {
		Project project = projectDAO.getByName(name);
		if(project!=null) {
			scenarioStepService.deleteFromProject(project);
			stepParameterService.deleteFromProject(project);
			parameterService.deleteFromProject(project);
			stepService.deleteFromProject(project);
			scenarioService.deleteFromProject(project);
			featureService.deleteFromProject(project);
			projectDAO.delete(project);
			}
		return project;
		}
}
