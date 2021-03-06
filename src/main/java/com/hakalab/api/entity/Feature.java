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
@Table(name="feature")
public class Feature {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_feature")
	private Integer idFeature;
	
	@Column(name = "name")
	private String nameFeature;

	@Column(name = "description")
	private String descriptionFeature;
	
	@OneToMany(mappedBy = "feature")
	private List<Scenario> scenarios;
	
	@ManyToOne
	@JoinColumn(name = "id_project", referencedColumnName = "id_project")
	private Project project;
	
//	@Embedded
//	private BaseEntity baseEntity;
	
	public Integer getIdFeature() {
		return idFeature;
	}

	public void setIdFeature(Integer idFeature) {
		this.idFeature = idFeature;
	}

	public String getNameFeature() {
		return nameFeature;
	}

	public void setNameFeature(String nameFeature) {
		this.nameFeature = nameFeature;
	}

	public String getDescriptionFeature() {
		return descriptionFeature;
	}

	public void setDescriptionFeature(String descriptionFeature) {
		this.descriptionFeature = descriptionFeature;
	}

	public List<Scenario> getScenarios() {
		return scenarios;
	}

	public void setScenarios(List<Scenario> scenarios) {
		this.scenarios = scenarios;
	}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "{"
				+ "\r\n \"idFeature\": \"" + idFeature+ "\","
				+ "\r\n \"nameFeature\": \"" + nameFeature + "\","
				+ "\r\n \"descriptionFeature\": \"" + descriptionFeature + "\","
				+ "\r\n \"scenarios\" : "+ scenarios + "}";
	}
	
	public void addScenario(Scenario scenarios){
        if(this.scenarios == null){
            this.scenarios = new ArrayList<>();
        }
        this.scenarios.add(scenarios);
    }
}
