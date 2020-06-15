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
@Table(name = "organizacion")
public class Organizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idOrganizacion;
	
	@Column(name = "nombre")
	private String nombreOrganizacion;
	
	@Column(name = "rut")
	private String rutOrganizacion;
	
	@Column(name = "direccion")
	private String direccionOrganizacion;
	
	@OneToMany(mappedBy = "organizacion")
	private List<Administrador> administradores;
	
	@ManyToOne
	@JoinColumn(name = "suscripcion", referencedColumnName = "id")
	private Suscripcion suscripcion;
	
	@Column(name = "estado_suscripcion")
	private Boolean estadoSuscripcion;
	
	public Integer getIdOrganizacion() {
		return idOrganizacion;
	}

	public void setIdOrganizacion(Integer idOrganizacion) {
		this.idOrganizacion = idOrganizacion;
	}

	public String getNombreOrganizacion() {
		return nombreOrganizacion;
	}

	public void setNombreOrganizacion(String nombreOrganizacion) {
		this.nombreOrganizacion = nombreOrganizacion;
	}

	public String getRutOrganizacion() {
		return rutOrganizacion;
	}

	public void setRutOrganizacion(String rutOrganizacion) {
		this.rutOrganizacion = rutOrganizacion;
	}

	public String getDireccionOrganizacion() {
		return direccionOrganizacion;
	}

	public void setDireccionOrganizacion(String direccionOrganizacion) {
		this.direccionOrganizacion = direccionOrganizacion;
	}

	public List<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}

	public Suscripcion getSuscripcion() {
		return suscripcion;
	}

	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}

	public Boolean getEstadoSuscripcion() {
		return estadoSuscripcion;
	}

	public void setEstadoSuscripcion(Boolean estadoSuscripcion) {
		this.estadoSuscripcion = estadoSuscripcion;
	}

	@Override
	public String toString() {
		return "{"
				+"\r\n \"idOrganizacion\": "+idOrganizacion+","
				+"\r\n \"nombreOrganizacion\": \""+nombreOrganizacion+"\","
				+"\r\n \"rutOrganizacion\": \""+rutOrganizacion+"\","
				+"\r\n \"direccionOrganizacion\": \""+direccionOrganizacion+"\","
				+"\r\n \"estadoSuscripcion\": "+estadoSuscripcion+","
				+"\r\n \"suscripcion\": "+suscripcion+","
				+"\r\n \"administradores\": "+administradores+"}";
	}
}
