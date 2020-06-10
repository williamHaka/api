package com.hakalab.api.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "cicloTest")
public class CicloTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ciclo")
	private Integer idCiclo;

	@Column(name = "name")
	private String nameCiclo;
	
	@Column(name = "description")
	private String descriptionCiclo;
	
	@CreationTimestamp
	@Column(name = "execution_date")
	private Timestamp executionDateCiclo;
	
	@ManyToMany(mappedBy = "ciclos")
	private List<Scenario> scenarios;
	
	public Integer getIdCiclo() {
		return idCiclo;
	}

	public void setIdCiclo(Integer idCiclo) {
		this.idCiclo = idCiclo;
	}

	public String getNameCiclo() {
		return nameCiclo;
	}

	public void setNameCiclo(String nameCiclo) {
		this.nameCiclo = nameCiclo;
	}

	public String getDescriptionCiclo() {
		return descriptionCiclo;
	}

	public void setDescriptionCiclo(String descriptionCiclo) {
		this.descriptionCiclo = descriptionCiclo;
	}

//	public Calendar getExecutionDateCiclo() {
//		return executionDateCiclo;
//	}
//
//	public void setExecutionDateCiclo(Calendar executionDateCiclo) {
//		this.executionDateCiclo = executionDateCiclo;
//	}

	public Timestamp getExecutionDateCiclo() {
		return executionDateCiclo;
	}

	public void setExecutionDateCiclo(Timestamp executionDateCiclo) {
		this.executionDateCiclo = executionDateCiclo;
	}

	public List<Scenario> getScenarios() {
		return scenarios;
	}

	public void setScenarios(List<Scenario> scenarios) {
		this.scenarios = scenarios;
	}
	
	@Override
	public String toString() {
		return "{"
				+ "\r\n \"idCiclo\": \"" + idCiclo+ "\","
				+ "\r\n \"nameCiclo\": \"" + nameCiclo+ "\","
				+ "\r\n \"descriptionCiclo\": \"" + descriptionCiclo+ "\","
				+ "\r\n \"executionDateCiclo\" : "+ executionDateCiclo + "}";
	}
}
