package org.formacio.component;

import java.util.Collection;

public interface RepositoriAlumnes {

	String obteAlumne(int id);
	
	void altaAlumne(int id, String nom);
	
	Collection<String> llistaAlumnes();
}
