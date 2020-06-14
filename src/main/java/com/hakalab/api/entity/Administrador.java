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
@Table(name = "administrador")
public class Administrador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idAdministrador;
	
	@Column(name = "correo")
	private String correoAdministrador;
	
	@Column(name = "password")
	private String passwordAdministrador;
	
	@Column(name = "nombre")
	private String nombreAdministrador;
	
	@Column(name = "apellido")
	private String apellidoAdministrador;
	
	@ManyToOne
	@JoinColumn(name = "organizacion", referencedColumnName = "id")
	private Organizacion organizacion;

	public Integer getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(Integer idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public String getNombreAdministrador() {
		return nombreAdministrador;
	}

	public void setNombreAdministrador(String nombreAdministrador) {
		this.nombreAdministrador = nombreAdministrador;
	}

	public String getApellidoAdministrador() {
		return apellidoAdministrador;
	}

	public void setApellidoAdministrador(String apellidoAdministrador) {
		this.apellidoAdministrador = apellidoAdministrador;
	}

	public String getCorreoAdministrador() {
		return correoAdministrador;
	}

	public void setCorreoAdministrador(String correoAdministrador) {
		this.correoAdministrador = correoAdministrador;
	}

	public String getPasswordAdministrador() {
		return passwordAdministrador;
	}

	public void setPasswordAdministrador(String passwordAdministrador) {
		this.passwordAdministrador = passwordAdministrador;
	}

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	@Override
	public String toString() {
		return "{"
				+"\r\n \"idAdministrador\": "+idAdministrador+","
				+"\r\n \"correoAdministrador\": \""+correoAdministrador+"\","
				+"\r\n \"passwordAdministrador\": \""+passwordAdministrador+"\","
				+"\r\n \"nombreAdministrador\": \""+nombreAdministrador+"\","
				+"\r\n \"apellidoAdministrador\": \""+apellidoAdministrador+"\""
				+"\n}";
				
	}
}