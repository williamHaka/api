package com.hakalab.api.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUsuario;
	
	@Column(name = "userNameUsuario")
	private String userNameUsuario;
	
	@Column(name = "passUsuario")
	private String passUsuario;
	
	@Column(name = "phoneUsuario")
	private String phoneUsuario;
	
	@Column(name = "emailUsuario")
	private String emailUsuario;
	
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
	
	@ManyToMany
	@JoinTable(
			name = "usuario_project",
			joinColumns = {@JoinColumn(name = "id_usuario")},
			inverseJoinColumns = {@JoinColumn(name = "id_project")}
			)
	private List<Project> projects;
	
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUserNameUsuario() {
		return userNameUsuario;
	}

	public void setUserNameUsuario(String userNameUsuario) {
		this.userNameUsuario = userNameUsuario;
	}

	public String getPassUsuario() {
		return passUsuario;
	}

	public void setPassUsuario(String passUsuario) {
		this.passUsuario = passUsuario;
	}

	public String getPhoneUsuario() {
		return phoneUsuario;
	}

	public void setPhoneUsuario(String phoneUsuario) {
		this.phoneUsuario = phoneUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getAddressUsuario() {
		return addressUsuario;
	}

	public void setAddressUsuario(String addressUsuario) {
		this.addressUsuario = addressUsuario;
	}

	public String getNameUsuario() {
		return nameUsuario;
	}

	public void setNameUsuario(String nameUsuario) {
		this.nameUsuario = nameUsuario;
	}

	public String getLastNameUsuario() {
		return lastNameUsuario;
	}

	public void setLastNameUsuario(String lastNameUsuario) {
		this.lastNameUsuario = lastNameUsuario;
	}

	public String getPostalCodeUsuario() {
		return postalCodeUsuario;
	}

	public void setPostalCodeUsuario(String postalCodeUsuario) {
		this.postalCodeUsuario = postalCodeUsuario;
	}

	public String getNameEmpresaUsuario() {
		return nameEmpresaUsuario;
	}

	public void setNameEmpresaUsuario(String nameEmpresaUsuario) {
		this.nameEmpresaUsuario = nameEmpresaUsuario;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "{"
				+ "\r\n \"idUsuario\": \"" + idUsuario + "\","
				+ "\r\n \"userNameUsuario\": \"" + userNameUsuario + "\","
				+ "\r\n \"passUsuario\": \"" + passUsuario + "\","
				+ "\r\n \"phoneUsuario\": \"" + phoneUsuario + "\","
				+ "\r\n \"emailUsuario\": \"" + emailUsuario + "\","
				+ "\r\n \"addressUsuario\": \"" + addressUsuario + "\","
				+ "\r\n \"nameUsuario\": \"" + nameUsuario + "\","
				+ "\r\n \"lastNameUsuario\": \"" + lastNameUsuario + "\","
				+ "\r\n \"postalCodeUsuario\": \"" + postalCodeUsuario + "\","
				+ "\r\n \"nameEmpresaUsuario\": \"" + nameEmpresaUsuario+ "\","
				+ "\r\n \"projects\" : " + projects + "}";
	}
	
}
