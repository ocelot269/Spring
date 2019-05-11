package org.formacio.mapping;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

/**
 * La classe Util l'ha de crear i inicialitzar Spring, per aixo l'annotem amb @Component
 * (mes envant veurem una anotacio mes adient)
 */
@Component
public class Util {

	/**
	 * L'EntityManager es l'objecte que ens permet fer les operacions de persistencia.
	 * Hem de declarar la variable i fer que Spring ens la inicialitzi amb @PersistenceContext
	 */
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Aquest metode crea una nova persona a la base de dades. Per aixo, veurem mes envant, necessita
	 * una transaccio activa i ho podem fer annotant el metode amb @Transactional
	 */
	@Transactional
	public void alta (String nom, Sexe sexe) {
		/*
		 * L'objecte que creem i inicialitzem no te res peculiar a que estiguem treballant 
		 * amb base de dades. Es codi Java "normal"
		 * Com a peculiaritat, fitxau-vos que no es necessari fitxar-li l'id, pel fet que al
		 * mapping hem emprat @GeneratedValue
		 */
		Persona nova = new Persona();
		nova.setNom(nom);
		nova.setSexe(sexe);
		
		/*
		 * Per les operacions de persistencia, quasi sempre emprarem l'Entity Manager
		 * En aquest cas emprem el metode persist per guardar un nou objecte a base de dades
		 */
		em.persist(nova);
	}
}
