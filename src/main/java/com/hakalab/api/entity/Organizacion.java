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
@Table(name = "organizacion")
public class Organizacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "rut")
	private String rut;
	
	@Column(name = "direccion")
	private String direccion;
	
	@OneToMany(mappedBy = "organizacion")
	private List<Administrador> administradores;

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

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}
	
	@Override
	public String toString() {
		return "{"
				+"\r\n \"idOrganizacion\": \""+id+"\","
				+"\r\n \"nombreOrganizacion\": \""+nombre+"\","
				+"\r\n \"rutOrganizacion\": \""+rut+"\","
				+"\r\n \"direccionOrganizacion\": \""+direccion+"\","
				+"\r\n \"administradores\": \""+administradores+"}";
	}
}
