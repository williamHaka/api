package com.hakalab.api.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_project")
	private Integer idProject;
	
	@Column(name = "name")
	private String nameProject;
	
	@Column(name = "description")
	private String descriptionProject;
	
	@CreationTimestamp
	@Column(name = "creation_date")
	private Timestamp creationDateProject;
	
	@OneToMany(mappedBy = "project")
	private List<Feature> features;

	@OneToMany(mappedBy = "project")
	private List<Usuario> usuarios;
	
//	@ManyToMany(mappedBy = "projects")
//	private List<Usuario> usuarios;
	
//	@Embedded
//	private BaseEntity baseEntity;
	
	public Integer getIdProject() {
		return idProject;
	}

	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}

	public String getNameProject() {
		return nameProject;
	}

	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public String getDescriptionProject() {
		return descriptionProject;
	}

	public void setDescriptionProject(String descriptionProject) {
		this.descriptionProject = descriptionProject;
	}
	
	public Timestamp getCreationDateProject() {
		return creationDateProject;
	}

	public void setCreationDateProject(Timestamp creationDateProject) {
		this.creationDateProject = creationDateProject;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return "{"
				+ "\r\n \"idProject\": "+idProject+","
				+ "\r\n \"nameProject\": \""+nameProject+"\","
				+ "\r\n \"descriptionProject\": \""+descriptionProject+"\","
				+ "\r\n \"creationDateProject\": \""+creationDateProject+"\","
				+ "\r\n \"usuarios\" :  "+ usuarios + ","
				+ "\r\n \"features\" :  "+ features + "}";
	}

}
