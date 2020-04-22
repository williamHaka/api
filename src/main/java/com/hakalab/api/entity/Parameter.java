package com.hakalab.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parameter")
public class Parameter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_parameter")
	private Integer id;
	
	@Column(name = "nombre")
	private String nameParameter;
	
	@Column(name = "valor")
	private String valueParameter;
	
	@ManyToOne
    @JoinColumns({
    	@JoinColumn(name = "id_step",referencedColumnName = "id_step")
    })
	private Step step;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
	
	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	@Override
	public String toString() {
		return "{"
				+ "\r\n \"nameParameter\": \"" + nameParameter + "\","
				+ "\r\n \"valueParameter\": \"" + valueParameter+"\"}";
	}
}
