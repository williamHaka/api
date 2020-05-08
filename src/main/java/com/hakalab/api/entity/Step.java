package com.hakalab.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "step")
public class Step {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_step")
	private Integer idStep;
	
	@Column(name = "name")
	private String nameStep;
	
	@Column(name = "description")
	private String descriptionStep;
	
	
//	@OneToMany(mappedBy="step")
//	private List<ScenarioStep> scenarioSteps;
	 
//	@OneToMany(mappedBy="step")
//	private List<StepParameter> stepParameter;
	
	 @ManyToMany(mappedBy = "steps")
	 private List<Scenario> scenarios;
	 
	 @ManyToMany
	 @JoinTable(
			 name = "step_parameter",
	         joinColumns = {@JoinColumn(name = "id_step")},
	         inverseJoinColumns = {@JoinColumn(name = "id_parameter")}
	 )
	private List<Parameter> parameters;
	
	 public Integer getIdStep() {
		 return idStep;
	 }

	 public void setIdStep(Integer idStep) {
		this.idStep = idStep;
	}

	public String getNameStep() {
		return nameStep;
	}

	public void setNameStep(String nameStep) {
		this.nameStep = nameStep;
	}

	public String getDescriptionStep() {
		return descriptionStep;
	}

	public void setDescriptionStep(String descriptionStep) {
		this.descriptionStep = descriptionStep;
	}
	
//	public List<ScenarioStep> getScenarioSteps() {
//		return scenarioSteps;
//	}
//
//	public void setScenarioSteps(List<ScenarioStep> scenarioSteps) {
//		this.scenarioSteps = scenarioSteps;
//	}
	
	public List<Scenario> getScenarios() {
		return scenarios;
	}

	public void setScenarios(List<Scenario> scenarios) {
		this.scenarios = scenarios;
	}
	
	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
//		List<Parameter> parameters = new ArrayList<Parameter>();
//		for (StepParameter sp : stepParameter) {
//			parameters.add(sp.getParameter());
//		}
		return "{"
				+ "\r\n \"idStep\": \"" + idStep + "\","
				+ "\r\n \"nameStep\": \"" + nameStep + "\","
				+ "\r\n \"descriptionStep\": \"" + descriptionStep +"\","
				+ "\r\n \"parameters\" : "+ parameters+"}";
	}	
	
	public void addScenario(Scenario scenario){
		if(this.scenarios == null)
			this.scenarios = new ArrayList<>();
		this.scenarios.add(scenario);
	}

//	public void addParameter(Parameter parameter){
//	StepParameter stpa = new StepParameter();
//	stpa.setStep(this);
//	stpa.setIdStep(this.getIdStep());
//	stpa.setParameter(parameter);
//	stpa.setIdParameter(parameter.getIdParameter());
//	if(this.stepParameter==null)
//		this.stepParameter = new ArrayList<>();
//	
//	this.stepParameter.add(stpa);
//	parameter.getStepParameter().add(stpa);
//	
//}
}
