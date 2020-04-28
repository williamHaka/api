package com.hakalab.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "step_parameter")
public class StepParameter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_step_parameter")
	private Integer idStepParameter;
	
	@Column(name = "id_step")
	private Integer idStep;
	
	@Column(name = "id_parameter")
	private Integer idParameter;
	
	@ManyToOne
	  @JoinColumn(name = "id_step",updatable = false, insertable = false,referencedColumnName = "id_step")
	private Step step;
	
	@ManyToOne
	  @JoinColumn(name = "id_parameter",updatable = false, insertable = false,referencedColumnName = "id_parameter")
	private Parameter parameter;

	public Integer getIdStepParameter() {
		return idStepParameter;
	}

	public void setIdStepParameter(Integer idStepParameter) {
		this.idStepParameter = idStepParameter;
	}

	public Integer getIdStep() {
		return idStep;
	}

	public void setIdStep(Integer idStep) {
		this.idStep = idStep;
	}

	public Integer getIdParameter() {
		return idParameter;
	}

	public void setIdParameter(Integer idParameter) {
		this.idParameter = idParameter;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

}
