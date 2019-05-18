package org.formacio.setmana2.domini;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_cursos")
public class Curs {
	@Id
	@Column(name = "cur_nom")
	private String nom;
    @Column(name = "cur_edatminima")
	private int edatMinima;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getEdatMinima() {
		return edatMinima;
	}
	public void setEdatMinima(int edatMinima) {
		this.edatMinima = edatMinima;
	}
	
	
}
