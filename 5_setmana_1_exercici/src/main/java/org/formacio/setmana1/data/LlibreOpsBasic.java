package org.formacio.setmana1.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.formacio.setmana1.domini.Llibre;
import org.formacio.setmana1.domini.Recomanacio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Modifica aquesta classe per tal que sigui un component Spring que realitza
 * les operacions de persistencia tal com indiquen les firmes dels metodes
 */
@Repository
public class LlibreOpsBasic {

	// variables
	Llibre libro = null;
	boolean LibroBorrado = false;

	@PersistenceContext
	private EntityManager em;

	/**
	 * Retorna el llibre amb l'ISBN indicat o, si no existeix, llança un
	 * LlibreNoExisteixException
	 */

	// getters and setters

	public Llibre getLibro() {
		return libro;
	}

	public void setLibro(Llibre libro) {
		this.libro = libro;
	}

	public boolean getLibroBorrado() {
		return LibroBorrado;
	}

	public void setLibroBorrado(boolean libroBorrado) {
		LibroBorrado = libroBorrado;
	}

	public Llibre carrega(String isbn) throws LlibreNoExisteixException {

		if (em.find(Llibre.class, isbn) != null) {
			setLibro(em.find(Llibre.class, isbn));
		}

		else {
			throw new LlibreNoExisteixException();
		}

		return getLibro();

	}

	/**
	 * Sense sorpreses: dona d'alta un nou llibre amb les propietats especificaques
	 */
	@Transactional
	public void alta(String isbn, String autor, Integer pagines, Recomanacio recomanacio, String titol) {
		libro.setIsbn(isbn);
		libro.setAutor(autor);
		libro.setPagines(pagines);
		libro.setRecomanacio(recomanacio);
		libro.setTitol(titol);
		em.persist(libro);
	}

	/**
	 * Elimina, si existeix, un llibre de la base de dades
	 * 
	 * @param isbn del llibre a eliminar
	 * @return true si s'ha esborrat el llibre, false si no existia
	 */

	@Transactional
	public boolean elimina(String isbn) {
		if (em.find(Llibre.class, isbn) != null) {
			setLibro(em.find(Llibre.class, isbn));
			em.remove(getLibro());
			setLibroBorrado(true);
		}
		return getLibroBorrado();
	}

	/**
	 * Guarda a bbdd l'estat del llibre indicat
	 */
	@Transactional
	public void modifica(Llibre llibre) {
		em.merge(llibre);

	}

	/**
	 * Retorna true o false en funcio de si existeix un llibre amb aquest ISBN
	 * (Aquest metode no llanca excepcions!)
	 */
	public boolean existeix(String isbn) {
		if (em.find(Llibre.class, isbn)!=null) {
			return true;
		}else  {
			return false;
		}
	}

	/**
	 * Retorna quina es la recomanacio per el llibre indicat Si el llibre indicat no
	 * existeix, retorna null
	 */
	public Recomanacio recomenacioPer(String isbn) {
		setLibro(em.find(Llibre.class, isbn));
		if (em.find(Llibre.class, isbn)!=null) {
			return getLibro().getRecomanacio();
		}else  {
			return null;
		}
	}

}
