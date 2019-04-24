package org.formacio.component;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServeiAlumnat {

	@Autowired
	private RepositoriAlumnesMemoria repositorio;
	
	public int numeroAlumnos=0;



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
	 * @return 
	 */
	
	public RepositoriAlumnesMemoria getRepositorio() {
		return repositorio;
	}
	
	public int getNumeroAlumnos() {
		return numeroAlumnos;
	}


	public void setNumeroAlumnos(int numeroAlumnos) {
		this.numeroAlumnos = numeroAlumnos;
	}
	
	
	public boolean matricula (int id, String alumne) {
		if (alumne == null) {
			return false;
		}
		
		getRepositorio().altaAlumne(id, alumne);
		return true;
	}
	
public void inicialitzarRepositoriAlumnes() {
		
		String[] alumnes = { "Antonia", "Joan" };
		final byte POSICIO_INICIAL_REPOSITORI = 1;

		short posicio = POSICIO_INICIAL_REPOSITORI;
		for (String alumne : alumnes) {
			repositorio.altaAlumne((int) posicio, alumne);
			posicio += 1;
		}
	}
	
	public int nombreAlumnesRepositori() {
		return repositorio.llistaAlumnes().size();
	}
	
}
