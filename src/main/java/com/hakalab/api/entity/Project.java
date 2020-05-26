package com.hakalab.api.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_project")
	private Integer idProject;
	
	@Column(name = "name")
	private String nameProject;
	
	@Column(name = "description")
	private String descriptionProject;
	
	@OneToMany(mappedBy = "project")
	private List<Feature> features;

//	@Embedded
//	private BaseEntity baseEntity;
	
//	@ManyToMany(mappedBy = "projects")
//	private List<Usuario> usuarios;
	
	
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
	
//	public BaseEntity getBaseEntity() {
//		return baseEntity;
//	}
//
//	public void setBaseEntity(BaseEntity baseEntity) {
//		this.baseEntity = baseEntity;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return 
				  "\r\n \"idProject\": "+idProject+","
				+ "\r\n \"nameProject\": \""+nameProject+"\","
				+ "\r\n \"descriptionProject\": \""+descriptionProject+"\","
				+ "\r\n \"features\" : "+ features;
	}
	
}
