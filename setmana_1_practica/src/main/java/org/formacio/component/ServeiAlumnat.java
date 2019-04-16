package org.formacio.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServeiAlumnat {

	@Autowired
	private RepositoriAlumnesMemoria repositorio;
	
	
	
	public RepositoriAlumnesMemoria getRepositorio() {
		return repositorio;
	}

	@PostConstruct
	public void initializer() {
		getRepositorio().altaAlumne(1, "Antonia");
		getRepositorio().altaAlumne(2, "Joan");
	}
	
	/**
	 * ha de donar d'alta a la base de dades d'alumnes l'alumne indicat amb 
	 * el corresponent codi.
	 * Si el nom de l'alumne es null, no l'ha de donar d'alta
	 * Retorna true si l'alumne s'ha inserit, false si no.
	 */
	public boolean matricula (int id, String alumne) {
		if (alumne == null) {
			return false;
		}
		
		getRepositorio().altaAlumne(id, alumne);
		return true;
	}
}
