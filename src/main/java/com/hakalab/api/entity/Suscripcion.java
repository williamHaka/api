package com.hakalab.api.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "suscripcion")
public class Suscripcion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idSuscripcion;
	
	@Column(name = "nombre")
	private String nombreSuscripcion;
	
	@Column(name = "costo")
	private Integer costoSuscripcion;
	
	@OneToMany(mappedBy = "suscripcion")
	private List<Organizacion> organizaciones;
	
	public Integer getIdSuscripcion() {
		return idSuscripcion;
	}

	public void setIdSuscripcion(Integer idSuscripcion) {
		this.idSuscripcion = idSuscripcion;
	}

	public String getNombreSuscripcion() {
		return nombreSuscripcion;
	}

	public void setNombreSuscripcion(String nombreSuscripcion) {
		this.nombreSuscripcion = nombreSuscripcion;
	}

	public Integer getCostoSuscripcion() {
		return costoSuscripcion;
	}

	public void setCostoSuscripcion(Integer costoSuscripcion) {
		this.costoSuscripcion = costoSuscripcion;
	}

	public List<Organizacion> getOrganizaciones() {
		return organizaciones;
	}

	public void setOrganizaciones(List<Organizacion> organizaciones) {
		this.organizaciones = organizaciones;
	}

	@Override
	public String toString() {
		return"{"
				+"\r\n \"idSuscripcion\": "+idSuscripcion+","
				+"\r\n \"nombreSuscripcion\": \""+nombreSuscripcion+"\","
				+"\r\n \"costoSuscripcion\": \""+costoSuscripcion+"\""
				+"\n}";
	}
	
}