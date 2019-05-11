package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //Objeto que se puede guardar en la base de datos
public class Persona {

	@Id //Clave primaria
	private Long id;
	
	private String nom;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
}
