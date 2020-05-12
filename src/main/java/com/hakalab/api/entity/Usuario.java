package com.hakalab.api.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_user")
	private Integer idUser;
	
	@Column(name = "name_user")
	private String nameUser;

	@Column(name = "pass_user")
	private String passUser;
	
	@Column(name="rol_user")
	private byte rolUser;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	public void setIdUser(String idUser) {
		this.idUser = Integer.parseInt(idUser);
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

	public final byte getRolUser() {
		return rolUser;
	}

	public final void setRolUser(byte rolUser) {
		this.rolUser = rolUser;
	}

	@Override
	public String toString() {
		return 
				  "\r\n \"idUser\": "+idUser+","
				+ "\r\n \"nameUser\": \""+nameUser+"\","
				+ "\r\n \"passUser\" : "+ passUser;
	}
}
