package com.hakalab.api.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "usuario_project")
public class UsuarioProject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario_project")
	private Integer idUsuarioProject;
	
	@Column(name = "id_usuario")
	private Integer idUsuario;
	
	@Column(name = "id_project")
	private Integer idProject;

	@CreationTimestamp
	@Column(name = "assignment_date")
	private Timestamp assignmentDate;
	
	public Integer getIdUsuarioProject() {
		return idUsuarioProject;
	}

	public void setIdUsuarioProject(Integer idUsuarioProject) {
		this.idUsuarioProject = idUsuarioProject;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdProject() {
		return idProject;
	}

	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}

	public Timestamp getAssignmentDate() {
		return assignmentDate;
	}

	public void setAssignmentDate(Timestamp assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	
}
