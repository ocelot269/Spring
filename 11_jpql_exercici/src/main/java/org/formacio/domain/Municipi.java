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
@Table(name="T_MUNICIPIS")
public class Municipi {
	
    @Id	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MUN_ID")
	private Long id;

    @Column(name="MUN_NOM")
    private String nom;
    
    @ManyToOne
    @JoinColumn(name="MUN_ILLA")
    private Illa illa;
    
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
	public Illa getIlla() {
		return illa;
	}
	public void setIlla(Illa illa) {
		this.illa = illa;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Municipi))
			return false;
		Municipi other = (Municipi) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Municipi [id=" + id + ", nom=" + nom + "]";
	}
	
	

}
