package org.formacio.setmana2.repositori;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.formacio.setmana2.domini.Alumne;
import org.formacio.setmana2.domini.Curs;
import org.formacio.setmana2.domini.Matricula;
import org.springframework.stereotype.Repository;

/**
 * Modifica aquesta classe per tal que sigui un component Spring que realitza
 * les operacions de persistencia tal com indiquen les firmes dels metodes
 */
@Repository
public class RepositoriEscola {

	@PersistenceContext
	private EntityManager em;

	public Curs carregaCurs(String nom) {
		return em.find(Curs.class, nom);
	}

	@Transactional
	public Matricula apunta(String alumne, String curs) throws EdatIncorrecteException {
		Alumne alumnoExistente = em.find(Alumne.class, alumne);
		Curs cursoExistente = this.carregaCurs(curs);
		
		if (alumnoExistente==null || cursoExistente==null) {
			return null;
		}
		
		if (alumnoExistente.getEdat()>=cursoExistente.getEdatMinima()) {
			Matricula nuevaMatricula = new Matricula();
			nuevaMatricula.setAlumne(alumnoExistente);
			nuevaMatricula.setCurs(cursoExistente);
			em.persist(nuevaMatricula);
			return nuevaMatricula;
			
		} else {
			
			throw new EdatIncorrecteException();
		}
		
	}

}
