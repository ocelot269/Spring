package org.formacio.mvc;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JAXB (l'eina que emprarà Spring MVC per obtenir representació en XML), requereix que 
 * els objecte que volguem que siguin arrels de l'XML generat (com és en aquesta cas Persona)
 * estiguin annotats amb @XmlRootElement.
 */
@XmlRootElement
public class Persona {

	private String nom;
	
	// JSonProperty ens permet controlar, entre altres coses, el nom de la propietat JSON
	@JsonProperty("localitat")
	private String municipi;
	
	// El tractament de dates sempre és més complexa que la resta
	// Amb aquesta annotació indiquem que volem un format de dia-mes-any
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	private Date dataNaixament;
	
	// La propietat password, per tenir JsonIgnore, no formarà part de la reposta
	@JsonIgnore
	private String password;
	
	public Persona() {
	}

	public Persona(String nom, String municipi, Date dataNaixament, String password) {
		this.nom = nom;
		this.municipi = municipi;
		this.dataNaixament = dataNaixament;
		this.password = password;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@XmlElement(name="localitat")
	public String getMunicipi() {
		return municipi;
	}
	public void setMunicipi(String municipi) {
		this.municipi = municipi;
	}

	public Date getDataNaixament() {
		return dataNaixament;
	}

	public void setDataNaixament(Date dataNaixament) {
		this.dataNaixament = dataNaixament;
	}

	@XmlTransient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
