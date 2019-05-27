package org.formacio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_PERSONES")
public class Persona {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PER_ID")
	private Long id;
	
	@Column(name="PER_NOM")
	private String nom;
	
	@Column(name="PER_EDAT")
	private int edat;
	
	@ManyToOne
	@JoinColumn(name="PER_MUNID")
	private Municipi municipi;
	
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
	public Municipi getMunicipi() {
		return municipi;
	}
	public void setMunicipi(Municipi municipi) {
		this.municipi = municipi;
	}
	@Override
	public String toString() {
		return "Persona [id=" + id + ", nom=" + nom + ", municipi=" + (municipi != null) + "]";
	}

	
}
