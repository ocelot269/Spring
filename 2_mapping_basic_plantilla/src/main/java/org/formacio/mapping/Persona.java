package org.formacio.mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * La classe Persona es una entitat: un tipus a on els seus objectes es guarden a la base de dades
 * Aixo ve donat per l'us de @Entity
 * 
 * @Table ens permet customitzar el nom de la taula a on es guardaran els objectes d'aquest tipus
 */
@Entity
@Table(name="T_PERSONES")
public class Persona {

	/**
	 * Es obligatori que les entitats indiquin quina es la propietat que sera la clau primaria
	 * Aixo ho feim amb @Id
	 * @GeneratedValue ens permet delegar a la base de dades la generacio del valor per la clau primaria
	 * @Column ens permet customitzar el nom que tindra la columna a la base de dades
	 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PER_ID")
	private Long id;
	
	@Column(name="PER_NOM")
	private String nom;
	
	/**
	 * @Transient ens serveix per indicar que una propietat de la nostra entitat no te correspondencia
	 * a la base de dades
	 */
	@Transient
	private String sessionId;
	
	/**
	 * Per fer que un enum guardi a la base de dades el nom de la instancia (DONA) i no l'ordinal (0)
	 * hem d'emprar @Enumerated(EnumType.STRING)
	 */
	@Column(name="PER_SEXE")
	@Enumerated(EnumType.STRING)
	private Sexe sexe;
	
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
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Sexe getSexe() {
		return sexe;
	}
	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}
	
}
