package com.hakalab.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hakalab.api.dao.ParameterDAO;
import com.hakalab.api.dao.StepDAO;
import com.hakalab.api.dao.StepParameterDAO;
import com.hakalab.api.entity.Feature;
import com.hakalab.api.entity.Parameter;
import com.hakalab.api.entity.Project;
import com.hakalab.api.entity.Scenario;
import com.hakalab.api.entity.Step;
import com.hakalab.api.entity.StepParameter;

@Service
public class ParameterService {

	@Autowired
	private ParameterDAO parameterDAO;
	@Autowired
	private StepParameterDAO stepParameterDAO;
	@Autowired
	private StepDAO stepDAO;

	public List<Parameter> getAll() {
		List<Parameter> parameter = parameterDAO.getAll();
		return parameter;
	}

	public Parameter getById(Integer id) {
		Parameter parameter = null;
		try {
			parameter = parameterDAO.getById(id);
		} catch (Exception e) {
		}
		return parameter;
	}

	public List<Parameter> getByName(String name) {
		List<Parameter> parameters = null;
		try {
			parameters = parameterDAO.getByName(name);
		} catch (Exception e) {
		}
		return parameters;
	}

	public String save(Step step) {
		String status = "";
		try {
			if (step.getIdStep() != null) {
				if (step.getIdStep() != 0) {
					Step stepExist = stepDAO.getById(step.getIdStep());
					if (stepExist != null) {
						for (Parameter parameter : step.getParameters()) {
							if (parameter.getNameParameter() != null) {
								if (!parameter.getNameParameter().equals("")) {
									if (parameter.getValueParameter() != null) {
										if (!parameter.getValueParameter().equals("")) {
											Parameter parameterExist = parameterDAO.getByNameAndValue(parameter.getNameParameter(), parameter.getValueParameter());
											if (parameterExist == null) {
												parameterDAO.save(parameter);
												Parameter parameter2 = parameterDAO.getByNameAndValue(parameter.getNameParameter(), parameter.getValueParameter());
												StepParameter stepParameter = new StepParameter();
												stepParameter.setIdStep(step.getIdStep());
												stepParameter.setIdParameter(parameter2.getIdParameter());
												stepParameterDAO.save(stepParameter);
												status = "successful";
											}else{
												return "parameterExist";
											}
										} else {
											return "valueParameter";
										}
									} else {
										return "valueParameter";
									}
								} else {
									return "nameParameter";
								}
							} else {
								return "nameParameter";
							}
						}
					} else {
						return "idStepNoExist";
					}
				} else {
					return "idStep";
				}
			} else {
				return "idStep";
			}

		} catch (Exception e) {
			
		}
		return status;
	}

	public Integer update(Parameter parameter) {
		Integer status = 0;
		try {
			Parameter parameterExist = parameterDAO.getById(parameter.getIdParameter());
			if (parameterExist != null) {
				parameterExist.setNameParameter(parameter.getNameParameter());
				parameterExist.setValueParameter(parameter.getValueParameter());
				parameterDAO.update(parameterExist);
				status = 1;
			}
		} catch (Exception e) {
		}
		return status;
	}

	public Parameter deleteAll(Parameter parameter) {
		Parameter parameterExist = parameterDAO.getById(parameter.getIdParameter());
		if (parameterExist != null) {
			List<StepParameter> stepParameters = stepParameterDAO.getByIdStep(parameterExist.getIdParameter());
			if (stepParameters != null) {
				for (StepParameter stepParameter : stepParameters) {
					stepParameterDAO.delete(stepParameter);
				}
			}
			parameterDAO.delete(parameterExist);
		}
		return parameterExist;
	}

	public void deleteFromProject(Project project) {
		for (Feature feature : project.getFeatures()) {
			for (Scenario scenario : feature.getScenarios()) {
				for (Step step : scenario.getSteps()) {
					for (Parameter parameter : step.getParameters()) {
						parameterDAO.delete(parameter);
						}
					}
				}
			}
		}
	
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
