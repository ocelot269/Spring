package org.formacio.api;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

/**
 * Modifica aquesta classe per tal que sigui un component Spring que realitza
 * les operacions de persistencia tal com indiquen les firmes dels metodes
 */

@Component
public class LocalitatOpBasic {

	@PersistenceContext
	private EntityManager em;


	public Localitat carrega(long id) {
		return em.find(Localitat.class, id);
		
	}
	
	@Transactional
	public void alta(String nom, Integer habitants) {
		Localitat localidad = new Localitat();
        localidad.setNom(nom);
        localidad.setHabitants(habitants);
        em.persist(localidad);
	}
	
	@Transactional
	public void elimina(long id) {
		if (carrega(id)!=null) {
			Localitat localidadEliminida = em.find(Localitat.class, id);
			em.remove(localidadEliminida);
		}
		else {
			
		}
	}
	
	@Transactional
	public void modifica(Localitat localitat) {
		Localitat localidadModificada = em.merge(localitat);
	}

}
