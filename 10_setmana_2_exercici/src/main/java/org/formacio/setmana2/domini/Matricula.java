package org.formacio.setmana2.domini;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_matricules")
public class Matricula {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mat_id")
	private Long id; 
	
	@JoinColumn(name = "mat_alumne")
	@OneToOne
	private Alumne alumne;
	
	@JoinColumn(name = "mat_curs")
	@OneToOne
	private Curs curs;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Alumne getAlumne() {
		return alumne;
	}
	public void setAlumne(Alumne alumne) {
		this.alumne = alumne;
	}
	public Curs getCurs() {
		return curs;
	}
	public void setCurs(Curs curs) {
		this.curs = curs;
	}
	
	
}
