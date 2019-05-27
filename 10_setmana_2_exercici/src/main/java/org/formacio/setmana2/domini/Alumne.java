package org.formacio.setmana2.domini;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


//Constructor vacio obligatorio

@Entity
@Table(name ="t_alumnes")
public class Alumne {
	
	@Id
	@Column(name = "alu_nom")
	private String nom;
	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}
	
	@Column(name ="alu_edat")
	private int edat;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getEdat() {
		return edat;
	}
	public void setEdat(int edat) {
		this.edat = edat;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Alumne))
			return false;
		Alumne other = (Alumne) obj;
		return Objects.equals(nom, other.nom);
	}
	
}
