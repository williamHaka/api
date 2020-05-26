//package com.hakalab.api.entity;
//
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "users")
//public class Usuario{
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id_user")
//	private Integer idUser;
//
//	@Column(name = "name")
//	private String nameUser;
//
//	@Column(name = "password")
//	private String passUser;
//
//	
//	
////	private Integer active;
////	private String roles="";
////	private String permissions;
//	
//	protected Usuario() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	public Integer getIdUser() {
//		return idUser;
//	}
//
//	public void setIdUser(Integer idUser) {
//		this.idUser = idUser;
//	}
//
//	public String getNameUser() {
//		return nameUser;
//	}
//
//	public void setNameUser(String nameUser) {
//		this.nameUser = nameUser;
//	}
//
//	public String getPassUser() {
//		return passUser;
//	}
//
//	public void setPassUser(String passUser) {
//		this.passUser = passUser;
//	}
//
////	public Integer getActive() {
////		return active;
////	}
////
////	public void setActive(Integer active) {
////		this.active = active;
////	}
////
////	public String getRoles() {
////		return roles;
////	}
////
////	public void setRoles(String roles) {
////		this.roles = roles;
////	}
////
////	public String getPermissions() {
////		return permissions;
////	}
////
////	public void setPermissions(String permissions) {
////		this.permissions = permissions;
////	}
////	
////	public List<String> getRolesList(){
////		if(this.roles.length()>0) {
////			return Arrays.asList(this.roles.split(","));
////		}
////		return new ArrayList<String>();
////	}
////	
////	public List<String> getPermissionList(){
////		if(this.permissions.length()>0) {
////			return Arrays.asList(this.permissions.split(","));
////		}
////		return new ArrayList<String>();
////	}
//	
//	@Override
//	public String toString() {
//		return 
//				  "\r\n \"nameUser\": \""+nameUser+"\","
//				+ "\r\n \"passUser\" : "+ passUser;
//	}
//}
