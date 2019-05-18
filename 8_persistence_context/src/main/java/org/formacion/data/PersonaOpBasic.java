package org.formacion.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
@Transactional
public class PersonaOpBasic {
	
	@PersistenceContext
	private EntityManager em;
	
	public void modificacio_i_merge() {
		System.out.println("modificacio_i_merge");
		Persona persona = em.find(Persona.class, 1L);
		persona.setNom("modificat");
		em.merge(persona);
	}
	
	public void modificacio_sense_merge() {
		System.out.println("modificacio_sense_merge");
		Persona persona = em.find(Persona.class, 1L);
		persona.setNom("modificat");
	}
	
	public void merge_sense_modificacio() {
		System.out.println("merge_sense_modificacio");
		Persona persona = em.find(Persona.class, 1L);
		em.merge(persona);
    }
	

}
