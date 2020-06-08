package com.hakalab.api.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "parameter")
public class Parameter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_parameter")
	private Integer idParameter;
	
	@Column(name = "name")
	private String nameParameter;
	
	@Column(name = "values_parameter")
	private String valueParameter;
	
	@ManyToMany(mappedBy = "parameters")
	private List<Step> steps;
	 
	public String getNameParameter() {
		return nameParameter;
	}

	public void setNameParameter(String nameParameter) {
		this.nameParameter = nameParameter;
	}

	public String getValueParameter() {
		return valueParameter;
	}

	public void setValueParameter(String valueParameter) {
		this.valueParameter = valueParameter;
	}
	
	public Integer getIdParameter() {
		return idParameter;
	}

	public void setIdParameter(Integer idParameter) {
		this.idParameter = idParameter;
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
				+ "\r\n \"idParameter\": \"" + idParameter + "\","
				+ "\r\n \"nameParameter\": \"" + nameParameter + "\","
				+ "\r\n \"valueParameter\": \"" + valueParameter+"\"}";
	}

}
