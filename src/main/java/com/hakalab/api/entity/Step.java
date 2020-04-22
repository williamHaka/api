package com.hakalab.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "step")
public class Step {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_step")
	private Integer id;
	
	@Column(name = "nombre")
	private String nameStep;
	
	@Column(name = "descripcion")
	private String descriptionStep;
	
	@ManyToOne
    @JoinColumns({
    	@JoinColumn(name = "id_scenario",referencedColumnName = "id_scenario")
    })
    private Scenario scenario;
	
	@OneToMany(mappedBy = "step")
    private List<Parameter> parameters;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	@Override
	public String toString() {
		return "{"
				+ "\r\n \"nameStep\": \"" + nameStep + "\","
				+ "\r\n \"descriptionStep\": \"" + descriptionStep +"\","
				+ "\r\n \"parameters\" : "+ parameters+"}";
	}	
	
	public void addParameter(Parameter parameter){
        if(this.parameters == null){
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(parameter);
    }
}
