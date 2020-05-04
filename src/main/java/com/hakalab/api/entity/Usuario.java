package com.hakalab.api.entity;

import java.io.Serializable;

//import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Usuario implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Integer idUser;

	@Column(name = "name")
	private String nameUser;

	@Column(name = "password")
	private String passUser;

	public final Integer getIdUser() {
		return idUser;
	}

	public final void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public final String getNameUser() {
		return nameUser;
	}

	public final void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public final String getPassUser() {
		return passUser;
	}

	public final void setPassUser(String passUser) {
		this.passUser = passUser;
	}

	@Override
	public String toString() {
		return 
				  "\r\n \"nameUser\": \""+nameUser+"\","
				+ "\r\n \"passUser\" : "+ passUser;
	}
}
