package com.hakalab.api.entity;

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
@Table(name="Scenario")
public class Scenario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_scenario")
	private Integer id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "tipo")
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name="id_feature", nullable=true)
	private Feature feature;
	
	@OneToMany(mappedBy="scenario")
	private List<Step> steps;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public List<Step> getSteps() {
		return steps;
	}
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	@Override
	public String toString() {
		return "Scenario ["
				+ "id=" + id + ","
				+ "nombre=" + nombre + ","
				+ "tipo=" + tipo + ","
				+ "steps=" + steps + "]";
	}	
	
}
