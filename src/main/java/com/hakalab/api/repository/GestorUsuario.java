package com.hakalab.api.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hakalab.api.entity.Usuario;


@Repository("GestorUsuario")
public interface GestorUsuario extends JpaRepository<Usuario, Serializable>{

	public abstract Usuario findByNameUser(String usuario);
}
