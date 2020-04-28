package com.hakalab.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="scenario")
public class Scenario{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_scenario")
	private Integer idScenario;
	
	@Column(name = "name")
	private String nameScenario;
	
	@Column(name = "type")
	private String typeScenario;
	
	@ManyToOne
	@JoinColumn(name = "id_feature",referencedColumnName = "id_feature")
	private Feature feature;
	
	
	@OneToMany(mappedBy="scenario")
	private List<ScenarioStep> scenarioSteps;

	private List<Step> steps;

	public Integer getIdScenario() {
		return idScenario;
	}

	public void setIdScenario(Integer idScenario) {
		this.idScenario = idScenario;
	}

	public String getNameScenario() {
		return nameScenario;
	}

	public void setNameScenario(String nameScenario) {
		this.nameScenario = nameScenario;
	}

	public String getTypeScenario() {
		return typeScenario;
	}

	public void setTypeScenario(String typeScenario) {
		this.typeScenario = typeScenario;
	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public List<ScenarioStep> getScenarioSteps() {
		return scenarioSteps;
	}

	public void setScenarioSteps(List<ScenarioStep> scenarioSteps) {
		this.scenarioSteps = scenarioSteps;
	}
	
	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	@Override
	public String toString() {
		List<Step> steps = new ArrayList<Step>();
		for (ScenarioStep scenarioStep : scenarioSteps) {
			steps.add(scenarioStep.getStep());
		}
		return	"{"
				+ "\r\n \"idScenario\": \""+idScenario+"\","
				+ "\r\n \"nameScenario\": \"" + nameScenario + "\","
				+ "\r\n \"typeScenario\": \"" + typeScenario+ "\","
				+ "\r\n \"steps\" : "+ steps
				+ "\r\n }";
	}
	
	public void addSteps(Step step){
		ScenarioStep scst = new ScenarioStep();
		scst.setStep(step);
		scst.setIdStep(step.getIdStep());
		scst.setScenario(this);
		scst.setIdScenario(this.getIdScenario());
		if(this.scenarioSteps==null)
			this.scenarioSteps = new ArrayList<>();
		
		this.scenarioSteps.add(scst);
		step.getScenarioSteps().add(scst);
		
    }
}
