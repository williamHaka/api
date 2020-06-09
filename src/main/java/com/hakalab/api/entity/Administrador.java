package com.hakalab.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "administrador")
public class Administrador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAdministrador")
	private Integer idAdministrador;
	
	@Column(name = "correoAdministrador")
	private String correoAdministrador;
	
	@Column(name = "passAdministrador")
	private String passAdministrador;
	
	@Column(name = "phoneUsuario")
	private String phoneUsuario;
	
	@Column(name = "addressUsuario")
	private String addressUsuario;
	
	@Column(name = "nameUsuario")
	private String nameUsuario;
	
	@Column(name = "lastNameUsuario")
	private String lastNameUsuario;
	
	@Column(name = "postalCodeUsuario")
	private String postalCodeUsuario;
	
	@Column(name = "nameEmpresaUsuario")
	private String nameEmpresaUsuario;
	
	
	
}
