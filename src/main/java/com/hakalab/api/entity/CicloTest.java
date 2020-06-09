package com.hakalab.api.entity;

import java.util.Calendar;
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
	private Calendar executionDate;
	
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

	public Calendar getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Calendar executionDate) {
		this.executionDate = executionDate;
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
				+ "\r\n \"descriptionCiclo\" : "+ descriptionCiclo + "}";
	}
}
