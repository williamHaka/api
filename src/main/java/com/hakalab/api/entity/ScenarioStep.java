package com.hakalab.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "scenario_step")
public class ScenarioStep {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_scenario_step")
	private Integer idScenarioStep;
	
	@Column(name = "id_scenario")
	private Integer idScenario;
	
	@Column(name = "id_step")
	private Integer idStep;
	
	@ManyToOne
	@JoinColumn(name = "id_scenario",updatable = false, insertable = false,referencedColumnName = "id_scenario")
	private Scenario scenario;

	@ManyToOne
	@JoinColumn(name = "id_step",updatable = false, insertable = false,referencedColumnName = "id_step")
	private Step step;
	
	public Integer getIdScenarioStep() {
		return idScenarioStep;
	}

	public void setIdScenarioStep(Integer idScenarioStep) {
		this.idScenarioStep = idScenarioStep;
	}

	public Integer getIdScenario() {
		return idScenario;
	}

	public void setIdScenario(Integer idScenario) {
		this.idScenario = idScenario;
	}

	public Integer getIdStep() {
		return idStep;
	}

	public void setIdStep(Integer idStep) {
		this.idStep = idStep;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}
	
}
