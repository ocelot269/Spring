package org.formacio.repositori;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.formacio.domain.Illa;
import org.formacio.domain.Municipi;
import org.formacio.domain.Persona;
import org.springframework.stereotype.Repository;

@Repository
public class InformesCens {
	
	@PersistenceContext
	EntityManager em;

	/**
	 * Retorna la llista de persones que viuen al municipi indicat
	 */
	public List<Persona> habitantsMunicipi(String municipi) {
		TypedQuery<Persona> personasMunicipis = em.createQuery("select persona from Persona persona where persona.municipi.nom = :municipio ", Persona.class);
        personasMunicipis.setParameter("municipio",municipi);
		
        return personasMunicipis.getResultList();
	}

	/**
	 * Retorna el nombre d'habitants de la illa
	 */
	public int nombreHabitantsIlla(String illa) {
		TypedQuery<Persona> habitantes = em.createQuery("select persona from Persona persona where persona.municipi.illa.nom = :illa ", Persona.class);
		habitantes.setParameter("illa", illa);
		List<Persona> resultat = habitantes.getResultList();
		return resultat.size();
	}	
	/**
	 * Retorna el nombre d'habitants del municipi que son menors d'edat
	 */
	public int nombreHabitantsMenorsEdat(String municipi) {
		TypedQuery<Persona> habitantes = em.createQuery("select persona from Persona persona where persona.municipi.nom = :municipio and persona.edat < 18 ", Persona.class);
		habitantes.setParameter("municipio", municipi);
		List<Persona> resultat = habitantes.getResultList();
		return resultat.size();
	}

	/**
	 * Retorna la llista de persones que no tenen informat de quin municipi son
	 */
	
	public List<Persona> llistaPersonesSenseMunicipi() {
		TypedQuery<Persona> habitantes = em.createQuery("select persona from Persona persona where persona.municipi.id = null", Persona.class);
		List<Persona> resultat = habitantes.getResultList();
		return resultat;
    }

	/**
	 * Retorna la llista de noms de persones d'una illa ordenats per l'edat
	 * siii, ja ho se ..., no hem vist com ordenar, pero emprau order by i la vostra intuicio ;-)
	 */
	public List<String> llistaNomsPersonesOrdenatPerEdat(String illa) {
		TypedQuery<String> habitantes = em.createQuery("select persona.nom from Persona persona where persona.municipi.illa.nom = :illa group by persona.nom order by persona.edat  ", String.class);
		habitantes.setParameter("illa", illa);
		List<String> resultat = habitantes.getResultList();
		return resultat;
    }
	
}
