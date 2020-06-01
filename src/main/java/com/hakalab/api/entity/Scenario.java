package com.hakalab.api.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="scenario")
public class Scenario{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_scenario")
	private Integer idScenario;
	
	@Column(name = "tag")
	private String tagScenario;
	
	@Column(name = "name")
	private String nameScenario;
	
	@Column(name = "type")
	private String typeScenario;
	
//	@Column(name = "status")
//	private String statusScenario;
	
	@ManyToOne
	@JoinColumn(name = "id_feature",referencedColumnName = "id_feature")
	private Feature feature;
	
	@ManyToMany
    @JoinTable(
            name = "scenario_step",
            joinColumns = {@JoinColumn(name = "id_scenario")},
            inverseJoinColumns = {@JoinColumn(name = "id_step")}
    )
	
	private List<Step> steps;

	public String getTagScenario() {
		return tagScenario;
	}

	public void setTagScenario(String tagScenario) {
		this.tagScenario = tagScenario;
	}

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

//	public String getStatusScenario() {
//		return statusScenario;
//	}
//
//	public void setStatusScenario(String statusScenario) {
//		this.statusScenario = statusScenario;
//	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	@Override
	public String toString() {
		return "{"
				+ "\r\n \"idScenario\": \""+idScenario+"\","
				+ "\r\n \"tagScenario\": \""+tagScenario+"\","
				+ "\r\n \"nameScenario\": \"" + nameScenario + "\","
				+ "\r\n \"typeScenario\": \"" + typeScenario+ "\","
//				+ "\r\n \"statusScenario\": \""+statusScenario+"\","
				+ "\r\n \"steps\" : "+ steps + "}";
	}
	
}
